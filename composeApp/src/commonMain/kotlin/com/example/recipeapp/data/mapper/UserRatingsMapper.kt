package com.example.recipeapp.data.mapper

import com.example.recipeapp.data.network.dto.UserRatingsDto
import com.example.recipeapp.domain.mapper.BidirectionalMapper
import com.example.recipeapp.domain.model.UserRatings

class UserRatingsMapper: BidirectionalMapper<UserRatings?, UserRatingsDto?> {
    override fun toDomain(value: UserRatingsDto?): UserRatings {
        return UserRatings(
            count_negative = value?.count_negative,
            count_positive = value?.count_positive,
            score = value?.score
        )
    }

    override fun fromDomain(value: UserRatings?): UserRatingsDto {
        return UserRatingsDto(
            count_negative = value?.count_negative,
            count_positive = value?.count_positive,
            score = value?.score
        )
    }
}
