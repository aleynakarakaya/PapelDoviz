package com.example.papeldoviz.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.LayoutRes

abstract class BaseActivity : AppCompatActivity() {

    @LayoutRes
    abstract fun myLayoutRes(): Int


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(myLayoutRes())
    }
}