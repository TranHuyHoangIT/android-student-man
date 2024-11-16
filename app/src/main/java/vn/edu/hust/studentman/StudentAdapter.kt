//package vn.edu.hust.studentman
//
//import android.app.AlertDialog
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.EditText
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.google.android.material.snackbar.Snackbar
//
//class StudentAdapter(private val students: MutableList<StudentModel>) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
//
//  class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//    val textStudentName: TextView = itemView.findViewById(R.id.text_student_name)
//    val textStudentId: TextView = itemView.findViewById(R.id.text_student_id)
//    val imageEdit: ImageView = itemView.findViewById(R.id.image_edit)
//    val imageRemove: ImageView = itemView.findViewById(R.id.image_remove)
//  }
//
//  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
//    val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_student_item, parent, false)
//    return StudentViewHolder(itemView)
//  }
//
//  override fun getItemCount(): Int = students.size
//
//  override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
//    val student = students[position]
//
//    holder.textStudentName.text = student.studentName
//    holder.textStudentId.text = student.studentId
//
//    holder.imageEdit.setOnClickListener {
//      showEditStudentDialog(holder, student, position)
//    }
//
//    holder.imageRemove.setOnClickListener {
//      showDeleteConfirmationDialog(holder, student, position)
//    }
//  }
//
//  private fun showEditStudentDialog(holder: StudentViewHolder, student: StudentModel, position: Int) {
//    val dialogView = LayoutInflater.from(holder.itemView.context).inflate(R.layout.dialog_add_student, null).apply {
//      findViewById<EditText>(R.id.editTextName).setText(student.studentName)
//      findViewById<EditText>(R.id.editTextId).setText(student.studentId)
//    }
//
//    AlertDialog.Builder(holder.itemView.context)
//      .setTitle("Edit Student")
//      .setView(dialogView)
//      .setPositiveButton("Save") { _, _ ->
//        val updatedName = dialogView.findViewById<EditText>(R.id.editTextName).text.toString()
//        val updatedId = dialogView.findViewById<EditText>(R.id.editTextId).text.toString()
//        if (updatedName.isNotEmpty() && updatedId.isNotEmpty()) {
//          student.studentName = updatedName
//          student.studentId = updatedId
//          notifyItemChanged(position)
//        }
//      }
//      .setNegativeButton("Cancel", null)
//      .create()
//      .show()
//  }
//
//  private fun showDeleteConfirmationDialog(holder: StudentViewHolder, student: StudentModel, position: Int) {
//    AlertDialog.Builder(holder.itemView.context)
//      .setTitle("Delete Student")
//      .setMessage("Are you sure you want to delete ${student.studentName}?")
//      .setPositiveButton("Yes") { _, _ ->
//        val removedStudent = students.removeAt(position)
//        notifyItemRemoved(position)
//        showUndoSnackbar(holder, removedStudent, position)
//      }
//      .setNegativeButton("No", null)
//      .create()
//      .show()
//  }
//
//  private fun showUndoSnackbar(holder: StudentViewHolder, removedStudent: StudentModel, position: Int) {
//    Snackbar.make(holder.itemView, "${removedStudent.studentName} was deleted", Snackbar.LENGTH_LONG)
//      .setAction("Undo") {
//        students.add(position, removedStudent)
//        notifyItemInserted(position)
//      }.show()
//  }
//}

package vn.edu.hust.studentman

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class StudentAdapter(
  private val students: MutableList<StudentModel>,
  private val mainActivity: MainActivity
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

  class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textStudentName: TextView = itemView.findViewById(R.id.text_student_name)
    val textStudentId: TextView = itemView.findViewById(R.id.text_student_id)
    val imageEdit: ImageView = itemView.findViewById(R.id.image_edit)
    val imageRemove: ImageView = itemView.findViewById(R.id.image_remove)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
    val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_student_item, parent, false)
    return StudentViewHolder(itemView)
  }

  override fun getItemCount(): Int = students.size

  override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
    val student = students[position]

    holder.textStudentName.text = student.studentName
    holder.textStudentId.text = student.studentId

    holder.imageEdit.setOnClickListener {
      showEditStudentDialog(holder, student, position)
    }

    holder.imageRemove.setOnClickListener {
      showDeleteConfirmationDialog(holder, student, position)
    }
  }

  private fun showEditStudentDialog(holder: StudentViewHolder, student: StudentModel, position: Int) {
    val dialogView = LayoutInflater.from(holder.itemView.context).inflate(R.layout.dialog_add_student, null).apply {
      findViewById<EditText>(R.id.editTextName).setText(student.studentName)
      findViewById<EditText>(R.id.editTextId).setText(student.studentId)
    }

    AlertDialog.Builder(holder.itemView.context)
      .setTitle("Edit Student")
      .setView(dialogView)
      .setPositiveButton("Save") { _, _ ->
        val updatedName = dialogView.findViewById<EditText>(R.id.editTextName).text.toString()
        val updatedId = dialogView.findViewById<EditText>(R.id.editTextId).text.toString()
        if (updatedName.isNotEmpty() && updatedId.isNotEmpty()) {
          student.studentName = updatedName
          student.studentId = updatedId
          notifyItemChanged(position)
        }
      }
      .setNegativeButton("Cancel", null)
      .create()
      .show()
  }

  private fun showDeleteConfirmationDialog(holder: StudentViewHolder, student: StudentModel, position: Int) {
    AlertDialog.Builder(holder.itemView.context)
      .setTitle("Delete Student")
      .setMessage("Are you sure you want to delete ${student.studentName}?")
      .setPositiveButton("Yes") { _, _ ->
        val removedStudent = students.removeAt(position)
        notifyItemRemoved(position)
        showUndoSnackbar(holder, removedStudent, position)
      }
      .setNegativeButton("No", null)
      .create()
      .show()
  }

  private fun showUndoSnackbar(holder: StudentViewHolder, removedStudent: StudentModel, position: Int) {
    Snackbar.make(holder.itemView, "${removedStudent.studentName} was deleted", Snackbar.LENGTH_LONG)
      .setAction("Undo") {
        students.add(position, removedStudent)
        notifyItemInserted(position)
      }.show()
  }
}
