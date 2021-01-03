package ise308.soyalp.gokhan.g17_person

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ShowPersonDialog:DialogFragment() {

    private lateinit var person: Person

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder= AlertDialog.Builder(this.activity!!)
        val inflater=activity!!.layoutInflater       // assigning values
        val dialogLayout=inflater.inflate(R.layout.dialog_show_person, null)

        val textViewShowName=dialogLayout.findViewById(R.id.et_name) as TextView
        val textViewShowAge=dialogLayout.findViewById(R.id.et_age) as TextView
        val textViewShowGender=dialogLayout.findViewById(R.id.et_gender) as TextView
        val textViewShowDep=dialogLayout.findViewById(R.id.et_dep) as TextView
        val textViewShowBplace=dialogLayout.findViewById(R.id.et_bplace) as TextView

        textViewShowName.text= person.name
        textViewShowAge.text=person.age
        textViewShowGender.text=person.gender
        textViewShowDep.text=person.department
        textViewShowBplace.text=person.bornPlace

        val textViewShowDegB=dialogLayout.findViewById(R.id.et_Deg) as TextView
        val textViewShowDegM=dialogLayout.findViewById(R.id.et_Deg1) as TextView
        val textViewShowDegD=dialogLayout.findViewById(R.id.et_Deg2) as TextView

        if (!person.educationDegB){
            textViewShowDegB.visibility=View.GONE
        }
        if (!person.educationDegM){
            textViewShowDegM.visibility=View.GONE
        }
        if (!person.educationDegD){
            textViewShowDegD.visibility=View.GONE
        }

        val btn_Done=dialogLayout.findViewById(R.id.btn_done) as Button

        btn_Done.setOnClickListener{
            dismiss()
        }                                   // it send dialog layout , showing for saved data's
        builder.setView(dialogLayout)
        return builder.create()



    }
    fun setPerson(personSelected: Person){
        this.person=personSelected
    }

}