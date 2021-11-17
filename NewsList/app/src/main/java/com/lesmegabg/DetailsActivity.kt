package com.lesmegabg

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class DetailsActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setTitle(getLocalClassName());
        val login = (applicationContext as NewsListApplication).login
        var user=findViewById<TextView>(R.id.TextDetails)
        user.setText("Bonjour " + login)
    }



}
