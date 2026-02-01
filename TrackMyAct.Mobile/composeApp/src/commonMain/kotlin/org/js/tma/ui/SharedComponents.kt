package org.js.tma.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import kotlinx.coroutines.flow.MutableStateFlow
import org.jetbrains.compose.resources.painterResource
import org.js.tma.AppColors
import org.js.tma.AppShapes
import org.js.tma.data.stateValue
import org.js.tma.util.StrengthLevel
import org.js.tma.util.calculatePasswordStrength
import org.js.tma.util.convertMillisToDate
import trackmyact.composeapp.generated.resources.Res
import trackmyact.composeapp.generated.resources.calendar

@Composable
fun AppButtonMedium(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        shape = AppShapes.large
    ) {
        Text(text = text, style = MaterialTheme.typography.labelMedium)
    }
}

@Composable
fun AppButtonLarge(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        shape = AppShapes.large
    ) {
        Text(text = text, style = MaterialTheme.typography.labelMedium)
    }
}

@Composable
fun AppTextField(
    value: MutableStateFlow<String>,
    initValue: String = "",
    placeholder: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: @Composable (() -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {

    LaunchedEffect(true) {
        value.value = initValue
    }

    TextField(
        value = value.stateValue(),
        onValueChange = {
            value.value = it
        },
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        textStyle = MaterialTheme.typography.bodyMedium,
        modifier = modifier
            .fillMaxWidth()
            .height(53.dp),
        shape = AppShapes.large,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface
        ),
        interactionSource = interactionSource,
        trailingIcon = trailingIcon,
        singleLine = singleLine
    )
}

@Composable
fun AppReadOnlyTextField(
    value: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    trailingIcon: @Composable (() -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    TextField(
        value = value,
        onValueChange = {},
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .height(53.dp),
        textStyle = MaterialTheme.typography.bodyMedium,
        shape = AppShapes.large,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface
        ),
        interactionSource = interactionSource,
        trailingIcon = trailingIcon,
        readOnly = true,
        singleLine = true
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDatePicker(
    value: MutableStateFlow<String>,
    placeholder: String,
    textFieldModifier: Modifier = Modifier,
) {

    val date = rememberDatePickerState()
    var showDatePicker by remember { mutableStateOf(false) }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    showDatePicker = false
                    value.value = date.selectedDateMillis?.let { millis ->
                        convertMillisToDate(millis)
                    } ?: ""
                }) {
                    Text(text = "ОК",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDatePicker = false
                    value.value = date.selectedDateMillis?.let { millis ->
                        convertMillisToDate(millis)
                    } ?: ""
                }) {
                    Text(text = "Отмена",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            },
            colors = DatePickerDefaults.colors(
                containerColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            DatePicker(
                state = date,
                colors = DatePickerDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    weekdayContentColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    headlineContentColor = MaterialTheme.colorScheme.primary,
                    subheadContentColor = MaterialTheme.colorScheme.primary,
                    navigationContentColor = MaterialTheme.colorScheme.primary,

                    yearContentColor = MaterialTheme.colorScheme.primary,
                    currentYearContentColor = MaterialTheme.colorScheme.primary,
                    selectedYearContentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledYearContentColor = MaterialTheme.colorScheme.onPrimary,
                    selectedYearContainerColor = MaterialTheme.colorScheme.primary,
                    disabledSelectedYearContainerColor = MaterialTheme.colorScheme.primary,

                    dayContentColor = MaterialTheme.colorScheme.primary,
                    selectedDayContentColor = MaterialTheme.colorScheme.onPrimary,
                    selectedDayContainerColor = MaterialTheme.colorScheme.primary,
                    disabledDayContentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledSelectedDayContainerColor = MaterialTheme.colorScheme.primary,

                    dividerColor = MaterialTheme.colorScheme.onPrimary,

                    dateTextFieldColors = TextFieldDefaults.colors(
                        focusedTextColor = MaterialTheme.colorScheme.primary,
                        unfocusedTextColor = MaterialTheme.colorScheme.primary,
                        disabledTextColor = MaterialTheme.colorScheme.primary,
                        errorTextColor = MaterialTheme.colorScheme.error,

                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        errorContainerColor = Color.Transparent,

                        focusedPlaceholderColor = MaterialTheme.colorScheme.primary,
                        unfocusedPlaceholderColor = MaterialTheme.colorScheme.primary,
                        disabledPlaceholderColor = MaterialTheme.colorScheme.primary,
                        errorPlaceholderColor = MaterialTheme.colorScheme.primary,

                        unfocusedLabelColor = MaterialTheme.colorScheme.primary,
                    )
                )
            )
        }
    }

    AppReadOnlyTextField(
        value = value.stateValue(),
        placeholder = placeholder,
        trailingIcon = {
            Icon(
                painter = painterResource(Res.drawable.calendar),
                contentDescription = "Выбрать дату",
                tint = MaterialTheme.colorScheme.surface,
            )
        },
        modifier = textFieldModifier
            .clickable { showDatePicker = true },
        interactionSource = remember { MutableInteractionSource() }
            .also { interactionSource ->
                LaunchedEffect(interactionSource) {
                    interactionSource.interactions.collect {
                        if (it is PressInteraction.Release) {
                            showDatePicker = true
                        }
                    }
                }
            }
    )

}

@Composable
fun AppCategoryCard(
    title: String,
    icon: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(4.dp),
        shape = AppShapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        )
    ) {
        Text(
            text = title.uppercase(),
            modifier = Modifier.padding(12.dp),
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Suppress("ParamsComparedByRef")
@Composable
fun AppByteImage(
    data: ByteArray,
    contentDescription: String? = null
) {
    val context = LocalPlatformContext.current

    val request = remember(data) {
        ImageRequest.Builder(context)
            .data(data)
            .build()
    }

    AsyncImage(
        model = request,
        contentDescription = contentDescription,
    )
}

@Composable
fun AppSmallText(
    text: String,
    size: TextUnit = TextUnit.Unspecified,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleSmall,
        fontSize = size,
        textAlign = textAlign,
        modifier = modifier,
    )
}

@Composable
fun AppMediumText(
    text: String,
    size: TextUnit = TextUnit.Unspecified,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        fontSize = size,
        textAlign = textAlign,
        modifier = modifier,
    )
}

@Composable
fun AppLargeText(
    text: String,
    size: TextUnit = TextUnit.Unspecified,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        fontSize = size,
        textAlign = textAlign,
        modifier = modifier,
    )
}

@Composable
fun AppPasswordStrengthLevelIndicator(
    password: String,
) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(6.dp)
    ) {
        drawRoundRect(
            color = when(password.calculatePasswordStrength()) {
                StrengthLevel.LOW -> AppColors.error
                StrengthLevel.MEDIUM -> AppColors.warning
                StrengthLevel.HIGH -> AppColors.success
            },
            cornerRadius = CornerRadius(3.dp.toPx(), 3.dp.toPx()),
        )
    }
}