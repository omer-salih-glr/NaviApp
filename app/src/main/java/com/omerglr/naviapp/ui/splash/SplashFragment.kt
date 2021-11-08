package com.omerglr.naviapp.ui.splash

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.omerglr.naviapp.R
import com.omerglr.naviapp.utils.AccessTokenUtils
import kotlin.math.log

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Kullanıcının giriş yapıp yapmadığını kontrol et.
        val token = AccessTokenUtils.getAccessToken(requireActivity());

        val timer = object: CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                kotlin.run {
                    if (token.isNotEmpty()) {
                        // Kullanıcı giriş yapmış; Token mevcut.
                        findNavController().navigate(R.id.navigation_home)
                    } else {
                        // Kullanıcı giriş yapmamış; Token mevcut değil.
                        findNavController().navigate(R.id.navigation_welcome)
                    }
                }}
        }
        timer.start()



        return inflater.inflate(R.layout.splash_fragment, container, false)
    }


}