package com.example.kotlinlearning.ui.adapter.cheese

import com.example.kotlinlearning.data.entity.Cheese

/**
 * Common UI model between the [Cheese] data class and separators.
 */
sealed class CheeseListItem(val name: String) {
    data class Item(val cheese: Cheese) : CheeseListItem(cheese.name)
    data class Separator(private val letter: Char) : CheeseListItem(letter.uppercaseChar().toString())
}
