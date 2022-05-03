package com.example.kotlin_tasktwo.repository

import com.example.kotlin_tasktwo.viewmodel.AppStateError

fun interface OnErrorListener {


    fun onError(appStateError:AppStateError)

}