package dev.elshaarawy.timeline.data.repositories

import dev.elshaarawy.timeline.data.entities.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author Mohamed Elshaarawy on Dec 24, 2019.
 */
interface UserRepository {
    suspend fun isLoggedIn(): Boolean
    suspend fun currentUser(): UserEntity?

    companion object : () -> UserRepository {
        override fun invoke(): UserRepository = UserRepositoryImpl()

    }
}

private class UserRepositoryImpl : UserRepository {
    override suspend fun isLoggedIn(): Boolean = withContext(Dispatchers.IO) {
        true
    }

    override suspend fun currentUser(): UserEntity? = withContext(Dispatchers.IO) {
        UserEntity(678987, null, "+201086782090", null)
    }
}