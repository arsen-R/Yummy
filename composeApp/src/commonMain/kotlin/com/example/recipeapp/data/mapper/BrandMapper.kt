package com.example.recipeapp.data.mapper

import com.example.recipeapp.data.network.dto.BrandDto
import com.example.recipeapp.domain.mapper.BidirectionalMapper
import com.example.recipeapp.domain.model.Brand

class BrandMapper: BidirectionalMapper<Brand?, BrandDto?> {
    override fun toDomain(value: BrandDto?): Brand {
        return Brand(
            id = value?.id,
            name = value?.name
        )
    }

    override fun fromDomain(value: Brand?): BrandDto {
        return BrandDto(
            id = value?.id,
            name = value?.name
        )
    }
}