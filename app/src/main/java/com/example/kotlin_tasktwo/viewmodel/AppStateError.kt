package com.example.kotlin_tasktwo.viewmodel


sealed class AppStateError{


    data class ErrorCl(val error: Throwable) : AppStateError()
    data class ErrorSrv(val error: Throwable) : AppStateError()





}
