package com.nuu.cmoney_technology_interview

interface ApiInterface {
    fun onSuccess(dataSting: String)
    fun onFailed(errorMsg: String?)
}