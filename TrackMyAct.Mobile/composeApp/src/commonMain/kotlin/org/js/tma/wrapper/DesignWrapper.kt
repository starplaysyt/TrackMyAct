package org.js.tma.wrapper

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.js.tma.AppTheme
import org.js.tma.data.stateValue
import org.js.tma.getBackground
import org.js.tma.service.HttpKtorService
import org.js.tma.ui.*
import org.js.tma.viewmodel.AppViewModel
import org.js.tma.viewmodel.AppViewModelFactory
import org.js.tma.viewmodel.CategoryViewModel
import org.js.tma.viewmodel.CategoryViewModelFactory
import org.js.tma.viewmodel.LoadingState
import org.js.tma.viewmodel.LoginViewModel
import org.js.tma.viewmodel.LoginViewModelFactory
import org.js.tma.viewmodel.OrganizerViewModel
import org.js.tma.viewmodel.OrganizerViewModelFactory
import org.js.tma.viewmodel.ParticipantRegistrationViewModel
import org.js.tma.viewmodel.ParticipantRegistrationViewModelFactory
import trackmyact.composeapp.generated.resources.*

enum class AppScreen(val needBottomBar: Boolean = true) {
    LOGIN(false),
    REG_STEP_1(false),
    REG_STEP_2(false),
    REG_STEP_3(false),
    ALL_CATEGORIES,
    ALL_ORGANIZERS,
    ACCOUNT,
    CREATE_EVENT(false),
    MAIN,
    EVENT,
    INVITE_BY_ORGANIZER,
    INVITE_BY_STUDENT,
    MY_INVITES,
    VISIT_CHECK,
    VISIT_REQUESTS,
    Test
}

