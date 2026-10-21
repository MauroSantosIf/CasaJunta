package br.edu.ifal.casajunta.data.home

import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun observeSummary(): Flow<HomeSummary>
}
