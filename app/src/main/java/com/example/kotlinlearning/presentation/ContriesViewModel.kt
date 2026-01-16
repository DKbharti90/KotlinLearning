package com.example.kotlinlearning.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinlearning.doamin.DetailedCountry
import com.example.kotlinlearning.doamin.SimpleCountry
import com.example.kotlinlearning.doamin.use_cases.GetCountriesUseCase
import com.example.kotlinlearning.doamin.use_cases.GetCountryUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


@HiltViewModel
class ContriesViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val getCountryUseCase: GetCountryUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(CountriesState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            _state.update {
                it.copy(countries = getCountriesUseCase.execute(), isLoading = false)
            }
        }


    }

    fun selectCountry(code: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(selectedCountry=getCountryUseCase.execute(code))
            }
        }
    }

    fun dismissCountryDialog() {
        _state.update{
            it.copy(selectedCountry = null)
        }
    }


    data class CountriesState(
        val isLoading: Boolean = false,
        val countries: List<SimpleCountry>? = emptyList(),
        val selectedCountry: DetailedCountry? = null
    )


}
