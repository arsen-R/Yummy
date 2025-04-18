package com.example.recipeapp.presentation.screen.settings.account_management

import android.content.res.Configuration
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
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.R
import com.example.recipeapp.domain.util.Constants
import com.example.recipeapp.presentation.component.DialogPreference
import com.example.recipeapp.presentation.component.MenuGroup
import com.example.recipeapp.presentation.component.MenuGroupItem
import com.example.recipeapp.presentation.component.MenuGroupItemWithSwitch
import com.example.recipeapp.presentation.component.SubtitleText
import com.example.recipeapp.presentation.component.TopAppBarWithArrow
import com.example.recipeapp.ui.theme.RecipeAppTheme

@Composable
internal fun AccountManagementScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AccountManagementViewModel = hiltViewModel()
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
    val currentAppThemeLabel = stringArrayResource(id = R.array.theme_label)[currentAppThemeKeys]
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
                    subTitle = stringResource(id = R.string.your_account_label)
                ) {
                    MenuGroupItem(
                        onClick = { /*TODO*/ },
                        title = stringResource(id = R.string.change_password_label)
                    )
                    MenuGroupItem(
                        onClick = { themeShowDialog.value = !themeShowDialog.value },
                        title = stringResource(id = R.string.app_theme_label),
                        subtitle = {
                            SubtitleText(subtitleText = currentAppThemeLabel)
                        }
                    )
//                    MenuGroupItem(
//                        onClick = { /*TODO*/ },
//                        title = stringResource(id = R.string.app_language_label),
//                        subtitle = {
//                            SubtitleText(subtitleText = "English")
//                        }
//                    )
                    MenuGroupItem(
                        onClick = { /*TODO*/ },
                        title = stringResource(id = R.string.measurement_system_label),
                        subtitle = {
                            SubtitleText(subtitleText = "Metric")
                        }
                    )
                    MenuGroupItem(onClick = { /*TODO*/ },
                        title = stringResource(id = R.string.autoplay_label),
                        subtitle = {
                            SubtitleText(subtitleText = "Never")
                        }
                    )
                    MenuGroupItemWithSwitch(
                        onClick = { /*TODO*/ },
                        title = stringResource(id = R.string.picture_in_picture_label),
                        subtitle = {
                            SubtitleText(subtitleText = "Never")
                        }
                    )
                }
                MenuGroup(subTitle = stringResource(id = R.string.deactivation_delete_label)) {
                    MenuGroupItem(
                        onClick = { /*TODO*/ },
                        title = stringResource(id = R.string.deactivation_account_label),
                        subtitle = {
                            SubtitleText(subtitleText = "Deactivate profile")
                        })
                    MenuGroupItem(
                        onClick = { /*TODO*/ },
                        title = stringResource(id = R.string.delete_account_label),
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
            title = stringResource(id = R.string.app_theme_label),
            currentValue = currentAppThemeLabel,
            labels = stringArrayResource(id = R.array.theme_label),
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