package com.mefy.cavista_demo.controller.db

import com.mefy.cavista_demo.model.db.Comment
import io.realm.Realm
import io.realm.RealmResults

interface CommentInterface {
    fun addOrUpdateComment(realm: Realm, comment: Comment) : Boolean
    fun getComment(realm: Realm, id: String) : Comment
}