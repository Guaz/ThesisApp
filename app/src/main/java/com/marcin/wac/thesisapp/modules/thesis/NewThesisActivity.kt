package com.marcin.wac.thesisapp.modules.thesis

import android.os.Bundle
import android.view.View
import android.view.View.*
import android.widget.Toast
import com.marcin.wac.thesisapp.R
import com.marcin.wac.thesisapp.infrastructure.di.DaggApp
import com.marcin.wac.thesisapp.utils.bases.BaseActivity
import kotlinx.android.synthetic.main.activity_new_thesis.*
import javax.inject.Inject

interface NewThesisView {
    fun setOnButtonClickListener()

    fun finishActivity()

    fun showErrorToast()

    fun showLoadingView()
    fun hideLoadingView()
}

class NewThesisActivity: BaseActivity(), NewThesisView{
    @Inject
    lateinit var presenter: NewThesisPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_thesis)
        ((application as DaggApp)).appComponent.inject(this)
        presenter.attachView(this)

    }

    override fun setOnButtonClickListener() {
        new_thesis_post_button.setOnClickListener {
            presenter.onPostButtonClick(
                new_thesis_title.text.toString(), new_thesis_description.text.toString(),
                new_thesis_language.text.toString(), new_thesis_student_skills.text.toString(),
                new_thesis_technologies.text.toString(), new_thesis_reviewer_email.text.toString()
            )
        }
    }

    override fun finishActivity() {
        finish()
    }

    override fun showErrorToast() {
        Toast.makeText(this, getString(R.string.error_try_again_later), Toast.LENGTH_SHORT).show()
    }

    override fun showLoadingView() {
        new_thesis_loading.visibility = VISIBLE
    }

    override fun hideLoadingView() {
        new_thesis_loading.visibility = GONE
    }
}