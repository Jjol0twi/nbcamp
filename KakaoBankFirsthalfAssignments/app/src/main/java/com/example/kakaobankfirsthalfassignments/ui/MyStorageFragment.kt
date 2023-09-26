package com.example.kakaobankfirsthalfassignments.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kakaobankfirsthalfassignments.adpater.StorageListAdapter
import com.example.kakaobankfirsthalfassignments.databinding.MyStorageFragmentBinding
import com.example.kakaobankfirsthalfassignments.model.SearchResultModel
import com.example.kakaobankfirsthalfassignments.utils.DataTransferListener

class MyStorageFragment : Fragment() {
    private var _binding: MyStorageFragmentBinding? = null
    private val binding get() = _binding!!

    private val storageList by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelableArrayList("key", SearchResultModel::class.java) ?: arrayListOf()
        } else {
            arguments?.getParcelableArrayList("key") ?: arrayListOf()
        }
    }

    private val listAdapter by lazy {
        StorageListAdapter(storageList) { data ->
            if (data.isStorage) {
                storageList.removeAll { it.title == data.title && it.previewImg == data.previewImg && it.postTime == data.postTime }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MyStorageFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        Log.d("test", "my stoarge: create")
    }

    private fun initView() = with(binding) {
        storageListView.adapter = listAdapter
        storageListView.layoutManager = LinearLayoutManager(context)
        Log.d("test", "$storageList")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("test", "my storage: destory")
        _binding = null
    }

    override fun onStop() {
        super.onStop()
        Log.d("test", "my storage: stop, ${storageList.size}")
        sendDataToActivity(storageList)
    }

    private fun sendDataToActivity(data: ArrayList<SearchResultModel>) {
        if (activity is DataTransferListener) {
            val listener = activity as DataTransferListener
            Log.d("test", "my stoarge: stop2, ${data.size}")
            listener.onDataTransfer(data)
        }
    }

    companion object {
        private lateinit var arguments: Bundle
        fun newInstance(dataList: ArrayList<SearchResultModel>): MyStorageFragment {
            val fragment = MyStorageFragment()
            val args = Bundle()
            args.putParcelableArrayList("key", dataList)
            fragment.arguments = args
            return fragment
        }
    }
}