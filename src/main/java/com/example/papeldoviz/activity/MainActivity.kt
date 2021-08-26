package com.example.papeldoviz.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.papeldoviz.R
import com.example.papeldoviz.fragment.LoginFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        //deneme

        val loginFragment = LoginFragment()
        fragmentTransaction.replace(R.id.fragmentContainer, loginFragment).commit()
    }

    /*override fun onBackPressed(){
        val fm =  supportFragmentManager
        if (fm.backStackEntryCount > 0) {
            fm.popBackStack()
        } else {
            super.onBackPressed()
        }
    }*/
}

