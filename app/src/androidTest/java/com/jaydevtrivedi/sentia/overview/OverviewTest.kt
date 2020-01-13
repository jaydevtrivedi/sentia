package com.jaydevtrivedi.sentia.overview

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jaydevtrivedi.sentia.R
import com.jaydevtrivedi.sentia.ServiceLocator
import com.jaydevtrivedi.sentia.data.DataSourceSentia
import com.jaydevtrivedi.sentia.data.source.FakeRemoteDataSourceSentia
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

//  TODO do this
@RunWith(AndroidJUnit4::class)
@MediumTest
@ExperimentalCoroutinesApi
class OverviewTest{
    private lateinit var dataSourceSentia: DataSourceSentia

    @Before
    fun initRepository() {
        dataSourceSentia = FakeRemoteDataSourceSentia()
        ServiceLocator.remoteDataSourceSentia = dataSourceSentia
    }

    val navController = Mockito.mock(NavController::class.java)


    @Test
    fun clickPlayer_navigateToDetailFragment() = runBlockingTest {

        // GIVEN - On the home screen
        val scenario = launchFragmentInContainer<Overview>(Bundle(), R.style.AppTheme)

        val navController = Mockito.mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }
        Thread.sleep(2000)

        // WHEN - Click on the first list item
        Espresso.onView(ViewMatchers.withId(R.id.recyclerview_listings))
            .perform(
                RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                    ViewMatchers.hasDescendant(ViewMatchers.withText("Eastern Suburbs")),
                    ViewActions.click()
                ))


//        // THEN - Verify that we navigate to the first detail screen
        Mockito.verify(navController).navigate(
            OverviewDirections.actionOverviewToDetailFragment("20145184059")
        )
    }

    @Test
    fun loadEmptyDataSource() = runBlockingTest {

        // GIVEN - On the home screen
        val scenario = launchFragmentInContainer<Overview>(Bundle(), R.style.AppTheme)

        val navController = Mockito.mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }
        Thread.sleep(2000)

    }

}