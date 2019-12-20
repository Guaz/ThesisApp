package com.marcin.wac.thesisapp.persistence

import android.content.Context
import com.marcin.wac.thesisapp.utils.bases.BaseStorage
import javax.inject.Inject

open class UserSession @Inject constructor(context: Context) : BaseStorage(context), IUserSession {

    private val TOKEN = "token"
    private val IS_LECTURER = "is_lecturer"
    private val USER = "user"

    override fun getStorageName() = "com.marcin.wac.thesisapp.user_session"

    override fun logIn(accessToken: String) {
        putString(TOKEN, accessToken)
    }

    override fun logOut() {
        removeKeys(TOKEN, USER, IS_LECTURER)
    }

    override fun getToken(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isLoggedIn() = getToken()!!.isNotEmpty() && getToken() != "default"


    override fun setUser(user: Object) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUser(): Object? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUserId(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setIsLecturer(isLecturer: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isLecturer(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}