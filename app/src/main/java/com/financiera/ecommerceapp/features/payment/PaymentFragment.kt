package com.financiera.ecommerceapp.features.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.financiera.ecommerceapp.databinding.FragmentPaymentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentFragment : Fragment() {

    private lateinit var binding: FragmentPaymentBinding
    private val viewModel: PaymentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaymentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.submitButton.setOnClickListener {
            val cardNumber = binding.cardNumber.text.toString()
            val expirationDate = binding.expirationDate.text.toString()
            val cvv = binding.cvv.text.toString()
            val email = binding.email.text.toString()
            val documentNumber = binding.documentNumber.text.toString()
            if (!validateCardNumber() || !validateExpirationDate() || !validateCVV() || !validateEmail() || !validateDocumentNumber()) {
                return@setOnClickListener
            }
            val name = binding.name.text.toString()
            val documentType = binding.documentType.selectedItem.toString()
            viewModel.pushPaymentToApi(cardNumber, expirationDate, cvv, email, name, documentType, documentNumber)

        }
    }

    private fun validateCardNumber(): Boolean {
        val cardNumber = binding.cardNumber.text.toString()
        return if (!luhnCheck(cardNumber)) {
            binding.cardNumber.error = "Invalid card number"
            false
        } else true
    }

    private fun validateExpirationDate(): Boolean {
        val expirationDate = binding.expirationDate.text.toString()
        val expirationDatePattern = "^(0[1-9]|1[0-2])/[0-9]{2}$".toRegex()
        return if (!expirationDatePattern.matches(expirationDate)) {
            binding.expirationDate.error = "Invalid expiration date"
            false
        } else true
    }

    private fun validateCVV(): Boolean {
        val cvv = binding.cvv.text.toString()
        return if (cvv.length != 3) {
            binding.cvv.error = "Invalid cvv"
            false
        } else true
    }

    private fun validateEmail(): Boolean {
        val email = binding.email.text.toString()
        val emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$".toRegex()
        return if (!emailPattern.matches(email)) {
            binding.email.error = "Invalid email"
            false
        } else true
    }

    private fun validateDocumentNumber(): Boolean {
        val documentNumber = binding.documentNumber.text.toString()
        return if (documentNumber.length != 8) {
            binding.documentNumber.error = "Invalid document number"
            false
        } else true
    }

    private fun luhnCheck(cardNumber: String): Boolean {
        val digits = cardNumber.filter { it.isDigit() }.map { it.toString().toInt() }
        return digits.reversed().mapIndexed { index, d ->
            if (index % 2 == 1) d * 2 % 9 else d
        }.sum() % 10 == 0
    }
}