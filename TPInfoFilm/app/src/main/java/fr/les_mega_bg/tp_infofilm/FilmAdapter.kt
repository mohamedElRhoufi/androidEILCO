package fr.les_mega_bg.tp_infofilm

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class FilmAdapter(films : List<Film>) : RecyclerView.Adapter<FilmAdapter.ViewHolder>(){

    val mFilms : List<Film> = films

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val titreTextview : TextView = view.findViewById(R.id.affichageTitre)
        val dateTextview : TextView = view.findViewById(R.id.affichageDate)
        val imageImageView : ImageView = view.findViewById(R.id.affichageImage)
        val btnView : Button = view.findViewById(R.id.ButtonDesc)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val contactView = inflater.inflate(R.layout.layout_item_film, parent, false)

        return ViewHolder(contactView)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val film : Film = mFilms.get(position)
        var titreTextView = holder.titreTextview
        titreTextView.setText(film.original_title.toString())

        var dateTextView = holder.dateTextview
        dateTextView.setText(film.release_date.toString())

        // image
        Glide.with(holder.imageImageView).load("https://image.tmdb.org/t/p/w500"+film.poster_path).apply(
            RequestOptions().override(200,600)).into(holder.imageImageView)


        var btn= holder.btnView
        btn.setOnClickListener(){
            launchDescScreen(holder.itemView.context,film)
            Toast.makeText( holder.itemView.context,"c'est " + film.original_title.toString(),  Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return mFilms.size
    }

    fun launchDescScreen(context: Context,film:Film){
        val intent = Intent(context, DescriptionActivity::class.java)
        intent.putExtra("EXTRA_FILM", film)
        context.startActivity(intent)



    }
}