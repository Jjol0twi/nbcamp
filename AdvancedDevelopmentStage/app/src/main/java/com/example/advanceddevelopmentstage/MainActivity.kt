package com.example.advanceddevelopmentstage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.advanceddevelopmentstage.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}