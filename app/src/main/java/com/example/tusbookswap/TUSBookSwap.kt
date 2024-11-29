package com.example.tusbookswap

import android.app.Application
import com.google.firebase.FirebaseApp

class TUSBookSwap: Application() {

    //initialize firebase

    override fun onCreate(){
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}