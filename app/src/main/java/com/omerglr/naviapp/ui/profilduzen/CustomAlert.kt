package com.omerglr.naviapp.ui.profilduzen

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.Intent.ACTION_PICK
import android.graphics.Color
import android.view.Window
import com.omerglr.naviapp.R
import android.view.ViewGroup
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat.startActivity
import com.omerglr.naviapp.databinding.DialogLayoutBinding
import androidx.fragment.app.Fragment


class CustomAlert {

    lateinit var button: Button
    private val pickImage = 100
    private var imageUri: Uri? = null
    lateinit var imageView: ImageView
    lateinit var binding: DialogLayoutBinding;
    private val TAG = "CustomAlert"

    fun showDialog(activity: Activity?,  msg: String?, launcher: ActivityResultLauncher<String> ) {
        val dialog = Dialog(activity!!)

         binding = DialogLayoutBinding.inflate(dialog.layoutInflater);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(binding.root)
        //dialog.


        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        imageView = activity.findViewById(R.id.profil_foto)

        binding.kameraAc.setOnClickListener {
            Log.d(TAG, "showDialog:Kamera Ac ")

            val gallery = Intent(Intent.ACTION_CAMERA_BUTTON,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            //ActivityResultCallback(gallery, pickImage)

            gallery.type = "image/jpg"
            gallery.putExtra(Intent.ACTION_CAMERA_BUTTON, true)
            launcher.launch("image/*")

        }

       binding.galeriAc.setOnClickListener {
           Log.d(TAG, "showDialog: ")
           //openGalleryBtn()

           val gallery = Intent(Intent.ACTION_PICK,
               MediaStore.Images.Media.INTERNAL_CONTENT_URI)
           //ActivityResultCallback(gallery, pickImage)

           gallery.type = "image/jpg"
           gallery.putExtra(Intent.ACTION_PICK, true)
           launcher.launch("image/*")


       }
        dialog.show()
        
    }




}



