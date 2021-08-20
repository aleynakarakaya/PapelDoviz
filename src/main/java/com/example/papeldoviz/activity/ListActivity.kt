package com.example.papeldoviz.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.papeldoviz.R
import com.example.papeldoviz.fragment.CustomListFragment
import com.google.firebase.auth.FirebaseAuth

class ListActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val listFragment = CustomListFragment()
        fragmentTransaction.replace(R.id.fragmentContainer2, listFragment).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = getMenuInflater()
        menuInflater.inflate(R.menu.secenekler_menusu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.cikis_yap){
            auth = FirebaseAuth.getInstance()
            auth.signOut()
            finish()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }


        return super.onOptionsItemSelected(item)
    }
}