package com.marcin.wac.thesisapp.persistence

import com.marcin.wac.thesisapp.models.UserModel

interface IUserSession {
    fun logIn(accessToken: String)
    fun logOut()
    fun getToken(): String
    fun isLoggedIn(): Boolean
    fun setUser(user: UserModel)
    fun getUser(): UserModel
    fun getUserId(): String
    fun isStudent(): Boolean
    fun setEmail(email: String)
    fun getEmail(): String
    fun getDepartment(): String
}