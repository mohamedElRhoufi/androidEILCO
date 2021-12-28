package fr.les_mega_bg.tp_infofilm

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DescriptionActivity : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)
        setTitle(getLocalClassName())


        filmDetails()

}
    fun filmDetails(){
        val film = intent.getSerializableExtra("EXTRA_FILM") as? Film
    if(film != null) {
        val titre = findViewById<TextView>(R.id.affichageTitre)
        val date = findViewById<TextView>(R.id.affichageDate)
        val image = findViewById<ImageView>(R.id.affichageImage)
        val description = findViewById<TextView>(R.id.affichageDescription)

        titre.setText(film.original_title.toString())
        date.setText(film.release_date.toString())
        Glide.with(image).load("https://image.tmdb.org/t/p/w500" + film.poster_path).fitCenter()
            .into(image)
        description.setText(film.overview.toString())

    }

    }
}