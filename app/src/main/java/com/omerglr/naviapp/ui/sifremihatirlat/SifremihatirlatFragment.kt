package com.omerglr.naviapp.ui.mesaj

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.omerglr.lovelica.api.ServiceBuilder
import com.omerglr.naviapp.R
import com.omerglr.naviapp.api.requests.RegisterRequest
import com.omerglr.naviapp.databinding.FragmentBegeniBinding
import com.omerglr.naviapp.databinding.FragmentRegisterBinding
import com.omerglr.naviapp.databinding.FragmentSifremihatirlatBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*

class SifremihatirlatFragment : Fragment() {

    private lateinit var sifremihatirlatViewModel: SifremihatirlatViewModel
    private var _binding: FragmentSifremihatirlatBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        sifremihatirlatViewModel =
            ViewModelProvider(this).get(SifremihatirlatViewModel::class.java)

        _binding = FragmentSifremihatirlatBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val button_sifrehatirlat: Button = root.findViewById(R.id.button_sifrehatirlat)

        button_sifrehatirlat.setOnClickListener {

            val email = email_text.text.toString().trim()
            val password = password.text.toString().trim()

            if (email.isEmpty()) {
                email_text.error = "Email Required"
                email_text.requestFocus()
                return@setOnClickListener

            }

            if (password.isEmpty()) {
                password_text.error = "Password Required"
                password_text.requestFocus()

                return@setOnClickListener
            }


            val params = RegisterRequest();

            params.password = password;
            params.email = email;

            params.combine = "AllowForHakan";

            ServiceBuilder.buildService(requireActivity()).sendRegisterRequest(params)
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
                            Toast.makeText(
                                requireContext(),
                                "Mail gönderildi!",
                                Toast.LENGTH_LONG
                            ).show();
                        }
                    },
                    { err -> println("Error -> " + err) },
                );

            findNavController().navigate(R.id.navigation_home)

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}