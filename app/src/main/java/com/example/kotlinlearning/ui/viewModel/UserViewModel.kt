package com.example.kotlinlearning.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinlearning.data.UiState
import com.example.kotlinlearning.data.entity.User
import com.example.kotlinlearning.data.repository.local.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) :ViewModel(){

    private val _userStateFlow = MutableStateFlow<UiState<List<User>>>(UiState.Loading)
    val userStateFlow: StateFlow<UiState<List<User>>> = _userStateFlow

    fun getAllUsers() {
        viewModelScope.launch {
            _userStateFlow.value = UiState.Loading
            try {
                val users = userRepository.getAll()
                _userStateFlow.value = if (users.isEmpty()) UiState.Empty else UiState.Success(users)
            } catch (e: Exception) {
                _userStateFlow.value = UiState.Error(e.localizedMessage ?: "Unknown Error")
            }
        }
    }
}