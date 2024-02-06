package com.deltax72.onlineshop.screens.registration.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deltax72.onlineshop.screens.registration.states.AuthUserState
import com.deltax72.onlineshop.ui.PreviewBox
import com.deltax72.onlineshop.ui.theme.colors
import com.deltax72.onlineshop.ui.theme.sfProDisplayMedium
import com.deltax72.onlineshop.ui.theme.styles

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationViewDisplay(
    authUserState: AuthUserState,
    onFirstNameChanged: (String) -> Unit,
    onLastNameChanged: (String) -> Unit,
    onPhoneNumberChanged: (String) -> Unit,
    onClearFirstName: () -> Unit,
    onClearLastName: () -> Unit,
    onClearPhoneNumber: () -> Unit,
    onButtonClicked: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 15.dp, bottom = 10.dp)
            ) {
                Text(
                    text = "Вход",
                    style = MaterialTheme.styles.title1,
                )
            }

            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .padding(top = 113.dp)
//                .fillMaxWidth()
            ) {
                TextField(
                    value = authUserState.firstName,
                    onValueChange = onFirstNameChanged,
                    placeholder = {
                        Text("Имя")
                    },
                    isError =
                        if (authUserState.isFirstNameCorrect != null)
                            !authUserState.isFirstNameCorrect
                        else false
                    ,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        placeholderColor = MaterialTheme.colors.Grey,
                        textColor = MaterialTheme.colors.Black,
                        containerColor = MaterialTheme.colors.LightGrey,
                        errorIndicatorColor = MaterialTheme.colors.Red
                    ),
                    trailingIcon = {
                        if (authUserState.firstName.isNotEmpty()) {
                            Icon(
                                Icons.Default.Clear,
                                contentDescription = null,
                                modifier = Modifier
                                    .clickable {
                                        onClearFirstName()
                                    }
                            )
                        }
                    }
                )
                TextField(
                    value = authUserState.lastName,
                    onValueChange = onLastNameChanged,
                    placeholder = {
                        Text("Фамилия")
                    },
                    isError = if (authUserState.isLastNameCorrect != null)
                        !authUserState.isLastNameCorrect
                    else false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        placeholderColor = MaterialTheme.colors.Grey,
                        textColor = MaterialTheme.colors.Black,
                        containerColor = MaterialTheme.colors.LightGrey,
                        errorIndicatorColor = MaterialTheme.colors.Red
                    ),
                    trailingIcon = {
                        if (authUserState.lastName.isNotEmpty()) {
                            Icon(
                                Icons.Default.Clear,
                                contentDescription = null,
                                modifier = Modifier
                                    .clickable {
                                        onClearLastName()
                                    }
                            )
                        }
                    }
                )
                TextField(
                    value = TextFieldValue(
                        text = authUserState.phoneNumber,
                        selection = TextRange(authUserState.phoneNumber.length)
                    ),
                    onValueChange = {
                        onPhoneNumberChanged(it.text)
                    },
                    placeholder = {
                        Text("+ 7 XXX-XXX-XX-XX")
                    },
                    isError = if (authUserState.isPhoneNumberCorrect != null)
                        !authUserState.isPhoneNumberCorrect
                    else false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        placeholderColor = MaterialTheme.colors.Grey,
                        textColor = MaterialTheme.colors.Black,
                        containerColor = MaterialTheme.colors.LightGrey,
                        errorIndicatorColor = MaterialTheme.colors.Red
                    ),
                    trailingIcon = {
                        if (authUserState.phoneNumber.isNotEmpty()) {
                            Icon(
                                Icons.Default.Clear,
                                contentDescription = null,
                                modifier = Modifier
                                    .clickable {
                                        onClearPhoneNumber()
                                    }
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                )
                Button(
                    onClick = {
                        onButtonClicked()
                    },
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = MaterialTheme.colors.Pink,
                        contentColor = MaterialTheme.colors.White,
                        disabledContainerColor = MaterialTheme.colors.LightPink,
                        disabledContentColor = MaterialTheme.colors.White,
                    ),
                    enabled = try {
                        authUserState.isFirstNameCorrect!! &&
                        authUserState.isLastNameCorrect!! &&
                        authUserState.isPhoneNumberCorrect!!
                    } catch (e: NullPointerException) {
                        false
                    }
                    ,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp)
                ) {
                    Text(
                        text = "Войти",
                        style = MaterialTheme.styles.buttonText2
                    )
                }
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(bottom = 2.dp)
        ) {
            Text(
                text = "Нажимая кнопку “Войти”, Вы принимаете",
                style = MaterialTheme.styles.caption
            )
            Text(
                text = "условия программы лояльности",
                style = MaterialTheme.styles.caption
            )
        }
    }
}

@Composable
@Preview
fun RegistrationViewDisplay_Preview() {
    PreviewBox {
        RegistrationViewDisplay(
            AuthUserState(
                firstName = "123",
                lastName = "456",
                phoneNumber = ""
            ),
            onFirstNameChanged = {},
            onLastNameChanged = {},
            onPhoneNumberChanged = {},
            onClearFirstName = {},
            onClearLastName = {},
            onClearPhoneNumber = {},
            onButtonClicked = {}
        )
    }
}