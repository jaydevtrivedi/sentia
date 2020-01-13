package com.jaydevtrivedi.sentia.overview

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.navigation.NavController
import com.jaydevtrivedi.sentia.data.remote.models.Json4Kotlin_Base
import com.jaydevtrivedi.sentia.sentiaApplication
import kotlinx.coroutines.*

enum class ApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel(application: Application) : AndroidViewModel(application) {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val remoteDataSource = (application as sentiaApplication).dataSource

    var status: LiveData<ApiStatus>
    var basedata: LiveData<Json4Kotlin_Base>

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
        Log.d("debug", "overviewviewmodel oncleared")
    }

    init {
        basedata = remoteDataSource.basedata
        status = remoteDataSource.status
        requestData()
    }


    //  TODO test candidate
    fun moveToDetailFragment(navController: NavController, listingId : String) {
        //  TODO get details from onclick
        val action = OverviewDirections.actionOverviewToDetailFragment(listingId)
        navController.navigate(action)
    }

    private fun requestData() {
        Log.d("debug", "overviewviewmodel requestdata")
        viewModelScope.launch {
            remoteDataSource.getData()
        }
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(OverviewViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return OverviewViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
