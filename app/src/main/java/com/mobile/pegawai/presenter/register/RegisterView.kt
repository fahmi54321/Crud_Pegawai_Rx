package com.mobile.pegawai.presenter.register

import com.mobile.pegawai.model.ResultItem

interface RegisterView {
    //todo 2(register) kerangka respon
    fun onSuccess(message:String)
    fun onError(message:String)
    fun inputKosong()
    fun emailAda()
}