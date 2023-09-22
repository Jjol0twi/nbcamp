package com.example.kakaobankfirsthalfassignments.adpater

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kakaobankfirsthalfassignments.databinding.ItemSearchResultsBinding
import com.example.kakaobankfirsthalfassignments.model.SearchResultModel

class StorageListAdapter(storageList: ArrayList<SearchResultModel>, private val onClick: (SearchResultModel) -> Unit) :
    RecyclerView.Adapter<StorageListAdapter.ViewHolder>() {

    private val itemList: ArrayList<SearchResultModel> = arrayListOf()

    init {
        itemList.clear()
        itemList.addAll(storageList)
    }

    class ViewHolder(private val binding: ItemSearchResultsBinding, private val onClick: (SearchResultModel) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SearchResultModel) = with(binding) {
            Glide.with(binding.root)
                .load(item.previewImg)
                .into(previewImg)
            resultTitle.text = item.title
            resultDate.text = item.postTime
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSearchResultsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),onClick
        )
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
        if (itemList.size > 0) {
            holder.itemView.setOnClickListener {
                switchItemStorage(position)
                onClick(item)
            }
        }
    }

    private fun switchItemStorage(position: Int) {
        itemList[position] = itemList[position].copy(isStorage = false)
        itemList.removeAll { it.title == itemList[position].title && it.previewImg == itemList[position].previewImg && it.postTime == itemList[position].postTime }
        notifyDataSetChanged()
    }
}