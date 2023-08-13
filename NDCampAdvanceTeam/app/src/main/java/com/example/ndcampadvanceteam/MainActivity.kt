package com.example.ndcampadvanceteam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import androidx.viewpager2.widget.ViewPager2
import com.example.ndcampadvanceteam.databinding.MainActivityBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private val binding: MainActivityBinding by lazy { MainActivityBinding.inflate(layoutInflater) }
    private val mainTabLayout: TabLayout by lazy { binding.mainTabLayout }
    private val mainViewPager: ViewPager2 by lazy { binding.mainViewPager }
    private val mainFloatingButton: FloatingActionButton by lazy { binding.mainFloatingButton }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}