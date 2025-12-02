package com.example.recipeapp.data.mapper

import com.example.recipeapp.data.network.dto.PriceDto
import com.example.recipeapp.domain.mapper.BidirectionalMapper
import com.example.recipeapp.domain.model.Price

class PriceMapper: BidirectionalMapper<Price?, PriceDto?> {
    override fun toDomain(value: PriceDto?): Price {
        return Price(
            consumption_portion = value?.consumption_portion,
            consumption_total = value?.consumption_total,
            portion = value?.portion,
            total = value?.total,
            updated_at = value?.updated_at,
        )
    }

    override fun fromDomain(value: Price?): PriceDto {
        return PriceDto(
            consumption_portion = value?.consumption_portion,
            consumption_total = value?.consumption_total,
            portion = value?.portion,
            total = value?.total,
            updated_at = value?.updated_at,
        )
    }
}