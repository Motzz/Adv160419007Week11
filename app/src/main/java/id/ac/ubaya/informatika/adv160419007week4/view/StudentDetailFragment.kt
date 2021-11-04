package id.ac.ubaya.informatika.adv160419007week4.view

import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat.Token.fromBundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.Person.fromBundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.media.AudioAttributesCompat.fromBundle
import id.ac.ubaya.informatika.adv160419007week4.R
import id.ac.ubaya.informatika.adv160419007week4.model.Global
import id.ac.ubaya.informatika.adv160419007week4.util.loadImage
import id.ac.ubaya.informatika.adv160419007week4.view.StudentDetailFragmentArgs.Companion.fromBundle
import id.ac.ubaya.informatika.adv160419007week4.viewmodel.DetailViewModel
import id.ac.ubaya.informatika.adv160419007week4.viewmodel.ListViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.fragment_student_list.*
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    private  val studentData = StudentListAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments!=null)
        {
            Global.id=StudentDetailFragmentArgs.fromBundle(requireArguments()).StudentID
        }

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch()
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            txtIdDetail.setText(it.id)
            txtNameDetail.setText(it.name)
            txtBodDetail.setText(it.bod)
            txtPhoneDetail.setText(it.Phone)
            imageView2.loadImage(it.photoUrl.toString(),progressBar2)

            //week 7

            var student = it
            btnNotif.setOnClickListener {
                Observable.timer(5, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Log.d("Messages", "five seconds")
                        MainActivity.ShowNotification(student.name.toString(),
                            "A new notification created",
                            R.drawable.ic_baseline_person_24)
                    }
            }

        })
    }


}