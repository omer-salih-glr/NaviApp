package com.omerglr.naviapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.omerglr.naviapp.R
import com.omerglr.naviapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {


    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val image: ImageView = root.findViewById(R.id.shape);
        image.setOnClickListener {
            findNavController().navigate(R.id.navigation_profilim)
        }

        val cevreprofil: ImageView = root.findViewById(R.id.profil_img);
        cevreprofil.setOnClickListener {
            findNavController().navigate(R.id.navigation_profilim)
        }


        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}