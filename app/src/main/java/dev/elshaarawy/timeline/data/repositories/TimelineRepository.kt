package dev.elshaarawy.timeline.data.repositories

/**
 * @author Mohamed Elshaarawy on Jan 03, 2020.
 */
interface TimelineRepository {

    suspend fun loadInitial()
    suspend fun loadAfter()
    suspend fun loadBefore()
}