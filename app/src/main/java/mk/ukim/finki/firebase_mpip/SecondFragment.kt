package mk.ukim.finki.firebase_mpip

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mk.ukim.finki.firebase_mpip.adapter.StudentsAdapter
import mk.ukim.finki.firebase_mpip.databinding.FragmentSecondBinding
import mk.ukim.finki.firebase_mpip.models.Student
import com.google.firebase.database.DatabaseError

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase

import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DatabaseReference







/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView

    private lateinit var studentsAdapter: StudentsAdapter
    var database = FirebaseDatabase.getInstance().getReference()
    var studentsRef=database.child("students")
//    var studentReference =
    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val students:MutableList<Student> = mutableListOf();
        recyclerView = view.findViewById(R.id.recyclerViewComponent)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        studentsAdapter = StudentsAdapter(students)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = studentsAdapter;
        studentsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (datasnapshot:DataSnapshot in dataSnapshot.children){
                    val index = datasnapshot.getValue(Student::class.java)?.index.toString()
                    val name = datasnapshot.getValue(Student::class.java)?.name.toString()
                    val lname = datasnapshot.getValue(Student::class.java)?.lastname.toString()
                    val number = datasnapshot.getValue(Student::class.java)?.number.toString()
                    val address = datasnapshot.getValue(Student::class.java)?.address.toString()
                    val student = Student(index,name,lname,number,address)

                    if (student != null) {
                        students.add(student)
                    }

                }
                studentsAdapter.notifyDataSetChanged();


            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value

            }
        })

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}