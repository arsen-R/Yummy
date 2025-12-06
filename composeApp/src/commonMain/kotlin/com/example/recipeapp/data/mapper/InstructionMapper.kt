package com.example.recipeapp.data.mapper

import com.example.recipeapp.data.network.dto.InstructionDto
import com.example.recipeapp.domain.mapper.BidirectionalMapper
import com.example.recipeapp.domain.model.Instruction

class InstructionMapper: BidirectionalMapper<Instruction?, InstructionDto?> {
    override fun toDomain(value: InstructionDto?): Instruction {
        return Instruction(
            appliance = value?.appliance,
            display_text = value?.display_text,
            end_time = value?.end_time,
            id = value?.id,
            position = value?.position,
            start_time = value?.start_time,
            temperature = value?.temperature,
        )
    }

    override fun fromDomain(value: Instruction?): InstructionDto {
        return InstructionDto(
            appliance = value?.appliance,
            display_text = value?.display_text,
            end_time = value?.end_time,
            id = value?.id,
            position = value?.position,
            start_time = value?.start_time,
            temperature = value?.temperature,
        )
    }
}