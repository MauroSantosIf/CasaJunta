package br.edu.ifal.casajunta.ui.screens.home

import br.edu.ifal.casajunta.data.home.HomeSummary
import br.edu.ifal.casajunta.util.MoneyFormatter

data class MemberUi(
    val name: String,
)

sealed interface HomeUiState {
    data object Loading : HomeUiState

    data class Success(
        val members: List<MemberUi>,
        val balanceText: String,
        val tasksProgress: Float,
        val tasksDone: Int,
        val tasksTotal: Int,
        val billsCount: Int,
        val billsTotalText: String,
        val billsDaysUntilDue: Int,
        val pantryMissingCount: Int,
    ) : HomeUiState
}

internal fun toUiState(summary: HomeSummary): HomeUiState.Success {
    val tasksProgress = if (summary.tasksTotal == 0) {
        0f
    } else {
        summary.tasksDone.toFloat() / summary.tasksTotal.toFloat()
    }
    return HomeUiState.Success(
        members = summary.memberNames.map(::MemberUi),
        balanceText = MoneyFormatter.format(summary.balanceCents),
        tasksProgress = tasksProgress,
        tasksDone = summary.tasksDone,
        tasksTotal = summary.tasksTotal,
        billsCount = summary.billsCount,
        billsTotalText = MoneyFormatter.format(summary.billsTotalCents),
        billsDaysUntilDue = summary.billsDaysUntilDue,
        pantryMissingCount = summary.pantryMissingCount,
    )
}
