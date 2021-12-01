package fr.les_mega_bg.td6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RepoAdapter(repos : List<Repo>): RecyclerView.Adapter<RepoAdapter.ViewHolder>() {

    val mRepos: List<Repo> = repos

    class ViewHolder(view :View) : RecyclerView.ViewHolder(view){
        val idTextView: TextView=view.findViewById(R.id.IdRepo)
        val nomTextView : TextView=view.findViewById(R.id.Nom)
        val full_nameTextView :TextView=view.findViewById(R.id.full_name)
        val html_urlTextView : TextView=view.findViewById(R.id.html_url)

    }

    // Déclaration de la vue d'un item pour le xml
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val contactView =inflater.inflate(R.layout.item_repo,parent,false)
        return ViewHolder(contactView)

    }

    // Utilisé pour afficher les données passées en paramètres de l'adapter
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo : Repo=mRepos.get(position)
        var idTextView= holder.idTextView
        idTextView.setText("Id : "+repo.id.toString())

        var nomTextView = holder.nomTextView
        nomTextView.setText("Nom : "+repo.name)

        var full_NomTextView = holder.full_nameTextView
        full_NomTextView.setText("Nom complet : "+repo.full_name)

        var htmlTextView = holder.html_urlTextView
        htmlTextView.setText("URL : "+repo.html_url)

    }

    // Retourne le nombre d'éléments de la liste
    override fun getItemCount(): Int {
        return mRepos.size
    }


}