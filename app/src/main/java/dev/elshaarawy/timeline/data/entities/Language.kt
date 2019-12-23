package dev.elshaarawy.timeline.data.entities

/**
 * @author Mohamed Elshaarawy on Dec 23, 2019.
 */
enum class Language(val id: Int, val code: String) {
    AR(0, "ar"), EN(1, "en");

    companion object {
        fun from(id: Int) = values().first { it.id == id }
    }
}