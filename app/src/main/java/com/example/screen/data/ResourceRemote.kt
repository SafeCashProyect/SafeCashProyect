package com.example.screen.data

sealed class ResourceRemote<T>(
    var data:T?= null,
    var message: String? =null,
    val status: Status? =null,
    val errorcode: Int?=null
){
    class Success<T>(data:T): ResourceRemote<T>(data =data, status = Status.Success)
    class Loading<T>(message: String= ""): ResourceRemote<T>(message =message, status = Status.Loading)
    class Error<T>(errorcode: Int?=null, message: String?=null): ResourceRemote<T>(errorcode =errorcode, message = message, status =Status.Error)
}

enum class Status {
    Success{
        override fun toString(): String {
            return this.name
        }
    },
    Error{
        override fun toString(): String {
            return this.name
        }
    },
    Loading{
        override fun toString(): String {
            return this.name
        }
    }
}