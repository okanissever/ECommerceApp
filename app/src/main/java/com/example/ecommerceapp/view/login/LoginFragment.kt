package com.example.ecommerceapp.view.login

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.ecommerceapp.R
import com.example.ecommerceapp.common.viewBinding
import com.example.ecommerceapp.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment(R.layout.fragment_login) {
    private val binding by viewBinding(FragmentLoginBinding::bind)
    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth
        val currentUser = auth.currentUser

        /*if(currentUser != null){
            val action = LoginFragmentDirections.actionLoginFragmentToNavGraph()
            findNavController().navigate(action)
        }*/

        binding.registerTextView.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            Navigation.findNavController(it).navigate(action)
        }

        binding.btnSignIn.setOnClickListener {
            signIn()
        }

    }

    private fun signIn(){
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(requireContext(),"Enter email or password",Toast.LENGTH_SHORT).show()
        }
        else{
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity()) { task->
                if(task.isSuccessful){
                    Log.d(TAG, "signInWithEmail:success")
                    val action = LoginFragmentDirections.actionLoginFragmentToNavGraph()
                    findNavController().navigate(action)

                }else{
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        requireContext(),
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
        }

    }
}