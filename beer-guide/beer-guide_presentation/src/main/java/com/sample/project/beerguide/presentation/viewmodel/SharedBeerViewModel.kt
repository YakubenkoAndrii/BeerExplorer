package com.sample.project.beerguide.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.project.beerguide.domain.model.Beer
import com.sample.project.beerguide.domain.repository.ProductsRepository
import com.sample.project.core.data.state.Result
import com.sample.project.core.data.state.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedBeerViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
) : ViewModel() {

    private val _beerDetailsUiState =
        MutableStateFlow<BeerDetailsUiState>(BeerDetailsUiState.Loading)
    val beerDetailsUiState = _beerDetailsUiState.asStateFlow()

    val beerGuideUiState: StateFlow<BeerGuideUiState> = beerGuideUiUiState(
        productsRepository = productsRepository
    )
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = BeerGuideUiState.Loading
        )

    private fun beerGuideUiUiState(
        productsRepository: ProductsRepository
    ): Flow<BeerGuideUiState> {
        // Get the list of beers
        val beerList: Flow<List<Beer>> = productsRepository.getBeers()
        return beerList
            .asResult()
            .map { result ->
                when (result) {
                    is Result.Success -> {
                        BeerGuideUiState.Success(
                            result.data
                        )
                    }
                    is Result.Loading -> {
                        BeerGuideUiState.Loading
                    }
                    is Result.Error -> {
                        BeerGuideUiState.Error(
                            result.exception?.message
                        )
                    }
                }
            }
    }

    fun getBeerDetailsById(beerId: Long) {
        viewModelScope.launch {
            when (val result = productsRepository.getBeerDetailsById(beerId).asResult()) {
                is Result.Success -> {
                    result.data?.let { beer ->
                        _beerDetailsUiState.value = BeerDetailsUiState.Success(beer)
                    }
                }
                is Result.Loading -> {
                    _beerDetailsUiState.value = BeerDetailsUiState.Loading
                }
                is Result.Error -> {
                    _beerDetailsUiState.value = BeerDetailsUiState.Error(result.exception?.message)
                }
            }
        }
    }

    sealed class BeerGuideUiState {
        data class Success(val beerItems: List<Beer>) : BeerGuideUiState()
        data class Error(val error: String?) : BeerGuideUiState()
        object Loading : BeerGuideUiState()
    }

    sealed class BeerDetailsUiState {
        data class Success(val beer: Beer) : BeerDetailsUiState()
        data class Error(val error: String?) : BeerDetailsUiState()
        object Loading : BeerDetailsUiState()
    }
}
