package com.omerglr.naviapp.ui.mesaj

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.omerglr.naviapp.R
import com.omerglr.naviapp.databinding.FragmentZiyaretBinding

class ZiyaretFragment : Fragment() {




    private lateinit var ziyaretViewModel: ZiyaretViewModel
    private var _binding: FragmentZiyaretBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ziyaretViewModel =
            ViewModelProvider(this).get(ZiyaretViewModel::class.java)

        _binding = FragmentZiyaretBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val image: ImageView = root.findViewById(R.id.shape);
        image.setOnClickListener {
            findNavController().navigate(R.id.navigation_profilim)
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}