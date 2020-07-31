package com.mefy.cavista_demo.view

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.mefy.cavista_demo.R
import com.mefy.cavista_demo.controller.BaseActivity
import com.mefy.cavista_demo.controller.db.CommentService
import com.mefy.cavista_demo.model.bean.Images
import com.mefy.cavista_demo.model.db.Comment
import com.mefy.cavista_demo.model.responces.CommonResponse
import com.mefy.cavista_demo.utils.Finals
import io.realm.Realm


class Details : BaseActivity() {
    private val TAG :String = "DetailsActivity"
    private lateinit var imageView : ImageView
    private lateinit var tvComment : TextView
    private lateinit var tvAddedComment : TextView
    private lateinit var commentService: CommentService
    private lateinit var realm: Realm
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        initViews()
        initData()
    }

    override fun onGetResponse(response: CommonResponse?, callFor: String?) {
        TODO("Not yet implemented")
    }

    private fun getIntentData(): Images {
        val intent = this.intent
        val bundle = intent.extras
        val images: Images? = bundle!!.getSerializable(Finals.SELECTED_IMAGE) as Images?
        return images!!;
    }

    private fun initData() {
        var image  : Images = getIntentData()
        if (image != null) {
            val actionBar = supportActionBar
            id = image.id
            actionBar?.title = image.id

            Glide
                    .with(this)
                    .load(image.link)
                    .placeholder(R.drawable.ic_baseline_local_florist_24)
                    .error(R.drawable.ic_baseline_error_outline_24)
                    .into(imageView)
        }

        try {
            commentService = CommentService()
            realm = Realm.getDefaultInstance()
            showComment()
        } catch (e: Exception) {
            Log.e(TAG, "Exception in creating realm object:", e)
        }
    }

    private fun showComment() {
        try {
            tvAddedComment.text = commentService.getComment(realm, id).textComment.toString()
        }catch (e: Exception) {
            Log.e(TAG, "Exception while getting comment:", e)
        }
    }

    private fun initViews() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        imageView = findViewById(R.id.image)
        tvComment = findViewById(R.id.comment)
        tvAddedComment = findViewById(R.id.addedComment)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId ==  android.R.id.home){
            onBackPressed()
            return true
        }else{
            return super.onOptionsItemSelected(item)
        }
    }

    fun submit(view: View) {
        var strComment = tvComment.text.toString().trim()
        if(strComment.isEmpty()){
            this@Details.customToastActivity(this@Details, resources.getString(R.string.no_comment_added))
        }else{
            var commentModel = Comment(id, strComment)
            var isInserted : Boolean = commentService.addOrUpdateComment(realm, commentModel)
            if(isInserted){
                tvComment.text = ""
                showComment()
                this@Details.customToastActivity(this@Details, resources.getString(R.string.added_comment))
            }else{
                this@Details.customToastActivity(this@Details, resources.getString(R.string.error_in_adding_comment))
            }
        }

    }
}