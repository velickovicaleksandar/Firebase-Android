package mk.ukim.finki.firebase_mpip

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.gms.tasks.OnCompleteListener
import mk.ukim.finki.firebase_mpip.databinding.FragmentFirstBinding
import com.google.firebase.database.DatabaseReference

import com.google.firebase.database.FirebaseDatabase
import mk.ukim.finki.firebase_mpip.models.Student


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var database = FirebaseDatabase.getInstance()
    var studentReference = database.getReference("students")
    private lateinit var studentIndex: EditText
    private lateinit var studentName: EditText
    private lateinit var studentLastName: EditText
    private lateinit var studentNumber: EditText
    private lateinit var studentAddress: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        studentIndex = view.findViewById(R.id.studentIndex)
        studentName = view.findViewById(R.id.studentName)
        studentLastName = view.findViewById(R.id.studentLastname)
        studentNumber = view.findViewById(R.id.studentNumber)
        studentAddress = view.findViewById(R.id.studentAddress)
        val addButton: Button = view.findViewById(R.id.addBtn);

        addButton.setOnClickListener{
            val index:String = studentIndex.text.toString();
            val name:String = studentName.text.toString();
            val lastname:String = studentLastName.text.toString();
            val number:String = studentNumber.text.toString();
            val address:String = studentAddress.text.toString();
            if(index.isNullOrEmpty()||name.isNullOrEmpty()||lastname.isNullOrEmpty()||number.isNullOrEmpty()||address.isNullOrEmpty()){
                    return@setOnClickListener
            }
            uploadData(index,name,lastname,number,address)
        }

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    private fun uploadData(
        index: String,
        name: String,
        lastname: String,
        number: String,
        address: String
    ) {
        val currentStudent = Student(index, name, lastname, number, address);
        studentReference.push().setValue(currentStudent)
            .addOnCompleteListener(OnCompleteListener<Void?> { task ->
                if (task.isSuccessful) {
                    Toast.makeText(activity, "New student successfully added", Toast.LENGTH_LONG)
                        .show()
                    studentIndex.text.clear()
                    studentName.text.clear()
                    studentLastName.text.clear()
                    studentNumber.text.clear()
                    studentAddress.text.clear()
                } else {
                    Toast.makeText(activity, "Error, student was not added", Toast.LENGTH_LONG)
                        .show()

                }
            }
            )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}