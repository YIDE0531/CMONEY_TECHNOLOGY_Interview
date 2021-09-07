package com.nuu.cmoney_technology_interview.util

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout

open abstract class BaseImageView@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    lateinit var imageView: ImageView
    lateinit var progressBar: ProgressBar
    val imageUtils: ImageUtils = ImageUtils()

    init {
        createProgress()
        createImageView()
    }

    open abstract fun createImageView()

    open abstract fun createProgress()

    fun setImageUrl(urlString: String?) {
        imageView.tag = urlString
        imageView.setImageBitmap(null)
        progressBar.visibility = View.VISIBLE
        imageUtils.loadImage(imageView, urlString, object: ImageUtils.ImageApiInterface{
            override fun onSuccess(dataSting: Bitmap?) {
                if (imageView.tag == urlString && imageView.tag != null) {
                    imageView.setImageBitmap(dataSting)
                    progressBar.visibility = View.GONE
                }else{
                    progressBar.visibility = View.VISIBLE
                }
            }

            override fun onFailed() {
                if (imageView.tag == urlString && imageView.tag != null) {
                    progressBar.visibility = View.GONE
                }else{
                    progressBar.visibility = View.VISIBLE
                }
            }
        })
    }
}