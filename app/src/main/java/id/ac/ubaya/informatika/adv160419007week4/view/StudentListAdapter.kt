package id.ac.ubaya.informatika.adv160419007week4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.adv160419007week4.R
import id.ac.ubaya.informatika.adv160419007week4.model.Student
import id.ac.ubaya.informatika.adv160419007week4.util.loadImage
import kotlinx.android.synthetic.main.student_list_item.view.*

class StudentListAdapter (val studentList:ArrayList<Student>) :RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(){

    class StudentViewHolder(val view: View):RecyclerView.ViewHolder(view)

    //method untuk refresh data supaya ke update
    fun updateStudentList(newStudentList:List<Student>)
    {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.student_list_item,parent,false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        with(holder.view)
        {
            txtID.text=studentList[position].id
            txtNameDetail.text=studentList[position].name
            imageView.loadImage(studentList[position].photoUrl.toString(),holder.view.progressBar)

            //ke detail
            btnDetail.setOnClickListener {
                val action = StudentListFragmentDirections.actionStudentDetail(studentList[position].id.toString())//kirim data id
                Navigation.findNavController(it).navigate(action)
            }
        }

    }

    override fun getItemCount(): Int {
        return studentList.size
    }
}