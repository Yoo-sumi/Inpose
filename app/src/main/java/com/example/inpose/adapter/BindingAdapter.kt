package com.example.inpose.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.inpose.R

@BindingAdapter("profileImageFromUrl")
fun bindProfileImageFromUrl(view: ImageView, imageUrl: String) {
    Glide.with(view.context)
        .load(imageUrl)
        .centerCrop()
        .circleCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(view)
}

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String) {
    Glide.with(view.context)
        .load(imageUrl)
        .centerCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(view)
}

@BindingAdapter("date")
fun getDate(view: TextView, createdAt: String) {
    val date = createdAt.split(" ")[0].split("-")
    view.text = view.context.getString(R.string.story_date_format, date[1].toInt(), date[2].toInt())
}