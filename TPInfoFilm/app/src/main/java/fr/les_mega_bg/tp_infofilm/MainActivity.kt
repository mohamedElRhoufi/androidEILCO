package fr.les_mega_bg.tp_infofilm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Console

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getLesFilms()
        gestionBoutons()

       val btn = findViewById<Button>(R.id.ButtonDesc)
     if(btn!=null){ btn.setOnClickListener() {
     //Toast.makeText(this, "ceci est un film", Toast.LENGTH_SHORT).show()
       }}
    }

    // Fonction qui va charger les données avec le réseau et l'API
    fun getLesFilms(){

        //Initialisation du service
        val filmService = Retrofit.Builder()

            .baseUrl(FilmService.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create<FilmService>(
                FilmService::class.java
            )

        //Appel réseau
        filmService.listPopularFilms().enqueue(object :Callback<Tabfilms>{

            override fun onResponse(call: Call<Tabfilms>, response: Response<Tabfilms>) {
                response.body()?.let { afficherPopularFilms(it) }?: kotlin.run {
                    Toast.makeText(this@MainActivity, "Il n'y a pas de film", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<Tabfilms>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Erreur Réseau", Toast.LENGTH_SHORT).show()

            }
        })
    }

    //Affiche les données dans le recyclerview
    fun afficherPopularFilms(films : Tabfilms){
        // Affichage avec le recycler view
        var listeFilms = films.results
        var filmListe : MutableList<Film> = ArrayList<Film>()
        val rvFilm : RecyclerView = findViewById(R.id.rvFilms)

        for(film in listeFilms){ filmListe.add(film)}

        // création de l'adapter :
        val adapter : FilmAdapter = FilmAdapter(listeFilms)
        // On notifie au RV notre adapter
        rvFilm.setAdapter(adapter)
        rvFilm.setLayoutManager(GridLayoutManager(this,2))
    }

    // gestion des boutons navbar
    fun gestionBoutons() {
        val btnPopular = findViewById<Button>(R.id.btnPopular)
        btnPopular.setOnClickListener(){
            Toast.makeText(this, "Vous êtes déjà sur cet écran", Toast.LENGTH_SHORT).show()
        }

        val btnUpcoming = findViewById<Button>(R.id.btnUpcoming)
        btnUpcoming.setOnClickListener(){
            val intent = Intent(this, UpcomingActivity::class.java)
            startActivity(intent)
            finish()
        }
    }



   /** fun description(){
        var linearFilm = findViewById<LinearLayout>(R.id.donneeFilm)
        if(linearFilm!=null) {
            linearFilm.setOnClickListener() {
                Toast.makeText(this, "ceci est un film", Toast.LENGTH_SHORT).show()
            }
        }


    }**/
}