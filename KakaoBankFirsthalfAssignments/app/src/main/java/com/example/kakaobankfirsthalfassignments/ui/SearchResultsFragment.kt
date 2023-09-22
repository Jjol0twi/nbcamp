package com.example.kakaobankfirsthalfassignments.ui

import android.content.Context
import android.os.Build
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
import com.example.kakaobankfirsthalfassignments.utils.DataTransferListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SearchResultsFragment : Fragment() {


    private var _binding: SearchResultsFragmentBinding? = null
    private val binding get() = _binding!!

    private val storageList by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelableArrayList("key", SearchResultModel::class.java) ?: arrayListOf<SearchResultModel>()
        } else {
            arguments?.getParcelableArrayList("key") ?: arrayListOf<SearchResultModel>()
        }
    }

    private val listAdapter by lazy {
        SearchResultsListAdapter() { data ->
            if (data.isStorage) {
                storageList.add(data)
            } else {
                storageList.removeAll { it.title == data.title && it.previewImg == data.previewImg && it.postTime == data.postTime }
            }
        }
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
        Log.d("test","search: create")
    }

    private fun initView() = with(binding) {
        Log.d("test", "$storageList")
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

    private fun getResultData(query: String?): Boolean {
        var itemDataList: ArrayList<SearchResultModel> = arrayListOf()
        if (query.isNullOrBlank()) return true
        GlobalScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val result =
                    KakaoRepository().getImageData(query = query, sortType = "recency")
                withContext(Dispatchers.Main) {
                    result.documents.forEach { doc ->
                        var date = doc.datetime
                        var parsedate =
                            LocalDateTime.parse(date, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                        date = parsedate.format(
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                        )
                        itemDataList.add(SearchResultModel(doc.thumbnailUrl, doc.siteName, date))
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
        Log.d("test","search: destory")
        _binding = null
    }

    override fun onStop() {
        super.onStop()
        sendDataToActivity(storageList)
        Log.d("test","search: stop")
    }

    private fun sendDataToActivity(data: ArrayList<SearchResultModel>) {
        if (activity is DataTransferListener) {
            val listener = activity as DataTransferListener
            listener.onDataTransfer(data)
        }
    }

    private fun getSearchQuery() {
        val savedQuery = pref.getString(query_key, null)
        binding.searchView.setQuery(savedQuery, true)
    }


    private fun saveSearchQuery(query: String?) {
        if (!query.isNullOrBlank()) {
            pref.edit().putString(query_key, query).apply()
        }

    }

    companion object {
        const val preference_file_key = "pref"
        const val query_key = "search_query"

        private lateinit var arguments: Bundle
        fun newInstance(dataList: ArrayList<SearchResultModel>): SearchResultsFragment {
            val fragment = SearchResultsFragment()
            val args = Bundle()
            args.putParcelableArrayList("key", dataList)
            fragment.arguments = args
            return fragment
        }
    }
}