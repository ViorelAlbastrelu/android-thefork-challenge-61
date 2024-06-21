package com.thefork.challenge.user

import com.thefork.challenge.api.UserPreview

sealed interface UserScreenState {
    data class Success(val userPreview: UserPreview): UserScreenState
    object Loading: UserScreenState
    object Error: UserScreenState
}