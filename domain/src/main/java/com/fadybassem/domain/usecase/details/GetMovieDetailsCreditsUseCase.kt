package com.fadybassem.domain.usecase.details

import com.fadybassem.domain.model.Credits
import com.fadybassem.domain.repository.MovieRepository
import com.fadybassem.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieDetailsCreditsUseCase @Inject constructor(private val repository: MovieRepository) {
    fun execute(id: Int): Flow<Resource<Credits>> = flow {
        repository.getMovieDetailsCredits(id = id).collect { resource ->
            if (resource is Resource.Success) {
                val credits = resource.data
                credits?.let {
                    it.cast = ArrayList(it.cast.distinctBy { castMember -> castMember.id })
                    it.crew = ArrayList(it.crew.distinctBy { crewMember -> crewMember.id })
                }
                emit(Resource.Success(credits))
            } else {
                emit(resource)
            }
        }
    }
}