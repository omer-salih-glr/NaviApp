package com.omerglr.naviapp.ui.profilduzen

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.net.Uri
import android.view.View
import android.view.Window
import android.widget.Button
import com.omerglr.naviapp.R
import android.view.ViewGroup
import android.graphics.drawable.ColorDrawable

class CustomAlert{

    val PERMISSION_CODE: Int = 12345
    val IMAGE_CAPTURE_CODE: Int = 12346
    var imageUri: Uri? = null

    fun showDialog(activity: Activity?, msg: String?) {


        //val dialog:Dialog = Dialog(this);
        val dialog = Dialog(activity!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_layout)
        dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)


        dialog.show()
/*

        val galeriButton: Button = dialog.findViewById<View>(R.id.btn_galeri) as Button
        galeriButton.setOnClickListener {
            dialog.dismiss()
        }

        val kameraButton: Button = dialog.findViewById<View>(R.id.btn_kamera) as Button
        kameraButton.setOnClickListener {






            *//*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(activity,android.Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_DENIED
                    || checkSelfPermission(
                        activity,toString()
                    ) == PackageManager.PERMISSION_DENIED
                ) {
                    val permission = arrayOf(
                        android.Manifest.permission.CAMERA,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                        )

                    requestPermissions(activity,permission, IMAGE_CAPTURE_CODE)
                } else {
                    openCamera(activity)
                }

            } else {
                    //api<23
             openCamera(activity)
            }
        }
        dialog.dismiss()*//*
    }*/
    //showDialog()


    /*private fun openCamera(activity: Activity) {
        val contentValues = ContentValues()
        contentValues.put(MediaStore.Images.Media.TITLE, "imageTitle")
        imageUri =
            activity.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(activity,intent, IMAGE_CAPTURE_CODE,null)
    }*/

     /*fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
       /* when (requestCode) {
            PERMISSION_CODE -> {

                if (grantResults.size > 0 && grantResults[0]
                    == PackageManager.PERMISSION_GRANTED
                ) {
                    openCamera(Activity())
                } else {
                   //Toast.makeText(
                   onActivityResult(requestCode,resultCode = 1,data = null)
                   ,

                   ).show()
                }

            }
        }
    }

     fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //cameraImage.setImageURI(imageUri)
        }*/
    }*/
}
}



