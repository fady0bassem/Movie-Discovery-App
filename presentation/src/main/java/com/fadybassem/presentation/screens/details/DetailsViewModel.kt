package com.fadybassem.presentation.screens.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fadybassem.core.R.string
import com.fadybassem.core.ResourceProvider
import com.fadybassem.domain.model.Credits
import com.fadybassem.domain.model.Movie
import com.fadybassem.domain.model.Movies
import com.fadybassem.domain.usecase.GetMovieDetailsCreditsUseCase
import com.fadybassem.domain.usecase.GetMovieDetailsSimilarUseCase
import com.fadybassem.domain.usecase.GetMovieDetailsUseCase
import com.fadybassem.util.MOVIE_ID
import com.fadybassem.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getMovieDetailsCreditsUseCase: GetMovieDetailsCreditsUseCase,
    private val getMovieDetailsSimilarUseCase: GetMovieDetailsSimilarUseCase,
    private val resourceProvider: ResourceProvider,
) : ViewModel(), DefaultLifecycleObserver {

    val apiStatus = mutableStateOf<Status?>(Status.DEFAULT)

    private val movieID = savedStateHandle.get<Int>(MOVIE_ID)

    private val _movieDetails = MutableStateFlow<Movie?>(null)
    val movieDetails: StateFlow<Movie?> get() = _movieDetails

    private val _movieDetailsCredit = MutableStateFlow<Credits?>(null)
    val movieDetailsCredit: StateFlow<Credits?> get() = _movieDetailsCredit

    private val _movieDetailsSimilar = MutableStateFlow<Movies?>(null)
    val movieDetailsSimilar: StateFlow<Movies?> get() = _movieDetailsSimilar

    val showApiError: MutableState<Pair<Boolean, String?>> = mutableStateOf(Pair(false, null))

    init {
        getScreenDetailsRequests()
    }

    private fun getScreenDetailsRequests() {
        if (movieID != null) {
            getMovieDetails(id = movieID)
            getMovieDetailsCredits(id = movieID)
            getMovieDetailsSimilar(id = movieID)
        } else {
            showApiError.value = Pair(true, resourceProvider.getString(string.something_went_wrong))
        }
    }

    private fun getMovieDetails(id: Int) {
        viewModelScope.launch {
            getMovieDetailsUseCase.execute(id = id).collect {
                apiStatus.value = it.apiStatus
                if (it.apiStatus == Status.SUCCESS) {
                    it.data?.let { movies ->
                        _movieDetails.value = movies
                    }
                } else if (it.apiStatus == Status.ERROR || it.apiStatus == Status.FAILED) {
                    showApiError.value = Pair(true, it.message)
                    _movieDetails.value = null
                }
            }
        }
    }

    private fun getMovieDetailsCredits(id: Int) {
        viewModelScope.launch {
            getMovieDetailsCreditsUseCase.execute(id = id).collect {
                apiStatus.value = it.apiStatus
                if (it.apiStatus == Status.SUCCESS) {
                    it.data?.let { credits ->
                        _movieDetailsCredit.value = credits
                    }
                } else if (it.apiStatus == Status.ERROR || it.apiStatus == Status.FAILED) {
                    showApiError.value = Pair(true, it.message)
                    _movieDetails.value = null
                }
            }
        }
    }

    private fun getMovieDetailsSimilar(id: Int) {
        viewModelScope.launch {
            getMovieDetailsSimilarUseCase.execute(id = id).collect {
                apiStatus.value = it.apiStatus
                if (it.apiStatus == Status.SUCCESS) {
                    it.data?.let { movies ->
                        _movieDetailsSimilar.value = movies
                    }
                } else if (it.apiStatus == Status.ERROR || it.apiStatus == Status.FAILED) {
                    showApiError.value = Pair(true, it.message)
                    _movieDetails.value = null
                }
            }
        }
    }
}