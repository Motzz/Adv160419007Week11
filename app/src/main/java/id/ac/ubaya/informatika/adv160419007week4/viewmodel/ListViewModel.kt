package id.ac.ubaya.informatika.adv160419007week4.viewmodel

import android.app.Application
import android.app.DownloadManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.ac.ubaya.informatika.adv160419007week4.model.Student
import java.util.*

class ListViewModel(application: Application):AndroidViewModel(application) {
    //untuk loading data
    val studentsLD = MutableLiveData<List<Student>>()//live data
    val loadingErrorLD = MutableLiveData<Boolean>()
    val loadingLD=MutableLiveData<Boolean>()

    private val TAG = "VolleyTag"//terserah variable nya
    private var queue:RequestQueue?=null

    fun refresh() {

        loadingErrorLD.value = false
        loadingLD.value = true

        queue=Volley.newRequestQueue(getApplication())
        var url="http://adv.jitusolution.com/student.php"
        var stringRequest=StringRequest(Request.Method.GET,url,
            //kalo berahsil
            {response ->
                val sType=object :TypeToken<List<Student>>(){ }.type
                val result=Gson().fromJson<List<Student>>(response,sType)
                studentsLD.value=result

                loadingLD.value=false
                Log.d("showVolley",response.toString())

            },
            {
                loadingErrorLD.value=true
                loadingLD.value=false
                Log.d("showVolley",it.toString())
            })
        stringRequest.tag=TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

}