package fr.les_mega_bg.tp_infofilm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class UpcomingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upcoming)
        //Gestion des boutons :
        getLesFilmsUpcoming()
        gestionBoutons()
    }


    fun getLesFilmsUpcoming(){

        //Initialisation du service
        val filmService = Retrofit.Builder()

            .baseUrl(FilmService.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create<FilmService>(
                FilmService::class.java
            )

        //Appel réseau
        filmService.listUpcomingFilms().enqueue(object : Callback<Tabfilms> {

            override fun onResponse(call: Call<Tabfilms>, response: Response<Tabfilms>) {
                response.body()?.let { afficherUpcomingFilms(it) }?: kotlin.run {
                    Toast.makeText(this@UpcomingActivity, "Il n'y a pas de film", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<Tabfilms>, t: Throwable) {
                Toast.makeText(this@UpcomingActivity, "Erreur Réseau", Toast.LENGTH_SHORT).show()

            }
        })
    }

    //Affiche les données dans le recyclerview
    fun afficherUpcomingFilms(films : Tabfilms){
        // Affichage avec le recycler view
        var listeFilmsUpcoming = films.results
        var filmListeUpcoming : MutableList<Film> = ArrayList<Film>()
        val rvFilmUpcoming : RecyclerView = findViewById(R.id.rvFilmsUpcoming)
        val dateFormat= SimpleDateFormat("yyyy-MM-dd")
       // val dateToday = dateFormat.format(Date())
        for(film in listeFilmsUpcoming){ val date = dateFormat.parse(film.release_date)
                    filmListeUpcoming.add(film)

        }

        // création de l'adapter :
        val adapter : FilmAdapter = FilmAdapter(listeFilmsUpcoming)

        // On notifie au RV notre adapter
        rvFilmUpcoming.setAdapter(adapter)
        rvFilmUpcoming.setLayoutManager(GridLayoutManager(this,2))
    }

    // gestion des boutons navbar
    fun gestionBoutons() {
        val btnPopular = findViewById<Button>(R.id.btnPopular)
        btnPopular.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val btnUpcoming = findViewById<Button>(R.id.btnUpcoming)
        btnUpcoming.setOnClickListener(){
            Toast.makeText(this, "Vous êtes déjà sur cet écran", Toast.LENGTH_SHORT).show()
        }
    }
}