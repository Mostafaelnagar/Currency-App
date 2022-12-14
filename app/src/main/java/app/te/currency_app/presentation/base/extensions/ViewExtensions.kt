package app.te.currency_app.presentation.base.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.text.Html
import android.util.Log
import android.view.View
import android.webkit.URLUtil
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.te.currency_app.R
import coil.ImageLoader
import coil.load
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar
import java.io.File

fun View.show() {
  if (visibility == View.VISIBLE) return

  visibility = View.VISIBLE
  if (this is Group) {
    this.requestLayout()
  }
}

fun View.hide() {
  if (visibility == View.GONE) return

  visibility = View.GONE
  if (this is Group) {
    this.requestLayout()
  }
}

fun View.invisible() {
  if (visibility == View.INVISIBLE) return

  visibility = View.INVISIBLE
  if (this is Group) {
    this.requestLayout()
  }
}

@RequiresApi(Build.VERSION_CODES.N)
@BindingAdapter("app:fromHtml")
fun TextView.fromHtml(text: String?) {
  if (text != null)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      setText(Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT))
    } else {
      setText(Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY))
    }
}

@BindingAdapter("app:goneUnless")
fun View.goneUnless(visible: Boolean) {
  visibility = if (visible) View.VISIBLE else View.GONE
  if (this is Group) {
    this.requestLayout()
  }
}

@BindingAdapter("app:rate")
fun RatingBar.rateApp(value: String) {
  if (value.isNotEmpty())
    rating = value.toFloat()
}

fun ImageView.drawCircle(backgroundColor: String, borderColor: String? = null) {
  val shape = GradientDrawable()
  shape.shape = GradientDrawable.OVAL
  shape.cornerRadii = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)

  shape.setColor(Color.parseColor(backgroundColor))

  borderColor?.let {
    shape.setStroke(10, Color.parseColor(it))
  }

  background = shape
}

fun ImageView.setTint(@ColorRes id: Int) =
  setColorFilter(ContextCompat.getColor(context, id), PorterDuff.Mode.SRC_IN)

fun View.enable() {
  isEnabled = true
  alpha = 1f
}

fun View.disable() {
  isEnabled = false
  alpha = 0.5f
}

fun View.showSnackBar(
  message: String,
  retryActionName: String? = null,
  action: (() -> Unit)? = null
) {
  val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)

  action?.let {
    snackBar.setAction(retryActionName) {
      it()
    }
  }

  snackBar.show()
}

@BindingAdapter(
  value = ["app:loadImage", "app:progressBar", "app:defaultImage"],
  requireAll = false
)
fun ImageView.loadImage(imageUrl: String?, progressBar: ProgressBar?, defaultImage: Any?) {
  if (imageUrl != null && imageUrl.isNotEmpty()) {
    if (URLUtil.isValidUrl(imageUrl)) {
      val request = ImageRequest.Builder(context)
        .data(imageUrl)
        .crossfade(true)
        .crossfade(400)
        .error(R.drawable.bg_no_image)
        .placeholder(R.color.colorLight)
        .target(
          onStart = { placeholder ->
            progressBar?.show()
            setImageDrawable(placeholder)
          },
          onSuccess = { result ->
            progressBar?.hide()
            setImageDrawable(result)
          }
        )
        .listener(onError = { request: ImageRequest, _: Throwable ->
          progressBar?.hide()
          setImageDrawable(request.error)
        })
        .build()

      ImageLoader(context).enqueue(request)
    } else {
      load(File(imageUrl)) {
        crossfade(750) // 75th percentile of a second
        build()
      }
    }

  } else {
    progressBar?.hide()
    Log.e("loadImage", "loadImage: $defaultImage")
    load(R.drawable.bg_no_image) {
      crossfade(true)
      transformations(
        CircleCropTransformation()
      )
    }
  }
}

