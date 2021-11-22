package com.omerglr.naviapp.ui.profilduzen

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.view.Window
import com.omerglr.naviapp.R
import android.view.ViewGroup
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.widget.ImageView
import android.widget.LinearLayout

class CustomAlert {

    private val REQUEST_IMAGE_CAPTURE = 0
    private val REQUEST_GALLERY_IMAGE = 1
    private var filePath: String = ""

    private lateinit var imgView: ImageView


    fun showDialog(activity: Activity?, msg: String?) {


        val dialog = Dialog(activity!!)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_layout)
        //dialog.

        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
println("çalış dialog")
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )



        val galeriac: LinearLayout =
            dialog.findViewById<LinearLayout>(R.id.galeri_ac) as LinearLayout
        galeriac.setOnClickListener {


            dialog.dismiss()

        }

        val kamera_ac: LinearLayout =
            dialog.findViewById<LinearLayout>(R.id.kamera_ac) as LinearLayout
        kamera_ac.setOnClickListener {


        }
        dialog.dismiss()
        dialog.show()
    }









}