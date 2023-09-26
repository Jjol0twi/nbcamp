package com.example.kakaobankfirsthalfassignments.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.example.kakaobankfirsthalfassignments.R
import com.example.kakaobankfirsthalfassignments.databinding.MainActivityBinding
import com.example.kakaobankfirsthalfassignments.model.SearchResultModel
import com.example.kakaobankfirsthalfassignments.utils.DataTransferListener

class MainActivity : AppCompatActivity(), DataTransferListener {
    private lateinit var binding: MainActivityBinding

    private var storageList: ArrayList<SearchResultModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() = with(binding) {
        displayFragment(SearchResultsFragment())

        changeFragmentButton.setOnClickListener {
            Log.d("test", "$storageList")
            when (supportFragmentManager.findFragmentById(binding.fragmentContainer.id)) {
                is SearchResultsFragment -> {
                    displayFragment(MyStorageFragment.newInstance(storageList))
                    changeFragmentButton.setImageResource(R.drawable.icon_dashboard)
                }

                is MyStorageFragment -> {
                    displayFragment(SearchResultsFragment.newInstance(storageList))
                    changeFragmentButton.setImageResource(R.drawable.icon_home)
                }
            }
        }
//        navigationView.setOnItemSelectedListener { it ->    // navigationView menu click event
//            when (it.itemId) {
//                R.id.my_storage -> {    // home menu
//                    displayFragment(MyStorageFragment())
//                }
//
//                R.id.search_results -> {    // my storage click
//                    displayFragment(SearchResultsFragment())
//                }
//            }
//            true
//        }
//        navigationView.selectedItemId = R.id.search_results // first screen clicked
    }

    private fun displayFragment(fragment: Fragment) {   // fragment change
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)    // get fragment
            .commit()
    }

    override fun onDataTransfer(data: ArrayList<SearchResultModel>) {
        Log.d("test","trnas1: ${data.size}")
        Log.d("test", "main1 : ${storageList.size}")
        storageList.clear()
        Log.d("test","trnas2: ${data.size}")
        Log.d("test", "main2 : ${storageList.size}")
        storageList.addAll(data)
        Log.d("test","trnas3: ${data.size}")
        Log.d("test", "main3 : ${storageList.size}")
    }
}