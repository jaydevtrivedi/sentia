package com.jaydevtrivedi.sentia.data

import androidx.lifecycle.LiveData
import com.jaydevtrivedi.sentia.data.remote.models.Json4Kotlin_Base
import com.jaydevtrivedi.sentia.overview.ApiStatus
import dagger.Component
import javax.inject.Singleton

//  This can be local or remote
interface DataSourceSentia {

    val status: LiveData<ApiStatus>
    val basedata: LiveData<Json4Kotlin_Base>

    suspend fun getData()
}