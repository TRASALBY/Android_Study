package com.example.micrecordmultipart

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieAnimationView

class MainActivity : AppCompatActivity() {

    private lateinit var mediaRecorder: MediaRecorder
    private var isRecording = false
    private val RECORD_AUDIO_PERMISSION_CODE = 123

    private lateinit var recordBtn: Button
    private lateinit var recordLottie : LottieAnimationView

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestAudioPermission()

        recordBtn = findViewById(R.id.btn_record)
        recordLottie = findViewById(R.id.lottieAnimationView)

        recordBtn.setOnClickListener {

            if(isRecording.not()){
                //녹음 시작
                recordLottie.playAnimation()
                recordBtn.text = "녹음 중단"
            } else{
                recordLottie.progress = 0f
                recordLottie.pauseAnimation()
                recordBtn.text = "녹음 시작"
            }

            isRecording = isRecording.not()
        }


    }

    private fun requestAudioPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                RECORD_AUDIO_PERMISSION_CODE
            )
        }
    }
}