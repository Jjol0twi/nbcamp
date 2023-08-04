package com.example.spartalogin

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.core.graphics.drawable.toDrawable
import kotlin.random.Random

class HomeActivity : ComponentActivity() {
    private val homeFinishButton: Button by lazy { findViewById(R.id.home_finish) }
    private val homeNameText: TextView by lazy { findViewById(R.id.home_user_name) }
    private val homeIdText: TextView by lazy { findViewById(R.id.home_user_id) }
    private val homeAgeText: TextView by lazy { findViewById(R.id.home_user_age) }
    private val homeMbtiText: TextView by lazy { findViewById(R.id.home_user_mbti) }
    private val homeImg: ImageView by lazy { findViewById(R.id.home_user_img) }

    private val idIntent: String by lazy {
        intent.getStringExtra("user_id").toString()
    } // singin에서 넘겨주는 id 받기
    private var homeImageRandom = ((1..99).random() % 5)    // 그냥 랜덤 변수 5개 0~4 입력
    private val homeImageList: ArrayList<Int> = arrayListOf(
        // 사진을 list로 만들어서 사용
        R.drawable.newjeans_bunny2,
        R.drawable.newjeans_bunny3,
        R.drawable.newjeans_bunny4,
        R.drawable.newjeans_bunny5,
        R.drawable.newjeans_bunny6,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        homeFinishButton.setOnClickListener { onButtonClickEvent(it) }
        homeIdText.text = "아이디 : $idIntent" // 원래 이것도 변수로 만들고 다른 fun에서 구현해야되는데
        homeNameText.text = "이름 : 와우"
        homeAgeText.text = "나이 : 3살"
        homeMbtiText.text = "MBTI : BABY-T"
        homeImg.setImageResource(homeImageList[homeImageRandom])    // random변수를 index로 해서 무작위 그림 넣기
    }

    private fun onButtonClickEvent(view: View) {
        when (view.id) {
            homeFinishButton.id -> {
                finish()    // startActivity로 호출했을 때 끝내는 방법
            }
        }
    }
}