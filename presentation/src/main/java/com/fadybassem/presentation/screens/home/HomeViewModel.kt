package com.fadybassem.presentation.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fadybassem.domain.usecase.GetPopularMoviesUseCase
import com.fadybassem.util.POPULAR_MOVIES_SORT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesUseCase: GetPopularMoviesUseCase
) : ViewModel(){


    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            moviesUseCase.execute(sortBy = POPULAR_MOVIES_SORT).collect{
                Log.d("getMovies", "getMovies: ${it.toString()}")
            }
        }
    }
}