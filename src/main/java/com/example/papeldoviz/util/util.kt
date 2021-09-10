package com.example.papeldoviz.util

import android.content.Context
import android.util.Log
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.bumptech.glide.Glide

import com.bumptech.glide.request.RequestOptions
import com.example.papeldoviz.R

fun ImageView.gorselIndir(url: String?, placeholder: CircularProgressDrawable){

    val options = RequestOptions().placeholder(placeholder).error(R.drawable.ic_empty_state)
    Log.wtf("tag",url)
    //Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)

}

fun placeholderYap(context: Context) : CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}

fun ImageView.loadUrl(url: String) {

    val imageLoader = ImageLoader.Builder(this.context)
        .componentRegistry { add(SvgDecoder(context)) }
        .build()

    val request = ImageRequest.Builder(this.context)
        .crossfade(true)
        .crossfade(500)
        .placeholder(R.drawable.ic_launcher_foreground)
        .data(url)
        .target(this)
        .build()

    imageLoader.enqueue(request)
}