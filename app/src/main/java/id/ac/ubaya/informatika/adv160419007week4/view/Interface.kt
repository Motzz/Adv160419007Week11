package id.ac.ubaya.informatika.adv160419007week4.view

import android.view.View
import id.ac.ubaya.informatika.adv160419007week4.model.Student

interface  ButtonDetailClickListener{
    fun onButtonDetailClick(v: View)
}

interface ButtonUpdateClickListener{
    fun onButtonUpdateClick(v: View)

}

interface ButtonNotifClickListener{
    fun onNotifClic(v:View)
}
