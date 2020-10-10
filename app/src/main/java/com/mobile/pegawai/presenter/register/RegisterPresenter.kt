package com.mobile.pegawai.presenter.register

import android.util.Log
import com.mobile.pendataransdmvprxandroid.network.kotlin.ConfigNetwork
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody

//todo 5(register) construct interface
class RegisterPresenter(var registerView: RegisterView) {

    //todo 3(register) kerangka respon
    fun signup(
        nama: RequestBody,
        email: RequestBody,
        password: RequestBody,
        photo: MultipartBody.Part
    ) {
        //todo 4(register) kerangka respon
        ConfigNetwork.getRetrofit().signup(nama, email, password, photo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                if (response.message.equals("Email address sudah ada")) {
                    registerView.emailAda()
                    Log.d("Response email", response.message ?: "")
                } else {
                    registerView.onSuccess(response.message ?: "")
                    Log.d("Response berhasil", response.message ?: "")
                }
            }, { t ->
                registerView.onError(t.localizedMessage)
                Log.d("Response gagal", t.localizedMessage)
            })
    }
}