package com.example.recipeapp.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.recipeapp.ui.theme.RecipeAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DialogPreference(
    modifier: Modifier = Modifier,
    showDialog: Boolean,
    title: String,
    currentValue: String? = null,
    labels: List<String>,
    onDismissRequest: () -> Unit,
    onOptionSelected: (Int) -> Unit
) {
    if (showDialog) {
        Dialog(onDismissRequest = onDismissRequest) {
            Card(
                elevation = 8.dp,
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(modifier = modifier.padding(16.dp)) {
                    Text(
                        text = title,
                        modifier = modifier,
                        style = MaterialTheme.typography.h3,
                        color = MaterialTheme.colors.onSurface,
                        fontSize = 22.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = modifier.height(5.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        labels.forEachIndexed { index, option ->
                            ItemPreferencesOption(
                                optionText = option,
                                selectedOption = option == currentValue
                            ) {
                                onOptionSelected(index)
                                onDismissRequest()
                            }
                        }
                    }
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "Cancel".uppercase(),
                            modifier = modifier.clickable { onDismissRequest() },
                            style = MaterialTheme.typography.h4,
                            color = MaterialTheme.colors.onPrimary,
                            fontSize = 16.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ItemPreferencesOption(
    modifier: Modifier = Modifier,
    optionText: String,
    selectedOption: Boolean,
    onOptionSelected: () -> Unit
) {
    Row(
        modifier = modifier.clickable { onOptionSelected() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        RadioButton(selected = selectedOption, onClick = { onOptionSelected() })

        Text(
            text = optionText,
            modifier = modifier
                .fillMaxWidth(),
            color = MaterialTheme.colors.onSurface,
            fontSize = 18.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start
        )
    }
}

@Preview
@Composable
fun DialogsPreview() {
    RecipeAppTheme {
        DialogPreference(
            showDialog = true,
            title = "Theme",
            labels = listOf(
                "System default",
                "Light",
                "Dark"
            ),
            onDismissRequest = { /*TODO*/ }) {

        }
    }
}