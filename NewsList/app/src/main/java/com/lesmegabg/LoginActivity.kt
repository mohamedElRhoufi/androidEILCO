package com.lesmegabg

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class LoginActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val login = (applicationContext as NewsListApplication).login
        setTitle(getLocalClassName())

        val btn=findViewById<Button>(R.id.btnLog)

        btn.setOnClickListener(){
            val editText = findViewById<EditText>(R.id.inputText)

            // Utilisation du contexte d'application
            val app = (applicationContext as NewsListApplication)
            app.login = editText.getText().toString();

            // Utilisation des extras d'intents
            val intent = Intent(this,NewsActivity::class.java)
            intent.putExtra("login",editText.text.toString())
            
            startActivity(intent)
            finish()
        }


    }






}
