package com.example.kakaobankfirsthalfassignments.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kakaobankfirsthalfassignments.adpater.SearchResultsListAdapter
import com.example.kakaobankfirsthalfassignments.databinding.SearchResultsFragmentBinding

class SearchResultsFragment : Fragment() {


    private var _binding: SearchResultsFragmentBinding? = null
    private val binding get() = _binding!!

    private val listAdapter by lazy {
        SearchResultsListAdapter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchResultsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = with(binding) {
        resultsGridView.adapter = listAdapter
        resultsGridView.layoutManager = GridLayoutManager(context, 2)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

    }
}