package com.example.retrofit_error_handling.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofit_error_handling.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}