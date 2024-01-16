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
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private var mediaRecorder: MediaRecorder? = null
    private var isRecording = false
    private val RECORD_AUDIO_PERMISSION_CODE = 123

    private lateinit var recordBtn: Button
    private lateinit var recordLottie: LottieAnimationView

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestAudioPermission()

        recordBtn = findViewById(R.id.btn_record)
        recordLottie = findViewById(R.id.lottieAnimationView)

        recordBtn.setOnClickListener {

            if (isRecording.not()) {
                //녹음 시작
                recordLottie.playAnimation()
                startRecording()
                recordBtn.text = "녹음 중단"
            } else {
                recordLottie.progress = 0f
                recordLottie.pauseAnimation()
                stopRecording()
                recordBtn.text = "녹음 시작"
            }

            isRecording = isRecording.not()
        }


    }

    private fun requestAudioPermission() {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.RECORD_AUDIO), RECORD_AUDIO_PERMISSION_CODE
            )
        }
    }

    private fun startRecording() {
        mediaRecorder = MediaRecorder()

        mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

        // 내부 저장소에 파일 생성
        val filePath = "${filesDir}/audio_record.m4a"
        mediaRecorder?.setOutputFile(filePath)

        try {
            mediaRecorder?.prepare()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        mediaRecorder?.start()
    }

    private fun stopRecording() {
        mediaRecorder?.stop()
        mediaRecorder?.release()
        mediaRecorder = null
    }
}