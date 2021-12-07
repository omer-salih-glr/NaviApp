package com.omerglr.naviapp.ui.profilduzen

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.view.Window
import com.omerglr.naviapp.R
import android.view.ViewGroup
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.omerglr.naviapp.databinding.DialogLayoutBinding


class CustomAlert {
    private val cameraRequest = 1888
    lateinit var button: Button
    private val pickImage = 100
    private var imageUri: Uri? = null
    lateinit var imageView: ImageView
    lateinit var binding: DialogLayoutBinding;
    private val TAG = "CustomAlert"



    fun showDialog(activity: Activity?, msg: String?, launcher: ActivityResultLauncher<String>, onOpenCameraClickedListener: () -> Unit,onOpenGalleryClickedListener: () -> Unit) {
        val dialog = Dialog(activity!!)

         binding = DialogLayoutBinding.inflate(dialog.layoutInflater);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(binding.root)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )


        if (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(activity, arrayOf(android.Manifest.permission.CAMERA), cameraRequest)
        imageView = activity.findViewById(R.id.profil_foto)



        binding.kameraAc.setOnClickListener {
            onOpenCameraClickedListener()

            Log.d(TAG, "showDialog:Kamera Ac ")

            /*val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            //startActivityForResult(cameraIntent, cameraRequest)
            cameraIntent.type = "image/jpg"
            cameraIntent.putExtra(MediaStore.ACTION_IMAGE_CAPTURE, true)
            activitykamera.launch("image/*")*/*/


            dialog.cancel()
        }

       binding.galeriAc.setOnClickListener {

           Log.d(TAG, "showDialog: ")
           onOpenGalleryClickedListener()
           //openGalleryBtn()
           /*val gallery = Intent(Intent.ACTION_PICK,
               MediaStore.Images.Media.INTERNAL_CONTENT_URI)
           //startActivityForResult(gallery, pickImage)
           //ActivityResultCallback(gallery, pickImage)
           gallery.type = "image/jpg"
           gallery.putExtra(Intent.ACTION_PICK, true)
           launcher.launch("image/*")*/*/
           dialog.cancel()

       }
        binding.vazgec.setOnClickListener{
            dialog.cancel()

        }
        dialog.show()

    }


}



