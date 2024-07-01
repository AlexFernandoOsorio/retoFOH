package com.financiera.ecommerceapp.features.store

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.financiera.ecommerceapp.R
import com.financiera.ecommerceapp.databinding.FragmentStoreBinding
import com.financiera.ecommerceapp.domain.models.candys.CandyModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreFragment : Fragment(), CandysAdapter.OnRecipeClickListener {

    private lateinit var binding: FragmentStoreBinding
    private val viewmodel: StoreViewModel by viewModels()

    private lateinit var candyAdapter: CandysAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStoreBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        session()
        viewmodel.getCandyListPopularFromApi()
        binding.reciclerCandys.layoutManager = LinearLayoutManager(context)

        viewmodel.candyListModel.observe(viewLifecycleOwner){candyList ->
            if(candyList.isNotEmpty()){
                candyAdapter = CandysAdapter(candyList, this)
                binding.reciclerCandys.adapter = candyAdapter
                candyAdapter.notifyDataSetChanged()
            }
        }

        viewmodel.montoTotal.observe(viewLifecycleOwner){monto ->
            binding.storeMonto.text = "S/. ${monto}"
        }

        binding.storeButtonPay.setOnClickListener {
            findNavController().navigate(R.id.action_storeFragment_to_paymentFragment)
        }
    }

    private fun session(){
        val prefs = requireContext().getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val password = prefs.getString("password", null)
        val name = prefs.getString("name", null)

        if (email != null && password != null) {
            binding.storeUser.text = email
        }
    }

    override fun onRecipeClick(candys: CandyModel, position: Int) {
        val monto = viewmodel.candyListModel.value?.get(position)?.price
        if (monto != null) {
            viewmodel.montoTotal.value = viewmodel.montoTotal.value?.plus(monto.toDouble())
        }
    }
}
