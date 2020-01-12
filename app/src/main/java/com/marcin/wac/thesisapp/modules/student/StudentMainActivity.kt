package com.marcin.wac.thesisapp.modules.student

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcin.wac.thesisapp.R
import com.marcin.wac.thesisapp.adapters.ThesisAdapter
import com.marcin.wac.thesisapp.infrastructure.di.DaggApp
import com.marcin.wac.thesisapp.models.ThesisModel
import com.marcin.wac.thesisapp.modules.details.ThesisDetailsActivity
import com.marcin.wac.thesisapp.utils.bases.BaseActivity
import kotlinx.android.synthetic.main.activity_student_main.*
import org.jetbrains.anko.intentFor
import javax.inject.Inject

interface StudentMainView{
    fun setOnUserThesisClickListener()
    fun setThesisRecycler(thesisList: List<ThesisModel>)

    fun setUserThesisStatus(text: String)

    fun startThesisDetailActivity(thesis: ThesisModel)

    fun showLoadingView()
    fun hideLoadingView()

    fun showClickAgainToExitToast()
    fun showErrorToast()
    fun finishActivity()
}

class StudentMainActivity: BaseActivity(),
    StudentMainView {

    @Inject
    lateinit var presenter: StudentMainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_main)
        ((application as DaggApp)).appComponent.inject(this)
        presenter.attachView(this)
    }

    override fun setOnUserThesisClickListener() {
        student_main_thesis_status.setOnClickListener {
            presenter.onUserThesisClickListener()
        }
    }

    override fun setThesisRecycler(thesisList: List<ThesisModel>) {
        val adapter = ThesisAdapter(thesisList) {onThesisClick(it)}
        student_main_thesis_recycler.layoutManager = LinearLayoutManager(this)
        student_main_thesis_recycler.adapter = adapter
    }

    private fun onThesisClick(thesis: ThesisModel) {
        presenter.onThesisClicked(thesis)
    }

    override fun setUserThesisStatus(text: String) {
        student_main_thesis_status.text = text
    }

    override fun startThesisDetailActivity(thesis: ThesisModel) {
        startActivity(intentFor<ThesisDetailsActivity>(ThesisDetailsActivity.THESIS to thesis))
        overridePendingTransition(R.anim.activity_enter, R.anim.activity_exit)
    }

    override fun showLoadingView() {
        student_main_loading.visibility = View.VISIBLE
    }

    override fun hideLoadingView() {
        student_main_loading.visibility = View.GONE
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    override fun showClickAgainToExitToast() {
        Toast.makeText(this, "Kliknij ponownie aby wyjść", Toast.LENGTH_SHORT).show()
    }

    override fun showErrorToast() {
        Toast.makeText(this, getString(R.string.error_try_again_later), Toast.LENGTH_SHORT).show()
    }

    override fun finishActivity() {
        finish()
    }
}