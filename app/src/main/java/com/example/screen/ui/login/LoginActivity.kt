package com.example.screen.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.screen.databinding.ActivityLoginBinding
import com.example.screen.ui.register.RegisterActivity
import com.example.screen.ui.screenmain.ScreenMainActivity
import com.example.screen.ui.main.MainActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var loginBinding: ActivityLoginBinding


    private  lateinit var loginMVVM: LoginMVVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)

        loginMVVM=ViewModelProvider(this)[LoginMVVM::class.java]


        val view =loginBinding.root
        setContentView(view)

        loginMVVM.errorMsg.observe(this){msg->
            showErrorMsg(msg)
        }
        loginMVVM.registerSuccess.observe(this){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        loginBinding.loginEnterButton.setOnClickListener {

            val email =loginBinding.loginUserText.text.toString()
            val password = loginBinding.loginPasswordText.text.toString()
            loginMVVM.validateFields(email,password)

        }

        loginBinding.LoginCreateButton.setOnClickListener {
            var intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showErrorMsg(msg: String?) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
    }
}