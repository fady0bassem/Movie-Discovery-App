package com.fadybassem.presentation.screens.watchlist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fadybassem.domain.model.Movie
import com.fadybassem.domain.usecase.watchlist.GetWatchlistMoviesUseCase
import com.fadybassem.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchlistViewModel @Inject constructor(
    private val getWatchlistMoviesUseCase: GetWatchlistMoviesUseCase,
) : ViewModel(), DefaultLifecycleObserver {

    val apiStatus = mutableStateOf<Status?>(Status.DEFAULT)

    private val _watchlistMovies = MutableLiveData<List<Movie>>()
    val watchlistMovies: LiveData<List<Movie>> = _watchlistMovies

    private fun fetchWatchlistMovies() = viewModelScope.launch {
        _watchlistMovies.value = emptyList()
        _watchlistMovies.value = getWatchlistMoviesUseCase.execute()
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        fetchWatchlistMovies()
    }
}