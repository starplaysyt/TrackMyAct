package org.js.tma.viewmodel

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import org.js.tma.data.stateFlow

@Stable
class RegistrationViewModel: ViewModel() {

    val nicknameField by stateFlow("")
    val emailField by stateFlow("")
    val passwordField by stateFlow("")
    val fullNameField by stateFlow("")
    val phoneField by stateFlow("")
    val birthDateField by stateFlow("")

}