package com.marcelocuevas.mercadolibrechallenge.presentation.mapper

import model.detail.ItemDetailModel
import model.detail.ReviewModel
import com.marcelocuevas.mercadolibrechallenge.presentation.model.ItemDetailUIModel as ItemDetailUIModel
import com.marcelocuevas.mercadolibrechallenge.presentation.model.ItemDetailUIModel.Attribute as AttributeUIModel
import com.marcelocuevas.mercadolibrechallenge.presentation.model.ItemDetailUIModel.Review as ReviewUIModel
import com.marcelocuevas.mercadolibrechallenge.presentation.model.ItemDetailUIModel.Review.Level as LevelUIModel
import com.marcelocuevas.mercadolibrechallenge.presentation.model.ItemDetailUIModel.Review.Item as ItemReviewUIModel
import model.dictionary.Dictionary

fun mapItemDetailDomain(input: ItemDetailModel, dictionary: Dictionary): ItemDetailUIModel {
    return ItemDetailUIModel(
        dictionary,
        input.item.title,
        input.item.subtitle,
        input.description.plainText,
        input.item.price,
        input.item.originalPrice,
        input.item.soldQuantity,
        input.item.condition,
        input.item.thumbnail,
        input.hasFulfillment(),
        input.hasStorePickUp(),
        input.hasFreeShipping(),
        input.hasShippingGuaranteed(),
        input.item.warranty,
        input.item.tags,
        input.item.pictures.map { mapPictureDomain(it) },
        mapReviewDomain(input.review),
        input.item.attributes.map { mapAttributeDomain(it) }
    )
}

fun mapPictureDomain(input: ItemDetailModel.ItemModel.Picture): String {
    return input.url
}

fun mapReviewDomain(input: ReviewModel): ReviewUIModel {
    return ReviewUIModel(
        input.ratingAverage,
        mapLevelDomain(input.levels),
        input.reviews.map { mapReviewItemDomain(it) }
    )
}

fun mapLevelDomain(input: ReviewModel.LevelModel): LevelUIModel {
    return LevelUIModel(
        input.oneStar,
        input.twoStar,
        input.threeStar,
        input.fourStar,
        input.fiveStar
    )
}

fun mapReviewItemDomain(input: ReviewModel.ItemModel): ItemReviewUIModel {
    return ItemReviewUIModel(
        input.title,
        input.content,
        input.rate,
        input.valorization,
        input.likes,
        input.dislikes,
        input.revelance
    )
}

fun mapAttributeDomain(input: ItemDetailModel.ItemModel.Attribute): AttributeUIModel {
    return AttributeUIModel(
        input.name,
        input.valueName
    )
}