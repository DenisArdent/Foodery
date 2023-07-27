package com.denisardent.local.restaurants.room.views

import androidx.room.ColumnInfo
import androidx.room.DatabaseView

@DatabaseView(
    viewName = "account_liked_restaurants_view",
    value = " SELECT \n" +
            "  accounts.id as account_id, " +
            " restaurants.id as restaurant_id, " +
            " IFNULL (accounts_restaurants_liked.is_liked, 0) as is_liked " +
            " FROM accounts " +
            " JOIN restaurants " +
            " LEFT JOIN accounts_restaurants_liked " +
            " ON accounts_restaurants_liked.account_id = accounts.id AND " +
            " accounts_restaurants_liked.restaurant_id = restaurants.id"
)
data class AccountLikedRestaurantsView(
    @ColumnInfo(name = "account_id") val accountId: Long,
    @ColumnInfo(name =  "restaurant_id") val restaurantId: Long,
    @ColumnInfo(name = "is_liked") val isLiked: Boolean
)