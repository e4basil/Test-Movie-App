package com.baz.fir.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.baz.fir.R
import com.baz.fir.databinding.RvItemListingBinding
import com.baz.fir.domain.model.ContentItem
import com.bumptech.glide.Glide

class HomeAdapter(): ListAdapter<ContentItem, HomeAdapter.ItemViewHolder>(DiffCallback) {


    class ItemViewHolder(private var mBinding: RvItemListingBinding) : RecyclerView.ViewHolder(mBinding.root){
        fun onBind(item: ContentItem, position: Int) {
            mBinding.tvMovie.text = item.name

            Glide
                .with(mBinding.root.context)
                .load(item.posterImage)
                .centerCrop()
                .placeholder(R.drawable.ic_round_error_24)
                .into(mBinding.ivMovie)
        }

    }

    companion object DiffCallback : DiffUtil.ItemCallback<ContentItem>() {
        override fun areItemsTheSame(oldItem: ContentItem, newItem: ContentItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ContentItem, newItem: ContentItem): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return  ItemViewHolder (RvItemListingBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item,position)
    }
}