package com.mobile.pegawai.presenter.home

import com.mobile.pegawai.model.ResultItem

//todo 1(selectUser) kerangka respon
interface HomeView {
    fun onSuccess(message:String,result: List<ResultItem?>?)
    fun onError(message:String)
    fun dataKosong()
}