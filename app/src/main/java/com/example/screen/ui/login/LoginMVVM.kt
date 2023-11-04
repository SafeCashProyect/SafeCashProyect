package com.example.screen.ui.login
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.screen.data.ResourceRemote
import com.example.screen.data.UserRepository
import emailValidator
import kotlinx.coroutines.launch

class LoginMVVM: ViewModel() {
    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg : LiveData<String?> =_errorMsg

    private  val _registerSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val registerSuccess:LiveData<Boolean> =_registerSuccess

    private  val userRepository = UserRepository()

    fun validateFields(email: String, password: String) {
        if(email.isEmpty()|| password.isEmpty()){
            _errorMsg.value ="Debe llenar todos los campos"
        }else{
            if (password.length<6){ _errorMsg.value="La contraseña debe tener minimo 6 caracteres"}
            else {
                if (!emailValidator(email)) { _errorMsg.value = "Por favor ingresa un correo valido"
                } else {
                    viewModelScope.launch {
                        val result = userRepository.loginUser(email, password)
                        result.let { resourceRemote ->
                            when (resourceRemote) {
                                is ResourceRemote.Success ->{
                                    _registerSuccess.postValue(true)
                                    _errorMsg.postValue("Bienvenido")
                                }


                                is ResourceRemote.Error -> {
                                    var msg = result.message
                                    when (msg) {
                                        "The email address is already in use by another account." -> msg ="El correo ya está registrado"

                                        "A network error (such as timeout,interrupted connection or unreachable host) has accurred." -> msg =
                                            "Revisa su conection a internet"
                                        "An internal error has occurred. [ INVALID_LOGIN_CREDENTIALS ]" -> msg="Correo o contraseña equivocada"
                                    }
                                    _errorMsg.postValue(msg)
                                }
                                else -> {
                                    //dont use
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}