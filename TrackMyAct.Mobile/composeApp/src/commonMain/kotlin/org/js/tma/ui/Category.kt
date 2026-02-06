package org.js.tma.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.js.tma.data.stateValue
import org.js.tma.viewmodel.CategoryViewModel

@Composable
fun AllCategories(
    categoryViewModel: CategoryViewModel
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

        categoryViewModel.categories.stateValue().forEach { category ->
            AppCategoryCard(
                image = category.image,
                title = category.title,
                description = category.description,
            )
        }
    }
}