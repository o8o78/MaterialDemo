package com.byteroll.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.byteroll.myapplication.R
import com.byteroll.myapplication.bean.ScreenShot

class ScreenShotAdapter(private val context: Context, private val shotList: List<ScreenShot>) : RecyclerView.Adapter<ScreenShotAdapter.ViewHolder>(){

    inner class ViewHolder(view : View): RecyclerView.ViewHolder(view){
        val shotPlaceHolder : ImageView = view.findViewById(R.id.iv_screen_shot)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shot = shotList[position]
        Glide.with(context).load(shot.imageId).into(holder.shotPlaceHolder)
    }

    override fun getItemCount() = shotList.size

}