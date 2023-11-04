package com.example.screen.ui.register

import android.util.Log
import androidx.lifecycle.ViewModel

import androidx.lifecycle.LiveData

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.screen.data.ResourceRemote
import com.example.screen.data.UserRepository
import com.example.screen.data.model.User
import emailValidator
import kotlinx.coroutines.launch


class RegisterMVVM:ViewModel() {

    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    private  val userRepository = UserRepository()
    private  val _registerSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val registerSuccess:LiveData<Boolean> =_registerSuccess
    val errorMsg : LiveData<String?> =_errorMsg
    fun validatefield(name: String, lastname: String, email: String, user: String, password: String, repassword: String,age:String,gender:String) {
        if(name.isEmpty()||lastname.isEmpty()||email.isEmpty()||user.isEmpty()||password.isEmpty()||repassword.isEmpty()||age.isEmpty()){
           _errorMsg.value ="Debe llenar todos los campos"
        }else{
            if(password != repassword){ _errorMsg.value="Las contraseñas no son iguales"}
            else{
                if (password.length<6){ _errorMsg.value="La contraseña debe tener minimo 6 caracteres"}
                else{
                    if (!emailValidator(email)){ _errorMsg.value="Por favor ingresa un correo valido"
                    }else{
                        viewModelScope.launch{
                            val result =userRepository.registerUser(email,password)
                            result.let { resourceRemote ->
                                when(resourceRemote){
                                    is ResourceRemote.Success-> {
                                        var uid =result.data
                                        uid?.let { Log.d("uid User",it) }
                                        val user= User(uid,name,lastname,email,user,age,gender)
                                        createUser(user)

                                    }
                                    is ResourceRemote.Error ->{
                                        var msg =result.message
                                        when(msg){
                                            "The email address is already in use by another account."-> msg="El correo ya está registrado"
                                            "A network error (such as timeout,interrupted connection or unreachable host) has accurred." ->msg="Revisa su conection a internet"
                                        }
                                        _errorMsg.postValue(msg)
                                    }
                                    else ->{//dont use
                                     }
                                }
                            }
                        }

                    }
                }
            }
        }

    }

    private fun createUser(user: User) {
        viewModelScope.launch {
            var result =userRepository.createUser(user)
            result.let { resourceRemote ->
                when(resourceRemote){
                    is ResourceRemote.Success->{
                        _registerSuccess.postValue(true)
                        _errorMsg.postValue("Usuario creado exitosamente")

                    }
                    is ResourceRemote.Success->{
                        var msg= result.message
                        _errorMsg.postValue(msg)
                    }
                    else ->{}
                }
            }
        }

    }

}