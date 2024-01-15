package com.example.frientreebackgroundtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ivTree = findViewById<ImageView>(R.id.iv_tree)

        Glide.with(this).load(R.raw.tree_background).into(ivTree)
    }
}