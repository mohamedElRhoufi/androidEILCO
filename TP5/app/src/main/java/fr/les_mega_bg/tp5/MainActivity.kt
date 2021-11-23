package fr.les_mega_bg.tp5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {//contact activity

    var contacts :MutableList<Contact> = ArrayList<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contact_activity)
        //récupération du recyclerView

    val rvContacts : RecyclerView = findViewById(R.id.rvContacts)


        //initialisation des contact naruto
        contacts.add(Contact("Uzumaki","Naruto","https://static.wikia.nocookie.net/naruto/images/f/f1/Naruto_Partie_I.png/revision/latest/scale-to-width-down/300?cb=20151201180820&path-prefix=fr"))
        contacts.add(Contact("Uchiha","Sasuke","https://static.wikia.nocookie.net/naruto/images/1/1e/Sasuke_Uchiwa.png/revision/latest/scale-to-width-down/300?cb=20150507111747&path-prefix=fr"))
        contacts.add(Contact("Haruno","Sakura","https://static.wikia.nocookie.net/naruto/images/5/5f/Sakura_Partie_1.png/revision/latest/scale-to-width-down/300?cb=20200607210950&path-prefix=fr"))
        contacts.add(Contact("Hatake","Kakashi","https://static.wikia.nocookie.net/naruto/images/2/27/Kakashi_Hatake.png/revision/latest/scale-to-width-down/300?cb=20141108182016&path-prefix=fr"))
        contacts.add(Contact("Hyuga","Hinata","https://static.wikia.nocookie.net/naruto/images/9/97/Hinata.png/revision/latest/scale-to-width-down/300?cb=20150508121138&path-prefix=fr"))
        contacts.add(Contact("Inuzuka","Kiba","https://static.wikia.nocookie.net/naruto/images/2/22/Kiba_Inuzuka.png/revision/latest/scale-to-width-down/300?cb=20150512175714&path-prefix=fr"))
        contacts.add(Contact("Aburame","Shino","https://static.wikia.nocookie.net/naruto/images/c/c4/Shino_Aburame_Partie_I.png/revision/latest/scale-to-width-down/300?cb=20150512221630&path-prefix=fr"))
        contacts.add(Contact("Yuhi","Kurenai","https://static.wikia.nocookie.net/naruto/images/c/c6/Kurenai_Partie_I.png/revision/latest/scale-to-width-down/300?cb=20210405192609&path-prefix=fr"))
        contacts.add(Contact("Nara","Shikamaru","https://static.wikia.nocookie.net/naruto/images/5/51/Jeune_Shikamaru_Nara.png/revision/latest/scale-to-width-down/300?cb=20150523220147&path-prefix=fr"))
        contacts.add(Contact("Yamanaka","Ino","https://static.wikia.nocookie.net/naruto/images/d/dd/Ino.png/revision/latest/scale-to-width-down/300?cb=20150525121423&path-prefix=fr"))
        contacts.add(Contact("Akimichi","Choji","https://static.wikia.nocookie.net/naruto/images/9/94/Ch%C3%B4ji_Akimichi_Pt_I.png/revision/latest/scale-to-width-down/300?cb=20150525140141&path-prefix=fr"))
        contacts.add(Contact("Sarutobi","Asuma","https://static.wikia.nocookie.net/naruto/images/7/7c/Asuma.png/revision/latest/scale-to-width-down/350?cb=20210117153743&path-prefix=fr"))
        contacts.add(Contact("Rock","Lee","https://static.wikia.nocookie.net/naruto/images/0/0c/Rock_Lee_pt1.png/revision/latest/scale-to-width-down/300?cb=20160219192935&path-prefix=fr"))
        contacts.add(Contact("Hyuga","Neji","https://static.wikia.nocookie.net/naruto/images/0/07/Neji_Part_1.png/revision/latest/scale-to-width-down/300?cb=20150701095606&path-prefix=fr"))
        contacts.add(Contact("Ten","Ten","https://static.wikia.nocookie.net/naruto/images/c/c9/Tenten_Partie_I.png/revision/latest/scale-to-width-down/300?cb=20170812145555&path-prefix=fr"))
        contacts.add(Contact("Might","Gai","https://static.wikia.nocookie.net/naruto/images/d/db/Ga%C3%AF_Maito.jpg/revision/latest/scale-to-width-down/300?cb=20131208193745&path-prefix=fr"))




//Création d'un adapter avec initialisation du constructeur avec notre liste de contacts
        val adapter :ContactsAdapter= ContactsAdapter(contacts)
        //On notifie au recyclerView notre adapter
        rvContacts.setAdapter(adapter)
        //On déclare quel type de LayoutMangare on désire
        rvContacts.setLayoutManager(LinearLayoutManager(this))






    }
}