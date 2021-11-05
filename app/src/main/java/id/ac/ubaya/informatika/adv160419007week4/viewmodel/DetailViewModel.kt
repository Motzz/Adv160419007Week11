package id.ac.ubaya.informatika.adv160419007week4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.ac.ubaya.informatika.adv160419007week4.model.Global
import id.ac.ubaya.informatika.adv160419007week4.model.Student

class DetailViewModel(application: Application): AndroidViewModel(application) {

    val studentLD = MutableLiveData<Student>()

    private val TAG = "VolleyTag"//terserah variable nya
    private var queue: RequestQueue?=null

    fun fetch() {
        queue= Volley.newRequestQueue(getApplication())
        var url="http://adv.jitusolution.com/student.php?id=${Global.id}"
       // var url="http://adv.jitusolution.com/student.php"
        var stringRequest= StringRequest(Request.Method.GET,url,
                {response ->
                    val sType=object : TypeToken<Student>(){ }.type
                    val result= Gson().fromJson<Student>(response,Student::class.java)
                    studentLD.value=result

                    Log.d("berhasil",response.toString())

                },
                {
                    Log.d("showVolley",it.toString())
                })
        stringRequest.tag=TAG
        queue?.add(stringRequest)
    }

}