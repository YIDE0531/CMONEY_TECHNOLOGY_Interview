package com.nuu.cmoney_technology_interview.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.util.LruCache
import android.widget.ImageView
import java.io.BufferedInputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

class ImageUtils {
    private lateinit var memoryCache: LruCache<String, Bitmap>
    lateinit var future: Future<*>
    lateinit var executor: ExecutorService
    var myInputStream: BufferedInputStream? = null

    init {
        initMemoryCache()
    }

    fun loadImage(view: ImageView, urlString: String?, apiInterface: ImageApiInterface? = null) {
        if (urlString != null) {
            val fileName = urlString.substringAfterLast("/")
            val value = memoryCache.get(fileName)
            if (value != null) {
                apiInterface?.onSuccess(value)
                Log.e("temp", "11111111")
            } else {
                executor = Executors.newSingleThreadExecutor()
                val handler = Handler(Looper.getMainLooper())
                future = executor.submit {
                    try {
                        val mURL = URL(urlString)


                        with(mURL.openConnection() as HttpURLConnection) {
                            connectTimeout = 300000
                            connectTimeout = 300000
                            setRequestProperty("Content-Type", "application/x-www-form-urlencoded")

                            myInputStream = BufferedInputStream(inputStream)
                            if (!executor.isTerminated) {
                                val bitmap = BitmapFactory.Options().run {
                                    inSampleSize =
                                        calculateInSampleSize(this, view.width, view.height)
                                    BitmapFactory.decodeStream(myInputStream, null, this)
                                }
                                memoryCache.put(fileName, bitmap)
                                handler.post {
                                    apiInterface?.onSuccess(bitmap)
                                    Log.e("online", "22222222")
                                }
                            }
                        }
                    } catch (e: Exception) {
                        handler.post {
                            apiInterface?.onFailed()
                        }
                    }
                }
            }
        }
    }

    private fun initMemoryCache() {
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        val cacheSize = maxMemory / 4
        memoryCache = object : LruCache<String, Bitmap>(cacheSize) {
            override fun sizeOf(key: String, bitmap: Bitmap): Int {
                return bitmap.byteCount / 1024
            }
        }
    }

    fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1
        if (height > reqHeight || width > reqWidth) {
            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2
            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }

    interface ImageApiInterface {
        fun onSuccess(dataSting: Bitmap?)
        fun onFailed()
    }
}