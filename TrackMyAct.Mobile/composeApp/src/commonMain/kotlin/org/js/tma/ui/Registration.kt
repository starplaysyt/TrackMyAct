package org.js.tma.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.js.tma.LocalUserData.currentScreen
import org.js.tma.data.stateValue
import org.js.tma.viewmodel.RegistrationViewModel
import org.js.tma.wrapper.AppScreen
import trackmyact.composeapp.generated.resources.Res
import trackmyact.composeapp.generated.resources.hide_password
import trackmyact.composeapp.generated.resources.logo_dark
import trackmyact.composeapp.generated.resources.logo_light

@Composable
fun RegistrationStep1Screen(
    darkTheme: Boolean,
    viewModel: RegistrationViewModel,
) {

    RegistrationWrapper(
        darkTheme = darkTheme,
        onClick = {
            currentScreen.value = AppScreen.REG_STEP_2
            // TODO
        }
    ) {

        AppTextField(
            value = viewModel.nicknameField.stateValue(),
            onValueChange = {viewModel.nicknameField.value = it},
            placeholder = "Никнейм"
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AppSmallText(
                text = "Никнейм - имя в системе.\nПо нему будет проще найти нужного пользователя.",
                modifier = Modifier
                    .padding(start = 15.dp),
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        AppTextField(
            value = viewModel.emailField.stateValue(),
            onValueChange = {viewModel.emailField.value = it},
            placeholder = "E-mail"
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AppSmallText(
                text = "На E-mail будет отправлено письмо с проверочным кодом.",
                modifier = Modifier
                    .padding(start = 15.dp),
            )
        }
    }
}

@Composable
fun RegistrationStep2Screen(
    darkTheme: Boolean,
    viewModel: RegistrationViewModel,
) {
    var showPassword by remember { mutableStateOf(false) }
    var passwordFieldFirst by remember { mutableStateOf("") }
    var passwordFieldSecond by remember { mutableStateOf("") }

    RegistrationWrapper(darkTheme,
        onClick = {
            currentScreen.value = AppScreen.REG_STEP_3
            // TODO
        }
    ) {
        AppTextField(
            value = passwordFieldFirst,
            onValueChange = {passwordFieldFirst = it},
            placeholder = "Придумайте пароль",
            visualTransformation = if (!showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(
                    onClick = {
                        showPassword = !showPassword
                    }
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.hide_password),
                        contentDescription = "Посмотреть пароль",
                        tint = MaterialTheme.colorScheme.surface,
                    )
                }
            },
        )

        Spacer(modifier = Modifier.height(8.dp))

        AppPasswordStrengthLevelIndicatorSwap(
            password = passwordFieldFirst,
            modifier = Modifier
                .padding(horizontal = 10.dp)
        )

        Spacer(modifier = Modifier.height(5.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AppSmallText(
                text = if (passwordFieldSecond.isNotEmpty() && passwordFieldFirst != passwordFieldSecond) "Пароли не совпадают" else "Придумайте сложный пароль",
                modifier = Modifier.padding(start = 15.dp),
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        AppTextField(
            value = passwordFieldSecond,
            onValueChange = {passwordFieldSecond = it},
            placeholder = "Повторите пароль",
            visualTransformation = if (!showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        )

        Spacer(modifier = Modifier.height(5.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AppSmallText(
                text = "Повторите ваш пароль",
                modifier = Modifier.padding(start = 15.dp),
            )
        }
    }
}

@Composable
fun RegistrationStep3Screen(
    darkTheme: Boolean,
    viewModel: RegistrationViewModel,
) {
    RegistrationWrapper(darkTheme,
        onClick = {
            currentScreen.value = AppScreen.LOGIN
            // TODO
        }
    ) {
        AppTextField(
            value = viewModel.fullNameField.stateValue(),
            onValueChange = {viewModel.fullNameField.value = it},
            placeholder = "ФИО (необязательно)"
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AppSmallText(
                text = "ФИО поможет идентифицировать Вас организатором.",
                modifier = Modifier
                    .padding(start = 15.dp),
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        AppTextField(
            value = viewModel.phoneField.stateValue(),
            onValueChange = {viewModel.phoneField.value = it},
            placeholder = "Телефон (необязательно)"
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AppSmallText(
                text = "Телефон поможет организатору связаться с Вами. Может быть использован для восстановления доступа к аккаунту.",
                modifier = Modifier
                    .padding(start = 15.dp),
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        AppDatePicker(
            value = viewModel.birthDateField.stateValue(),
            onValueChange = {viewModel.birthDateField.value = it},
            placeholder = "Дата рождения (необязательно)"
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AppSmallText(
                text = "Дата рождения.",
                modifier = Modifier
                    .padding(start = 15.dp),
            )
        }
    }
}

@Composable
private fun RegistrationWrapper(
    darkTheme: Boolean,
    onClick: () -> Unit,
    content: @Composable () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 25.dp, horizontal = 25.dp,),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(14.dp))

        Image(
            painter = if (darkTheme) painterResource(Res.drawable.logo_dark) else painterResource(Res.drawable.logo_light),
            contentDescription = null,
        )

        Spacer(modifier = Modifier.height(14.dp))

        AppLargeText(
            text = "Регистрация",
            size = 32.sp,
        )

        Spacer(modifier = Modifier.height(81.dp))

        content()

        Spacer(modifier = Modifier.weight(1f))

        AppButtonLarge(
            text = "Далее",
            onClick = {
                onClick()
            }
        )
    }
}