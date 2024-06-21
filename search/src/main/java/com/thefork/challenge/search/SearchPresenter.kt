package com.thefork.challenge.search

import com.thefork.challenge.api.UserService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchPresenter(
    private val userService: UserService,
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    fun init(view: SearchScreen) {
        CoroutineScope(mainDispatcher).launch {
            val response = withContext(ioDispatcher) { userService.getUsers(1u) }

            if (response.isSuccessful) {
                if (response.body() != null)
                    view.displayUsers(response.body()!!.data)
                else {
                    view.displayError()
                }
            } else {
                view.displayError()
            }
        }
    }

}
