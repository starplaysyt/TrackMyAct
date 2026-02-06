package org.js.tma.wrapper

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.resources.painterResource
import org.js.tma.AppTheme
import org.js.tma.LocalUserData.currentBottomBarSelected
import org.js.tma.LocalUserData.currentScreen
import org.js.tma.data.stateValue
import org.js.tma.getBackground
import org.js.tma.ui.*
import org.js.tma.viewmodel.RegistrationViewModel
import trackmyact.composeapp.generated.resources.*

enum class AppScreen(val needBottomBar: Boolean = true) {
    LOGIN(false),
    REG_STEP_1(false),
    REG_STEP_2(false),
    REG_STEP_3(false),
    ALL_CATEGORIES,
    MY_CATEGORIES,
    ALL_ORGANIZERS,
    MY_ORGANIZERS,
    ORGANIZER,
    ACCOUNT,
    CREATE_EVENT,
    ALL_EVENTS,
    MY_EVENTS,
    EVENT,
    INVITE_BY_ORGANIZER,
    INVITE_BY_STUDENT,
    MY_INVITES,
    VISIT_CHECK,
    VISIT_REQUESTS,
    Test
}

enum class AppBarPage(val title: String, val painter: @Composable () -> Painter) {
    NOTHING("Ничего", {painterResource(Res.drawable.n_a)}),
    FOOTBALL("Футбол", {painterResource(Res.drawable.football_page)}),
    MAIN("Главная", {painterResource(Res.drawable.main_page)}),
    CALENDAR("Календарь", {painterResource(Res.drawable.calendar_page)}),
    ACCOUNT("Аккаунт", {painterResource(Res.drawable.account_page)}),
}

@Composable
fun AppPreviewWrapper(
    isDarkTheme: Boolean = false,
    registrationViewModel: RegistrationViewModel = viewModel{ RegistrationViewModel() }
) {
    AppTheme(darkTheme = isDarkTheme) {
        Scaffold(
            bottomBar = {
                if (currentScreen.stateValue().needBottomBar) {
                    AppBottomBar(darkTheme = isDarkTheme)
                }
            },
        ) { innerPadding ->
            Box(
                modifier = getBackground()
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                when (currentScreen.stateValue()) {
                    AppScreen.LOGIN -> LoginScreen(isDarkTheme)
                    AppScreen.REG_STEP_1 -> RegistrationStep1Screen(isDarkTheme, registrationViewModel)
                    AppScreen.REG_STEP_2 -> RegistrationStep2Screen(isDarkTheme, registrationViewModel)
                    AppScreen.REG_STEP_3 -> RegistrationStep3Screen(isDarkTheme, registrationViewModel)
                    AppScreen.ALL_CATEGORIES -> CategoriesScreen()
                    AppScreen.CREATE_EVENT -> CreateEventScreen()
                    AppScreen.Test -> TestScreen()
                    AppScreen.MY_CATEGORIES -> TODO()
                    AppScreen.ALL_ORGANIZERS -> TODO()
                    AppScreen.MY_ORGANIZERS -> TODO()
                    AppScreen.ORGANIZER -> TODO()
                    AppScreen.ACCOUNT -> TODO()
                    AppScreen.ALL_EVENTS -> TODO()
                    AppScreen.MY_EVENTS -> TODO()
                    AppScreen.EVENT -> TODO()
                    AppScreen.INVITE_BY_ORGANIZER -> TODO()
                    AppScreen.INVITE_BY_STUDENT -> TODO()
                    AppScreen.MY_INVITES -> TODO()
                    AppScreen.VISIT_CHECK -> TODO()
                    AppScreen.VISIT_REQUESTS -> TODO()
                }
            }
        }
    }
}

@Composable
fun AppBottomBar(
    darkTheme: Boolean
) {
    NavigationBar(
        containerColor = if (darkTheme) Color(red = 0, green = 0, blue = 0, alpha = 64) else Color(red = 255, green = 255, blue = 255, alpha = 64),
    ) {
        for (item in AppBarPage.entries) {
            NavigationBarItem(
                icon = {
                    Icon(painter = item.painter(), contentDescription = null)
                },
                label = {
                    Text(
                        text = item.title,
                        maxLines = 1,
                        style = MaterialTheme.typography.labelSmall,
                        color = if (currentBottomBarSelected.stateValue() == item) colorScheme.outline else colorScheme.outlineVariant
                    )
                },
                selected = currentBottomBarSelected.stateValue() == item,
                onClick = {
                    currentBottomBarSelected.value = item
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,

                    selectedIconColor = colorScheme.outline,
                    unselectedIconColor = colorScheme.outlineVariant,
                )
            )
        }
    }
}