package com.sun_aterisk.demomvp.data.source.remote

import android.os.AsyncTask
import com.sun_aterisk.demomvp.base.Result
import com.sun_aterisk.demomvp.utils.JsonUtils
import org.json.JSONObject

class MyAsync(var callback: Result) : AsyncTask<String, Int, String>() {

    override fun doInBackground(vararg p0: String?): String =
           BaseRemote.connectionServer(p0[0],p0[1],p0[2]?.toMap())

    override fun onPostExecute(result: String?) {
        result?.let { callback.onResponse(result) }
        super.onPostExecute(result)
    }

    fun String.toMap(): Map<String, String> = JsonUtils.convertToMap( JSONObject(this))
}


