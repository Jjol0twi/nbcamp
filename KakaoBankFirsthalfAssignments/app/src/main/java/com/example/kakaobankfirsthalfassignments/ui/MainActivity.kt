package com.example.kakaobankfirsthalfassignments.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.kakaobankfirsthalfassignments.R
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
        displayFragment(SearchResultsFragment())

        changeFragmentButton.setOnClickListener {
            when (supportFragmentManager.findFragmentById(binding.fragmentContainer.id)) {
                is SearchResultsFragment -> {
                    displayFragment(MyStorageFragment())
                    changeFragmentButton.setImageResource(R.drawable.icon_dashboard)
                }

                is MyStorageFragment -> {
                    displayFragment(SearchResultsFragment())
                    changeFragmentButton.setImageResource(R.drawable.icon_home)
                }
            }
        }
    }

    private fun displayFragment(fragment: Fragment) {   // fragment change
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)    // get fragment
            .commit()
    }
}