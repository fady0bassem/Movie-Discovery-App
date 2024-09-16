package com.fadybassem.presentation.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.fadybassem.domain.model.Movie
import com.fadybassem.domain.usecase.movie.Get2024MoviesPagingSourceUseCase
import com.fadybassem.domain.usecase.movie.GetPopularMoviesUseCase
import com.fadybassem.util.SORT_BY_POPULARITY_DESC
import com.fadybassem.util.SORT_BY_RELEASE_DATE_DESC
import com.fadybassem.util.Status
import com.fadybassem.util.YEAR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesUseCase: GetPopularMoviesUseCase,
    private val get2024MoviesPagingSourceUseCase: Get2024MoviesPagingSourceUseCase,
) : ViewModel(), DefaultLifecycleObserver {

    val apiStatus = mutableStateOf<Status?>(Status.DEFAULT)

    private val _popularMovies = MutableStateFlow<List<Movie>>(emptyList())
    val popularMovies: StateFlow<List<Movie>> get() = _popularMovies

    private val _moviesPageFlow =
        MutableStateFlow<PagingData<Pair<Movie, String>>>(PagingData.empty())
    val moviesPageFlow: StateFlow<PagingData<Pair<Movie, String>>> = _moviesPageFlow

    val showApiError: MutableState<Pair<Boolean, String?>> = mutableStateOf(Pair(false, null))

    var popularMoviesScrollIndex by mutableIntStateOf(0)
    var popularMoviesScrollOffset by mutableIntStateOf(0)
    var moviesScrollIndex by mutableIntStateOf(0)
    var moviesScrollOffset by mutableIntStateOf(0)

    init {
        getMoviesSequentially()
    }

    private fun getMoviesSequentially() {
        viewModelScope.launch {
            fetchPopularMovies()
            fetch2024MoviesPagingSource()
        }
    }

    private suspend fun fetchPopularMovies() {
        moviesUseCase.execute(sortBy = SORT_BY_POPULARITY_DESC).collect {
            apiStatus.value = it.apiStatus

            it.data?.results?.let { movieArrayList ->
                if (_popularMovies.value != movieArrayList) {
                    _popularMovies.value = movieArrayList
                }
            }

            if (it.apiStatus == Status.ERROR || it.apiStatus == Status.FAILED) {
                showApiError.value = Pair(true, it.message)
            }
        }
    }

    private suspend fun fetch2024MoviesPagingSource() {
        get2024MoviesPagingSourceUseCase.execute(
            sortBy = SORT_BY_RELEASE_DATE_DESC, year = YEAR, scope = viewModelScope
        ).collect {
            apiStatus.value = it.apiStatus

            it.data?.let { movieArrayList ->
                val transformedPagingData = movieArrayList.map { movie ->
                    val releaseMonth = LocalDate.parse(movie.releaseDate).month.toString()
                    movie to releaseMonth
                }

                if (_moviesPageFlow.value != transformedPagingData) {
                    _moviesPageFlow.value = transformedPagingData
                }
            }

            if (it.apiStatus == Status.ERROR || it.apiStatus == Status.FAILED) {
                showApiError.value = Pair(true, it.message)
            }
        }
    }
}