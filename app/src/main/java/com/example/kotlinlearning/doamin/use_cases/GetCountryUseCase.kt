package com.example.kotlinlearning.doamin.use_cases

import com.example.kotlinlearning.doamin.CountryClient
import com.example.kotlinlearning.doamin.DetailedCountry
import javax.inject.Inject

class GetCountryUseCase @Inject constructor(private val countryClient: CountryClient) {
    suspend fun execute(countryCode: String): DetailedCountry? {
        return countryClient
            .getCountry(countryCode)
    }
}
