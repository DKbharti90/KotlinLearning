package com.example.kotlinlearning.data.apollo_client

import com.apollographql.apollo.ApolloClient
import com.example.kotlinlearning.GetCountriesQuery
import com.example.kotlinlearning.GetCountyQuery
import com.example.kotlinlearning.data.mapper.toDetailedCountry
import com.example.kotlinlearning.data.mapper.toSimpleCountry
import com.example.kotlinlearning.doamin.CountryClient
import com.example.kotlinlearning.doamin.DetailedCountry
import com.example.kotlinlearning.doamin.SimpleCountry
import javax.inject.Inject

class ApolloCountryClient @Inject constructor(private val apolloClient: ApolloClient): CountryClient {
    override suspend fun getCountries(): List<SimpleCountry> {
       // apolloClient.query(()).execute()
        return apolloClient.query(GetCountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map {it.toSimpleCountry()}
            ?:emptyList()
    }

    override suspend fun getCountry(code: String): DetailedCountry ? {
      return apolloClient
          .query(GetCountyQuery(code))
          .execute()
          .data
          ?.country?.toDetailedCountry()
    }
}

