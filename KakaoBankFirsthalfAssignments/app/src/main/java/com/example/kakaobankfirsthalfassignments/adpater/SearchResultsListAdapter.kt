package com.example.kakaobankfirsthalfassignments.adpater

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kakaobankfirsthalfassignments.databinding.ItemSearchResultsBinding
import com.example.kakaobankfirsthalfassignments.model.SearchResultModel

class SearchResultsListAdapter(private val onClick: (SearchResultModel) -> Unit) :
    RecyclerView.Adapter<SearchResultsListAdapter.ViewHolder>() {

    private val itemList: ArrayList<SearchResultModel> = arrayListOf()

    fun refreshData(item: ArrayList<SearchResultModel>) {
        itemList.clear()
        itemList.addAll(item)
        notifyDataSetChanged()
    }

    private fun switchItemStorage(position: Int) {
        itemList[position] = itemList[position].copy(isStorage = !itemList[position].isStorage)
    }

    class ViewHolder(
        private val binding: ItemSearchResultsBinding,
        private val onClick: (SearchResultModel) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
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
            ), onClick
        )
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            switchItemStorage(position)
            onClick(itemList[position])
        }
    }
}