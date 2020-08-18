package com.sun_aterisk.demomvp.data.model

import com.sun_aterisk.demomvp.utils.AppConstraints
import org.json.JSONObject

data class User(
    val userId: Int,
    val name: String,
    val userName: String,
    val email: String,
    var phone: String,
    var website: String,
    var address: String,
    var company: String
) {
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getInt(AppConstraints.JSON_KEY_ID),
        jsonObject.getString(AppConstraints.JSON_KEY_NAME),
        jsonObject.getString(AppConstraints.JSON_KEY_USERNAME),
        jsonObject.getString(AppConstraints.JSON_KEY_EMAIL),
        jsonObject.getString(AppConstraints.JSON_KEY_PHONE),
        jsonObject.getString(AppConstraints.PREF_KEY_USER_WEBSITE),
        jsonObject.getString(AppConstraints.JSON_KEY_ADDRESS_NAME),
        jsonObject.getString(AppConstraints.JSON_KEY_COMPANY_NAME)
    )

    companion object {
        val default = User(0, "", "", "", "", "", "", "")
        fun createJsonObjectUser(json: JSONObject): JSONObject {
            val jsonAdress = json.getJSONObject(AppConstraints.JSON_KEY_ADDRESS)
            val jsonCompany = json.getJSONObject(AppConstraints.JSON_KEY_COMPANY)
            json.put(
                AppConstraints.JSON_KEY_ADDRESS_NAME,
                jsonAdress.getString(AppConstraints.JSON_KEY_STREET) +
                        " - " +
                        jsonAdress.getString(AppConstraints.JSON_KEY_CITY)
            )
            json.put(
                AppConstraints.JSON_KEY_COMPANY_NAME,
                jsonCompany.getString(AppConstraints.JSON_KEY_NAME)
            )
            return json
        }
    }
}
