package fr.les_mega_bg.tp_infofilm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
    var lang : String="fr-FR"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upcoming)
        //Gestion des boutons :
        lang = intent.getSerializableExtra("language").toString()
        if(lang.equals("en-US")){
            getLesFilmsUpcoming()

        }
        else if(lang.equals("ja-JP")){
            getLesFilmsUpcoming()
        }
            else{
                lang="fr-FR"
            getLesFilmsUpcoming()
        }
        gestionBoutons()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.

        val id = item.getItemId()

        if (id == R.id.en) {

            lang="en-US"
            getLesFilmsUpcoming()
            gestionBoutons()
            Toast.makeText(this, "Language : English", Toast.LENGTH_SHORT).show()

            return true
        }
        if (id == R.id.fr) {
            lang="fr-FR"
            getLesFilmsUpcoming()
            gestionBoutons()
            Toast.makeText(this, "Langue : Français", Toast.LENGTH_SHORT).show()
            return true
        }
        if (id == R.id.jap) {
            lang="ja-JP"
            getLesFilmsUpcoming()
            gestionBoutons()
            Toast.makeText(this, "用語：　日本", Toast.LENGTH_SHORT).show()
            return true
        }


        return super.onOptionsItemSelected(item)

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
        filmService.listUpcomingFilms(lang).enqueue(object : Callback<Tabfilms> {

            override fun onResponse(call: Call<Tabfilms>, response: Response<Tabfilms>) {
                response.body()?.let { afficherUpcomingFilms(it) }?: kotlin.run {
                 //   Toast.makeText(this@UpcomingActivity, "Il n'y a pas de film", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<Tabfilms>, t: Throwable) {
               // Toast.makeText(this@UpcomingActivity, "Erreur Réseau", Toast.LENGTH_SHORT).show()

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
        if(lang.equals("en-US")){
            findViewById<Button>(R.id.btnPopular).setText("Popular")
            findViewById<Button>(R.id.btnUpcoming).setText("Upcoming")
            findViewById<Button>(R.id.btnSearch).setText("Search")
        }
        else if(lang.equals("ja-JP")){
            findViewById<Button>(R.id.btnPopular).setText("人気")
            findViewById<Button>(R.id.btnUpcoming).setText("今後")
            findViewById<Button>(R.id.btnSearch).setText("検索")

        }
        else{
            findViewById<Button>(R.id.btnPopular).setText("Populaire")
            findViewById<Button>(R.id.btnUpcoming).setText("Bientot")
            findViewById<Button>(R.id.btnSearch).setText("Recherche")
        }
        val btnPopular = findViewById<Button>(R.id.btnPopular)
        btnPopular.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("language", lang)
            startActivity(intent)
            finish()
        }

        val btnUpcoming = findViewById<Button>(R.id.btnUpcoming)
        btnUpcoming.setOnClickListener(){
            Toast.makeText(this, "Vous êtes déjà sur cet écran", Toast.LENGTH_SHORT).show()
        }

       val btnSearch = findViewById<Button>(R.id.btnSearch)
        btnSearch.setOnClickListener(){

            val intent = Intent(this, SearchActivity::class.java)

            intent.putExtra("language", lang)
            startActivity(intent)
            finish()
        }
    }
}