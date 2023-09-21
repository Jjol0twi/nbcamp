package com.example.kakaobankfirsthalfassignments.adpater

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kakaobankfirsthalfassignments.databinding.ItemSerachResultsBinding
import com.example.kakaobankfirsthalfassignments.model.KakaoApiSearchImage

class SearchResultsListAdapter : RecyclerView.Adapter<SearchResultsListAdapter.ViewHolder>() {
    class ViewHolder(private val binding: ItemSerachResultsBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind() = with(binding){

            }
    private var itemList: ArrayList<KakaoImageModel.Document> = arrayListOf()
    }

    class ViewHolder(private val binding: ItemSearchResultsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: KakaoImageModel.Document) = with(binding) {
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