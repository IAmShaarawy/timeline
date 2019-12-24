package dev.elshaarawy.timeline.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author Mohamed Elshaarawy on Dec 24, 2019.
 */
interface UserRepository {
    suspend fun isLoggedIn(): Boolean

    companion object : () -> UserRepository {
        override fun invoke(): UserRepository = UserRepositoryImpl()

    }
}

private class UserRepositoryImpl : UserRepository {
    override suspend fun isLoggedIn(): Boolean = withContext(Dispatchers.IO) {
        true
    }
}