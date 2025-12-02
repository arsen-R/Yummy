package com.example.recipeapp.data.mapper

import com.example.recipeapp.data.network.dto.LinkedRecipeDto
import com.example.recipeapp.domain.mapper.BidirectionalMapper
import com.example.recipeapp.domain.model.LinkedRecipe

class LinkerdRecipeMapper : BidirectionalMapper<LinkedRecipe?, LinkedRecipeDto?> {
    override fun toDomain(value: LinkedRecipeDto?): LinkedRecipe {
        return LinkedRecipe(
            approved_at = value?.approved_at,
            id = value?.id,
            name = value?.name,
            slug = value?.slug,
            thumbnail_url = value?.thumbnail_url
        )
    }

    override fun fromDomain(value: LinkedRecipe?): LinkedRecipeDto {
        return LinkedRecipeDto(
            approved_at = value?.approved_at,
            id = value?.id,
            name = value?.name,
            slug = value?.slug,
            thumbnail_url = value?.thumbnail_url
        )
    }

}
