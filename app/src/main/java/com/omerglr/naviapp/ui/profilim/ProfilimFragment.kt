package com.omerglr.naviapp.ui.mesaj


import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.omerglr.lovelica.api.ServiceBuilder
import com.omerglr.naviapp.api.requests.LoginRequest
import com.omerglr.naviapp.databinding.FragmentProfilimBinding
import com.omerglr.naviapp.ui.profilim.PageAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_profilim.*


class ProfilimFragment : Fragment() {

    private lateinit var profilimViewModel: ProfilimViewModel
    private var _binding: FragmentProfilimBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profilimViewModel =
            ViewModelProvider(this).get(ProfilimViewModel::class.java)

        _binding = FragmentProfilimBinding.inflate(inflater, container, false)
        val root: View = binding.root



        val viewPager :ViewPager = root.findViewById(com.omerglr.naviapp.R.id.viewPager)
        viewPager.adapter = PageAdapter(requireActivity().supportFragmentManager)

        val tabLayout :TabLayout = root.findViewById(com.omerglr.naviapp.R.id.tableLl)
        tabLayout.setupWithViewPager(viewPager)

        val profildesigin: ImageView = root.findViewById(com.omerglr.naviapp.R.id.profildesigin)
        profildesigin.setOnClickListener{
            val name = usernameprofil.text.toString().trim()
            val age = age_text.text.toString().trim()
            val city = city_text.text.toString().trim()


            if (name.isEmpty()) {
                usernameprofil.error = "Name Required"
                usernameprofil.requestFocus()
                return@setOnClickListener

            }


            if (age.isEmpty()) {
                age_text.error = "Password Required"
                age_text.requestFocus()
                return@setOnClickListener
            }

            if (city.isEmpty()) {
                city_text.error = "Password Required"
                city_text.requestFocus()
                return@setOnClickListener
            }

            val params = LoginRequest();

            //params.email = user_email;
            //params.password = password;
            //params.combine = "AllowForHakan";

            /*loginDisposable = ServiceBuilder.buildService(requireActivity()).sendLoginRequest(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { t ->
                        kotlin.run {
                            println("On Next -> " + t);
                            if (t?.status !== true) {
                                Toast.makeText(
                                    requireContext(),
                                    "Bir hata meydana geldi!",
                                    Toast.LENGTH_LONG
                                ).show();
                                return@run;
                            }

                            sharedLogin.edit().putString("Token",t?.data?.accessToken).apply();
                            Toast.makeText(
                                requireContext(),
                                "Başarıyla giriş yapıldı!",
                                Toast.LENGTH_LONG
                            ).show();
                            findNavController().navigate(com.omerglr.naviapp.R.id.navigation_home)
                        }
                    },
                    { err -> println("Error -> " + err) },
                )*/
    }




        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}