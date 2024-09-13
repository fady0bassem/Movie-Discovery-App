package com.fadybassem.presentation.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fadybassem.domain.model.Movie
import com.fadybassem.domain.usecase.GetPopularMoviesUseCase
import com.fadybassem.util.SORT_BY_POPULARITY_DESC
import com.fadybassem.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesUseCase: GetPopularMoviesUseCase,
) : ViewModel(), DefaultLifecycleObserver {

    val apiStatus = mutableStateOf<Status?>(Status.DEFAULT)

    private val _popularMovies = MutableStateFlow<List<Movie>>(emptyList())
    val popularMovies: StateFlow<List<Movie>> get() = _popularMovies

    val showApiError: MutableState<Pair<Boolean, String?>> = mutableStateOf(Pair(false, null))

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            moviesUseCase.execute(sortBy = SORT_BY_POPULARITY_DESC).collect {
                apiStatus.value = it.apiStatus
                if(it.apiStatus == Status.SUCCESS) {
                    it.data?.results?.let { movieArrayList ->
                        _popularMovies.value = movieArrayList
                    }
                } else if (it.apiStatus == Status.ERROR || it.apiStatus == Status.FAILED) {
                    showApiError.value = Pair(true, it.message)
                    _popularMovies.value = emptyList()
                }
            }
        }
    }
}