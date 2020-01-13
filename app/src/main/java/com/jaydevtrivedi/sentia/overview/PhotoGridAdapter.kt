package com.jaydevtrivedi.sentia.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jaydevtrivedi.sentia.data.remote.models.Listings
import com.jaydevtrivedi.sentia.databinding.ItemBinding

class PhotoGridAdapter( private val onClickListener: OnClickListener ) : ListAdapter<Listings, PhotoGridAdapter.TopPlayerViewHolder>(DiffCallback) {

    class TopPlayerViewHolder(private var binding: ItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listings: Listings) {
            binding.listings = listings
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Listings>() {
        override fun areItemsTheSame(oldItem: Listings, newItem: Listings) : Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Listings, newItem: Listings): Boolean {
            return oldItem.Id == newItem.Id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): TopPlayerViewHolder {
        return TopPlayerViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: TopPlayerViewHolder, position: Int) {
        val topPlayersDomainModel = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(topPlayersDomainModel)
        }
        holder.bind(topPlayersDomainModel)
    }

    class OnClickListener(val clickListener: (listings: Listings) -> Unit) {
        fun onClick(listings: Listings) = clickListener(listings)
    }

}