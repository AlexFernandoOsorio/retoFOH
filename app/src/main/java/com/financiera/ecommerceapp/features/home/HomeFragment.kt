package com.financiera.ecommerceapp.features.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.financiera.ecommerceapp.R
import com.financiera.ecommerceapp.core.utils.GlobalConstants
import com.financiera.ecommerceapp.databinding.FragmentHomeBinding
import com.financiera.ecommerceapp.domain.models.movies.MovieModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), MoviesAdapter.OnRecipeClickListener {

    private lateinit var binding: FragmentHomeBinding
    private val viewmodel: HomeViewModel by viewModels()

    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.getMovieListPopularFromApi()

        binding.popularRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        viewmodel.moviesListModel.observe(viewLifecycleOwner){moviesList ->
            if(moviesList.isNotEmpty()){
                moviesAdapter = MoviesAdapter(moviesList, this)
                binding.popularRecyclerView.adapter = moviesAdapter
                moviesAdapter.notifyDataSetChanged()

                Glide.with(requireContext())
                    .load(GlobalConstants.poster_path + moviesList[0].image)
                    .centerCrop()
                    .into(binding.hlMovieImage);
            }
        }
    }


    override fun onRecipeClick(movies: MovieModel, position: Int) {
        val movie = viewmodel.moviesListModel.value?.get(position)
        findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
    }
}
