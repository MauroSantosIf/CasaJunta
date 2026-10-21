package br.edu.ifal.casajunta.ui.screens.home

import br.edu.ifal.casajunta.data.home.HomeSummary
import br.edu.ifal.casajunta.util.MoneyFormatter
import org.junit.Assert.assertEquals
import org.junit.Test

class HomeUiStateMapperTest {

    @Test
    fun mapsProgressAndCurrency() {
        val state = toUiState(
            HomeSummary(
                memberNames = listOf("Lívia", "Thiago", "Mauro", "Viviam"),
                balanceCents = 47230L,
                tasksDone = 8,
                tasksTotal = 12,
                billsCount = 2,
                billsTotalCents = 32050L,
                billsDaysUntilDue = 5,
                pantryMissingCount = 3,
            ),
        )

        assertEquals(8f / 12f, state.tasksProgress, 0f)
        assertEquals(8, state.tasksDone)
        assertEquals(12, state.tasksTotal)
        assertEquals("R$\u00A0472,30", state.balanceText)
        assertEquals("R$\u00A0320,50", state.billsTotalText)
        assertEquals(MoneyFormatter.format(47230L), state.balanceText)
        assertEquals(
            listOf("Lívia", "Thiago", "Mauro", "Viviam"),
            state.members.map(MemberUi::name),
        )
    }

    @Test
    fun zeroTasksTotal_progressIsZero() {
        val state = toUiState(summary(tasksDone = 0, tasksTotal = 0))

        assertEquals(0f, state.tasksProgress, 0f)
        assertEquals(0, state.tasksDone)
        assertEquals(0, state.tasksTotal)
    }

    @Test
    fun zeroBillsAndPantry_countsStayZero() {
        val state = toUiState(
            summary(
                billsCount = 0,
                billsTotalCents = 0L,
                billsDaysUntilDue = 0,
                pantryMissingCount = 0,
            ),
        )

        assertEquals(0, state.billsCount)
        assertEquals(0, state.billsDaysUntilDue)
        assertEquals(0, state.pantryMissingCount)
        assertEquals(MoneyFormatter.format(0L), state.billsTotalText)
        assertEquals("R$\u00A00,00", state.billsTotalText)
    }

    private fun summary(
        tasksDone: Int = 8,
        tasksTotal: Int = 12,
        billsCount: Int = 2,
        billsTotalCents: Long = 32050L,
        billsDaysUntilDue: Int = 5,
        pantryMissingCount: Int = 3,
    ) = HomeSummary(
        memberNames = emptyList(),
        balanceCents = 47230L,
        tasksDone = tasksDone,
        tasksTotal = tasksTotal,
        billsCount = billsCount,
        billsTotalCents = billsTotalCents,
        billsDaysUntilDue = billsDaysUntilDue,
        pantryMissingCount = pantryMissingCount,
    )
}
