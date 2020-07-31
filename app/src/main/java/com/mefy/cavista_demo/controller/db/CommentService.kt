package com.mefy.cavista_demo.controller.db

import com.mefy.cavista_demo.model.db.Comment
import io.realm.Realm
import io.realm.RealmResults


class CommentService : CommentInterface {

    override fun getComment(realm: Realm, id: String): Comment {
        return realm.where(Comment::class.java).equalTo("_ID", id).findFirst()!!
    }

    override fun addOrUpdateComment(realm: Realm, comment: Comment): Boolean {
        return try {
            realm.beginTransaction()
            realm.copyToRealmOrUpdate(comment)
            realm.commitTransaction()
            true
        } catch (e: Exception) {
            false
        }
    }
}