package org.js.tma.ui

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import org.jetbrains.compose.resources.painterResource
import org.js.tma.AppShapes
import org.js.tma.data.HeapDateLink
import org.js.tma.data.HeapStringLink
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
    value: HeapStringLink,
    initValue: String = "",
    placeholder: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: @Composable (() -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {

    var text by remember { mutableStateOf(initValue) }

    TextField(
        value = text,
        onValueChange = {
            text = it
            value.setData(it)
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
    value: HeapDateLink,
    placeholder: String,
    textFieldModifier: Modifier = Modifier,
) {

    if (value.getData() == null) value.setData(rememberDatePickerState())
    var showDatePicker by remember { mutableStateOf(false) }
    val selectedDate = value.getData()!!.selectedDateMillis?.let { millis ->
        convertMillisToDate(millis)
    } ?: ""

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("ОК")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Отмена")
                }
            }
        ) {
            DatePicker(state = value.getData()!!)
        }
    }

    AppReadOnlyTextField(
        value = selectedDate,
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
fun AppByteImage(data: ByteArray, contentDescription: String? = null) {
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
    value: String,
    size: TextUnit = TextUnit.Unspecified,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier,
) {
    Text(
        text = value,
        style = MaterialTheme.typography.titleSmall,
        fontSize = size,
        textAlign = textAlign,
        modifier = modifier,
    )
}

@Composable
fun AppMediumText(
    value: String,
    size: TextUnit = TextUnit.Unspecified,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier,
) {
    Text(
        text = value,
        style = MaterialTheme.typography.titleMedium,
        fontSize = size,
        textAlign = textAlign,
        modifier = modifier,
    )
}

@Composable
fun AppLargeText(
    value: String,
    size: TextUnit = TextUnit.Unspecified,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier,
) {
    Text(
        text = value,
        style = MaterialTheme.typography.titleLarge,
        fontSize = size,
        textAlign = textAlign,
        modifier = modifier,
    )
}