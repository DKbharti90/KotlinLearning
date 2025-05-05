package com.example.kotlinlearning.ui.viewModel

import com.example.kotlinlearning.data.UiState
import com.example.kotlinlearning.data.entity.User
import com.example.kotlinlearning.data.repository.local.UserRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class UserViewModelFromBaseViewModel @Inject constructor(private val userRepository: UserRepository) : BaseViewModel() {
    val userState :StateFlow<UiState<List<User>>> =flowUiState { userRepository.getAll() }
}