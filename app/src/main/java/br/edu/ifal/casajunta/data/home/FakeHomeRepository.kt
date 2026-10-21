package br.edu.ifal.casajunta.data.home

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeHomeRepository : HomeRepository {
    override fun observeSummary(): Flow<HomeSummary> = flowOf(
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
}
