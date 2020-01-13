package com.jaydevtrivedi.sentia.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jaydevtrivedi.sentia.data.DataSourceSentia
import com.jaydevtrivedi.sentia.data.remote.models.*
import com.jaydevtrivedi.sentia.overview.ApiStatus
import org.mockito.Mockito


class FakeRemoteDataSourceSentia : DataSourceSentia {

    private val _status = MutableLiveData<ApiStatus>()
    override val status: LiveData<ApiStatus>
        get() = _status

    private val _baseData = MutableLiveData<Json4Kotlin_Base>()
    override val basedata: LiveData<Json4Kotlin_Base>
        get() = _baseData


    override suspend fun getData() {

        try {
            _status.value = ApiStatus.LOADING
            _baseData.value = dummyDetails()
            _status.value = ApiStatus.DONE
        } catch (e: Exception) {
            _status.value = ApiStatus.ERROR
            _baseData.value = null
        }
    }

    private fun dummyDetails(): Json4Kotlin_Base {
        //  Could create more or empty as well to check code
        return getFakeData()
    }

    private fun getFakeData() : Json4Kotlin_Base {
        val imageUrls = arrayListOf(
            "",
            "",
            "",
            "",
            "https://storage.googleapis.com/idx-photos-gs.ihouseprd.com/TX-NTREIS/13807656/org/008.jpg"
        )
        val location =
            Location("79 Anglesea Street, Bondi", "81 Anglesea Street, Bondi", "NSW", "BONDI")
        val owner = Owner("","","",null)

        val listing = Listings(
            "20145184059",
            "https://agencycompile.bl…l_contact_logo_horiz.jpg",
            "Eastern Suburbs",
            "",
            null,
            4,
            6,
            2,
            "",
            "",
            "",
            "",
            "",
            location,
            owner,
            imageUrls,
            0,
            0,
            0.0,
            null,
            null,
            "",
            "",
            0.0
        )
        val data = Data(arrayListOf<Listings>())
        val json4kotlinBase = Json4Kotlin_Base(123, data, "Test Sentia")

        return json4kotlinBase
    }
}