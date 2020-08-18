package com.sun_aterisk.demomvp.data.source.remote

import com.sun_aterisk.demomvp.utils.AppConstraints
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


object  BaseRemote {
    fun connectionServer(url: String?, method: String?, data: Map<String, String>?): String {
        val lineEnd = "\r\n"
        val twoHyphens = "--"
        val boundary = "*****"
        val requestURL = URL(url)
        try {
            val conn: HttpURLConnection = requestURL.openConnection() as HttpURLConnection
            conn.setReadTimeout(10000 /* milliseconds */)
            conn.setConnectTimeout(15000 /* milliseconds */)
            conn.setRequestMethod(method)
            conn.setDoInput(true)
            conn.connect()
            if (!method.equals(AppConstraints.METHOD_GET)){
                val dos = DataOutputStream(conn.outputStream)
                data?.let {
                    for (name in data.keys) {
                        dos.writeBytes(lineEnd)
                        dos.writeBytes(twoHyphens + boundary + lineEnd)
                        dos.writeBytes("Content-Disposition: form-data; name=\"$name\"$lineEnd")
                        dos.writeBytes(lineEnd)
                        dos.writeBytes(data.get(name))
                    }
                }
            }
      //lấy response
            if (conn.responseCode != 200)
                return AppConstraints.MESSAGE_ERROR
            val inputStream: InputStream = conn.inputStream
            val bufferedReader =
                BufferedReader(InputStreamReader(inputStream))
            val response = StringBuffer()
            bufferedReader.forEachLine {
                response.append(it)
            }
            return String(response)
        } catch (e: Exception) {
            e.printStackTrace()
            return AppConstraints.MESSAGE_ERROR
        }
    }
}
