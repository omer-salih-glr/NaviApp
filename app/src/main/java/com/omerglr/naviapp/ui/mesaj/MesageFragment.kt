package com.omerglr.naviapp.ui.mesaj

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.omerglr.naviapp.databinding.FragmentMesageBinding

class MesageFragment : Fragment() {

    private lateinit var mesageViewModel: MesageViewModel
    private var _binding: FragmentMesageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mesageViewModel =
            ViewModelProvider(this).get(MesageViewModel::class.java)

        _binding = FragmentMesageBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}