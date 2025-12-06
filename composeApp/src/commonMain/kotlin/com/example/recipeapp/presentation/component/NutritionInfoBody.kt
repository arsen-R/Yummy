package com.example.recipeapp.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.domain.model.Nutrition
import com.example.recipeapp.resources.Res
import com.example.recipeapp.resources.calories_label
import com.example.recipeapp.resources.carbohydrates_label
import com.example.recipeapp.resources.fat_label
import com.example.recipeapp.resources.fiber_label
import com.example.recipeapp.resources.protein_label
import com.example.recipeapp.resources.sugar_label
import com.example.recipeapp.ui.theme.RecipeAppTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun NutritionInfoBody(
    modifier: Modifier = Modifier,
    nutrition: Nutrition?
) {
    nutrition?.updated_at?.let {
        Row(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Nutrition Info",
                    modifier = modifier.padding(vertical = 5.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                NutritionInfoItem(
                    nutritionTitle = stringResource(Res.string.calories_label),
                    nutritionCount = nutrition?.calories
                )
                NutritionInfoItem(
                    nutritionTitle = stringResource(Res.string.carbohydrates_label),
                    nutritionCount = nutrition?.carbohydrates
                )
                NutritionInfoItem(
                    nutritionTitle = stringResource(Res.string.fat_label),
                    nutritionCount = nutrition?.fat
                )
                NutritionInfoItem(
                    nutritionTitle = stringResource(Res.string.fiber_label),
                    nutritionCount = nutrition?.fiber
                )
                NutritionInfoItem(
                    nutritionTitle = stringResource(Res.string.protein_label),
                    nutritionCount = nutrition?.protein
                )
                NutritionInfoItem(
                    nutritionTitle = stringResource(Res.string.sugar_label),
                    nutritionCount = nutrition?.sugar
                )
                Text(
                    text = "Estimated value based on one serving size",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = modifier.padding(5.dp),
                )
            }
        }
    }

}

@Composable
fun NutritionInfoItem(
    modifier: Modifier = Modifier,
    nutritionTitle: String,
    nutritionCount: Int?
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = nutritionTitle,
            modifier = modifier
                .weight(.5f)
                .padding(end = 15.dp),
            color = MaterialTheme.colors.onPrimary,
        )
        Text(
            text = nutritionCount.toString(),
            fontWeight = FontWeight.SemiBold,
            modifier = modifier.padding(horizontal = 5.dp),
        )
    }
    Divider()
}


@Preview(showBackground = true)
@Composable
fun NutritionInfoComponentPreview() {
    val nutrition = Nutrition(
        calories = 473,
        carbohydrates = 11,
        fat = 21,
        fiber = 1,
        protein = 60,
        sugar = 8
    )
    RecipeAppTheme {
        NutritionInfoBody(nutrition = nutrition)
    }
}