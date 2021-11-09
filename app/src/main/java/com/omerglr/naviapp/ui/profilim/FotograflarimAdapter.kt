package com.omerglr.naviapp.ui.profilim

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.omerglr.naviapp.R

class FotograflarimAdapter(val imageUrlList: List<Int>) :
    RecyclerView.Adapter<FotograflarimAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: View = view.findViewById(R.id.image);

        fun bindItems(item:Int) {
            imageView.setBackgroundColor(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.desigin_foto,parent,false);
        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bindItems(imageUrlList[position]);
    }

    override fun getItemCount(): Int {
        return imageUrlList.size;
    }
}