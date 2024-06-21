package com.thefork.challenge.user

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.thefork.challenge.api.UserPreview
import kotlinx.coroutines.flow.StateFlow

@Composable
fun UserDestination(state: StateFlow<UserScreenState>) {
    val uiState: UserScreenState by state.collectAsState()
    MaterialTheme(
        colorScheme = darkColorScheme()
    ) {
        when (uiState) {
            UserScreenState.Error -> ErrorScreen()
            UserScreenState.Loading -> LoadingScreen()
            is UserScreenState.Success -> UserScreen((uiState as UserScreenState.Success).userPreview)
        }
    }

}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun UserScreen(userPreview: UserPreview, modifier: Modifier = Modifier) {
    val painter = rememberImagePainter(userPreview.picture) {
        crossfade(true)
    }
    Column(
        verticalArrangement = Arrangement.Top,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(32.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = "Profile picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(width = 88.dp, height = 88.dp)
                    .clip(shape = CircleShape)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.onSurface,
                        shape = CircleShape
                    )
            )
            Text(
                text = "${userPreview.firstName} ${userPreview.lastName}",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = "Senior Accountant",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.outline
            )
        }
        UserContactInfo()
    }
}

@Composable
fun UserContactInfo(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "CONTACT INFORMATION",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.padding(start = 8.dp)
        )
        Divider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onSurface,
        )

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(4) {
                UserDetailRow(title = "Email", value = "example@email.com")
            }
        }

        Divider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.size(32.dp))
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    MessageScreen(message = "Loading...", modifier)
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    MessageScreen(message = "Something went wrong!", modifier)
}

@Composable
fun MessageScreen(message: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.outline
        )
    }
}

@Composable
fun UserDetailRow(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.outline
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun PreviewUserScreen() {
    UserScreen(
        UserPreview(
            id = "",
            title = "Senior Accountant",
            firstName = "Jesse",
            lastName = "Smith",
            ""
        )
    )
}

@Preview
@Composable
private fun PreviewErrorScreen() {
    ErrorScreen()
}