package com.jaydevtrivedi.sentia.network

import com.jaydevtrivedi.sentia.data.remote.models.Json4Kotlin_Base
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

/**
 * A retrofit service to fetch data.
 */
interface DataService {
    @GET("/test/properties")
    fun getData() : Deferred<Json4Kotlin_Base>
}