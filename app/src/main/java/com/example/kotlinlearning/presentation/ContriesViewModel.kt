package com.example.kotlinlearning.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import com.example.kotlinlearning.doamin.DetailedCountry
import com.example.kotlinlearning.doamin.SimpleCountry
import com.example.kotlinlearning.doamin.use_cases.GetCountriesUseCase
import com.example.kotlinlearning.doamin.use_cases.GetCountryUseCase
import javax.inject.Inject


@HiltViewModel
class ContriesViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val getCountryUseCase: GetCountryUseCase
) : ViewModel() {
    val _state = mutableStateOf(CountriesState())
    val state: State<CountriesState> = _state

    init {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {

            _state.value = state.value.copy(
                countries = getCountriesUseCase.execute(),
                isLoading = false
            )
        }


    }

    fun selectCountry(code: String) {
        viewModelScope.launch {
            _state.value = state.value.copy(
                selectedCountry = getCountryUseCase.execute(code)
            )
        }
    }

    fun dismissCountryDialog() {
        _state.value = state.value.copy(
            selectedCountry = null
        )
    }


    fun CountryState() {
        val isLoading: Boolean = false
        val countries: List<SimpleCountry>? = emptyList()
        val selectedCountry: DetailedCountry? = null


    }


}
