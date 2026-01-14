package com.example.kotlinlearning.ui.viewModel


import androidx.lifecycle.ViewModel
import com.example.kotlinlearning.data.repository.shared.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModelFromBaseViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
   // val userState :StateFlow<UiState<List<User>>> = flowUiState { userRepository.getUsersFlow() }
}