package id.ac.ubaya.informatika.adv160419007week4.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import id.ac.ubaya.informatika.adv160419007week4.R
import id.ac.ubaya.informatika.adv160419007week4.util.createNotificationChannel

class MainActivity : AppCompatActivity() {

    //week 7
    init {
        instance = this
    }

    companion object{
        private var instance:MainActivity ?=null
        fun ShowNotification(title:String,content:String,icon:Int){
            val channelId =
                    "${instance?.packageName}-${instance?.getString(R.string.app_name)}"

            val notificationBuilder = NotificationCompat.Builder(instance!!.applicationContext,channelId).apply {
                setSmallIcon(icon)
                setContentTitle(title)
                setContentText(content)
                setStyle(NotificationCompat.BigTextStyle())
                priority = NotificationCompat.PRIORITY_DEFAULT
                setAutoCancel(true)
            }
            val notificationManager = NotificationManagerCompat.from(instance!!.applicationContext)//di slide kedobelan
            notificationManager.notify(1001, notificationBuilder.build())

        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //week 7 manggi method
        createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT, false,getString(R.string.app_name), "App notification channel.")



//        Observable.just("a stream of data","hellow","world")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(//cara cepet
//                        {Log.d("Messages", "data: $it")},
//                        {Log.e("Messages", "error: ${it.message.toString()}")},
//                        {Log.d("Messages", "complete")}
//                )


//        val observable = Observable.just("a stream of data","hellow","world")
//        val observer = object : Observer<String> { //cara panjang
//            override fun onSubscribe(d: Disposable?) {
//                Log.d("Messages", "begin subscribe")
//            }
//            override fun onNext(t: String?) {//kalo ada data baru
//                Log.d("Messages", "data: $t")
//            }
//            override fun onError(e: Throwable?) {
//                Log.e("Messages", "error: ${e!!.message.toString()}")
//            }
//            override fun onComplete() {
//                Log.d("Messages", "complete")
//            }
//        }

//        observable
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer)
    }
}