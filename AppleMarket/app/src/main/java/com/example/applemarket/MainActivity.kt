package com.example.applemarket

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.applemarket.adapter.MainProductListAdapter
import com.example.applemarket.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    private val listAdapter by lazy { MainProductListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() = with(binding) {
        val decoration = DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
        productListView.adapter = listAdapter
        productListView.addItemDecoration(decoration)
    }

}