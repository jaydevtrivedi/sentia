package com.jaydevtrivedi.sentia

import androidx.annotation.VisibleForTesting
import com.jaydevtrivedi.sentia.data.DataSourceSentia
import com.jaydevtrivedi.sentia.data.remote.RemoteDataSourceSentia
import com.jaydevtrivedi.sentia.network.network

//  TODO to be replaced by Dagger/Koin
object ServiceLocator {

    @Volatile
    var remoteDataSourceSentia: DataSourceSentia? = null
        @VisibleForTesting set

    fun provideDataSource(): DataSourceSentia {
        synchronized(this) {
            return remoteDataSourceSentia ?: createRemoteDataSource()
        }
    }

    private fun createRemoteDataSource(): DataSourceSentia {
        val newDataSource = RemoteDataSourceSentia(
            network
        )
        remoteDataSourceSentia = newDataSource
        return newDataSource
    }

    private val lock = Any()
    @VisibleForTesting
    fun resetRepository() {
        synchronized(lock) {
            remoteDataSourceSentia = null
        }
    }
}