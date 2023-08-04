package com.example.spartalogin

import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.graphics.Color

class SignInActivity : ComponentActivity() {
    /* ui 요소 선언 class 변수로 넣기 위해서 위에서 선언
    *  근데 onCreate 안에서 연결해줘야지 오류가 안 뜬다.
    *  ui가 만들어지기 전에 연결하면 오류가 떠서 앱이 아에 뜨지 않는다
    *  근데 class 변수로 만들기 위해서 나중에 값을 넣어주는 lateint or lazy 사용 */
    private val signInButton: Button by lazy { findViewById(R.id.signIn_button) }
    private val signUpButton: Button by lazy { findViewById(R.id.signUp_button) }
    private val signInIdEdit: EditText by lazy { findViewById(R.id.signIn_id) }
    private val signInPWEdit: EditText by lazy { findViewById(R.id.signIn_password) }
    private lateinit var callbakcSignUp: ActivityResultLauncher<Intent> // registerForActivityResult 사용하기 위해 ActivityResultLauncher 선언
    //    private lateinit var signInButton: Button
//    private lateinit var signUpButton: Button
//    private lateinit var signInIdEdit: EditText
//    private lateinit var signInPWEdit: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
//        signInButton = findViewById(R.id.signIn_button)   // lateinit을 사용했을 경우 사용되는 초기화
//        signUpButton = findViewById(R.id.signUp_button)
//        signInIdEdit = findViewById(R.id.signIn_id)
//        signInPWEdit = findViewById(R.id.signIn_password)
        // button click event
        signInButton.setOnClickListener { onButtonClickEvent(it) }
        signUpButton.setOnClickListener { onButtonClickEvent(it) }
        // +@ editText 밑줄 색 원하는 대로 바꾸기
        setEditTextBackgroundTint(signInIdEdit)
        setEditTextBackgroundTint(signInPWEdit)
        getSignupUserInformation()
    }

    private fun setEditTextBackgroundTint(editText: EditText) { // click 했을 때 balck, click하지 않았을 때 gray
        val color = if (editText.isFocused) Color.Black.hashCode() else Color.Gray.hashCode()
        editText.backgroundTintList = ColorStateList.valueOf(color)
    }

    private fun getSignupUserInformation() {
        callbakcSignUp =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val id = result.data?.getStringExtra("user_id")
                    val pw = result.data?.getStringExtra("user_pw")
                    signInIdEdit.setText(id)
                    signInPWEdit.setText(pw)    // edittext의 text와 settext가 요구하는 자료형이 달라요
//                    edittext text settext
//                    (signInIdEdit as TextView).text = id  // edittext가 아닌 textview로 형변화하여 settext
//                    signInIdEdit.text=Editable.Factory.getInstance().newEditable(id)  // text일 경우 editable! 자료형을 요구하여 string->editable로 변환하여 입력
                }
            }
    }

    private fun onButtonClickEvent(view: View) {
        when (view.id) {
            signInButton.id -> {
//                if (signInIdEdit.text.isNullOrBlank() || signInPWEdit.text.isNullOrBlank()) {   // docs가 원하는 처리
//                    Toast.makeText(this, "아이디/비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
//                    signInIdEdit.requestFocus()
//                    return
//                }
                if (signInIdEdit.text.isNullOrBlank()) {    // ID edittext가 비였을 경우
                    Toast.makeText(this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show()
                    signInIdEdit.requestFocus() // 입력 요구하면서 마우스? foucs 가져다주기
                    return  // 없으면 아래 코드 실행됨
                }
                if (signInPWEdit.text.isNullOrBlank()) {    // pw edittext가 비였을 경우
                    Toast.makeText(this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
                    signInPWEdit.requestFocus() // 입력 요구하면서 마우스? foucs 가져다주기
                    return
                }
                val callHome = Intent(this, HomeActivity::class.java)
                Log.d("error", signInIdEdit.text.toString())
                callHome.putExtra("user_id", signInIdEdit.text.toString())
                startActivity(callHome)
            }

            signUpButton.id -> {
                val callSingUp = Intent(this, SignUpActivity::class.java)
                callbakcSignUp.launch(callSingUp)   // registerForActivityResult 기능을 사용하려면 activity를 startacitivty가 아닌 ActivityResultLauncher을 통해서 lauch
            }
        }

    }
}

