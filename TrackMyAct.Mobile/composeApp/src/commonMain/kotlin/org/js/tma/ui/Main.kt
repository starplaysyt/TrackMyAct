package org.js.tma.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.js.tma.data.stateValue
import org.js.tma.viewmodel.MainViewModel

@Composable
fun Main(
    mainViewModel: MainViewModel
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp)
        ) {
            AppLargeText(
                text = "Категории",
                size = 32.sp
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        mainViewModel.events.stateValue().forEach { event ->
            AppCategoryCard(
                image = null,
                title = event.name,
                description = event.description,
            )
        }
    }

}