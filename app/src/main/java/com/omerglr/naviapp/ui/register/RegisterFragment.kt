package com.omerglr.naviapp.ui.mesaj

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.omerglr.lovelica.api.ServiceBuilder
import com.omerglr.naviapp.R
import com.omerglr.naviapp.api.requests.RegisterRequest
import com.omerglr.naviapp.databinding.FragmentRegisterBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*

class RegisterFragment : Fragment() {

    lateinit var sharedRegister : SharedPreferences
    lateinit var registerDisposable: Disposable

    private lateinit var registerViewModel: RegisterViewModel
    private var _binding: FragmentRegisterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        registerDisposable.dispose();
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        registerViewModel =
            ViewModelProvider(this).get(RegisterViewModel::class.java)

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val gender = arrayOf("Kadın", "Erkek")
        registerViewModel.text.observe(viewLifecycleOwner, Observer {
            gender_picker.minValue = 0
            gender_picker.maxValue = gender.size - 1
            gender_picker.displayedValues = gender
        })

        sharedRegister = requireActivity().getSharedPreferences("Auth", MODE_PRIVATE)


        val button_register: Button = root.findViewById(R.id.button_register)

        button_register.setOnClickListener {
            val name = locationText.text.toString().trim()
            val email = email_text.text.toString().trim()
            val password = password.text.toString().trim()
            val birthday = (
                    date_Picker.year.toString() + "-" +
                            (date_Picker.month + 1) + "-" +
                            date_Picker.dayOfMonth.toString()
                    )
            var gender = (
                    gender_picker.toString()
                    )

              if (name.isEmpty()){
                  locationText.error = "Name Required"
                  locationText.requestFocus()
                  return@setOnClickListener

              }
              if (email.isEmpty()){
                  email_text.error = "Email Required"
                  email_text.requestFocus()
                  return@setOnClickListener

              }

              if (password.isEmpty()){
                  password_text.error = "Password Required"
                  password_text.requestFocus()

                  return@setOnClickListener
              }

            if (birthday.isEmpty()){
                return@setOnClickListener
            }

            if (gender.isEmpty()){

                return@setOnClickListener
            }

            val params = RegisterRequest();
            params.username = name;
            params.password = password;
            params.email = email;
            params.birthday = "2002-06-21";
            params.lang = "TR"
            params.timezone = "10800";
            params.gender = "1";
            params.city = "Bursa";
            params.country = "Turkey";
            params.combine = "AllowForHakan";

            registerDisposable = ServiceBuilder.buildService(requireActivity()).sendRegisterRequest(params)
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
                            sharedRegister.edit().putString("Token",t?.data?.accessToken).apply();
                            println("Başarıyla kayıt olundu. Token ->" + t?.data?.accessToken)
                            Toast.makeText(
                                requireContext(),
                                "Başarıyla kayıt olundu!",
                                Toast.LENGTH_LONG
                            ).show();
                            findNavController().navigate(R.id.navigation_home)
                        }
                    },
                    { err -> println("Error -> " + err) },
                );
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}