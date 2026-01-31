package org.js.tma.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.js.tma.data.HeapDateLink
import org.js.tma.data.HeapStringLink
import trackmyact.composeapp.generated.resources.Res
import trackmyact.composeapp.generated.resources.hide_password
import trackmyact.composeapp.generated.resources.logo_dark
import trackmyact.composeapp.generated.resources.logo_light

@Composable
fun RegistrationStep1Screen(
    darkTheme: Boolean,
) {

    RegistrationWrapper(
        darkTheme = darkTheme,
        onClick = {
            // TODO
        }
    ) {

        AppReadOnlyTextField(
            value = "Никнейм",
            placeholder = "Никнейм"
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AppSmallText(
                value = "Никнейм - имя в системе.\nПо нему будет проще найти нужного пользователя.",
                modifier = Modifier
                    .padding(start = 15.dp),
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        AppReadOnlyTextField(
            value = "E-mail",
            placeholder = "E-mail"
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AppSmallText(
                value = "На E-mail будет отправлено письмо с проверочным кодом.",
                modifier = Modifier
                    .padding(start = 15.dp),
            )
        }
    }
}

@Composable
fun RegistrationStep2Screen(
    darkTheme: Boolean,
) {
    var showPassword by remember { mutableStateOf(false) }

    RegistrationWrapper(darkTheme,
        onClick = {
            // TODO
        }
    ) {
        AppTextField(
            value = HeapStringLink(),
            placeholder = "Придумайте пароль",
            visualTransformation = if (!showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                Icon(
                    painter = painterResource(Res.drawable.hide_password),
                    contentDescription = "Посмотреть пароль",
                    tint = MaterialTheme.colorScheme.surface,
                )
            },
            interactionSource = remember { MutableInteractionSource() }
                .also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                showPassword = !showPassword
                            }
                        }
                    }
                }
        )

        Spacer(modifier = Modifier.height(5.dp))

        // TODO: Create a password strength check

        Spacer(modifier = Modifier.height(5.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AppSmallText(
                value = "Придумайте сложный пароль",
                modifier = Modifier.padding(start = 15.dp),
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        AppTextField(
            value = HeapStringLink(),
            placeholder = "Повторите пароль",
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        )

        Spacer(modifier = Modifier.height(5.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AppSmallText(
                value = "Повторите ваш пароль",
                modifier = Modifier.padding(start = 15.dp),
            )
        }
    }
}

@Composable
fun RegistrationStep3Screen(
    darkTheme: Boolean,
) {
    RegistrationWrapper(darkTheme,
        onClick = {
            // TODO
        }
    ) {
        AppReadOnlyTextField(
            value = "ФИО (необязательно)",
            placeholder = "Никнейм"
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AppSmallText(
                value = "ФИО поможет идентифицировать Вас организатором.",
                modifier = Modifier
                    .padding(start = 15.dp),
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        AppReadOnlyTextField(
            value = "Телефон (необязательно)",
            placeholder = "Никнейм"
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AppSmallText(
                value = "Телефон поможет организатору связаться с Вами. Может быть использован для восстановления доступа к аккаунту.",
                modifier = Modifier
                    .padding(start = 15.dp),
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        AppDatePicker(
            value = HeapDateLink(),
            placeholder = "Дата рождения (необязательно)"
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AppSmallText(
                value = "Дата рождения.",
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
            value = "Регистрация",
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