package com.omerglr.naviapp.ui.mesaj


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.omerglr.naviapp.R
import com.omerglr.naviapp.databinding.FragmentProfilimBinding
import com.omerglr.naviapp.ui.profilim.PageAdapter


class ProfilimFragment : Fragment() {

    private lateinit var profilimViewModel: ProfilimViewModel
    private var _binding: FragmentProfilimBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("ProfilimScreen. Drawing Screen.")
        profilimViewModel =
            ViewModelProvider(this).get(ProfilimViewModel::class.java)

        _binding = FragmentProfilimBinding.inflate(inflater, container, false)
        val root: View = binding.root



        val viewPager :ViewPager = root.findViewById(R.id.viewPager)
        viewPager.adapter = PageAdapter(this.childFragmentManager)

        val tabLayout :TabLayout = root.findViewById(R.id.tableLl)
        tabLayout.setupWithViewPager(viewPager)

       val profil_desigin :ImageView = root.findViewById(R.id.profildesigin)
        profil_desigin.setOnClickListener{
            findNavController().navigate(R.id.fragmentprofilduzen)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}