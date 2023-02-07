package com.example.recipeapp.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.domain.model.Ingredient

@Composable
fun IngredientItemListHeader(
    modifier: Modifier = Modifier,
    ingredient: Ingredient? = null
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
                modifier = modifier.padding(5.dp),
            ) {
                Text(
                    text = "Marinade",
                    modifier = modifier.padding(vertical = 5.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                IngredientItemList()
                IngredientItemList()
                IngredientItemList()

//                LazyColumn(modifier = modifier.fillMaxWidth()) {
//                    items(6) {
//                        IngredientItemList()
//                    }
//                }
            }
        }
    }
}
@Composable
fun IngredientItemList(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth().padding(vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = modifier) {
            Text(
                text = "Cumin"
            )
        }
        Column(modifier = modifier) {
            Text(
                text = "1 teaspoon",
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
    Divider()
}

@Preview(showBackground = true)
@Composable
fun IngredientItemListHeaderPreview() {
    IngredientItemList()
}


@Preview(showBackground = true)
@Composable
fun IngredientItemListPreview() {
    IngredientItemListHeader()
}