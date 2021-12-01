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



        /*
               GESTION COMPTE GITHUB
         */
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

        /*
               GESTION REPO GITHUB
         */
        val btnRecherche2 = findViewById<Button>(R.id.btnRechercher2)
        btnRecherche2.setOnClickListener{
            // récupération de la valeur entrée dans l'edittext :
            val editText2 = findViewById<EditText>(R.id.edittxt2)
            val entree2 = editText2.getText().toString()
            if(entree2!=null){ // si il y a quelque chose d'écrit, on essaie :
                // on envoie l'entrée dans l'autre activité :
                Toast.makeText(this, "Recherche pour " + entree2 + " en cours.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, RepoActivity::class.java)
                intent.putExtra("entree", entree2) // le nom de l'utilisateur entré
                startActivity(intent)
                finish() // on termine l'activité
            }
        }

    }
}