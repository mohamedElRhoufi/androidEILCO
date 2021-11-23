package fr.les_mega_bg.tp5

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ContactsAdapter(contacts : List<Contact>): RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    val mContacts: List<Contact> = contacts

    class ViewHolder(view :View) : RecyclerView.ViewHolder(view){
         val prenomTextView: TextView=view.findViewById(R.id.prenom)
         val nomTextView : TextView=view.findViewById(R.id.nom)
        val imageImageView : ImageView= view.findViewById(R.id.img)
    }

    // Déclaration de la vue d'un item pour le xml
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val context = parent.context
        val inflater = LayoutInflater.from(context)

        val contactView =inflater.inflate(R.layout.item_contact,parent,false)
        return ViewHolder(contactView)

    }

    // Utilisé pour afficher les données passées en paramètres de l'adapter
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact : Contact=mContacts.get(position)
        var prenomTextView= holder.prenomTextView
        prenomTextView.setText(contact.prenom)

        var nomTextView = holder.nomTextView
        nomTextView.setText(contact.nom)

        Glide.with(holder.imageImageView).load(contact.imageUrl).into(holder.imageImageView);
    }

    // Retourne le nombre d'éléments de la liste
    override fun getItemCount(): Int {
        return mContacts.size
    }


}