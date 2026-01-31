package org.js.tma.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.js.tma.data.HeapStringLink
import trackmyact.composeapp.generated.resources.Res
import trackmyact.composeapp.generated.resources.logo_dark
import trackmyact.composeapp.generated.resources.logo_light

@Composable
fun LoginScreen(
    darkTheme: Boolean,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 46.dp,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(modifier = Modifier.height(14.dp))

        Image(
            painter = if (darkTheme) painterResource(Res.drawable.logo_dark) else painterResource(Res.drawable.logo_light),
            contentDescription = null,
        )

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    vertical = 14.dp,
                    horizontal = 39.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AppLargeText(
                value = "Логин",
                size = 32.sp,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(8.dp))

            AppSmallText(
                value = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(35.dp))

            AppReadOnlyTextField(
                value = "Логин",
                placeholder = ""
            )

            Spacer(modifier = Modifier.height(15.dp))

            AppReadOnlyTextField(
                value = "Пароль",
                placeholder = ""
            )

            Spacer(modifier = Modifier.height(15.dp))

            AppButtonMedium(
                text = "Войти",
            )

            Spacer(modifier = Modifier.height(20.dp))

            AppMediumText(
                value = "Не зарегистрирован?",
                textAlign = TextAlign.Center,
            )

            AppLargeText(
                value = "Регистрация",
                size = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.clickable(
                    onClick = {
                        // TODO: Add redirect to Registration page
                    }
                )
            )
        }
    }
}