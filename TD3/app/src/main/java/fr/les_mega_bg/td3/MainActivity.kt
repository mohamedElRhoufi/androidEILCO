package fr.les_mega_bg.td3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //btn
        val rollButton = findViewById<Button>(R.id.btnDice)
        rollButton.setOnClickListener{
            this.onClickButtonLanceDe()
        }
    }

    /*
        Fonction exécutée lors du clic sur le bouton
     */
    private fun onClickButtonLanceDe(){
        val random = (1..6).random() // avoir un nbre aléatoire entre 1 et 6
        val random2 = (1..6).random()

        val txtView = findViewById<TextView>(R.id.text) // On récupère les textviews
        val txtView2 = findViewById<TextView>(R.id.text2)

        txtView.setText(random.toString()) // On affecte les valeurs
        txtView2.setText(random2.toString())
    }
}