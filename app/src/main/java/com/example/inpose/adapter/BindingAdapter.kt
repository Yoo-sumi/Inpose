package com.example.inpose.adapter

import android.graphics.Bitmap
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.example.inpose.R
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

@BindingAdapter("profileImageFromUrl")
fun bindProfileImageFromUrl(view: ImageView, imageUrl: String) {
    Glide.with(view.context)
        .load(imageUrl)
        .centerCrop()
        .circleCrop()
        .into(view)
}

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String) {
    val multiLeftTopCorner: MultiTransformation<Bitmap> = MultiTransformation(
        CenterCrop(),
        RoundedCornersTransformation( 70, 0, RoundedCornersTransformation.CornerType.TOP_RIGHT),
    )
    Glide.with(view.context)
        .load(imageUrl)
        .apply(bitmapTransform(multiLeftTopCorner))
        .into(view)
}

@BindingAdapter("date")
fun getDate(view: TextView, createdAt: String) {
    val date = createdAt.split(" ")[0].split("-")
    view.text = view.context.getString(R.string.story_date_format, date[1].toInt(), date[2].toInt())
}