package com.example.screen.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.screen.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var registerBinding: ActivityRegisterBinding
    private lateinit var registerMVVM: RegisterMVVM

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        registerMVVM =ViewModelProvider(this)[RegisterMVVM::class.java]

        val  view= registerBinding.root
        setContentView(view)
        registerMVVM.errorMsg.observe(this){msg->
            showErrorMsg(msg)
        }

        registerMVVM.registerSuccess.observe(this){
            onBackPressedDispatcher.onBackPressed()
        }

        registerBinding.registerCreatebutton.setOnClickListener {
            val name= registerBinding.registerNameText.text.toString()
            val lastname= registerBinding.registerLastnameText.text.toString()
            val email= registerBinding.registerEmailText.text.toString()
            val user= registerBinding.registerUserText.text.toString()
            val password= registerBinding.registerPasswordText.text.toString()
            val repassword= registerBinding.registerRepasswordText.text.toString()
            val age =registerBinding.registerAgeText.text.toString()


            var gender="Male"
            if(registerBinding.registerMaleRadioButton.isClickable){
                 gender= "Male"
            }
            else if(registerBinding.registerFemaleRadioButton.isClickable){
                 gender ="Female"
            }

            registerMVVM.validatefield(name,lastname,email,user,password,repassword,age,gender)
            }
    }

    private fun showErrorMsg(msg: String?) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()

    }


}