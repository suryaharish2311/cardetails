package com.fintech.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.app.ShareCompat
import coil.load
import com.fintech.myapplication.databinding.ActivityPostDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class PostDetailsActivity : BaseActivity<PostDetailsViewModel, ActivityPostDetailsBinding>() {
    @Inject
    lateinit var viewModelFactory: PostDetailsViewModel.PostDetailsViewModelFactory

    override val mViewModel: PostDetailsViewModel by viewModels {
        val postId =
            intent.extras?.getInt(KEY_POST_ID)
                ?: throw IllegalArgumentException("`postId` must be non-null")

        PostDetailsViewModel.provideFactory(viewModelFactory, postId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)

        setSupportActionBar(mViewBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onStart() {
        super.onStart()
        initPost()
    }

    private fun initPost() {
        mViewModel.post.observe(this) { post ->
            mViewBinding.postContent.apply {
                postTitle.text = post.title
                postAuthor.text = post.author
                postBody.text = post.body
            }
            mViewBinding.imageView.load(post.imageUrl)
        }
    }

    @SuppressLint("StringFormatInvalid")
    private fun share() {
        val post = mViewModel.post.value ?: return
        val shareMsg = getString(R.string.share_message, post.title, post.author)

        val intent =
            ShareCompat.IntentBuilder
                .from(this)
                .setType("text/plain")
                .setText(shareMsg)
                .intent

        startActivity(Intent.createChooser(intent, null))
    }

    override fun getViewBinding(): ActivityPostDetailsBinding = ActivityPostDetailsBinding.inflate(layoutInflater)

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                supportFinishAfterTransition()
                return true
            }

            R.id.action_share -> {
                share()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val KEY_POST_ID = "postId"

        fun getStartIntent(
            context: Context,
            postId: Int,
        ) = Intent(context, PostDetailsActivity::class.java).apply { putExtra(KEY_POST_ID, postId) }
    }
}
