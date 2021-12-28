package fr.les_mega_bg.tp_infofilm

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class DescriptionApplication: Application() {
    var film :Film = TODO()
    override fun onCreate() {
        super.onCreate()
        this.film
        //Gestion des boutons :

    }
}