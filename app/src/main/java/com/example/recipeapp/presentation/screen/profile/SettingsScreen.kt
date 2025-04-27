package com.example.recipeapp.presentation.screen.profile

import android.content.res.Configuration
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.R
import com.example.recipeapp.presentation.component.MenuGroup
import com.example.recipeapp.presentation.component.MenuGroupItem
import com.example.recipeapp.presentation.component.TopBar
import com.example.recipeapp.presentation.navigation.navigateToAccountManagement
import com.example.recipeapp.ui.theme.RecipeAppTheme

@Composable
internal fun SettingsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel()
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
            TopBar(title = stringResource(id = R.string.settings_label))
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
                            title = stringResource(id = R.string.account_management_label)
                        )
                        MenuGroupItem(
                            onClick = { /*TODO*/ },
                            title = stringResource(id = R.string.notification_label),

                            )
                        MenuGroupItem(
                            onClick = { /*TODO*/ },
                            title = stringResource(id = R.string.privacy_data_label)
                        )
                        MenuGroupItem(
                            onClick = { /*TODO*/ },
                            title = stringResource(id = R.string.reports_label)
                        )
                    }, subTitle = stringResource(id = R.string.settings_label)
                )

                MenuGroup(
                    modifier = modifier,
                    content = {
                        MenuGroupItem(
                            onClick = { /*TODO*/ },
                            title = stringResource(id = R.string.help_center_label)
                        )
                        MenuGroupItem(
                            onClick = { /*TODO*/ },
                            title = stringResource(id = R.string.term_service_label)
                        )
                        MenuGroupItem(
                            onClick = { /*TODO*/ },
                            title = stringResource(id = R.string.privacy_policy_label)
                        )
                        MenuGroupItem(
                            onClick = { /*TODO*/ },
                            title = stringResource(id = R.string.about_label)
                        )
                    }, subTitle = stringResource(id = R.string.support_label)
                )
                MenuGroup(
                    modifier = modifier,
                    content = {
                        MenuGroupItem(
                            onClick = { onSignOutCurrentUser() },
                            title = stringResource(id = R.string.sign_out_label),
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
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=portrait"
)
@Preview(
    name = "Dark Mode Portrait",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    showSystemUi = true,
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=portrait"
)
@Preview(
    name = "Light Mode Landscape",
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"

)
@Preview(
    name = "Dark Mode Landscape",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    showSystemUi = true,
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
)
@Composable
fun ProfileScreenPreview() {
    RecipeAppTheme {
        SettingScreenBody(navController = rememberNavController(), onSignOutCurrentUser = {})
    }
}