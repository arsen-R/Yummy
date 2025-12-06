package com.example.recipeapp.data.mapper

import com.example.recipeapp.data.network.dto.MeasurementDto
import com.example.recipeapp.domain.mapper.BidirectionalMapper
import com.example.recipeapp.domain.model.Measurement

class MeasurementMapper(
    private val unitsMapper: UnitsMapper
): BidirectionalMapper<Measurement?, MeasurementDto?> {
    override fun toDomain(value: MeasurementDto?): Measurement {
        return Measurement(
            id = value?.id,
            quantity = value?.quantity,
            unit = unitsMapper.toDomain(value?.unit)
        )
    }

    override fun fromDomain(value: Measurement?): MeasurementDto {
        return MeasurementDto(
            id = value?.id,
            quantity = value?.quantity,
            unit = unitsMapper.fromDomain(value?.unit)
        )
    }
}
