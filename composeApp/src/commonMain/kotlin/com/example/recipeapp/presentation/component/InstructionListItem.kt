package com.example.recipeapp.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.domain.model.Instruction
import com.example.recipeapp.ui.theme.RecipeAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun InstructionListItem(
    modifier: Modifier = Modifier,
    instruction: Instruction?,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            modifier = modifier.padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = instruction?.position.toString(),
                modifier = modifier,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
            Spacer(modifier = modifier.width(10.dp))
            Text(
                text = instruction?.display_text!!,
                modifier = modifier.weight(1f),
            )
        }
    }
}

@Preview
@Composable
fun InstructionListItemPreview() {
    val instruction = Instruction(
        display_text = "Combine all marinade ingredients in a large bowl and whisk together.",
        position = 1,
    )
    RecipeAppTheme {
        InstructionListItem(instruction = instruction)
    }
}