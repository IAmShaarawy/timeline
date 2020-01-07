package dev.elshaarawy.timeline.extensions

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.media.MediaMetadataRetriever
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

/**
 * @author Mohamed Elshaarawy on Dec 24, 2019.
 */
@BindingAdapter(value = ["isGone"])
fun View.isGone(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun <T : ViewDataBinding> ViewGroup.inflate(@LayoutRes layoutId: Int): T {
    val inflater = LayoutInflater.from(context)
    return DataBindingUtil.inflate<T>(
        inflater,
        layoutId,
        this,
        false
    )
}

@BindingAdapter("loadBitmap")
fun ImageView.loadBitmap(bitmap: Bitmap?) {
    setImageBitmap(bitmap)
}

/**
 * Load local and remote images with glide
 *
 * If you provided an imgDrawable and imgURL the adapter will render the localImage then load the remote one
 *
 * If you didn't provide nether imgDrawable nor imgURL nothing will happen
 *
 * @receiver [ImageView] that we want to load in
 * @param drawable optional local [Drawable] that we want to render
 * @param url optional [String] that represent remote image url
 * @param error optional error [Drawable] just in case loading images error
 * @param placeholder optional placeholder [Drawable] to show it if loading imgDrawable or imgURL take much time
 * @param isRounded optional [Boolean] to indicate that image is loaded is rounded or not
 * @author Mohamed Elshaarawy*/
@BindingAdapter(
    value = ["imgDrawable",
        "imgURL",
        "imgError",
        "imgPlaceholder",
        "isRounded"],
    requireAll = false
)
fun ImageView.setImage(
    drawable: Drawable?,
    url: String?,
    error: Drawable?,
    placeholder: Drawable?,
    isRounded: Boolean = false
) {
    val requestOptions = formRequestOptions(
        error = error,
        placeholder = placeholder,
        isRounded = isRounded
    )
    drawable?.also {
        loadLocal(it, requestOptions)
    } ?: loadRemote(url, requestOptions)
}

private fun formRequestOptions(
    error: Drawable?,
    placeholder: Drawable?,
    isRounded: Boolean
): RequestOptions = RequestOptions().apply {
    skipMemoryCache(true)
    diskCacheStrategy(DiskCacheStrategy.RESOURCE)
    if (isRounded) circleCrop()
    error(error)
    placeholder(placeholder)
}

private fun ImageView.loadLocal(drawable: Drawable, requestOptions: RequestOptions) {
    Glide.with(this)
        .load(drawable)
        .apply(requestOptions)
        .into(this)
}

private fun ImageView.loadRemote(url: String?, requestOptions: RequestOptions) {
    Glide.with(this)
        .load(url)
        .thumbnail(0.1f)
        .apply(requestOptions)
        .into(this)
}