package com.fintech.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fintech.myapplication.Post.Companion.TABLE_NAME

/**
 * Data class for Database entity and Serialization.
 */
@Entity(tableName = TABLE_NAME)
data class Post(
    @PrimaryKey
    var id: Int? = 0,
    var title: String? = null,
    var author: String? = null,
    var body: String? = null,
    var imageUrl: String? = null,
) {
    companion object {
        const val TABLE_NAME = "car_posts"
    }
}
