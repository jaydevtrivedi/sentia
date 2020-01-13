package com.jaydevtrivedi.sentia.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.jaydevtrivedi.sentia.R
import com.jaydevtrivedi.sentia.sentiaApplication
import kotlinx.android.synthetic.main.detail_fragment.*
import javax.inject.Inject

//import com.jaydevtrivedi.sentia.databinding.DetailFragmentBinding

class DetailFragment : Fragment() {

    val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textview_listing.setText("listingId: " + args.listingId)
    }
}
