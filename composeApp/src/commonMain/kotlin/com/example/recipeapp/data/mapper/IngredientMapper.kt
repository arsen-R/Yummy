package com.example.recipeapp.data.mapper

import com.example.recipeapp.data.network.dto.IngredientDto
import com.example.recipeapp.domain.mapper.BidirectionalMapper
import com.example.recipeapp.domain.model.Ingredient

class IngredientMapper: BidirectionalMapper<Ingredient?, IngredientDto?> {
    override fun toDomain(value: IngredientDto?): Ingredient {
        return Ingredient(
            created_at = value?.created_at,
            display_plural = value?.display_plural,
            display_singular = value?.display_singular,
            id = value?.id,
            name = value?.name,
            updated_at = value?.updated_at,
        )
    }

    override fun fromDomain(value: Ingredient?): IngredientDto {
        return IngredientDto(
            created_at = value?.created_at,
            display_plural = value?.display_plural,
            display_singular = value?.display_singular,
            id = value?.id,
            name = value?.name,
            updated_at = value?.updated_at,
        )
    }

}
