package dev.elshaarawy.timeline.data.entities

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.PropertyName
import com.google.firebase.firestore.model.value.ReferenceValue

/**
 * @author Mohamed Elshaarawy on Jan 03, 2020.
 */
data class Post(
    @PropertyName("img")
    val img: String? = null,
    @PropertyName("text")
    val text: String? = null,
    @PropertyName("video")
    val video: String? = null,
    @PropertyName("time_stamp")
    val timeStamp: Timestamp? = null,
    @PropertyName("uuid")
    val uuid: DocumentReference? = null
) : IdWrapper()