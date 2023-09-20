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
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}