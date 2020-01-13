package com.jaydevtrivedi.sentia.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.jaydevtrivedi.sentia.databinding.OverviewFragmentBinding

//  Todo add headerview to recycyler view depends on requirements
class Overview : Fragment() {

    private val viewModel: OverviewViewModel by lazy {
        //  Can only be accessed after onActivityCreated
        val activity = requireNotNull(this.activity)
        ViewModelProviders.of(
            this, OverviewViewModel.Factory(
                activity.application
            )
        )
            .get(OverviewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = OverviewFragmentBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        // Sets the adapter of the photosGrid RecyclerView
        binding.recyclerviewListings.adapter = PhotoGridAdapter(PhotoGridAdapter.OnClickListener {
            viewModel.moveToDetailFragment(
                this.findNavController(),
                it.Id
            )
        })

        return binding.root
    }
}
