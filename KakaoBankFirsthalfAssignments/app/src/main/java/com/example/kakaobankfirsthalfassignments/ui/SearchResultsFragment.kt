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
                return getResultData(query)
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun getResultData(query: String?): Boolean {
        if (query.isNullOrBlank()) return true
        GlobalScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val result =
                    KakaoRepository().getImageData(query = query, sortType = "recency")
                withContext(Dispatchers.Main) {
                    listAdapter.refreshData(result)
                }
            }.onFailure {
                Log.d("error", "response failed")
            }
        }
        return false
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

    }
}