@BindingAdapter(value = ["app:loadCircleImage", "app:progressBar"], requireAll = false)
fun ImageView.loadCircleImage(imageUrl: String?, progressBar: ProgressBar?) {
  if (imageUrl != null && imageUrl.isNotEmpty()) {
    val request = ImageRequest.Builder(context)
      .data(imageUrl)
      .crossfade(true)
      .crossfade(400)
      .placeholder(R.color.colorLight)
      .error(R.drawable.bg_no_image)
      .transformations(
        CircleCropTransformation()
      )
      .target(
        onStart = { placeholder ->
          progressBar?.show()
          setImageDrawable(placeholder)
        },
        onSuccess = { result ->
          progressBar?.hide()
          setImageDrawable(result)
        }
      )
      .listener(onError = { request: ImageRequest, _: Throwable ->
        progressBar?.hide()
        setImageDrawable(request.error)
      })
      .build()

    ImageLoader(context).enqueue(request)
  } else {
    progressBar?.hide()

    load(R.drawable.bg_no_image) {
      crossfade(true)
      transformations(
        CircleCropTransformation()
      )
    }
  }
}

@BindingAdapter(value = ["app:loadRoundImage", "app:progressBar"], requireAll = false)
fun ImageView.loadRoundImage(imageUrl: String?, progressBar: ProgressBar?) {
  if (imageUrl != null && imageUrl.isNotEmpty()) {
    val request = ImageRequest.Builder(context)
      .data(imageUrl)
      .crossfade(true)
      .crossfade(400)
      .placeholder(R.color.colorLight)
      .error(R.drawable.bg_no_image)
      .transformations(
        RoundedCornersTransformation(
          resources.getDimension(R.dimen.dimen7)
        )
      )
      .target(
        onStart = { placeholder ->
          progressBar?.show()
          setImageDrawable(placeholder)
        },
        onSuccess = { result ->
          progressBar?.hide()
          setImageDrawable(result)
        }
      )
      .listener(onError = { request: ImageRequest, _: Throwable ->
        progressBar?.hide()
        setImageDrawable(request.error)
      })
      .build()

    ImageLoader(context).enqueue(request)
  } else {
    progressBar?.hide()

    load(R.drawable.bg_no_image) {
      crossfade(true)
      transformations(
        RoundedCornersTransformation(
          resources.getDimension(R.dimen.dimen7)
        )
      )
    }
  }
}

@BindingAdapter("load_drawable")
fun loadDrawable(imageView: ImageView, drawable: Drawable?) {
  imageView.setImageDrawable(drawable)
}

@BindingAdapter("app:changeBackground")
fun MaterialCardView.changeBackground(background_color: Int) {
  this.setCardBackgroundColor(background_color)
}

@BindingAdapter("app:changeBackground")
fun View.changeBackground(background_color: Int) {
  this.setBackgroundResource(background_color)
}

@BindingAdapter("app:adapter", "app:span", "app:orientation")
fun getItemsV2Binding(
  recyclerView: RecyclerView,
  itemsAdapter: RecyclerView.Adapter<*>?,
  spanCount: String,
  orientation: String
) {
  if (orientation == "1") initVerticalRV(
    recyclerView,
    recyclerView.context,
    spanCount.toInt()
  ) else initHorizontalRV(recyclerView, recyclerView.context, spanCount.toInt())
  recyclerView.adapter = itemsAdapter
}

@SuppressLint("WrongConstant")
fun initVerticalRV(recyclerView: RecyclerView, context: Context?, spanCount: Int) {
  recyclerView.setHasFixedSize(true)
  recyclerView.setItemViewCacheSize(30)
  recyclerView.layoutManager =
    GridLayoutManager(context, spanCount, LinearLayoutManager.VERTICAL, false)
}

@SuppressLint("WrongConstant")
fun initHorizontalRV(recyclerView: RecyclerView, context: Context?, spanCount: Int) {
  recyclerView.setHasFixedSize(true)
  recyclerView.setItemViewCacheSize(30)
  recyclerView.layoutManager =
    GridLayoutManager(context, spanCount, LinearLayoutManager.HORIZONTAL, false)
}

fun RecyclerView.setUpAdapter(
  itemsAdapter: RecyclerView.Adapter<*>?,
  spanCount: String,
  orientation: String
) {
  if (orientation == "1") initVerticalRV(
    this,
    this.context,
    spanCount.toInt()
  ) else initHorizontalRV(this, this.context, spanCount.toInt())
  this.adapter = itemsAdapter

}