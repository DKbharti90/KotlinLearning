package com.example.kotlinlearning.data.mapper

import com.example.kotlinlearning.GetCountriesQuery
import com.example.kotlinlearning.GetCountyQuery
import com.example.kotlinlearning.doamin.DetailedCountry
import com.example.kotlinlearning.doamin.SimpleCountry

fun GetCountyQuery.Country.toDetailedCountry(): DetailedCountry{
    return DetailedCountry(
        code=code,
        name=name,
        capital = capital ?: "No capital" ,
        emoji=emoji ,
        currency=currency ?:"No currency",
        languages=languages.mapNotNull { it.name },
        continent= continent.name
    )
}

fun GetCountriesQuery.Country.toSimpleCountry(): SimpleCountry{
    return SimpleCountry(
        code=code,
        name=name,
        capital = capital ?: "No capital" ,
        emoji=emoji ,
        currency=currency ?:"No currency",
    )
}