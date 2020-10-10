package com.mobile.pegawai.presenter.edit

import android.util.Log
import com.mobile.pendataransdmvprxandroid.network.kotlin.ConfigNetwork
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody

//todo 4(editUser) construct interface
class EdirPresenter(var editView: EditView) {

    //todo 2(editUser) membuat fungsi editUser
    fun edtUser(
        idUser: RequestBody,
        nama: RequestBody,
        email: RequestBody,
        password: RequestBody,
        photo: MultipartBody.Part
    ) {
        //todo 3(editUser) konfigurasi rx
        ConfigNetwork.getRetrofit().editPegawai(idUser, nama, email, password, photo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                editView.onSuccessEdit(response.message ?: "")
                Log.d("Response Berhasil", response.message ?: "")
            }, { t ->
                editView.onErrorEdit(t.localizedMessage)
                Log.d("Response Gagal", t.localizedMessage)
            })
    }
}