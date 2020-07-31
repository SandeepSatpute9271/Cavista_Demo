package com.mefy.cavista_demo.model.db

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * by default kotlin class is final therefore declare it with open annotation
 * to allow to create the object
 */
open class Comment(
        @PrimaryKey open var _ID: String = "",
        open var textComment: String = "" )
    : RealmObject() {
}