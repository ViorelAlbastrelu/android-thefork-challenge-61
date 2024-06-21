package com.thefork.challenge.user

import android.R
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.thefork.challenge.api.Api


@Suppress("DEPRECATION")
class UserActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels {
        UserViewModel.factory(Api().userService)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.apply {
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
        val selectedUserId = intent.getStringExtra("USER_ID")
        userViewModel.getUserById(selectedUserId)

        setContent {
            UserDestination(state = userViewModel.uiState)
        }
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.home -> {
                super.onBackPressed()
                return true
            }
        }
        return (super.onOptionsItemSelected(menuItem))
    }

}
