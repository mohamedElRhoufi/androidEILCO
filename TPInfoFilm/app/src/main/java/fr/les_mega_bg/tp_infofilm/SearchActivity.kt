package fr.les_mega_bg.tp_infofilm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat

class SearchActivity : AppCompatActivity() {
    var lang : String="fr-FR"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        //Gestion des boutons :
        lang = intent.getSerializableExtra("language").toString()
       // Toast.makeText(this,"la langue est "+lang,Toast.LENGTH_SHORT).show()

        if(lang.equals("en-US")){

        }
        else if(lang.equals("ja-JP")){

        }
        else{
            lang="fr-FR"
        }
        gestionBoutons()
        getSearch()

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
            getSearch()
            gestionBoutons()
            return true
        }
        if (id == R.id.fr) {
            lang="fr-FR"
            getSearch()
            gestionBoutons()
            Toast.makeText(this, "Langue : Français", Toast.LENGTH_SHORT).show()
            return true
        }
        if (id == R.id.jap) {
            lang="ja-JP"
            getSearch()
            gestionBoutons()
            Toast.makeText(this, "用語：　日本", Toast.LENGTH_SHORT).show()
            return true
        }


        return super.onOptionsItemSelected(item)

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

            val intent = Intent(this, UpcomingActivity::class.java)

            intent.putExtra("language", lang)
            startActivity(intent)
            finish()
        }

        val btnSearch = findViewById<Button>(R.id.btnSearch)
        btnSearch.setOnClickListener(){
            Toast.makeText(this, "Vous êtes déjà sur cet écran", Toast.LENGTH_SHORT).show()
        }
        val btnRecherche =findViewById<Button>(R.id.ButtonSearch)
        btnRecherche.setOnClickListener(){
            getSearch()
        }
    }

    fun getSearch(){

        //Initialisation du service
        val filmService = Retrofit.Builder()

            .baseUrl(FilmService.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create<FilmService>(
                FilmService::class.java
            )

        //Appel réseau
        val filmSearch = findViewById<EditText>(R.id.editSearch)

        filmService.search(filmSearch.text.toString(),lang).enqueue(object : Callback<Tabfilms> {

            override fun onResponse(call: Call<Tabfilms>, response: Response<Tabfilms>) {
                response.body()?.let { afficherFilms(it) }?: kotlin.run {
                   // Toast.makeText(this@SearchActivity, "Il n'y a pas de film", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<Tabfilms>, t: Throwable) {
                //Toast.makeText(this@SearchActivity, "Erreur Réseau", Toast.LENGTH_SHORT).show()

            }
        })
    }
    fun afficherFilms(films : Tabfilms){
        // Affichage avec le recycler view
        var listeFilmsSearch = films.results
        var filmListeSearch : MutableList<Film> = ArrayList<Film>()
        val rvFilmSearch : RecyclerView = findViewById(R.id.rvFilmsSearch)
        val dateFormat= SimpleDateFormat("yyyy-MM-dd")
        // val dateToday = dateFormat.format(Date())
        for(film in listeFilmsSearch){
            filmListeSearch.add(film)

        }

        // création de l'adapter :
        val adapter : FilmAdapter = FilmAdapter(listeFilmsSearch)

        // On notifie au RV notre adapter
        rvFilmSearch.setAdapter(adapter)
        rvFilmSearch.setLayoutManager(GridLayoutManager(this,2))
    }

    private fun launchDescScreen(context: Context, film:Film){
        val intent = Intent(context, DescriptionActivity::class.java)
        intent.putExtra("EXTRA_FILM", film)
        context.startActivity(intent)



    }




}