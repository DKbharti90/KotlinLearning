package com.example.kotlinlearning.doamin

interface CountryClient {
    suspend fun getCountries(): List<SimpleCountry>
    suspend fun getCountry(code:String) : DetailedCountry?
}