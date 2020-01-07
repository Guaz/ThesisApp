package com.marcin.wac.thesisapp.persistence

interface IUserSession {
    fun logIn(accessToken: String)
    fun logOut()
    fun getToken(): String
    fun isLoggedIn(): Boolean
    fun setUser(user: Object) //todo
    fun getUser(): Object? //todo
    fun getUserId(): String
    fun setIsLecturer(isLecturer: Boolean)
    fun isLecturer(): Boolean
    fun setEmail(email: String)
    fun getEmail(): String
    fun setDepartment(department: String)
    fun getDepartment(): String
}