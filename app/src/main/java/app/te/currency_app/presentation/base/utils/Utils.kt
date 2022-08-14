package app.te.currency_app.presentation.base.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.Toast
import app.te.currency_app.R
import com.tapadoo.alerter.Alerter
import java.util.*
import kotlin.reflect.full.memberProperties

fun showMessage(context: Context, message: String?) {
  Toast.makeText(
    context,
    message ?: context.resources.getString(R.string.some_error),
    Toast.LENGTH_SHORT
  )
    .show()
}

fun showNoInternetAlert(activity: Activity) {
  Alerter.create(activity)
    .setTitle(activity.resources.getString(R.string.connection_error))
    .setText(activity.resources.getString(R.string.no_internet))
    .setIcon(R.drawable.ic_no_internet)
    .setBackgroundColorRes(R.color.red)
    .enableClickAnimation(true)
    .enableSwipeToDismiss()
    .show()
}

fun showInternetAlert(activity: Activity) {
  Alerter.create(activity)
    .setTitle(activity.resources.getString(R.string.connection_success))
    .setIcon(R.drawable.ic_internet_available)
    .setBackgroundColorRes(R.color.green)
    .enableClickAnimation(true)
    .enableSwipeToDismiss()
    .show()
}

fun showNoApiErrorAlert(activity: Activity, message: String) {
  Alerter.create(activity)
    .setText(message)
    .setIcon(R.drawable.ic_api_warning)
    .setBackgroundColorRes(R.color.red)
    .enableClickAnimation(true)
    .enableSwipeToDismiss()
    .show()
}


fun showLoadingDialog(activity: Activity?, hint: String?): Dialog? {
  if (activity == null || activity.isFinishing) {
    return null
  }
  val progressDialog = Dialog(activity)
  progressDialog.show()
  if (progressDialog.window != null) {
    progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
  }
  progressDialog.setContentView(R.layout.progress_dialog)
  progressDialog.setCancelable(false)
  progressDialog.setCanceledOnTouchOutside(false)
  progressDialog.show()

  return progressDialog
}

fun hideLoadingDialog(mProgressDialog: Dialog?, activity: Activity?) {
  if (activity != null && !activity.isFinishing && mProgressDialog != null && mProgressDialog.isShowing) {
    mProgressDialog.dismiss()
  }
}


inline fun <reified T : Any> T.asMap(): Map<String, Any?> {
  val props = T::class.memberProperties.associateBy { it.name }
  return props.keys.associateWith { props[it]?.get(this) }
}

fun getDefaultCurrency(): String = Currency.getInstance(Locale.getDefault()).symbol