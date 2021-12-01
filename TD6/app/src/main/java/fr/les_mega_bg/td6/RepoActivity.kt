package fr.les_mega_bg.td6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)


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
            val affichageNom = findViewById<TextView>(R.id.searchText)
            affichageNom.setText("Recherche : " + entree)

            val githubService = Retrofit.Builder()
            .baseUrl(GitHubService.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create<GitHubService>(
                GitHubService::class.java
            )

        githubService.searchRepos(entree.toString()).enqueue(object : Callback<Repos> {
            override fun onResponse(
                call: Call<Repos>,
                response: Response<Repos>
            ) {
                afficherRepos(response.body());
            }

            override fun onFailure(call: Call<Repos>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }


    }

    // Fonction qui affiche les repos avec un recyclerview
    fun afficherRepos(repos: Repos?) {
        if (repos != null) {
            //Toast.makeText(this, "Nombre de dépots : " + repos.size, Toast.LENGTH_SHORT).show()
            var reposListe  :MutableList<Repo> = ArrayList<Repo>()
            val rvRepo : RecyclerView = findViewById(R.id.rvRepo)
            for (repo in repos.items){reposListe.add(repo)}

            //Création d'un adapter avec initialisation du constructeur avec notre liste de contacts
            val adapter :RepoAdapter= RepoAdapter(repos.items)
            //On notifie au recyclerView notre adapter
            rvRepo.setAdapter(adapter)
            //On déclare quel type de LayoutMangare on désire
            rvRepo.setLayoutManager(LinearLayoutManager(this))


        }
    }
}

