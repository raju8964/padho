package com.fdbanks.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.widget.ImageView
import androidx.exifinterface.media.ExifInterface
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import java.io.File


fun ImageView.loadFromFile(file: File, defaultImage: Int) {
    if (file.exists()) {
        val requestOptions = RequestOptions()
        requestOptions.placeholder(defaultImage)
        requestOptions.error(defaultImage)
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL)
        Glide.with(this.context)
            .load(file)
            .apply(requestOptions)
            .into(this)
    } else {
        setImageResource(defaultImage)
    }

}

fun ImageView.loadFromString(file: String?, defaultImage: Int) {
    if (!file.isNullOrEmpty()) {
        val requestOptions = RequestOptions()
        requestOptions.placeholder(defaultImage)
        requestOptions.error(defaultImage)
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL)
        Glide.with(this.context)
            .load(file)
            .apply(requestOptions)
            .into(this)
    } else {
        setImageResource(defaultImage)
    }
}


fun decodeSampledBitmap(pathName: String): Bitmap? {
    val bitmap = BitmapFactory.decodeFile(pathName)
    val exif = ExifInterface(File(pathName).toString())
    val orientation =
        exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
    val matrix = Matrix()
    when (orientation) {
        ExifInterface.ORIENTATION_ROTATE_90 -> matrix.postRotate(90F)
        ExifInterface.ORIENTATION_ROTATE_180 -> matrix.postRotate(180F)
        ExifInterface.ORIENTATION_ROTATE_270 -> matrix.postRotate(270F)
    }
    val rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    return rotatedBitmap
}
