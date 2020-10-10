package com.mobile.pegawai.presenter.home

import com.mobile.pendataransdmvprxandroid.network.kotlin.ConfigNetwork
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

//todo 4(selectUser) construct interface
class HomePresenter(var homeView: HomeView) {

    //todo 2(selectUser) membuat fungsi selectUser
    fun selectUser() {

        //todo 3(selectUser) konfigurasi rx
        ConfigNetwork.getRetrofit().selectUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                var data = response.result
                if (data?.size ?: 0 > 0) {
                    homeView.onSuccess(response.message ?: "", data)
                } else {
                    homeView.dataKosong()
                }
            }, { t ->
                homeView.onError(t.localizedMessage)
            })
    }
}