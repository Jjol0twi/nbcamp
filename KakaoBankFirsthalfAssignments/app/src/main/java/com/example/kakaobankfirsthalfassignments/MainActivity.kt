package com.example.kakaobankfirsthalfassignments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.kakaobankfirsthalfassignments.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() = with(binding) {
        navigationView.setOnItemSelectedListener { it ->    // navigationView menu click event
            when (it.itemId) {
                R.id.my_storage -> {    // home menu
                    displayFragment(MyStorageFragment())
                }

                R.id.search_results -> {    // my storage click
                    displayFragment(SearchResultsFragment())
                }
            }
            true
        }
        navigationView.selectedItemId = R.id.search_results // first screnn clicked
    }

    private fun displayFragment(fragment: Fragment) {   // fragment change
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)    // get fragment
            .commit()
    }
}