package com.marcin.wac.thesisapp.modules.student.smain

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcin.wac.thesisapp.R
import com.marcin.wac.thesisapp.adapters.ThesisAdapter
import com.marcin.wac.thesisapp.infrastructure.di.DaggApp
import com.marcin.wac.thesisapp.models.ThesisModel
import com.marcin.wac.thesisapp.utils.bases.BaseActivity
import kotlinx.android.synthetic.main.activity_student_main.*
import javax.inject.Inject

interface StudentMainView{
    fun setThesisRecycler(thesisList: List<ThesisModel>)

    fun setUserThesisStatus(text: String)

    fun showLoadingView()
    fun hideLoadingView()
}

class StudentMainActivity: BaseActivity(), StudentMainView{

    @Inject
    lateinit var presenter: StudentMainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_main)
        ((application as DaggApp)).appComponent.inject(this)
        presenter.attachView(this)

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

    override fun showLoadingView() {
        student_main_loading.visibility = View.VISIBLE
    }

    override fun hideLoadingView() {
        student_main_loading.visibility = View.GONE
    }

}