enum class AppBarPage(val title: String, val painter: @Composable () -> Painter, val screen: AppScreen) {
    NOTHING("Ничего", {painterResource(Res.drawable.n_a)}, AppScreen.LOGIN),
    FOOTBALL("Футбол", {painterResource(Res.drawable.football_page)}, AppScreen.ALL_CATEGORIES),
    MAIN("Главная", {painterResource(Res.drawable.main_page)}, AppScreen.MAIN),
    CALENDAR("Календарь", {painterResource(Res.drawable.calendar_page)}, AppScreen.MAIN),
    ACCOUNT("Аккаунт", {painterResource(Res.drawable.account_page)}, AppScreen.ACCOUNT),
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun AppPreviewWrapper(
    isDarkTheme: Boolean = false,
    httpKtorService: HttpKtorService = HttpKtorService(),

    participantRegistrationViewModel: ParticipantRegistrationViewModel = viewModel (
        factory = ParticipantRegistrationViewModelFactory (
            httpKtorService = httpKtorService
        )
    ),
    categoryViewModel: CategoryViewModel = viewModel (
        factory = CategoryViewModelFactory(
            httpKtorService = httpKtorService
        )
    ),
    organizerViewModel: OrganizerViewModel = viewModel (
        factory = OrganizerViewModelFactory(
            httpKtorService = httpKtorService
        )
    ),
    appViewModel: AppViewModel = viewModel (
        factory = AppViewModelFactory(
            httpKtorService = httpKtorService
        )
    ),
    loginViewModel: LoginViewModel = viewModel (
        factory = LoginViewModelFactory(
            httpKtorService = httpKtorService
        )
    )

    ) {
    AppTheme(darkTheme = isDarkTheme) {
        Scaffold(
            bottomBar = {
                if (appViewModel.currentScreen.stateValue().needBottomBar) {
                    AppBottomBar(darkTheme = isDarkTheme, appViewModel = appViewModel)
                }
            },
        ) { innerPadding ->
            Box(
                modifier = getBackground()
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                if (appViewModel.appState.stateValue() != LoadingState.Success) {
                    if (appViewModel.appState.stateValue() == LoadingState.Loading) {
                        LoadingScreen()
                    }
                    else if (appViewModel.appState.stateValue() == LoadingState.NotStarted) {
                        appViewModel.checkLogIn()
                    }
                }
                else if (loginViewModel.state.stateValue() != LoadingState.NotStarted) {
                    if (loginViewModel.state.stateValue() == LoadingState.Loading) {
                        LoadingScreen()
                    }
                    else when (loginViewModel.state.stateValue()) {
                        LoadingState.Success -> {
                            appViewModel.currentScreen.value = AppScreen.MAIN
                            appViewModel.currentBottomBarSelected.value = AppBarPage.MAIN
                        }
                        is LoadingState.Failed -> {
                            loginViewModel.state.value = LoadingState.NotStarted
                        }
                        else -> {
                            loginViewModel.state.value = LoadingState.NotStarted
                        }
                    }
                }
                else if (participantRegistrationViewModel.sendingState.stateValue() != LoadingState.NotStarted) {
                    if (participantRegistrationViewModel.sendingState.stateValue() == LoadingState.Loading) {
                        LoadingScreen()
                    }
                    else when (participantRegistrationViewModel.sendingState.stateValue()) {
                        LoadingState.Success -> {
                            appViewModel.currentScreen.value = AppScreen.MAIN
                            appViewModel.currentBottomBarSelected.value = AppBarPage.MAIN
                        }
                        is LoadingState.Failed -> {
                            participantRegistrationViewModel.sendingState.value = LoadingState.NotStarted
                        }
                        else -> {
                            participantRegistrationViewModel.sendingState.value = LoadingState.NotStarted
                        }
                    }
                }
                else {
                    when (appViewModel.currentScreen.stateValue()) {
                        AppScreen.LOGIN -> LoginScreen(
                            isDarkTheme, loginViewModel,
                            onLogInClick = {
                                loginViewModel.login()
                            },
                            onRegisterClick = {
                                appViewModel.currentScreen.value = AppScreen.REG_STEP_1
                            })
                        AppScreen.REG_STEP_1 -> RegistrationStep1Screen(isDarkTheme, participantRegistrationViewModel) {
                            appViewModel.currentScreen.value = AppScreen.REG_STEP_2
                        }
                        AppScreen.REG_STEP_2 -> RegistrationStep2Screen(isDarkTheme, participantRegistrationViewModel) {
                            appViewModel.currentScreen.value = AppScreen.REG_STEP_3
                        }
                        AppScreen.REG_STEP_3 -> RegistrationStep3Screen(isDarkTheme, participantRegistrationViewModel) {
                            participantRegistrationViewModel.sendRegisterRequest()
                        }
                        AppScreen.ALL_CATEGORIES -> AllCategories(categoryViewModel)
                        AppScreen.CREATE_EVENT -> TODO()
                        AppScreen.Test -> TestScreen(httpKtorService)
                        AppScreen.ALL_ORGANIZERS -> AllOrganizers(organizerViewModel)
                        AppScreen.ACCOUNT -> TODO()
                        AppScreen.MAIN -> TODO()
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
}

@Composable
fun AppBottomBar(
    darkTheme: Boolean,
    appViewModel: AppViewModel
) {
    NavigationBar(
        containerColor = if (darkTheme) Color(red = 0, green = 0, blue = 0, alpha = 64) else Color(red = 255, green = 255, blue = 255, alpha = 64),
    ) {
        for (item in AppBarPage.entries) {
            NavigationBarItem(
                icon = {
                    Icon(painter = item.painter(), contentDescription = null, modifier = Modifier.size(24.dp))
                },
                label = {
                    Text(
                        text = item.title,
                        maxLines = 1,
                        style = MaterialTheme.typography.labelSmall,
                        color = if (appViewModel.currentBottomBarSelected.stateValue() == item) colorScheme.outline else colorScheme.outlineVariant
                    )
                },
                selected = appViewModel.currentBottomBarSelected.stateValue() == item,
                onClick = {
                    appViewModel.currentBottomBarSelected.value = item
                    appViewModel.currentScreen.value = item.screen
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