package fr.les_mega_bg.td6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class FormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)


        // quand un utilisateur clique sur le bouton :
        val btnRecherche = findViewById<Button>(R.id.btnRechercher)
        btnRecherche.setOnClickListener{
            // récupération de la valeur entrée dans l'edittext :
            val editText = findViewById<EditText>(R.id.edittxt)
            val entree = editText.getText().toString()
            if(entree!=null){ // si il y a quelque chose d'écrit, on essaie :
                // on envoie l'entrée dans l'autre activité :
                Toast.makeText(this, "Recherche pour " + entree + " en cours.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("entree", entree) // le nom de l'utilisateur entré
                startActivity(intent)
                finish() // on termine l'activité
            }
        }


    }
}