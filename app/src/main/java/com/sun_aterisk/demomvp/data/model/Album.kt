package com.sun_aterisk.demomvp.data.model

import com.sun_aterisk.demomvp.utils.AppConstraints
import org.json.JSONObject

data class Album(val albumId: Int, var title: String, var listPhoto: MutableList<Photo>){
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getInt(AppConstraints.JSON_KEY_ID),
        jsonObject.getString(AppConstraints.JSON_KEY_TITLE),
        mutableListOf()
    )
}

