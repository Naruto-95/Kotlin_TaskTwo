package com.example.kotlin_tasktwo.Repository

import com.example.kotlin_tasktwo.viewmodel.AppStateError

fun interface OnErrorListener {


    fun onError(appStateError:AppStateError)

}