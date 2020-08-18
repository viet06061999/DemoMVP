package com.sun_aterisk.demomvp.data.source.local.prefs

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import com.sun_aterisk.demomvp.data.model.User
import com.sun_aterisk.demomvp.utils.AppConstraints

class AppPreferencesHelper : PreferencesHelper {
    var preferences: SharedPreferences
    var editor: Editor

    constructor(context: Context) {
        preferences =
            context.getSharedPreferences(AppConstraints.PREF_NAME_USER, Context.MODE_PRIVATE)
        editor = preferences.edit()
    }

    override fun setUser(user: User) {
        editor.putInt(AppConstraints.PREF_KEY_USER_ID, user.userId)
        editor.putString(AppConstraints.PREF_KEY_USER_NAME, user.name)
        editor.putString(AppConstraints.PREF_KEY_USER_USERNAME, user.userName)
        editor.putString(AppConstraints.PREF_KEY_USER_EMAIL, user.email)
        editor.putString(AppConstraints.PREF_KEY_USER_PHONE, user.phone)
        editor.putString(AppConstraints.PREF_KEY_USER_ADDRESS, user.address)
        editor.putString(AppConstraints.PREF_KEY_USER_WEBSITE, user.website)
        editor.putString(AppConstraints.PREF_KEY_USER_COMPANY, user.company)
        editor.apply()
    }

    override fun getUser(): User {
        var userId: Int = preferences.getInt(AppConstraints.PREF_KEY_USER_ID, 0)
        var name: String = preferences.getString(AppConstraints.PREF_KEY_USER_NAME, "") ?: ""
        var userName = preferences.getString(AppConstraints.PREF_KEY_USER_USERNAME, "") ?: ""
        var email = preferences.getString(AppConstraints.PREF_KEY_USER_EMAIL, "") ?: ""
        var phone = preferences.getString(AppConstraints.PREF_KEY_USER_PHONE, "") ?: ""
        var address = preferences.getString(AppConstraints.PREF_KEY_USER_ADDRESS, "") ?: ""
        var website = preferences.getString(AppConstraints.PREF_KEY_USER_WEBSITE, "") ?: ""
        var company = preferences.getString(AppConstraints.PREF_KEY_USER_COMPANY, "") ?: ""
        return User(userId, name, userName, email, phone, website, address, company)
    }

}
