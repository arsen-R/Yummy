package com.example.recipeapp.presentation.screen.account_management

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.presentation.component.DialogPreference
import com.example.recipeapp.presentation.component.MenuGroup
import com.example.recipeapp.presentation.component.MenuGroupItem
import com.example.recipeapp.presentation.component.MenuGroupItemWithSwitch
import com.example.recipeapp.presentation.component.SubtitleText
import com.example.recipeapp.presentation.component.TopAppBarWithArrow
import com.example.recipeapp.resources.*
import com.example.recipeapp.ui.theme.RecipeAppTheme
import org.jetbrains.compose.resources.stringArrayResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun AccountManagementScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AccountManagementViewModel = koinViewModel()
) {
    val selectedAppThemeKey = viewModel.selectAppThemeKey.collectAsStateWithLifecycle().value
    AccountManagementScreen(
        modifier = modifier,
        navController = navController,
        onChangePassword = {},
        onChangeAppTheme = viewModel::saveAppThemeSelection,
        onChangeAppLanguage = { key, selection -> },
        onChangeMeasurementSystem = { key, selection -> },
        onChangeAutoPlayMode = { key, selection -> },
        onTogglePictureInPicture = {},
        onDeactivateAccount = {},
        onDeleteAccount = {},
        currentSelectedAppThemeKey = selectedAppThemeKey
    )
}

@Composable
fun AccountManagementScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    onChangePassword: () -> Unit,
    onChangeAppTheme: (selection: Int) -> Unit,
    onChangeAppLanguage: (key: String, selection: Int) -> Unit,
    onChangeMeasurementSystem: (key: String, selection: Int) -> Unit,
    onChangeAutoPlayMode: (key: String, selection: Int) -> Unit,
    onTogglePictureInPicture: () -> Unit,
    onDeactivateAccount: () -> Unit,
    onDeleteAccount: () -> Unit,
    currentSelectedAppThemeKey: Int?,
) {
    val currentAppThemeKeys = currentSelectedAppThemeKey ?: 0
    val currentAppThemeLabel = stringArrayResource(Res.array.theme_label)[currentAppThemeKeys]
    val themeShowDialog = remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        TopAppBarWithArrow(
            navController = navController,
            title = {
                Text(text = "Account management")
            }
        )
        LazyColumn(
            modifier = modifier
                .fillMaxSize()

        ) {
            item {
                MenuGroup(
                    modifier = modifier.padding(top = 5.dp),
                    subTitle = stringResource(Res.string.your_account_label)
                ) {
                    MenuGroupItem(
                        onClick = { /*TODO*/ },
                        title = stringResource(Res.string.change_password_label)
                    )
                    MenuGroupItem(
                        onClick = { themeShowDialog.value = !themeShowDialog.value },
                        title = stringResource(Res.string.app_theme_label),
                        subtitle = {
                            SubtitleText(subtitleText = currentAppThemeLabel)
                        }
                    )
                    MenuGroupItem(
                        onClick = { /*TODO*/ },
                        title = stringResource(Res.string.measurement_system_label),
                        subtitle = {
                            SubtitleText(subtitleText = "Metric")
                        }
                    )
                    MenuGroupItem(onClick = { /*TODO*/ },
                        title = stringResource(Res.string.autoplay_label),
                        subtitle = {
                            SubtitleText(subtitleText = "Never")
                        }
                    )
                    MenuGroupItemWithSwitch(
                        onClick = { /*TODO*/ },
                        title = stringResource(Res.string.picture_in_picture_label),
                        subtitle = {
                            SubtitleText(subtitleText = "Never")
                        }
                    )
                }
                MenuGroup(subTitle = stringResource(Res.string.deactivation_delete_label)) {
                    MenuGroupItem(
                        onClick = { /*TODO*/ },
                        title = stringResource(Res.string.deactivation_account_label),
                        subtitle = {
                            SubtitleText(subtitleText = "Deactivate profile")
                        })
                    MenuGroupItem(
                        onClick = { /*TODO*/ },
                        title = stringResource(Res.string.delete_account_label),
                        subtitle = {
                            SubtitleText(subtitleText = "Permanently delete your data")
                        }
                    )
                }
            }
        }
    }

    if (themeShowDialog.value) {
        DialogPreference(
            showDialog = themeShowDialog.value,
            title = stringResource(Res.string.app_theme_label),
            currentValue = currentAppThemeLabel,
            labels = stringArrayResource(Res.array.theme_label),
            onDismissRequest = { themeShowDialog.value = false },
            onOptionSelected = { selection ->
                onChangeAppTheme(selection)
            }
        )
    }
}

@Preview(
    name = "Light Mode Portrait",
    showBackground = true,
)
@Composable
fun AccountManagementScreenPreview() {
    RecipeAppTheme {
        AccountManagementScreen(
            navController = rememberNavController(),
            onChangePassword = {},
            onChangeAppTheme = { selection -> },
            onChangeAppLanguage = { key, selection -> },
            onChangeMeasurementSystem = { key, selection -> },
            onChangeAutoPlayMode = { key, selection -> },
            onTogglePictureInPicture = {},
            onDeactivateAccount = {},
            onDeleteAccount = {},
            currentSelectedAppThemeKey = 0
        )
    }
}