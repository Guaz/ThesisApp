package com.marcin.wac.thesisapp.modules.login

import android.util.Log
import com.marcin.wac.thesisapp.infrastructure.api.ThesisApi
import com.marcin.wac.thesisapp.models.body.LoginBody
import com.marcin.wac.thesisapp.persistence.IUserSession
import com.marcin.wac.thesisapp.persistence.UserSession
import com.marcin.wac.thesisapp.utils.BaseCallback
import com.marcin.wac.thesisapp.utils.BooleanCallback
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginInteractor @Inject constructor(private val api: ThesisApi,
                                          private val userSession: IUserSession) {
    private val disposable = CompositeDisposable()

    fun login(loginBody: LoginBody, callback: BooleanCallback) {
        disposable.add(api.login(loginBody)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                userSession.logIn(response.token)
                getUserData(response.email, callback)
                userSession.setEmail(response.email)
                Log.d("TESTAGH", "suc")

            }, {
                Log.d("TESTAGH", "err " + it.message)
//                if (logoutManager.isUnauthorized(it)) {
//                    logoutManager.logout()
//                }
            }
            ))
    }

    private fun getUserData(email: String, callback: BooleanCallback){
        disposable.add(api.getUserDetails("Bearer " + userSession.getToken(), email)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                userSession.setUser(response)
                Log.d("TESTAGH", "0suc")

                if (response.role == "student"){
                    callback.success(true)
                } else
                    callback.success(false)
            }, {
                Log.d("TESTAGH", "0err " + it.message)

                callback.error()
//                if (logoutManager.isUnauthorized(it)) {
//                    logoutManager.logout()
//                }
            }
            ))
    }

    fun clearNetworking() {
        disposable.clear()
    }
}
