package com.thefork.challenge.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.thefork.challenge.api.UserService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserViewModel(
    private val userService: UserService
): ViewModel() {

    private val _uiState by lazy { MutableStateFlow<UserScreenState>(UserScreenState.Loading) }
    val uiState: StateFlow<UserScreenState>
        get() = _uiState

    fun getUserById(id: String?) {
        if (id.isNullOrEmpty()) {
            _uiState.update { UserScreenState.Error }
            return
        }

        viewModelScope.launch {
            val userPreview = userService.getUserById(id)
            _uiState.update {
                UserScreenState.Success(userPreview)
            }
        }
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun factory(userService: UserService) = object: ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return UserViewModel(userService) as T
            }
        }
    }
}