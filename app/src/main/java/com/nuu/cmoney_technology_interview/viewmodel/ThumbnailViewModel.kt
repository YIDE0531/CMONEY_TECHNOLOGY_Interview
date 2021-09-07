package com.nuu.cmoney_technology_interview.viewmodel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.nuu.cmoney_technology_interview.ApiInterface
import com.nuu.cmoney_technology_interview.ThumbnailRepository
import com.nuu.cmoney_technology_interview.config.AppConfig
import com.nuu.cmoney_technology_interview.model.ThumbnailInfo
import java.util.*

class ThumbnailViewModel: ViewModel(){
    val thumbInfoAllArray = MutableLiveData<Array<ThumbnailInfo>>()

    init {
        getPhotosInfo()  // call api
    }

    private fun getPhotosInfo() {
        ThumbnailRepository.getInstance().getDataApi(AppConfig.URL_CALL_PHOTOS, null, object :
            ApiInterface {
            override fun onSuccess(dataSting: String) {
                val dataModelArray = Gson().fromJson(dataSting, Array<ThumbnailInfo>::class.java)
                thumbInfoAllArray.postValue(dataModelArray)
            }

            override fun onFailed(errorMsg: String?) {

            }
        })
    }
}