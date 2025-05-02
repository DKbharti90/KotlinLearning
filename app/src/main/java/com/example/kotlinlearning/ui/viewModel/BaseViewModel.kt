package com.example.kotlinlearning.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinlearning.data.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    fun <T> flowUiState(loader: suspend ()->T) : StateFlow<UiState<T>> {
        val stateFlow = MutableStateFlow<UiState<T>>(UiState.Loading)
        viewModelScope.launch {
            try {
                val data = loader();
                val isEmpty = (data as? Collection<*>)?.isEmpty() ?: false
                stateFlow.value = if (isEmpty) UiState.Empty else UiState.Success(data)
            }catch (e:Exception){
                stateFlow.value = UiState.Error(e.localizedMessage?:"Something went wrong")
            }
        }

        return stateFlow
    }


}