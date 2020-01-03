package dev.elshaarawy.timeline.extensions

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * @author Mohamed Elshaarawy on Jan 03, 2020.
 */

suspend fun CollectionReference.getAsync(source: Source = Source.DEFAULT): QuerySnapshot =
    get(source).async()

suspend fun DocumentReference.getAsync(source: Source = Source.DEFAULT): DocumentSnapshot =
    get(source).async()

suspend fun Query.getAsync(source: Source = Source.DEFAULT): QuerySnapshot =
    get(source).async()

suspend fun <T> Task<T>.async(): T = withContext(Dispatchers.IO) {
    suspendCoroutine<T> { continuation ->
        addOnCompleteListener {
            if (it.isSuccessful) {
                continuation.resume(it.result!!)
            } else {
                continuation.resumeWithException(it.exception!!)
            }
        }
    }
}