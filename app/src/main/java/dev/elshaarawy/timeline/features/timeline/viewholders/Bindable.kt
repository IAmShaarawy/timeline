package dev.elshaarawy.timeline.features.timeline.viewholders

/**
 * @author Mohamed Elshaarawy on Jan 03, 2020.
 */
interface Bindable<T> {
    fun bind(viewModel: T)

    fun unBind() {}
}