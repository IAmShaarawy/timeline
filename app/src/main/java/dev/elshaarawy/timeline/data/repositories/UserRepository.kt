package dev.elshaarawy.timeline.data.repositories

import android.app.Activity
import android.net.Uri
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import dev.elshaarawy.timeline.data.entities.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * @author Mohamed Elshaarawy on Dec 24, 2019.
 */
interface UserRepository {
    suspend fun logOut()
    suspend fun isLoggedIn(): Boolean
    suspend fun currentUser(): UserEntity?
    suspend fun updateName(name: String)
    suspend fun updatePhoto(url: String)

    companion object : () -> UserRepository {
        override fun invoke(): UserRepository = UserRepositoryImpl()

    }
}

private class UserRepositoryImpl : UserRepository {
    private val fireAuth by lazy { FirebaseAuth.getInstance() }
    override suspend fun isLoggedIn(): Boolean = withContext(Dispatchers.IO) {
        fireAuth.currentUser != null
    }

    override suspend fun logOut() = withContext(Dispatchers.IO) {
        fireAuth.signOut()
    }

    override suspend fun currentUser(): UserEntity? = withContext(Dispatchers.IO) {
        fireAuth.currentUser?.toUserEntity()
    }

    override suspend fun updateName(name: String) = withContext(Dispatchers.IO) {
        updateProfile(
            UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build()
        )
    }

    override suspend fun updatePhoto(url: String) = withContext(Dispatchers.IO) {
        updateProfile(
            UserProfileChangeRequest.Builder()
                .setPhotoUri(Uri.parse(url))
                .build()
        )
    }

    private suspend fun updateProfile(req: UserProfileChangeRequest) =
        suspendCoroutine<Unit> { cont ->
            fireAuth.currentUser!!
                .updateProfile(req)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        cont.resume(Unit)
                    } else {
                        cont.resumeWithException(it.exception!!)
                    }
                }
        }

    private fun FirebaseUser.toUserEntity() =
        UserEntity(uid, displayName, phoneNumber!!, photoUrl.toString())
}