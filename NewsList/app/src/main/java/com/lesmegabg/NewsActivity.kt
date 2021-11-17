package com.lesmegabg

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class NewsActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        setTitle(getLocalClassName())


        //récupération de données
        if(intent.hasExtra("login")){
            val login = intent.getStringExtra("login")
            var output = findViewById<TextView>(R.id.txtNews2)
            output.setText("Bonjour " + login)
        }





        val btn=findViewById<Button>(R.id.btnNews)
        btn.setOnClickListener(){
            val url="https://news.google.com/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)

        }



        val btnDetail=findViewById<Button>(R.id.btngoToDetails)
        btnDetail.setOnClickListener(){
            val intent = Intent(this,DetailsActivity::class.java)

            startActivity(intent)

        }


    }
}
