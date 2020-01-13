package com.jaydevtrivedi.sentia

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jaydevtrivedi.sentia.data.remote.models.Listings
import com.jaydevtrivedi.sentia.overview.ApiStatus
import com.jaydevtrivedi.sentia.overview.PhotoGridAdapter
import kotlin.coroutines.coroutineContext


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Listings>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
    if (data.isNullOrEmpty()){
        //  TODO error handling
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String) {
    val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
    Glide.with(imgView.context)
        .load(imgUri)
        //.load(imgUrl)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
        )
        .into(imgView)

}

@BindingAdapter("ApiStatus")
fun bindStatus(statusImageView: ImageView, status: ApiStatus?) {
    when (status) {
        ApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        ApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("toString")
fun bindStatus(textView: TextView, paramint: Int) {
    textView.text = Integer.toString(paramint)
}

@BindingAdapter("address", "address2", "suburb", "state")
fun bindStatus(textView: TextView, address: String, address2: String, suburb: String, state: String) {
    val seperator = " "
    textView.text = address + seperator + address2 + seperator + suburb + seperator + state
}