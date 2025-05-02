package com.example.kotlinlearning.ui.adapter.module

import com.example.kotlinlearning.data.entity.Module

sealed class ModuleListItem(val name: String) {
    data class Item(val module: Module) : ModuleListItem(module.name)
    data class Separator(private val letter: Char) : ModuleListItem(letter.uppercaseChar().toString())
}
