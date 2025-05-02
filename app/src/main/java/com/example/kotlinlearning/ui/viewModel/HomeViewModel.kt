package com.example.kotlinlearning.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.kotlinlearning.data.entity.Module
import com.example.kotlinlearning.data.repository.local.ModuleRepository
import com.example.kotlinlearning.utils.ioThread
import com.example.kotlinlearning.ui.adapter.module.ModuleListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: ModuleRepository) : ViewModel() {

    val allModule : Flow<PagingData<ModuleListItem>> = Pager(
        config = PagingConfig(
            pageSize = 60,
            enablePlaceholders = true,
            maxSize = 200,
        )
    ){
        repository.allModuleByName()
    }.flow.map { pagingData ->
        pagingData
            // Map cheeses to common UI model.
            .map { module -> ModuleListItem.Item(module) }
            .insertSeparators { before: ModuleListItem?, after: ModuleListItem? ->
                if (before == null && after == null) {
                    // List is empty after fully loaded; return null to skip adding separator.
                    null
                } else if (after == null) {
                    // Footer; return null here to skip adding a footer.
                    null
                } else if (before == null) {
                    // Header
                    ModuleListItem.Separator(after.name.first())
                } else if (!before.name.first().equals(after.name.first(), ignoreCase = true)){
                    // Between two items that start with different letters.
                    ModuleListItem.Separator(after.name.first())
                } else {
                    // Between two items that start with the same letter.
                    null
                }
            }
    }.cachedIn(viewModelScope)

    fun insert(text: CharSequence) = ioThread {
        repository.insert(Module(id = 0, name = text.toString(), navigation = text.toString()))
    }

    fun remove(module : Module) = ioThread {
        repository.delete(module)
    }




    suspend fun insertModule() {
        repository.insert(Module(name = "USER", navigation = "USER"))
        repository.insert(Module(name = "Employee",navigation = "USER"))
        repository.insert(Module(name = "RRARRA",navigation = "USER"))
        repository.insert(Module(name = "DDDD", navigation ="USER"))
        repository.insert(Module(name = "RRRR", navigation ="USER"))
        repository.insert(Module(name = "TTTTT",navigation = "USER"))
        repository.insert(Module(name = "RRRRR",navigation = "USER"))
        repository.insert(Module(name = "$$$$$",navigation = "USER"))
        repository.insert(Module(name = "FFFFFF",navigation = "USER"))
        repository.insert(Module(name =  "VVVVVV",navigation = "USER"))
        repository.insert(Module(name =  "DDDDDD", navigation ="USER"))
        repository.insert(Module( name = "YYYYYY", navigation ="USER"))
    }


}