package com.marcin.wac.thesisapp.persistence

import android.content.Context
import com.marcin.wac.thesisapp.models.UserModel
import com.marcin.wac.thesisapp.utils.bases.BaseStorage
import javax.inject.Inject

open class UserSession @Inject constructor(context: Context) : BaseStorage(context), IUserSession {

    private val TOKEN = "token"
    private val USER = "user"
    private val EMAIL = "email"

    override fun getStorageName() = "com.marcin.wac.thesisapp.user_session"

    override fun logIn(accessToken: String) {
        putString(TOKEN, accessToken)
    }

    override fun logOut() {
        removeKeys(TOKEN, USER, EMAIL)
    }

    override fun getToken(): String {
        return getString(TOKEN, "default").toString()
    }

    override fun isLoggedIn() = getToken()!!.isNotEmpty() && getToken() != "default"


    override fun setUser(user: UserModel) {
        putObject(USER, user)
    }

    override fun getUser(): UserModel {
        return getObject<UserModel>(USER)!!
    }

    override fun getUserId(): String {
        return getUser().idNumber
    }

    override fun isStudent(): Boolean {
        return getUser().role == "student"
    }

    override fun setEmail(email: String) {
        putString(EMAIL, email)
    }

    override fun getEmail(): String {
        return getString(EMAIL, "").toString()
    }

    override fun getDepartment(): String {
        return getUser().department
    }

}