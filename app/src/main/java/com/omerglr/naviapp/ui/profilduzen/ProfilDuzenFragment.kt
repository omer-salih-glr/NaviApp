package com.omerglr.naviapp.ui.mesaj

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.omerglr.naviapp.R
import com.omerglr.naviapp.databinding.FragmentProfilduzenBinding
import com.omerglr.naviapp.ui.profilduzen.CustomAlert

class ProfilDuzenFragment : Fragment() {

    private var _binding: FragmentProfilduzenBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



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
        val profilfoto :ImageView = root.findViewById(R.id.profil_foto)
        profilfoto.setOnClickListener{
            val alert = CustomAlert()
            alert.showDialog(activity,"")

        }








        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}