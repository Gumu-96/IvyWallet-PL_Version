package com.ivy.core.domain.algorithm.calc

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.ivy.core.persistence.algorithm.calc.CalcTrn
import com.ivy.data.transaction.TransactionType
import org.junit.jupiter.api.Test
import java.time.Instant

internal class RawStatsTest {
    @Test
    fun givenListOfTransactions_whenMapping_thenReturnCorrectRawStats() {
        val transactions = listOf(
            CalcTrn(
                amount = 15.0,
                currency = "USD",
                type = TransactionType.Income,
                time = Instant.MIN
            ),
            CalcTrn(
                amount = 10.0,
                currency = "USD",
                type = TransactionType.Expense,
                time = Instant.MIN
            ),
            CalcTrn(
                amount = 7.0,
                currency = "USD",
                type = TransactionType.Income,
                time = Instant.MIN
            ),
            CalcTrn(
                amount = 23.0,
                currency = "EUR",
                type = TransactionType.Expense,
                time = Instant.MIN
            ),
            CalcTrn(
                amount = 90.0,
                currency = "EUR",
                type = TransactionType.Expense,
                time = Instant.MIN
            ),
        )

        val rawStats = rawStats(transactions)

        assertThat(rawStats.expensesCount).isEqualTo(3)
        assertThat(rawStats.incomesCount).isEqualTo(2)
        assertThat(rawStats.expenses["EUR"]).isEqualTo(113.0)
        assertThat(rawStats.incomes["EUR"]).isEqualTo(null)
    }
}
