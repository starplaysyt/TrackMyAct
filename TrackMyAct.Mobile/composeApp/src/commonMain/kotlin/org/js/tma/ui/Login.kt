package org.js.tma.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.js.tma.data.stateValue
import org.js.tma.viewmodel.LoginViewModel
import org.js.tma.wrapper.AppScreen
import trackmyact.composeapp.generated.resources.Res
import trackmyact.composeapp.generated.resources.logo_dark
import trackmyact.composeapp.generated.resources.logo_light

@Composable
fun LoginScreen(
    darkTheme: Boolean,
    loginViewModel: LoginViewModel,
    onLogInClick: () -> Unit,
    onRegisterClick: () -> Unit,
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
                text = "Логин",
                size = 32.sp,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(8.dp))

            AppSmallText(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(35.dp))

            AppTextField(
                value = loginViewModel.usernameField.stateValue(),
                placeholder = "Логин",
                onValueChange = {
                    loginViewModel.usernameField.value = it
                },
            )

            Spacer(modifier = Modifier.height(15.dp))

            AppTextField(
                value = loginViewModel.passwordField.stateValue(),
                placeholder = "Пароль",
                onValueChange = {
                    loginViewModel.passwordField.value = it
                },
            )

            Spacer(modifier = Modifier.height(15.dp))

            AppButtonMedium(
                text = "Войти",
                onClick = {
                    onLogInClick()
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            AppMediumText(
                text = "Не зарегистрирован?",
                textAlign = TextAlign.Center,
            )

            AppLargeText(
                text = "Регистрация",
                size = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.clickable(
                    onClick = onRegisterClick
                )
            )
        }
    }
}