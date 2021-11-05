package id.ac.ubaya.informatika.adv160419007week4.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.adv160419007week4.R
import id.ac.ubaya.informatika.adv160419007week4.databinding.FragmentStudentDetailBinding
import id.ac.ubaya.informatika.adv160419007week4.model.Global
import id.ac.ubaya.informatika.adv160419007week4.viewmodel.DetailViewModel

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_student_detail.*
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment(),ButtonUpdateClickListener,ButtonNotifClickListener {
    private lateinit var viewModel: DetailViewModel
    private  lateinit var dataBinding: FragmentStudentDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding= DataBindingUtil.inflate<FragmentStudentDetailBinding>(inflater,R.layout.fragment_student_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        if (arguments!=null)
        {
            Global.id=StudentDetailFragmentArgs.fromBundle(requireArguments()).StudentID
        }
        viewModel.fetch()
        dataBinding.listener=this
        dataBinding.notif=this
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            dataBinding.student=it
//            txtIdDetail.setText(it.id)
//            txtNameDetail.setText(it.name)
//            txtBodDetail.setText(it.bod)
//            txtPhoneDetail.setText(it.Phone)
//            imageView2.loadImage(it.photoUrl.toString(),progressBar2)

            //week 7

//            var student = it
//            btnNotif.setOnClickListener {
//                Observable.timer(5, TimeUnit.SECONDS)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe {
//                        Log.d("Messages", "five seconds")
//                        MainActivity.ShowNotification(student.name.toString(),
//                            "A new notification created",
//                            R.drawable.ic_baseline_person_24)
//                    }
//            }

        })
    }

    override fun onButtonUpdateClick(v: View) {
        val action = StudentDetailFragmentDirections.actionStudentList()
        Navigation.findNavController(v).navigate(action)
    }

    override fun onNotifClic(v: View) {
        Observable.timer(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d("Messages", "five seconds")
                    MainActivity.ShowNotification(v.tag.toString(),
                            "A new notification created",
                            R.drawable.ic_baseline_person_24)
                }
    }


}