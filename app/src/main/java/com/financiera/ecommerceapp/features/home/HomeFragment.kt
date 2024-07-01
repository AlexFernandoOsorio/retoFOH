package com.financiera.ecommerceapp.features.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.financiera.ecommerceapp.R
import com.financiera.ecommerceapp.core.utils.GlobalConstants
import com.financiera.ecommerceapp.databinding.FragmentHomeBinding
import com.financiera.ecommerceapp.domain.models.movies.MovieModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), MoviesAdapter.OnRecipeClickListener {

    private lateinit var binding: FragmentHomeBinding
    private val viewmodel: HomeViewModel by viewModels()

    private lateinit var moviesAdapter: MoviesAdapter

    private var currentImageIndex = 0

    private suspend fun changeImage() {
        while (true) {
            if (moviesAdapter.itemCount == 0) {
                return
            }
            // Cambia al siguiente ítem en el RecyclerView
            currentImageIndex = (currentImageIndex + 1) % moviesAdapter.itemCount
            binding.popularRecyclerView.smoothScrollToPosition(currentImageIndex)

            delay(3000)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

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

                lifecycleScope.launch(Dispatchers.IO) {
                    changeImage()
                }

                binding.hlMovieTitle.text = moviesList[0].title
                Glide.with(requireContext())
                    .load(GlobalConstants.POSTER_URL + moviesList[0].image)
                    .centerCrop()
                    .into(binding.hlMovieImage);
            }
        }
    }


    override fun onRecipeClick(movies: MovieModel, position: Int) {
        val movie = viewmodel.moviesListModel.value?.get(position)
        findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
