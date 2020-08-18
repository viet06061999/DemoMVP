package com.sun_aterisk.demomvp.utils

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.provider.Settings
import org.json.JSONArray
import java.util.regex.Matcher
import java.util.regex.Pattern

object CommonUtils {

    private const val TAG = "CommonUtils"

    fun showLoadingDialog(context: Context?): ProgressDialog? {
        val progressdialog = ProgressDialog(context)
        progressdialog.setMessage("Please Wait....")
        return progressdialog
    }

    @SuppressLint("all")
    fun getDeviceId(context: Context): String? {
        return Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ANDROID_ID
        )
    }

    fun isEmailValid(email: String?): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val EMAIL_PATTERN = ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
        pattern = Pattern.compile(EMAIL_PATTERN)
        matcher = pattern.matcher(email)
        return matcher.matches()
    }

    fun isResponeSuccess(response: String) =
        (!response.equals(AppConstraints.MESSAGE_ERROR)) && (JSONArray(response).length() > 0)


}
