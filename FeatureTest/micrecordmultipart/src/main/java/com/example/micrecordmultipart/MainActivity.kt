package com.example.micrecordmultipart

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieAnimationView
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var mediaRecorder: MediaRecorder? = null
    private var mediaPlayer: MediaPlayer? = null

    private var isRecording = false
    private var isPlaying = false

    private val RECORD_AUDIO_PERMISSION_CODE = 123

    private val cameraPhotoPath: String = "YourDirectoryPath" // 디렉토리 경로를 설정하세요.
    private var audioFileUri: Uri? = null


    private lateinit var recordBtn: Button
    private lateinit var recordPlayBtn: Button
    private lateinit var recordLottie: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestAudioPermission()

        recordBtn = findViewById(R.id.btn_record)
        recordLottie = findViewById(R.id.lottieAnimationView)
        recordPlayBtn = findViewById(R.id.btn_play)


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


        recordPlayBtn.setOnClickListener {
            if (isPlaying) {
                stopAudio()
            } else {
                playAudio()
            }
            isPlaying = isPlaying.not()
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
        val audioFile: File = createAudioFile()
        audioFileUri = Uri.fromFile(audioFile)
        mediaRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            setOutputFile(audioFile.absolutePath)
        }

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


    private fun playAudio() {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer()
        recordPlayBtn.text = "녹음 파일 중단"

        try {
            mediaPlayer?.setDataSource(applicationContext,audioFileUri!!)
            mediaPlayer?.prepare()
            mediaPlayer?.start()

            mediaPlayer?.setOnCompletionListener {
                stopAudio()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun stopAudio() {
        recordPlayBtn.text = "녹음 파일 재생"
        mediaPlayer?.apply {
            if (isPlaying) {
                stop()
            }
            release()
        }
    }
    private fun createAudioFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val audioFileName = "AUDIO_${timeStamp}_"
        val storageDir: File = getExternalFilesDir(cameraPhotoPath)!!
        return File.createTempFile(audioFileName, ".3gp", storageDir)
    }

}