package com.example.recipeapp.presentation.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.domain.model.Component
import com.example.recipeapp.domain.model.Ingredient
import com.example.recipeapp.domain.model.Measurement
import com.example.recipeapp.domain.model.Section
import com.example.recipeapp.domain.model.Units
import com.example.recipeapp.ui.theme.RecipeAppTheme

@Composable
fun IngredientItemListHeader(
    modifier: Modifier = Modifier,
    ingredient: Section? = null,
) {
    Box(
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = modifier.padding(vertical = 5.dp),
            ) {
                ingredient?.name?.let { sectionName ->
                    Text(
                        text = sectionName,
                        modifier = modifier.padding(vertical = 5.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = MaterialTheme.colors.onPrimary,
                    )
                }
                ingredient?.components?.forEach {
                    IngredientItemList(components = it)
                }
            }
        }
    }
}

@Composable
fun IngredientItemList(modifier: Modifier = Modifier, components: Component) {
    val component = components.measurements?.get(components.measurements.lastIndex)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        var extraComment = StringBuilder()
        if (components.extra_comment?.isNotBlank()!!) {
            extraComment.append(", ${components.extra_comment}")
        }
        components.ingredient?.name?.let { ingredientName ->
            Text(
                text = "$ingredientName$extraComment",
                modifier = modifier
                    .weight(.5f)
                    .padding(end = 15.dp),
                color = MaterialTheme.colors.onPrimary,
            )
        }

        if (component?.quantity!! != "0") {
            Text(
                text = component.quantity,
                fontWeight = FontWeight.SemiBold,
                modifier = modifier.padding(horizontal = 5.dp),
            )
        }
        component.unit?.name?.let { unitName ->
            Text(
                text = unitName,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
    Divider()
}

@Preview(showBackground = true, name = "Light mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark mode"
)
@Composable
fun IngredientItemListHeaderPreview() {
    val section = Section(
        name = "Marinade", components = listOf(
            Component(
                ingredient = Ingredient(name = "cumin"), measurements = listOf(
                    Measurement(quantity = "1", unit = Units(name = "teaspoon"))
                )
            ),
            Component(
                ingredient = Ingredient(name = "ground cardamom"), measurements = listOf(
                    Measurement(quantity = "1", unit = Units(name = "teaspoon")),
                )
            )
        )
    )
    RecipeAppTheme() {
        IngredientItemListHeader(ingredient = section)
    }
}


@Preview(showBackground = true, name = "Light mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark mode"
)
@Composable
fun IngredientItemListPreview() {
    val component = Component(
        ingredient = Ingredient(name = "cumin"), measurements = listOf(
            Measurement(quantity = "1", unit = Units(name = "teaspoon")),
            Measurement(quantity = "1", unit = Units(name = "teaspoon"))
        ),
        extra_comment = ""
    )
    RecipeAppTheme {
        IngredientItemList(
            components = component
        )
    }
}