package com.kalebkircher.wubbalubbadubdub.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kalebkircher.wubbalubbadubdub.R

@Composable
fun Loading(@StringRes loadingMessage: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(0.dp, 16.dp, 0.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        Icon(
            modifier = Modifier.size(64.dp),
            painter = painterResource(id = R.drawable.rick),
            contentDescription = stringResource(R.string.label_loading_icon),
        )

        Text(
            modifier = Modifier
                .padding(8.dp),
            text = stringResource(loadingMessage)
        )

        CircularProgressIndicator(Modifier.padding(8.dp))
    }
}