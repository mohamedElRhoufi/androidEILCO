package com.lesmegabg
import android.app.Application
class NewsListApplication : Application() {

    var login :String = "";

    override fun onCreate(){
    super.onCreate()
        this.login=""
    }



}