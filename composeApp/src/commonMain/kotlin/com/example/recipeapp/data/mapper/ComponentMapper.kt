package com.example.recipeapp.data.mapper

import com.example.recipeapp.data.network.dto.ComponentDto
import com.example.recipeapp.domain.mapper.BidirectionalMapper
import com.example.recipeapp.domain.model.Component

class ComponentMapper(
    private val ingredientMapper: IngredientMapper,
    private val linkerdRecipeMapper: LinkerdRecipeMapper,
    private val measurementMapper: MeasurementMapper,
): BidirectionalMapper<Component?, ComponentDto?> {
    override fun toDomain(value: ComponentDto?): Component {
        return Component(
            extra_comment = value?.extra_comment,
            id = value?.id,
            ingredient = ingredientMapper.toDomain(value?.ingredient),
            linked_recipe = linkerdRecipeMapper.toDomain(value?.linked_recipe),
            measurements = value?.measurements?.map { measurementMapper.toDomain(it) },
            position = value?.position,
            raw_text = value?.raw_text,
        )
    }

    override fun fromDomain(value: Component?): ComponentDto {
        return ComponentDto(
            extra_comment = value?.extra_comment,
            id = value?.id,
            ingredient = ingredientMapper.fromDomain(value?.ingredient),
            linked_recipe = linkerdRecipeMapper.fromDomain(value?.linked_recipe),
            measurements = value?.measurements?.map { measurementMapper.fromDomain(it) },
            position = value?.position,
            raw_text = value?.raw_text,
        )
    }
}
