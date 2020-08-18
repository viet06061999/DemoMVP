package com.sun_aterisk.demomvp.base


import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.sun_aterisk.demomvp.utils.CommonUtils


abstract class BaseFragment : Fragment() {

    private var progressDialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    fun showLoading() {
        hideLoading()
        progressDialog = CommonUtils.showLoadingDialog(this.context)
        progressDialog?.show()
    }

    fun hideLoading() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.cancel()
        }
    }

    fun isNetworkConnected(): Boolean {
        if (context != null) {
            val cm =
                context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm?.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        } else return false
    }

}
