package com.sun_aterisk.demomvp.data.model

import com.sun_aterisk.demomvp.utils.AppConstraints
import org.json.JSONArray
import org.json.JSONObject

data class Comment(val cmtId: Int, var email: String, var name: String, var body: String, var postId: Int) {
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getInt(AppConstraints.JSON_KEY_ID),
        jsonObject.getString(AppConstraints.JSON_KEY_EMAIL),
        jsonObject.getString(AppConstraints.JSON_KEY_NAME),
        jsonObject.getString(AppConstraints.JSON_KEY_BODY),
        jsonObject.getInt(AppConstraints.JSON_KEY_POST_ID)
    )

    companion object {
        val default = Comment(0, "", "", "",0)
    }

}
