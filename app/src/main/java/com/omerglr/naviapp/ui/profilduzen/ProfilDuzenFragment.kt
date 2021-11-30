package com.omerglr.naviapp.ui.mesaj

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.omerglr.naviapp.R
import com.omerglr.naviapp.databinding.FragmentProfilduzenBinding
import com.omerglr.naviapp.ui.profilduzen.CustomAlert
import android.net.Uri
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts


class ProfilDuzenFragment : Fragment() {


    val REQUEST_CODE = 200

    lateinit var imageView: ImageView
    lateinit var button: Button
    private val pickImage = 100
    private var imageUri: Uri? = null


    private var _binding: FragmentProfilduzenBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var  profilfoto :ImageView;
    private lateinit var  hesap : TextView;
    private lateinit var activityLauncher:ActivityResultLauncher<String>;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) {
            binding.profilFoto.setImageURI(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activityLauncher.unregister();
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*profilDuzenViewModel =
            ViewModelProvider(this).get(SifremihatirlatViewModel::class.java)*/


        _binding = FragmentProfilduzenBinding.inflate(inflater, container, false)
        val root: View = binding.root
        /*val image: ImageView = root.findViewById(R.id.shape);
        image.setOnClickListener {
            findNavController().navigate(R.id.navigation_profilim)


        }*/

       profilfoto= root.findViewById(R.id.profil_foto)
        profilfoto.setOnClickListener{
            //findNavController().navigate(R.id.fragmentcustomalert)

            val alert = CustomAlert()
            alert.showDialog(activity,"ff",activityLauncher)
        }

        return root
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            val value = data?.getStringExtra("input")
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}