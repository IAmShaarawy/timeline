package dev.elshaarawy.timeline.data.entities

/**
 * @author Mohamed Elshaarawy on Dec 23, 2019.
 */
enum class Language(val id: Int) {
    AR(0), EN(1);

    companion object {
        fun from(id: Int) = values().first { it.id == id }
    }
}