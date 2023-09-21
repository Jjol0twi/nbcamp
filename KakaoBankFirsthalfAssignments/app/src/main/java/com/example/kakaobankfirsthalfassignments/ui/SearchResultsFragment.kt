package com.example.kakaobankfirsthalfassignments.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kakaobankfirsthalfassignments.adpater.SearchResultsListAdapter
import com.example.kakaobankfirsthalfassignments.databinding.SearchResultsFragmentBinding
import com.example.kakaobankfirsthalfassignments.model.SearchResultModel
import com.example.kakaobankfirsthalfassignments.network.KakaoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchResultsFragment : Fragment() {


    private var _binding: SearchResultsFragmentBinding? = null
    private val binding get() = _binding!!

    private val listAdapter by lazy {
        SearchResultsListAdapter()
    }

    private val pref by lazy {
        requireContext().getSharedPreferences(preference_file_key, Context.MODE_PRIVATE)
    }

//    private val kakaoSearchService by lazy {
//        KakaoNetwork.apiService
//    }

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
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            //                kakaoSearchService.requestImage(query).enqueue(object : Callback<KakaoImageSearch> {
//                    override fun onResponse(
//                        call: Call<KakaoImageSearch>,
//                        response: Response<KakaoImageSearch>
//                    ) {
//                        if (response.isSuccessful) {
//                            Log.d("test", "response success")
//                        } else {
//                            Log.d("error", "response failed")
//                        }
//                    }
//
//                    override fun onFailure(call: Call<KakaoImageSearch>, t: Throwable) {
//                        Log.d("error", "call api fail: " + t.message)
//                    }
//                })
            override fun onQueryTextSubmit(query: String?): Boolean {
                saveSearchQuery(query)
                return getResultData(query)
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        getSearchQuery()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun getResultData(query: String?): Boolean {
        var itemDataList : ArrayList<SearchResultModel> = arrayListOf()
        if (query.isNullOrBlank()) return true
        GlobalScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val result =
                    KakaoRepository().getImageData(query = query, sortType = "recency")
                withContext(Dispatchers.Main) {
                    result.documents.forEach { doc ->
                        itemDataList.add(SearchResultModel(doc.thumbnailUrl, doc.siteName, doc.datetime))
                    }
                    listAdapter.refreshData(itemDataList)
                }
            }.onFailure {
                Log.d("error", "response failed")
            }
        }
        return false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        saveSearchQuery(binding.searchView.query.toString())
        _binding = null
    }

    private fun getSearchQuery() {
        val savedQuery = pref.getString(query_key, null)
        binding.searchView.setQuery(savedQuery,true)
    }

    private fun saveSearchQuery(query: String?) {
        if (!query.isNullOrBlank()) {
            pref.edit().putString(query_key, query).apply()
        }

    }

    companion object {
        const val preference_file_key = "pref"
        const val query_key = "search_query"
    }
}