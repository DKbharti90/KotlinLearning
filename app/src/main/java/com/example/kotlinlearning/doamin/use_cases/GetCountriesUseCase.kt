package com.example.kotlinlearning.doamin.use_cases

import com.example.kotlinlearning.doamin.CountryClient
import com.example.kotlinlearning.doamin.SimpleCountry
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(private val countryClient: CountryClient) {
    suspend fun execute(): List<SimpleCountry> {
        return countryClient
            .getCountries()
            .sortedBy { it.name }
    }
}