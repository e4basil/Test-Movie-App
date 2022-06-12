package com.baz.diagonalmovieapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.baz.diagonalmovieapp.R
import com.baz.diagonalmovieapp.databinding.RvItemListingBinding
import com.baz.diagonalmovieapp.domain.model.ContentItem
import com.bumptech.glide.Glide

class HomeAdapter(private var listener:  HomeListingFragment.OnItemClickListener ) : ListAdapter<ContentItem, HomeAdapter.ItemViewHolder>(DiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            RvItemListingBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item = item,listener)
    }


    companion object DiffCallback : DiffUtil.ItemCallback<ContentItem>() {
        override fun areItemsTheSame(oldItem: ContentItem, newItem: ContentItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ContentItem, newItem: ContentItem): Boolean {
            return oldItem.name == newItem.name
        }
    }


    class ItemViewHolder(private var mBinding: RvItemListingBinding) : RecyclerView.ViewHolder(
        mBinding.root
    ) {

        fun onBind(item: ContentItem, listener: HomeListingFragment.OnItemClickListener) {
            mBinding.root.setOnClickListener { listener.onItemClick(item = item) }
            mBinding.tvMovie.text = item.name

            Glide
                .with(mBinding.root.context)
                .load(item.posterImage)
                .centerCrop()
                .placeholder(R.drawable.placeholder_for_missing_posters)
                .into(mBinding.ivMovie)
        }

    }
}