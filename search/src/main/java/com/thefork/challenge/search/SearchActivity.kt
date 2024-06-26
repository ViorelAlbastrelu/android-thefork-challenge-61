package com.thefork.challenge.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.thefork.challenge.api.Api
import com.thefork.challenge.api.UserPreview


class SearchActivity : AppCompatActivity(), SearchScreen {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        SearchPresenter(Api().userService).init(this)
    }

    override fun displayUsers(users: List<UserPreview>) {
        findViewById<RecyclerView>(R.id.recycler_view).adapter = UsersAdapter(users) { view ->
            navigateToUser(view.tag as String)
        }
    }

    override fun displayError() {
        Snackbar
            .make(findViewById(R.id.recycler_view), R.string.error, Snackbar.LENGTH_LONG)
            .show()
    }

    private fun navigateToUser(userId: String) {
        val deepLinkUri = Uri.parse("thefork://challenge/profile?userid=$userId")
        val intent = Intent(Intent.ACTION_VIEW, deepLinkUri)
        startActivity(intent)

    }
}
