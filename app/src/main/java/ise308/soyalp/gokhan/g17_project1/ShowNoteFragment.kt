package ise308.soyalp.gokhan.g17_person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ShowNoteFragment() : Fragment() , View.OnClickListener{
    private lateinit var animFlash: Animation
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.note_frame, container, false)
        val tvName=view.findViewById(R.id.et_name) as TextView
        val tvAge=view.findViewById(R.id.et_age) as TextView
        val tvGender=view.findViewById(R.id.et_gender) as TextView
        val tvDep=view.findViewById(R.id.et_dep) as TextView
        val tvBplace=view.findViewById(R.id.et_bplace) as TextView

        animFlash = AnimationUtils.loadAnimation(
                context, R.anim.flash)
        view.findViewById(R.id.btn_delete) as Button

         val fragName= view.findViewById(R.id.et_name) as TextView


         fun onClick(v: View?) {
            when (v?.id){
                R.id.btn_delete -> {
                    fragName.startAnimation(animFlash)
                }
            }
        }

        tvName.text= arguments!!.getString("name")
        tvAge.text= arguments!!.getString("age")
        tvGender.text=arguments!!.getString("gender")
        tvDep.text= arguments!!.getString("department")
        tvBplace.text= arguments!!.getString("bplace")

        val tvShowDegB=view.findViewById(R.id.et_Deg) as TextView
        val tvShowDegM=view.findViewById(R.id.et_Deg1) as TextView
        val tvShowDegD=view.findViewById(R.id.et_Deg2) as TextView


        tvShowDegB.text= arguments!!.getString("BacDegree")
        tvShowDegM.text= arguments!!.getString("MasDegree")
        tvShowDegD.text= arguments!!.getString("PhdDegree")



        return view
    }



    companion object{
        fun newInstance(person: Person) : ShowNoteFragment{
            val fragment = ShowNoteFragment()
            val bundle = Bundle(1)
            bundle.putString("name", person.name)
            bundle.putString("age", person.age)
            bundle.putString("gender",person.gender)
            bundle.putString("department", person.department)
            bundle.putString("bplace", person.bornPlace)
            bundle.putString("BacDegree", person.educationDegB.toString())
            bundle.putString("MasDegree", person.educationDegM.toString())
            bundle.putString("PhdDegree", person.educationDegD.toString())
            fragment.arguments= bundle
            return fragment
        }
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.btn_delete -> {
                v.startAnimation(animFlash)
            }
        }
    }


}