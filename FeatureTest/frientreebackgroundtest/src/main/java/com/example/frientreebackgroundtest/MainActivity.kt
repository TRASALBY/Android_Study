package com.example.frientreebackgroundtest

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    private lateinit var gestureDetector: GestureDetector

    private lateinit var ivTree: ImageView
    private var isZoomIn = false
    private var isZoomTop = true

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivTree = findViewById(R.id.iv_tree)

        Glide.with(this).load(R.raw.tree_background).into(ivTree)

        val ivTop = findViewById<View>(R.id.iv_top)
        val ivBottom = findViewById<View>(R.id.iv_bottom)



        ivTree.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // 터치 다운 이벤트 처리
                }

                MotionEvent.ACTION_UP -> {
                    // 터치 업 이벤트 처리
                    if (isZoomIn.not()) {

                        if (event.y < ivTree.height / 2) {
                            // 화면 위쪽을 눌렀을 때
                            zoomView(
                                ivTree,
                                1.0f,
                                2.5f,
                                (ivTree.width / 2).toFloat(),
                                (ivTree.height / 8).toFloat(),
                                500
                            )
                            isZoomTop = true
                        } else {
                            // 화면 아래쪽을 눌렀을 때
                            zoomView(
                                ivTree,
                                1.0f,
                                2.5f,
                                (ivTree.width / 2).toFloat(),
                                (ivTree.height / 8 * 7).toFloat(),
                                500
                            )
                            isZoomTop = false
                        }
                    }
                }
            }
            true
        }
    }

    private fun zoomView(
        view: View,
        fromScale: Float,
        toScale: Float,
        pivotX: Float,
        pivotY: Float,
        duration: Long
    ) {
        val valueAnimator = ValueAnimator.ofFloat(fromScale, toScale)
        valueAnimator.addUpdateListener { animation ->
            val value = animation.animatedValue as Float
            view.pivotX = pivotX
            view.pivotY = pivotY
            view.scaleX = value
            view.scaleY = value
        }
        valueAnimator.interpolator = DecelerateInterpolator()
        valueAnimator.duration = duration
        valueAnimator.start()
        isZoomIn = true
    }


    override fun onBackPressed() {
        if (isZoomIn) {
            if (isZoomTop) {
                zoomView(
                    ivTree,
                    2.5f,
                    1.0f,
                    (ivTree.width / 2).toFloat(),
                    (ivTree.height / 8).toFloat(),
                    500
                )
            } else {
                zoomView(
                    ivTree,
                    2.5f,
                    1.0f,
                    (ivTree.width / 2).toFloat(),
                    (ivTree.height / 8 * 7).toFloat(),
                    500
                )
            }
            isZoomIn = false
        } else {
            super.onBackPressed()
        }
    }

}