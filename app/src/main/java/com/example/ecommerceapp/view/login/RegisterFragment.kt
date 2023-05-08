package com.example.ecommerceapp.view.login

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ecommerceapp.R
import com.example.ecommerceapp.common.viewBinding
import com.example.ecommerceapp.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class RegisterFragment : Fragment(R.layout.fragment_register) {
    private val binding by viewBinding(FragmentRegisterBinding::bind)
    private lateinit var auth: FirebaseAuth
    private val firestore: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        binding.btnRegister.setOnClickListener {
            registerUser()
        }

    }

    private fun registerUser(){
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val phone = binding.etPhone.text.toString()
        val username = binding.etUsername.text.toString()
        val name = binding.etName.text.toString()

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(requireContext(),"Enter email or password",Toast.LENGTH_SHORT).show()
        }
        else{
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(requireActivity()){task->
            if(task.isSuccessful){
                val userId = auth.currentUser?.uid

                val userMap = hashMapOf(
                    "name" to name,
                    "username" to username,
                    "phone" to phone,
                    "email" to email
                )

                firestore.collection("users").document(userId!!)
                    .set(userMap)
                    .addOnSuccessListener {
                        println("Kullanıcı Bilgileri başarıyla kaydedildi.")
                        val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                        findNavController().navigate(action)
                    }
                    .addOnFailureListener { e ->
                        // Hata meydana geldi
                        Log.w(TAG, "Error adding user data", e)
                        Toast.makeText(
                            requireContext(),
                            "Error adding user data.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

            }
            else{
                Log.w(TAG, "createUserWithEmail:failure", task.exception)
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