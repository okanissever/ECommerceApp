package com.example.ecommerceapp.view.ui

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.ecommerceapp.R
import com.example.ecommerceapp.common.viewBinding
import com.example.ecommerceapp.databinding.FragmentPaymentBinding


class PaymentFragment : Fragment(R.layout.fragment_payment) {
    private val binding by viewBinding(FragmentPaymentBinding::bind)
    private val args : PaymentFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.let {
            binding.tvTotalAmount.text = it.totalAmount.toString()
        }

        binding.btnPayNow.setOnClickListener {
            findNavController().navigate(R.id.action_paymentFragment_to_homeFragment)
            Toast.makeText(context,"Payment Succesful...",Toast.LENGTH_LONG).show()
        }


        val months = arrayOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
        val monthAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, months)
        binding.actExpireOnMonth.setAdapter(monthAdapter)

        val yearList = listOf(2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030, 2031, 2032)
        val yearAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, yearList)
        binding.actExpireOnYear.setAdapter(yearAdapter)
    }





}