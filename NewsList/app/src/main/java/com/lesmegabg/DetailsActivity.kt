package com.lesmegabg

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class DetailsActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setTitle(getLocalClassName());
        val login = (applicationContext as NewsListApplication).login
        var user=findViewById<TextView>(R.id.TextDetails)
        user.setText("Bonjour " + login)



        val btn=findViewById<Button>(R.id.btngoToNews)

        btn.setOnClickListener(){
            val intent = Intent(this,NewsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }



}
