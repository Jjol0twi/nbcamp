package com.example.spartalogin

import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.ui.graphics.Color

class SignUpActivity : ComponentActivity() {
    private val signUpFinishButton: Button by lazy { findViewById(R.id.signUp_finish) }
    private val signUpIdEdit: EditText by lazy { findViewById(R.id.signUp_id_edit) }
    private val signUpNameEdit: EditText by lazy { findViewById(R.id.signUp_name_edit) }
    private val signUpPWEdit: EditText by lazy { findViewById(R.id.signUp_password_edit) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        setEditTextBackgroundTint(signUpNameEdit)
        setEditTextBackgroundTint(signUpIdEdit)
        setEditTextBackgroundTint(signUpPWEdit)
        signUpFinishButton.setOnClickListener { onButtonClickEvent(it) }
    }

    private fun onButtonClickEvent(view: View) {
        when (view.id) {
            signUpFinishButton.id -> {
//                if (nameEdit.text.isNullOrBlank() || idEdit.text.isNullOrBlank() || passwordEdit.text.isNullOrBlank()) Toast.makeText(
//                    this,
//                    "입력되지 않은 정보가 있습니다.",
//                    Toast.LENGTH_SHORT
//                ).show()
                if (signUpNameEdit.text.isNullOrBlank()) {  // name edittext가 비였을 경우
                    Toast.makeText(
                        this,
                        "사용자 이름 정보가 입력되지 않았습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                    signUpNameEdit.requestFocus()   // 입력 요구하면서 마우스? foucs 가져다주기
                    return  // 없으면 아래 코드 실행됨
                }
                if (signUpIdEdit.text.isNullOrBlank()) {    // ID edittext가 비였을 경우
                    Toast.makeText(
                        this,
                        "아이디 정보가 입력되지 않았습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                    signUpIdEdit.requestFocus() // 입력 요구하면서 마우스? foucs 가져다주기
                    return
                }
                if (signUpPWEdit.text.isNullOrBlank()) {    // pw edittext가 비였을 경우
                    Toast.makeText(
                        this,
                        "비밀번호 정보가 입력되지 않았습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                    signUpPWEdit.requestFocus() // 입력 요구하면서 마우스? foucs 가져다주기
                    return
                }
                val callbackSignIn = Intent()   // activity를 명시적으로 지정해줄 필요가 없어요
                callbackSignIn.putExtra("user_id", signUpIdEdit.text.toString())    // string 요구하여 string으로 자료형 변환
                callbackSignIn.putExtra("user_pw", signUpPWEdit.text.toString())
                setResult(Activity.RESULT_OK, callbackSignIn)   // result_ok로 받는 activity에게 신호를 주고 해당 intent 넘기기
                finish()
            }
        }
    }

    private fun setEditTextBackgroundTint(editText: EditText) { // click 했을 때 balck, click하지 않았을 때 gray
        val color = if (editText.isFocused) Color.Black.hashCode() else Color.Gray.hashCode()
        editText.backgroundTintList = ColorStateList.valueOf(color)
    }
}