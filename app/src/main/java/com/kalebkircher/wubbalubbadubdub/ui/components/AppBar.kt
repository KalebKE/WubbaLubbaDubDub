package com.kalebkircher.wubbalubbadubdub.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kalebkircher.wubbalubbadubdub.R

class AppBar {
    @SuppressLint("NotConstructor")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AppBar() {
        TopAppBar(
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        painter = painterResource(id = R.drawable.rick_and_morty),
                        contentDescription = "Rick and Morty Icon",
                    )

                    Icon(
                        modifier = Modifier
                            .fillMaxWidth(fraction = 0.25f)
                            .padding(8.dp, 0.dp, 0.dp, 0.dp),
                        painter = painterResource(id = R.drawable.rick_and_morty_text),
                        contentDescription = "Rick and Morty Text",
                    )
                }
            }
        )
    }
}