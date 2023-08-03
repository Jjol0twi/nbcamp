package com.example.spartalogin

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class HomeActivity : ComponentActivity() {
    private val homeFinishButton : Button  by lazy { findViewById(R.id.home_finish) }
    private val homeNameText : TextView  by lazy { findViewById(R.id.home_user_name) }
    private val homeIdText : TextView  by lazy { findViewById(R.id.home_user_id) }
    private val homeAgeText : TextView  by lazy { findViewById(R.id.home_user_age) }
    private val homeMbtiText : TextView  by lazy { findViewById(R.id.home_user_mbti) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val idIntent : String =intent.getStringExtra("user_id").toString()
        homeFinishButton.setOnClickListener { onButtonClickEvent(it) }
        homeIdText.text= "아이디 : $idIntent"
        homeNameText.text = "이름 : 와우"
        homeAgeText.text = "나이 : 3살"
        homeMbtiText.text = "MBTI : BABY-T"
    }

    private fun onButtonClickEvent(view: View) {
        when (view.id){
            homeFinishButton.id->{
                finish()
            }
        }
    }
}