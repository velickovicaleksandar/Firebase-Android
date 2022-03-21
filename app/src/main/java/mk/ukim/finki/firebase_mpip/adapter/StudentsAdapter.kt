package mk.ukim.finki.firebase_mpip.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mk.ukim.finki.firebase_mpip.R

import mk.ukim.finki.firebase_mpip.models.Student

class StudentsAdapter(var students:MutableList<Student>): RecyclerView.Adapter<StudentsAdapter.ViewHolder>(){
    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val index:TextView
        val name:TextView
        val lastname:TextView
        val number:TextView
        val address:TextView
        init {
                index = view.findViewById(R.id.listIndex)
                name = view.findViewById(R.id.listName)
                lastname = view.findViewById(R.id.listLastName)
                number = view.findViewById(R.id.listNumber)
                address = view.findViewById(R.id.listAddress)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val currentStudent = students[position]
        holder.index.text = currentStudent.index
        holder.name.text = currentStudent.name
        holder.lastname.text = currentStudent.lastname
        holder.number.text = currentStudent.number
        holder.address.text = currentStudent.address
    }

    override fun getItemCount(): Int {
       return students.size
    }


}