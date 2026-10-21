package br.edu.ifal.casajunta.data.home

data class HomeSummary(
    val memberNames: List<String>,
    val balanceCents: Long,
    val tasksDone: Int,
    val tasksTotal: Int,
    val billsCount: Int,
    val billsTotalCents: Long,
    val billsDaysUntilDue: Int,
    val pantryMissingCount: Int,
)
