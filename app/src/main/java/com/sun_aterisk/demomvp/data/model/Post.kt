package com.sun_aterisk.demomvp.data.model

import com.sun_aterisk.demomvp.utils.AppConstraints
import org.json.JSONObject

data class Post(
    var postId: Int,
    var userId: Int,
    var title: String,
    var body: String,
    var isFavorite: Boolean
) {
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getInt(AppConstraints.JSON_KEY_ID),
        jsonObject.getInt(AppConstraints.JSON_KEY_USER_ID),
        jsonObject.getString(AppConstraints.JSON_KEY_TITLE),
        jsonObject.getString(AppConstraints.JSON_KEY_BODY),
        false
    )
}

