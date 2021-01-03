package ise308.soyalp.gokhan.g17_person

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import androidx.fragment.app.DialogFragment
import kotlin.Int as KotlinInt

class NewPersonDialog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity!!)
        val inflater=activity!!.layoutInflater    // assigning values
        val newPersonDialog=inflater.inflate(R.layout.dialog_new_person, null)

        val editTextName = newPersonDialog.findViewById(R.id.et_name) as EditText
        val editTextAge = newPersonDialog.findViewById(R.id.et_age) as EditText
        val editTextGender = newPersonDialog.findViewById(R.id.et_gender) as EditText
        val editTextDep = newPersonDialog.findViewById(R.id.et_dep) as EditText
        val editTextBplace = newPersonDialog.findViewById(R.id.et_bplace) as EditText
        val editTextGLevelB = newPersonDialog.findViewById(R.id.checkBox) as CheckBox
        val editTextGLevelM = newPersonDialog.findViewById(R.id.checkBox1) as CheckBox
        val editTextGLevelD = newPersonDialog.findViewById(R.id.checkBox2) as CheckBox


        val buttonOk=newPersonDialog?.findViewById<Button>(R.id.btn_ok)
        val buttonCancel=newPersonDialog?.findViewById<Button>(R.id.btn_cancel)

        builder.setView(newPersonDialog).setMessage("Add a new person")

        buttonCancel?.setOnClickListener {
            dismiss()  // click cancel button, leave this screen
        }

        buttonOk?.setOnClickListener{
            val newPerson =Person()

            newPerson.name=editTextName.text.toString()
            newPerson.age= editTextAge.text.toString()
            newPerson.gender=editTextGender.text.toString()
            newPerson.department=editTextDep.text.toString()
            newPerson.bornPlace=editTextBplace.text.toString()
            newPerson.educationDegB=editTextGLevelB.isChecked
            newPerson.educationDegM=editTextGLevelM.isChecked
            newPerson.educationDegD=editTextGLevelD.isChecked




            val callingActivity=activity as MainActivity
            callingActivity.createNewPerson(newPerson)  // calling create new film function it takes newFilm parameter that has type of Film class
            dismiss()
        }
        return builder.create()
    }

}