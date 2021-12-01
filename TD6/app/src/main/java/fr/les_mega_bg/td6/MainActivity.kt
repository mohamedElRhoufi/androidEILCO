package fr.les_mega_bg.td6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Console
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Gestion du bouton retour :
        val btnRetour = findViewById<Button>(R.id.btnRetour)
        btnRetour.setOnClickListener(){
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
            finish() // on termine l'activité
        }


        // Récupération du nom de l'utilisateur via l'activité FormActivity
        if (intent.hasExtra("entree")) {
            val entree = intent.getStringExtra("entree")
            // affichage du nom de l'utilisateur :
            val affichageNom = findViewById<TextView>(R.id.userText)
            affichageNom.setText("Utilisateur : " + entree)


            val githubService = Retrofit.Builder()
                .baseUrl(GitHubService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create<GitHubService>(
                    GitHubService::class.java
                )

            githubService.listRepos(entree).enqueue(object : Callback<List<Repo>> {
                override fun onResponse(
                    call: Call<List<Repo>>,
                    response: Response<List<Repo>>
                ) {
                    afficherRepos(response.body());
                }

                override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                    //Toast.makeText(this, "Problème", Toast.LENGTH_SHORT).show()
                }

            })
        }



    }

    // Fonction qui affiche les repos avec un recyclerview
    fun afficherRepos(repos: List<Repo>?) {
        if (repos != null) {
            Toast.makeText(this, "Nombre de dépots : " + repos.size, Toast.LENGTH_SHORT).show()
            var reposListe  :MutableList<Repo> = ArrayList<Repo>()
            val rvRepo : RecyclerView = findViewById(R.id.rvRepo)
            for (repo in repos){reposListe.add(repo)}

            //Création d'un adapter avec initialisation du constructeur avec notre liste de contacts
            val adapter :RepoAdapter= RepoAdapter(repos)
            //On notifie au recyclerView notre adapter
            rvRepo.setAdapter(adapter)
            //On déclare quel type de LayoutMangare on désire
            rvRepo.setLayoutManager(LinearLayoutManager(this))


        }
    }


}
