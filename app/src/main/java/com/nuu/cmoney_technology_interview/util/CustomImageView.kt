package com.nuu.cmoney_technology_interview.util

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintSet

class CustomImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseImageView(context, attrs, defStyleAttr) {

    override fun createImageView(){
        imageView = ImageView(context)
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            0
        )
        imageView.layoutParams = lp
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        imageView.id = View.generateViewId()
        val set = ConstraintSet()
        addView(imageView)
        set.clone(this)
        set.connect(imageView.id, ConstraintSet.TOP, this.id, ConstraintSet.TOP, 0)
        set.connect(imageView.id, ConstraintSet.START, this.id, ConstraintSet.START, 0)
        set.connect(imageView.id, ConstraintSet.END, this.id, ConstraintSet.END, 0)
        set.connect(imageView.id, ConstraintSet.BOTTOM, this.id, ConstraintSet.BOTTOM, 0)
        set.applyTo(this)
    }

    override fun createProgress(){
        progressBar = ProgressBar(context)
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        progressBar.layoutParams = lp
        progressBar.id = View.generateViewId()
        addView(progressBar)
        val constSet = ConstraintSet()
        constSet.clone(this)
        constSet.connect(progressBar.id, ConstraintSet.TOP, this.id, ConstraintSet.TOP, 0)
        constSet.connect(progressBar.id, ConstraintSet.START, this.id, ConstraintSet.START, 0)
        constSet.connect(progressBar.id, ConstraintSet.END, this.id, ConstraintSet.END, 0)
        constSet.connect(progressBar.id, ConstraintSet.BOTTOM, this.id, ConstraintSet.BOTTOM, 0)
        constSet.applyTo(this)
    }
}