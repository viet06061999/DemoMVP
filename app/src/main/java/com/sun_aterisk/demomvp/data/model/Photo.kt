package com.sun_aterisk.demomvp.data.model

import com.sun_aterisk.demomvp.utils.AppConstraints
import org.json.JSONObject

data class Photo(val photoId: Int, var title: String, var url: String, var thumbnailUrl: String) {
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getInt(AppConstraints.JSON_KEY_ID),
        jsonObject.getString(AppConstraints.JSON_KEY_TITLE),
        jsonObject.getString(AppConstraints.JSON_KEY_URL),
        jsonObject.getString(AppConstraints.JSON_KEY_THUMBNAIL_URL)
    )
}
