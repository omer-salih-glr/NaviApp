package com.omerglr.naviapp.ui.mesaj

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.omerglr.lovelica.api.ServiceBuilder
import com.omerglr.naviapp.R
import com.omerglr.naviapp.api.requests.LoginRequest
import com.omerglr.naviapp.databinding.FragmentLoginBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*


class LoginFragment : Fragment() {

    lateinit var sharedLogin : SharedPreferences
    lateinit var loginDisposable: Disposable

    private lateinit var loginViewModel: LoginViewModel
    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginViewModel =
            ViewModelProvider(this).get(LoginViewModel::class.java)

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val siremihatirlat: TextView = root.findViewById(R.id.sifremi_harilat);
        siremihatirlat.setOnClickListener {
            findNavController().navigate(R.id.navigation_sifremihatirlat)
        }

        sharedLogin = requireActivity().getSharedPreferences("Auth", Context.MODE_PRIVATE)

        val button_login: Button = root.findViewById(R.id.button_login);

        button_login.setOnClickListener {
            val user_email = user_email_text.text.toString().trim()
            val password = password_text.text.toString().trim()

            if (user_email.isEmpty()) {
                user_email_text.error = "Name Required"
                user_email_text.requestFocus()
                return@setOnClickListener

            }


            if (password.isEmpty()) {
                password_text.error = "Password Required"
                password_text.requestFocus()
                return@setOnClickListener
            }

            val params = LoginRequest();

            params.email = user_email;
            params.password = password;
            params.combine = "AllowForHakan";

            loginDisposable = ServiceBuilder.buildService(requireActivity())
                .sendLoginRequest(params)
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
                            findNavController().navigate(R.id.navigation_home)
                        }
                    },
                    { err -> println("Error -> " + err) },
                )



        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}