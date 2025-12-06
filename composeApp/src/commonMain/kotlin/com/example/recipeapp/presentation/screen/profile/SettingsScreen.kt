package com.example.recipeapp.presentation.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.presentation.component.MenuGroup
import com.example.recipeapp.presentation.component.MenuGroupItem
import com.example.recipeapp.presentation.component.TopBar
import com.example.recipeapp.presentation.navigation.navigateToAccountManagement
import com.example.recipeapp.resources.*
import com.example.recipeapp.ui.theme.RecipeAppTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun SettingsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: SettingsViewModel = koinViewModel()
) {
    SettingsScreen(
        modifier = modifier,
        navController = navController,
        onSignOutCurrentUser = viewModel::fetchSignOutCurrentUser
    )
}

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    onSignOutCurrentUser: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(title = stringResource(Res.string.settings_label))
        }, content = {
            Box(modifier = modifier.padding(it)) {
                SettingScreenBody(
                    modifier = modifier,
                    onSignOutCurrentUser = onSignOutCurrentUser,
                    navController = navController
                )
            }
        }
    )
}

@Composable
fun SettingScreenBody(
    modifier: Modifier = Modifier,
    onSignOutCurrentUser: () -> Unit,
    navController: NavController
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
    ) {
        item {
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                MenuGroup(
                    modifier = modifier,
                    content = {
                        MenuGroupItem(
                            onClick = { navController.navigateToAccountManagement() },
                            title = stringResource(Res.string.account_management_label)
                        )
                        MenuGroupItem(
                            onClick = { /*TODO*/ },
                            title = stringResource(Res.string.notification_label),

                            )
                        MenuGroupItem(
                            onClick = { /*TODO*/ },
                            title = stringResource(Res.string.privacy_data_label)
                        )
                        MenuGroupItem(
                            onClick = { /*TODO*/ },
                            title = stringResource(Res.string.reports_label)
                        )
                    }, subTitle = stringResource(Res.string.settings_label)
                )

                MenuGroup(
                    modifier = modifier,
                    content = {
                        MenuGroupItem(
                            onClick = { /*TODO*/ },
                            title = stringResource(Res.string.help_center_label)
                        )
                        MenuGroupItem(
                            onClick = { /*TODO*/ },
                            title = stringResource(Res.string.term_service_label)
                        )
                        MenuGroupItem(
                            onClick = { /*TODO*/ },
                            title = stringResource(Res.string.privacy_policy_label)
                        )
                        MenuGroupItem(
                            onClick = { /*TODO*/ },
                            title = stringResource(Res.string.about_label)
                        )
                    }, subTitle = stringResource(Res.string.support_label)
                )
                MenuGroup(
                    modifier = modifier,
                    content = {
                        MenuGroupItem(
                            onClick = { onSignOutCurrentUser() },
                            title = stringResource(Res.string.sign_out_label),
                            trailingIcon = null
                        )
                    },
                    subTitle = null
                )
            }
        }
    }
}

@Preview(
    name = "Light Mode Portrait",
    showBackground = true,
)
@Composable
fun ProfileScreenPreview() {
    RecipeAppTheme {
        SettingScreenBody(navController = rememberNavController(), onSignOutCurrentUser = {})
    }
}