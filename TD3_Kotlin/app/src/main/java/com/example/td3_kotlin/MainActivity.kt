package com.example.td3_kotlin

import android.app.Activity
import android.os.Bundle
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.text.Editable



class MainActivity : Activity() {

    // Nombre de faces du dé
    var nbFaces = 6;

    // Méthode de création de l'activité
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Gestion du bouton de lancé de dé
        val rollButton = findViewById<Button>(R.id.btnDice)
        rollButton.setOnClickListener{
            this.onClickButtonLanceDe() // lance la méthode
        }

        // Gestion du onchange sur l'edittext
        val edittxt = findViewById<EditText>(R.id.inputText);
        val outputTxt = findViewById<TextView>(R.id.txtFaces); // affichage sortie
        outputTxt.setText("Nombre de faces : "+ nbFaces)
        edittxt.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int,before: Int, count: Int) {
                if(s.toString()!=""){ // Si il y a une valeur dans l'edittext
                    nbFaces = Integer.parseInt(s.toString());
                    outputTxt.setText("Nombre de faces : "+ s)
                }
            }
        })


    }

    /*
        Fonction exécutée lors du clic sur le bouton
     */
    private fun onClickButtonLanceDe(){
        val random = (1..nbFaces).random() // avoir un nbre aléatoire entre 1 et nbFaces
        val random2 = (1..nbFaces).random()

        val txtView = findViewById<TextView>(R.id.text) // On récupère les textviews
        val txtView2 = findViewById<TextView>(R.id.text2)

        txtView.setText(random.toString()) // On affecte les valeurs
        txtView2.setText(random2.toString())
    }


}