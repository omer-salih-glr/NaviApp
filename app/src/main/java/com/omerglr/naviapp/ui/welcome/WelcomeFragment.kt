package com.omerglr.naviapp.ui.mesaj

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.omerglr.naviapp.R
import com.omerglr.naviapp.databinding.FragmentWelcomeBinding
import kotlinx.android.synthetic.main.activity_main.*


class WelcomeFragment : Fragment() {


    private lateinit var welcomeViewModel: WelcomeViewModel
    private var _binding: FragmentWelcomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        welcomeViewModel =
            ViewModelProvider(this).get(WelcomeViewModel::class.java)

        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        val root: View = binding.root



        val login: LinearLayout = root.findViewById(R.id.loginLl);
        login.setOnClickListener {
            findNavController().navigate(R.id.navigation_login)
        }
        val register: LinearLayout = root.findViewById(R.id.registerLl);
        register.setOnClickListener {
            findNavController().navigate(R.id.navigation_register)
        }




        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}