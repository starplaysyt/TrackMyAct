package org.js.tma.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Photo
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.js.tma.service.HttpKtorService

@Composable
fun TestScreen(
    httpKtorService: HttpKtorService
) {

    var testVal by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        AppButtonMedium(
            text = "AppButtonMediumАБВГД",
        )

        Spacer(modifier = Modifier.height(24.dp))

        AppButtonLarge(
            text = "AppButtonLargeАБВГД",
            onClick = {

            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        AppTextField(
            value = testVal,
            onValueChange = { testVal = it },
            placeholder = "AppTextFieldPlaceholderАБВГД",
        )

        Spacer(modifier = Modifier.height(24.dp))

        AppPasswordStrengthLevelIndicator(testVal)

        Spacer(modifier = Modifier.height(24.dp))

        AppPasswordStrengthLevelIndicatorGradient(testVal)

        Spacer(modifier = Modifier.height(24.dp))

        AppPasswordStrengthLevelIndicatorSwap(testVal)

        Spacer(modifier = Modifier.height(24.dp))

        AppCategoryCard(
            title = "AppCategoryCardАБВГД",
            description = "AppCategoryCardDescr"
        )

        Spacer(modifier = Modifier.height(24.dp))

        AppSmallText("AppSmallTextАБВГД")

        Spacer(modifier = Modifier.height(24.dp))

        AppMediumText("AppMediumTextАБВГД")

        Spacer(modifier = Modifier.height(24.dp))

        AppLargeText("AppLargeText14АБВГД")

        Spacer(modifier = Modifier.height(24.dp))

        AppLargeText("AppLargeText32АБВГД", size = 32.sp)
    }
}