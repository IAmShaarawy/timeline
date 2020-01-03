package dev.elshaarawy.timeline.data.entities

import com.google.firebase.firestore.Exclude

/**
 * @author Mohamed Elshaarawy on Jan 03, 2020.
 */
open class IdWrapper {
    @Exclude
    var id: String = ""
        set(value) {
            if (id.isBlank()) {
                field = value
            } else {
                throw IllegalStateException("id was set before")
            }
        }

    inline fun <reified T : IdWrapper> withId(id: String):  T{
        this.id = id
        return this as T
    }
}