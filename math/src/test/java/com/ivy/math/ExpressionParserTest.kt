package com.ivy.math

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.ivy.parser.Parser
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class ExpressionParserTest {
    private lateinit var parser: Parser<TreeNode>

    @BeforeEach
    fun setup() {
        parser = expressionParser()
    }

    @ParameterizedTest
    @CsvSource(
        "3+6/3-(-10), 15.0",
        "5+6, 11.0",
        "5.000000, 5.0",
        "100/(10*10), 1.0",
        "9*(3+6), 81.0"
    )
    fun givenValidExpression_whenEvaluating_thenGetCorrectResult(
        expression: String,
        expected: Double
    ) {
        val result = parser(expression).first()

        val actual = result.value.eval()

        assertThat(actual).isEqualTo(expected)
    }
}
