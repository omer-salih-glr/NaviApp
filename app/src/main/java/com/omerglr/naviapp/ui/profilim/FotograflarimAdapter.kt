package com.omerglr.naviapp.ui.profilim

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omerglr.naviapp.R
import okhttp3.Interceptor.Companion.invoke
import okhttp3.internal.notify

class FotograflarimAdapter(val imageUrlList: List<String>, val onItemClicked: (imageUrl: String, position: Number) ->  Unit) :
    RecyclerView.Adapter<FotograflarimAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view.findViewById(R.id.image);



        fun bindItems(item:String,number: Int,  onItemClicked: (imageUrl: String, position:Number) -> Unit) {
            if (item == "upload_photo") {

                imageView.setOnClickListener() {
                    onItemClicked(item,number)

                }
                imageView.setImageResource(R.drawable.new_image);

                return;
            }



           Glide.with(imageView)
               .load(item)
               .into(imageView);

        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.desigin_foto,parent,false);
        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bindItems(imageUrlList[position],position,onItemClicked);
    }

    override fun getItemCount(): Int {
        return imageUrlList.size;
    }

}