package com.example.papeldoviz.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.papeldoviz.R
import com.example.papeldoviz.fragment.LoginFragment
import java.lang.ref.WeakReference

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val loginFragment = LoginFragment()
        fragmentTransaction.replace(R.id.baseFragmentContainer, loginFragment).commit()
    }

    override fun myLayoutRes() = R.layout.activity_base

    /*override fun onBackPressed(){
        val fm =  supportFragmentManager
        if (fm.backStackEntryCount > 0) {
            fm.popBackStack()
        } else {
            super.onBackPressed()
        }
    }*/
}

