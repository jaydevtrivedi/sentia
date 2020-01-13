package com.jaydevtrivedi.sentia.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jaydevtrivedi.sentia.data.DataSourceSentia
import com.jaydevtrivedi.sentia.data.remote.models.Json4Kotlin_Base
import com.jaydevtrivedi.sentia.network.network
import com.jaydevtrivedi.sentia.overview.ApiStatus

class RemoteDataSourceSentia(val network: network) : DataSourceSentia {

    private val _status = MutableLiveData<ApiStatus>()
    override val status: LiveData<ApiStatus>
        get() = _status

    private val _baseData = MutableLiveData<Json4Kotlin_Base>()
    override val basedata: LiveData<Json4Kotlin_Base>
        get() = _baseData


    //override suspend fun getData() {
    override suspend fun getData() {
        var data = network.data.getData()
        try {
            _status.value = ApiStatus.LOADING
            _baseData.value = data.await()
            _status.value = ApiStatus.DONE
        } catch (e: Exception) {
            _status.value = ApiStatus.ERROR
            _baseData.value = null
        }
    }
}