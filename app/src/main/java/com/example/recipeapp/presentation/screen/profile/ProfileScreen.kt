package com.example.recipeapp.presentation.screen.profile

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.R
import com.example.recipeapp.presentation.component.TopBar
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
                ProfileMenu(
                    modifier = modifier,
                    content = {
                        ProfileSubMenu(onClick = { /*TODO*/ }, text = "Account management")
                        ProfileSubMenu(onClick = { /*TODO*/ }, text = "Notifications")
                        ProfileSubMenu(onClick = { /*TODO*/ }, text = "Privacy and data")
                        ProfileSubMenu(onClick = { /*TODO*/ }, text = "Reports")
                    }, subTitle = "Settings"
                )

                ProfileMenu(
                    modifier = modifier,
                    content = {
                        ProfileSubMenu(onClick = { /*TODO*/ }, text = "Help center")
                        ProfileSubMenu(onClick = { /*TODO*/ }, text = "Term of service")
                        ProfileSubMenu(onClick = { /*TODO*/ }, text = "Privacy policy")
                        ProfileSubMenu(onClick = { /*TODO*/ }, text = "About")
                    }, subTitle = "Support"
                )
                ProfileMenu(
                    modifier = modifier,
                    content = {
                        ProfileSubMenu(
                            onClick = { onSignOutCurrentUser() },
                            text = "Sign out",
                            trailingIcon = null
                        )
                    },
                    subTitle = null
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileSubMenu(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    trailingIcon: ImageVector? = Icons.AutoMirrored.Filled.KeyboardArrowRight
) {
    ListItem(
        modifier = modifier.clickable { onClick() },
        text = {
            Text(
                text = text,
                style = TextStyle(
                    color = MaterialTheme.colors.onBackground,
                    fontSize = 16.sp
                )
            )
        },
        trailing = {
            trailingIcon?.let { icon ->
                Icon(
                    imageVector = icon,
                    contentDescription = "",
                    tint = MaterialTheme.colors.onSurface
                )
            }
        }
    )
}

@Composable
fun ProfileMenu(
    modifier: Modifier = Modifier,
    subTitle: String?,
    content: @Composable ColumnScope.() -> Unit,
    ) {
    Column(modifier = modifier.fillMaxWidth()) {
        subTitle?.let { subtitle ->
            Text(
                text = subtitle,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onSecondary,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                modifier = modifier.padding(start = 15.dp)
            )
        }
        Column(modifier = modifier, content = content)
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
            .clickable {  },
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