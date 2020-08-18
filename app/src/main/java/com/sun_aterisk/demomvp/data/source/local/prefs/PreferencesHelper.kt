package com.sun_aterisk.demomvp.data.source.local.prefs

import com.sun_aterisk.demomvp.data.model.User

interface PreferencesHelper {
    fun setUser(user: User)
    fun getUser(): User
}
