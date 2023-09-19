package com.example.kakaobankfirsthalfassignments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kakaobankfirsthalfassignments.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}