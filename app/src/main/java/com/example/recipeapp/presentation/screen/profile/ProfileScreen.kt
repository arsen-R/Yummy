package com.example.recipeapp.presentation.screen.profile

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.R
import com.example.recipeapp.presentation.component.MenuGroup
import com.example.recipeapp.presentation.component.MenuGroupItem
import com.example.recipeapp.presentation.component.SubtitleText
import com.example.recipeapp.presentation.component.TopBar
import com.example.recipeapp.presentation.navigation.navigateToAccountManagement
import com.example.recipeapp.ui.theme.RecipeAppTheme

@Composable
internal fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    ProfileScreen(
        modifier = modifier,
        navController = navController,
        onSignOutCurrentUser = viewModel::fetchSignOutCurrentUser
    )
}

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    onSignOutCurrentUser: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(title = stringResource(id = R.string.profile_screen))
        }, content = {
            Box(modifier = modifier.padding(it)) {
                ProfileScreenBody(
                    modifier = modifier,
                    onSignOutCurrentUser = onSignOutCurrentUser,
                    navController = navController
                )
            }
        }
    )
}

@Composable
fun ProfileScreenBody(
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
                ProfileInfo()
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

@Composable
fun ProfileInfo(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(color = MaterialTheme.colors.background)
            .clickable { },
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Image(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "account_logo",
            modifier = modifier.size(75.dp),
            colorFilter = ColorFilter.tint(color = Color.Gray)
        )
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = "Arsen",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            )
            Text(
                text = "arsenr412@gmail.com",
                style = TextStyle(
                    color = MaterialTheme.colors.onBackground
                )
            )
        }
    }
}

@Preview(
    name = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true

)
@Composable
fun ProfilePreview() {
    RecipeAppTheme {
        ProfileInfo()
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
        ProfileScreenBody(navController = rememberNavController(), onSignOutCurrentUser = {})
    }
}