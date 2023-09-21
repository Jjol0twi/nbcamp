package com.example.kakaobankfirsthalfassignments.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kakaobankfirsthalfassignments.databinding.ItemSearchResultsBinding
import com.example.kakaobankfirsthalfassignments.model.KakaoImageModel
import com.example.kakaobankfirsthalfassignments.model.SearchResultModel

class SearchResultsListAdapter : RecyclerView.Adapter<SearchResultsListAdapter.ViewHolder>() {

    private var itemList: ArrayList<SearchResultModel> = arrayListOf()

    fun refreshData(item: ArrayList<SearchResultModel>) {
        itemList.clear()
        itemList = item
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemSearchResultsBinding) :
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
            )
        )
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }
}