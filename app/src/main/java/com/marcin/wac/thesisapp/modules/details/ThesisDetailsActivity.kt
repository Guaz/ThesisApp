package com.marcin.wac.thesisapp.modules.details

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcin.wac.thesisapp.R
import com.marcin.wac.thesisapp.adapters.StudentsAdapter
import com.marcin.wac.thesisapp.infrastructure.di.DaggApp
import com.marcin.wac.thesisapp.models.UserModel
import com.marcin.wac.thesisapp.utils.bases.BaseActivity
import kotlinx.android.synthetic.main.activity_thesis_details.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton
import javax.inject.Inject

interface ThesisDetailsView{
    fun setTitle(text: String)
    fun setDescription(text: String)
    fun setLanguage(text: String)
    fun setStudentSkills(text: String)
    fun setTechnologies(text: String)
    fun setPromoterEmail(text: String)
    fun setReviewerEmail(text: String)
    fun setReserved(text: String)
    fun setOccupied(text: String)
    fun setStudentEmail(text: String)

    fun setOnChangeStatusClickListener()
    fun setOnDeleteClickListener()
    fun setOnCheckBoxClickLogic()
    fun setOnTextEnteredTextWatcher()
    fun setOnChangeStatusConfirmationClickListener()
    fun setStudentsListAdapter(studentList: List<UserModel>)

    fun hideStudentEmailLayout()
    fun hideButtons()

    fun showRemoveDialog()
    fun showStatusChangedToast()
    fun showErrorToast()
    fun showThesisRemovedToast()
    fun finishActivity()

    fun showChoosenStudent(name: String)
    fun showChangeStatusConfirmationButton()

    fun showChangeLayout()
    fun hideChangeLayout()

    fun showStudentRecycler()
    fun hideStudentRecycler()

    fun showLoadingLayout()
    fun hideLoadingLayout()
}
class ThesisDetailsActivity: BaseActivity(), ThesisDetailsView{

    @Inject
    lateinit var presenter: ThesisDetailsPresenter

    companion object {
        const val THESIS = "thesis"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thesis_details)
        ((application as DaggApp)).appComponent.inject(this)
        presenter.attachView(this)
        presenter.init(intent?.extras?.getParcelable(THESIS))
    }

    override fun setTitle(text: String) {
        thesis_detail_title.text = text
    }

    override fun setDescription(text: String) {
        thesis_detail_description.text = text
    }

    override fun setLanguage(text: String) {
        thesis_detail_language.text = text
    }

    override fun setStudentSkills(text: String) {
        thesis_detail_student_skills.text = text
    }

    override fun setTechnologies(text: String) {
        thesis_detail_technologies.text = text
    }

    override fun setPromoterEmail(text: String) {
        thesis_detail_promoter_email.text = text
    }

    override fun setReviewerEmail(text: String) {
        thesis_detail_reviewer_email.text = text
    }

    override fun setReserved(text: String) {
        thesis_detail_reserved.text = text
    }

    override fun setOccupied(text: String) {
        thesis_detail_occupied.text = text
    }

    override fun setStudentEmail(text: String) {
        thesis_detail_student_email.text = text
    }

    override fun setOnChangeStatusClickListener() {
        thesis_detail_change_status.setOnClickListener {
            presenter.onChangeStatusClick(thesis_detail_change_status_layout.isShown)
        }
    }

    override fun setOnDeleteClickListener() {
        thesis_detail_delete.setOnClickListener {
            presenter.onDeleteClick()
        }
    }

    override fun setOnCheckBoxClickLogic() {
        thesis_detail_reserve_checkbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked && thesis_detail_occupy_checkbox.isChecked) thesis_detail_occupy_checkbox.isChecked = false
            if (!isChecked && !thesis_detail_occupy_checkbox.isChecked) thesis_detail_occupy_checkbox.isChecked = true
        }
        thesis_detail_occupy_checkbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked && thesis_detail_reserve_checkbox.isChecked) thesis_detail_reserve_checkbox.isChecked = false
            if (!isChecked && !thesis_detail_reserve_checkbox.isChecked) thesis_detail_reserve_checkbox.isChecked = true
        }
    }

    override fun setOnTextEnteredTextWatcher() {
        thesis_detail_student_edittext.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.onSearchTextChanged(s.toString())
            }
        })
    }

    override fun setOnChangeStatusConfirmationClickListener() {
        thesis_detail_change_status_confirm_change.setOnClickListener {
            presenter.onChangeStatusConfirmationClick(thesis_detail_occupy_checkbox.isChecked)
        }
    }

    override fun setStudentsListAdapter(studentList: List<UserModel>) {
        val adapter = StudentsAdapter(studentList) {onStudentClick(it)}
        thesis_detail_user_recycler.layoutManager = LinearLayoutManager(this)
        thesis_detail_user_recycler.adapter = adapter
    }

    private fun onStudentClick(student: UserModel){
        presenter.onStudentClick(student)
    }

    override fun hideStudentEmailLayout() {
        thesis_detail_student_email_layout.visibility = View.GONE
    }

    override fun hideButtons() {
        thesis_detail_buttons_layout.visibility = View.GONE
    }

    override fun showRemoveDialog() {
        this.alert("Czy chcesz usunac ten temat?") {
            yesButton { presenter.onDeleteDialogYesClicked() }
            noButton {  }
        }.show()
    }

    override fun showStatusChangedToast() {
        Toast.makeText(this, "Status został zmieniony", Toast.LENGTH_SHORT).show()
    }

    override fun showErrorToast() {
        Toast.makeText(this, getString(R.string.error_try_again_later), Toast.LENGTH_SHORT).show()
    }

    override fun showThesisRemovedToast() {
        Toast.makeText(this, "Temat usuniety", Toast.LENGTH_SHORT).show()
    }

    override fun finishActivity() {
        finish()
    }

    override fun showChoosenStudent(name: String) {
        thesis_detail_choosen_student.text = name
        thesis_detail_choosen_student.visibility = View.VISIBLE
    }

    override fun showChangeStatusConfirmationButton() {
        thesis_detail_change_status_confirm_change.visibility = View.VISIBLE
    }

    override fun showChangeLayout() {
        thesis_detail_change_status_layout.visibility = View.VISIBLE
    }

    override fun hideChangeLayout() {
        thesis_detail_change_status_layout.visibility = View.GONE
    }

    override fun showStudentRecycler() {
        thesis_detail_user_recycler.visibility = View.VISIBLE
    }

    override fun hideStudentRecycler() {
        thesis_detail_user_recycler.visibility = View.GONE
    }

    override fun showLoadingLayout() {
        thesis_detail_loading.visibility = View.VISIBLE
    }

    override fun hideLoadingLayout() {
        thesis_detail_loading.visibility = View.GONE
    }
}