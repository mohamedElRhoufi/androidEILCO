package fr.les_mega_bg.tp_infofilm

import android.content.Context
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
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {
    var lang : String="fr-FR"


    override fun onCreate(savedInstanceState: Bundle?,) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lang = intent.getSerializableExtra("language").toString()
        if(lang.equals("en-US")){
            getLesFilms()

        }
        else if(lang.equals("ja-JP")){
            getLesFilms()
        }
        else{
            lang="fr-FR"
            getLesFilms()
        }

        gestionBoutons(this)

       val btn = findViewById<Button>(R.id.ButtonDesc)
     if(btn!=null){ btn.setOnClickListener() {
     //Toast.makeText(this, "ceci est un film", Toast.LENGTH_SHORT).show()
       }}
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
            Toast.makeText(this, "Language : English", Toast.LENGTH_SHORT).show()
            getLesFilms()
            gestionBoutons(this)
            return true
        }
        if (id == R.id.fr) {
            lang="fr-FR"
            getLesFilms()
            gestionBoutons(this)
            Toast.makeText(this, "Langue : Français", Toast.LENGTH_SHORT).show()
            return true
        }
        if (id == R.id.jap) {
            lang="ja-JP"
            getLesFilms()
            gestionBoutons(this)
            Toast.makeText(this, "用語：　日本", Toast.LENGTH_SHORT).show()
            return true
        }


        return super.onOptionsItemSelected(item)

    }


    // Fonction qui va charger les données avec le réseau et l'API
    fun getLesFilms(){

        //Initialisation du service


        var filmService = Retrofit.Builder()


            .baseUrl(FilmService.ENDPOINT)

            .addConverterFactory(GsonConverterFactory.create())
            .build()

            .create<FilmService>(
                FilmService::class.java

            )

        //Appel réseau

        filmService.listPopularFilms(lang).enqueue(object :Callback<Tabfilms>{

            override fun onResponse(call: Call<Tabfilms>, response: Response<Tabfilms>) {
                response.body()?.let { afficherPopularFilms(it) }?: kotlin.run {
                   // Toast.makeText(this@MainActivity, "Il n'y a pas de film", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<Tabfilms>, t: Throwable) {
              //  Toast.makeText(this@MainActivity, "Erreur Réseau", Toast.LENGTH_SHORT).show()

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
    fun gestionBoutons(context:Context) {
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
            Toast.makeText(this, "Vous êtes déjà sur cet écran", Toast.LENGTH_SHORT).show()
        }

        val btnUpcoming = findViewById<Button>(R.id.btnUpcoming)
        btnUpcoming.setOnClickListener(){

            val intent = Intent(context, UpcomingActivity::class.java)

            intent.putExtra("language", lang)
            startActivity(intent)
            finish()
        }
       val btnSearch = findViewById<Button>(R.id.btnSearch)
        btnSearch.setOnClickListener(){

            val intent = Intent(context, SearchActivity::class.java)

            intent.putExtra("language", lang)
            startActivity(intent)
            finish()
        }

    }



}