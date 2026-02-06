package org.js.tma.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.Path
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import org.jetbrains.compose.resources.painterResource
import org.js.tma.AppColors
import org.js.tma.AppShapes
import org.js.tma.data.ByteArrayWrapper
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
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: @Composable (() -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    isError: Boolean = false,
) {

    val textColor = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface

    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            color = textColor,
        ),
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
        ),
        interactionSource = interactionSource,
        trailingIcon = trailingIcon,
        singleLine = singleLine,
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
    value: String,
    onValueChange: (String) -> Unit,
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
                    onValueChange(date.selectedDateMillis?.let { millis ->
                        convertMillisToDate(millis)
                    } ?: "")
                }) {
                    Text(
                        text = "ОК",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDatePicker = false
                    onValueChange(date.selectedDateMillis?.let { millis ->
                        convertMillisToDate(millis)
                    } ?: "")
                }) {
                    Text(
                        text = "Отмена",
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
        value = value,
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
    image: ByteArrayWrapper? = null,
    title: String,
    description: String,
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        if (image != null) AppByteImage(data = image, contentDescription =  description, modifier = Modifier.size(30.dp))

        val secondColor = MaterialTheme.colorScheme.secondaryContainer

        Canvas(
            modifier = Modifier
                .size(100.dp)
        ) {

            val cornerRadius = 30.dp.toPx()

            val path = Path().apply {
                addRoundRect(
                    RoundRect(
                        rect = size.toRect(),
                        topLeft = CornerRadius(cornerRadius, cornerRadius),
                        bottomLeft = CornerRadius(cornerRadius, cornerRadius),
                        topRight = CornerRadius.Zero,
                        bottomRight = CornerRadius.Zero
                    )
                )
            }

            drawPath(
                path = path,
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(red = 0, green = 0, blue = 0, alpha = 0),
                        secondColor
                    )
                ),
            )
        }

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(start = 100.dp)
        ) {

            val cornerRadius = 30.dp.toPx()

            val path = Path().apply {
                addRoundRect(
                    RoundRect(
                        rect = size.toRect(),
                        topLeft = CornerRadius.Zero,
                        bottomLeft = CornerRadius.Zero,
                        topRight = CornerRadius(cornerRadius, cornerRadius),
                        bottomRight = CornerRadius(cornerRadius, cornerRadius)
                    )
                )
            }

            drawPath(
                path = path,
                color = secondColor,
            )

        }

        Column (
            modifier = Modifier
                .padding(start = 100.dp, top = 24.dp)
        ) {
            AppLargeText(
                text = title,
                modifier = Modifier
                    .padding(start = 21.dp),
                size = 20.sp,
                color = MaterialTheme.colorScheme.surface,
            )

            AppMediumText(
                text = description,
                modifier = Modifier
                    .padding(start = 21.dp, top = 8.dp),
                size = 12.sp,
                color = MaterialTheme.colorScheme.surface,
            )
        }

    }

}

@Suppress("ParamsComparedByRef")
@Composable
fun AppByteImage(
    data: ByteArrayWrapper,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    val context = LocalPlatformContext.current

    val request = remember(data.data) {
        ImageRequest.Builder(context)
            .data(data.data)
            .build()
    }

    AsyncImage(
        modifier = modifier,
        model = request,
        contentDescription = contentDescription,
    )
}

@Composable
fun AppSmallText(
    text: String,
    color: Color = Color.Unspecified,
    size: TextUnit = TextUnit.Unspecified,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleSmall,
        color = if (isError) MaterialTheme.colorScheme.error else color,
        fontSize = size,
        textAlign = textAlign,
        modifier = modifier,
    )
}

@Composable
fun AppMediumText(
    text: String,
    color: Color = Color.Unspecified,
    size: TextUnit = TextUnit.Unspecified,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        color = if (isError) MaterialTheme.colorScheme.error else color,
        fontSize = size,
        textAlign = textAlign,
        modifier = modifier,
    )
}

@Composable
fun AppLargeText(
    text: String,
    color: Color = Color.Unspecified,
    size: TextUnit = TextUnit.Unspecified,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        color = if (isError) MaterialTheme.colorScheme.error else color,
        fontSize = size,
        textAlign = textAlign,
        modifier = modifier,
    )
}

@Composable
fun AppPasswordStrengthLevelIndicator(
    password: String,
) {
    val defaultColor = MaterialTheme.colorScheme.surfaceVariant
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(6.dp)
    ) {
        drawRoundRect(
            color = if (password.trim() == "")
                defaultColor
            else when (password.calculatePasswordStrength()) {
                StrengthLevel.LOW -> AppColors.error
                StrengthLevel.MEDIUM -> AppColors.warning
                StrengthLevel.HIGH -> AppColors.success
            },
            cornerRadius = CornerRadius(3.dp.toPx(), 3.dp.toPx()),
        )
    }
}

@Composable
fun AppPasswordStrengthLevelIndicatorGradient(
    password: String,
) {
    val currentColor by animateColorAsState(
        targetValue = if (password.trim() == "")
            MaterialTheme.colorScheme.surfaceVariant
        else when (password.calculatePasswordStrength()) {
            StrengthLevel.LOW -> AppColors.error
            StrengthLevel.MEDIUM -> AppColors.warning
            StrengthLevel.HIGH -> AppColors.success
        },
    )

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(6.dp)
    ) {
        drawRoundRect(
            color = currentColor,
            cornerRadius = CornerRadius(3.dp.toPx(), 3.dp.toPx()),
        )
    }
}

@Composable
fun AppPasswordStrengthLevelIndicatorSwap(
    password: String,
    modifier: Modifier = Modifier,
) {
    val defaultColor = MaterialTheme.colorScheme.surfaceVariant

    var currentColor by remember { mutableStateOf(defaultColor) }
    var nextColor by remember { mutableStateOf(currentColor) }

    val fadedWidth = remember { Animatable(0f) }

    LaunchedEffect(nextColor) {
        if (nextColor != currentColor) {
            fadedWidth.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 250)
            )
            currentColor = nextColor
            fadedWidth.snapTo(0f)
        }
    }

    LaunchedEffect(password) {
        nextColor = if (password.trim() != "") {
            when (password.calculatePasswordStrength()) {
                StrengthLevel.LOW -> AppColors.error
                StrengthLevel.MEDIUM -> AppColors.warning
                StrengthLevel.HIGH -> AppColors.success
            }
        } else defaultColor
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
        ) {
            drawRoundRect(
                color = currentColor,
                cornerRadius = CornerRadius(3.dp.toPx(), 3.dp.toPx()),
            )
        }
        if (fadedWidth.value != 0f) {
            Canvas(
                modifier = Modifier
                    .fillMaxWidth(fadedWidth.value)
                    .height(6.dp)
            ) {
                drawRoundRect(
                    color = nextColor,
                    cornerRadius = CornerRadius(3.dp.toPx(), 3.dp.toPx()),
                )
            }
        }
    }
}