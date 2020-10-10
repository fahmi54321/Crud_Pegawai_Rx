package com.mobile.pegawai.presenter.delete

import com.mobile.pendataransdmvprxandroid.network.kotlin.ConfigNetwork
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

//todo 4(deleteUser) construct interface
class DeletePresenter(var deleteView: DeleteView) {

    //todo 2(deleteUser) membuat fungsi deleteUser
    fun deleteUser(idUser: String?) {

        //todo 3(deleteUser) konfigurasi rx
        ConfigNetwork.getRetrofit().deleteUser(idUser)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                deleteView.onSuccessDelete(response.message ?: "")
            }, { t ->
                deleteView.onErrorDelete(t.localizedMessage)
            })
    }
}