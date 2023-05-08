package com.example.ecommerceapp.view.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.ecommerceapp.R
import com.example.ecommerceapp.common.viewBinding
import com.example.ecommerceapp.databinding.FragmentLoginBinding
import com.example.ecommerceapp.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val binding by viewBinding(FragmentProfileBinding::bind)
    private lateinit var auth: FirebaseAuth
    private val firestore: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        auth = Firebase.auth
        val userId = auth.currentUser?.uid
        binding.loadingIndicator.visibility = View.VISIBLE
        binding.infoContainer.visibility = View.GONE
        if(userId!= null){
            firestore.collection("users").document(userId).get().addOnSuccessListener {document->
            if(document!=null){
                val username = document.getString("username")
                val phone = document.getString("phone")
                val email = document.getString("email")

                binding.tvEmail.text = email
                binding.tvNickname.text = username
                binding.tvPhoneNumber.text = phone
            }else {
                Log.d(TAG, "No such document")
            }
                binding.loadingIndicator.visibility = View.GONE
                binding.infoContainer.visibility = View.VISIBLE

            } .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
                binding.loadingIndicator.visibility = View.GONE
                binding.infoContainer.visibility = View.VISIBLE
            }
        }else {
            Log.d(TAG, "User not logged in")
            binding.loadingIndicator.visibility = View.GONE
            binding.infoContainer.visibility = View.VISIBLE
        }

        /*binding.btnSignOut.setOnClickListener {
            auth.signOut()
            val action = ProfileFragmentDirections.actionProfileFragmentToLogin()
            Navigation.findNavController(it).navigate(action)
        }*/


    }
}