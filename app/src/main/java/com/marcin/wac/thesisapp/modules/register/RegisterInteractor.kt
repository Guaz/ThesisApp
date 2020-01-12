package com.marcin.wac.thesisapp.modules.register

import android.util.Log
import com.marcin.wac.thesisapp.infrastructure.api.ThesisApi
import com.marcin.wac.thesisapp.models.body.RegisterBody
import com.marcin.wac.thesisapp.persistence.IUserSession
import com.marcin.wac.thesisapp.utils.BaseCallback
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RegisterInteractor @Inject constructor(private val api: ThesisApi) {
    private val disposable = CompositeDisposable()

    fun register(registerBody: RegisterBody, callback: BaseCallback) {
        disposable.add(api.register(registerBody)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                callback.success()
            }, {
                callback.error()
            }
            ))
    }
}