package dev.elshaarawy.timeline.features.timeline.viewholders

import android.view.View
import androidx.annotation.CallSuper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView
import java.io.Closeable

/**
 * @author Mohamed Elshaarawy on Jan 07, 2020.
 */
abstract class ViewHolder<VM : Closeable>(view: View) : RecyclerView.ViewHolder(view),
    LifecycleOwner {
    private var viewModel: VM? = null
    private val lifecycleRegistry by lazy {
        LifecycleRegistry(this).apply {
            handleLifecycleEvent(Lifecycle.Event.ON_START)
        }
    }

    override fun getLifecycle(): Lifecycle = lifecycleRegistry

    @CallSuper
    open fun bind(viewModel: VM) {
        this.viewModel = viewModel
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    @CallSuper
    protected open fun unBind(viewModel: VM) {
    }

    fun unBind() {
        viewModel?.let {
            unBind(it)
            it.close()
        }
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        viewModel = null
    }
}