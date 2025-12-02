package com.example.recipeapp.data.mapper

import com.example.recipeapp.data.network.dto.TagDto
import com.example.recipeapp.domain.mapper.BidirectionalMapper
import com.example.recipeapp.domain.model.Tag

class TagMapper: BidirectionalMapper<Tag?, TagDto?> {
    override fun toDomain(value: TagDto?): Tag {
        return Tag(
            display_name = value?.display_name,
            id = value?.id,
            name = value?.name,
            type = value?.type
        )
    }

    override fun fromDomain(value: Tag?): TagDto {
        return TagDto(
            display_name = value?.display_name,
            id = value?.id,
            name = value?.name,
            type = value?.type
        )
    }
}
