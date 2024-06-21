package com.thefork.challenge.search

import com.thefork.challenge.api.UserPreview

interface SearchScreen {

    fun displayUsers(users: List<UserPreview>)
    fun displayError()
}