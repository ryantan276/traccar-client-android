package vn.anttrack.client

import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.text.TextUtils
import android.view.ContextThemeWrapper
import android.view.KeyEvent
import org.traccar.client.R

open class DialogUtil protected constructor() {
    private var dlg: ProgressDialog? = null
    fun showLoading(ctx: Context, message: String?) {
        (ctx as Activity).runOnUiThread {
            dlg = ProgressDialog(ctx, R.style.Theme_DialogCustom)
            dlg!!.setTitle(null)
            if (TextUtils.isEmpty(message)) {
                dlg!!.setMessage("Loading...")
            } else {
                dlg!!.setMessage(message)
            }
            dlg!!.setCancelable(true)
            dlg!!.show()
        }
    }

    fun showLoadingNotCancelble(ctx: Context, message: String?) {
        (ctx as Activity).runOnUiThread {
            dlg = ProgressDialog(ctx, R.style.Theme_DialogCustom)
            dlg!!.setTitle(null)
            if (TextUtils.isEmpty(message)) {
                dlg!!.setMessage("Loading...")
            } else {
                dlg!!.setMessage(message)
            }
            dlg!!.setCancelable(false)
            dlg!!.show()
        }
    }

    fun hideLoadingNotCancelble() {
        if (dlg != null) {
            dlg!!.dismiss()
        }
    }

    fun hideLoading() {
        if (dlg != null) {
            dlg!!.dismiss()
        }
    }

    companion object {
        var instance: DialogUtil? = null
            get() {
                if (field == null) {
                    field = DialogUtil()
                }
                return field
            }
            private set

        fun showAlertWith1Button(
            context: Context?, tittle: String?,
            message: String?, positiveButtonText: String?,
            positiveButtonlistener: DialogInterface.OnClickListener?
        ) {
            try {
                val alertbox = AlertDialog.Builder(
                    ContextThemeWrapper(context, R.style.Theme_DialogCustom)
                )
                alertbox.setTitle(tittle)
                alertbox.setMessage(message)
                alertbox.setCancelable(false)
                alertbox.setOnKeyListener { _, keyCode, _ ->
                    keyCode == KeyEvent.KEYCODE_BACK
                }
                alertbox.setPositiveButton(
                    positiveButtonText,
                    positiveButtonlistener
                )
                alertbox.show()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }

        fun showAlertWith2Buttons(
            act: Activity?,
            tittle: String?, message: String?, positiveButtonText: String?,
            negativeButtonText: String?,
            positiveButtonlistener: DialogInterface.OnClickListener?,
            negativeButtonlistener: DialogInterface.OnClickListener?
        ): AlertDialog.Builder {
            val alertbox = AlertDialog.Builder(
                ContextThemeWrapper(act, R.style.Theme_DialogCustom)
            )
            alertbox.setTitle(tittle)
            alertbox.setMessage(message)
            alertbox.setCancelable(false)
            alertbox.setOnKeyListener { _, keyCode, _ ->
                keyCode == KeyEvent.KEYCODE_BACK
            }
            alertbox.setPositiveButton(positiveButtonText, positiveButtonlistener)
            alertbox.setNegativeButton(negativeButtonText, negativeButtonlistener)
            alertbox.show()
            return alertbox
        }
    }
}