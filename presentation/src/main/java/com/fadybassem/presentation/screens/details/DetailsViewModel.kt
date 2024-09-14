package com.fadybassem.presentation.screens.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.fadybassem.util.MOVIE_ID
import com.fadybassem.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel(), DefaultLifecycleObserver {

    val apiStatus = mutableStateOf<Status?>(Status.DEFAULT)

    private val movieID = savedStateHandle.get<Int>(MOVIE_ID)

}