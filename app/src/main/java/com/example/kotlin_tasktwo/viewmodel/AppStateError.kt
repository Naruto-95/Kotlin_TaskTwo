package com.example.kotlin_tasktwo.viewmodel


sealed class AppStateError{


    object ErrorCl : AppStateError()
    object ErrorSrv : AppStateError()



}
