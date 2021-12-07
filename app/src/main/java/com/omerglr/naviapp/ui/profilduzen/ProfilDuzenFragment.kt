package com.omerglr.naviapp.ui.mesaj

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.omerglr.naviapp.R
import com.omerglr.naviapp.databinding.FragmentProfilduzenBinding
import com.omerglr.naviapp.db.UserInformationDB
import com.omerglr.naviapp.ui.profilduzen.CustomAlert
import com.omerglr.naviapp.utils.toEditable
import kotlinx.android.synthetic.main.fragment_bilgilerim.*
import kotlinx.android.synthetic.main.fragment_profilduzen.*
import kotlinx.android.synthetic.main.fragment_profilduzen.view.*


class ProfilDuzenFragment : Fragment(){

    private var imageUri: Uri? = null
    private val pickImage = 100
    private val cameraRequest = 1888
    val Image_Capture_Code = 24
    val REQUEST_CODE=24
    val REQUEST_TAKE_PHOTO=24

    private var _binding: FragmentProfilduzenBinding? = null
    private val binding get() = _binding!!
    private lateinit var  profilfoto :ImageView
    private lateinit var activityLauncher:ActivityResultLauncher<String>

    private lateinit var activitykamera:ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) {
            binding.profilFoto.setImageURI(it)
        }

        activitykamera = registerForActivityResult(ActivityResultContracts.GetContent()) {// sorun burada...
            binding.profilFoto.setImageURI(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activityLauncher.unregister();
        activitykamera.unregister()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfilduzenBinding.inflate(inflater, container, false)
        binding.profilFoto.setOnClickListener{
            //findNavController().navigate(R.id.fragmentcustomalert)

            val alert = CustomAlert()
            alert.showDialog(activity,"ff",activityLauncher,
            onOpenCameraClickedListener = {
                Log.d("TAG", "onCreateView: Open the camera'!!!")
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, cameraRequest)
            },
            onOpenGalleryClickedListener = {
                val gallery = Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                startActivityForResult(gallery, pickImage)
            })

        }







        val userInfo = UserInformationDB.getUserInformation(requireActivity())!!;
        binding.usernameText.text = userInfo.data.username!!.toEditable();
        binding.emailText.text = userInfo.data.email!!.toEditable();
        binding.locationText.text = userInfo.data.cityName!!.toEditable();






        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            profilfoto.setImageURI(imageUri)

        }
        if (requestCode == cameraRequest) {
            val photo: Bitmap = data?.extras?.get("data") as Bitmap
            profilfoto.setImageBitmap(photo)

        }
    }


}