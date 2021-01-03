package ise308.soyalp.gokhan.g17_person

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PersonAdapter(private val personList: ArrayList<Person>, private val mainActivity: MainActivity ) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {
    inner class PersonViewHolder(personItem: View) :RecyclerView.ViewHolder(personItem), View.OnClickListener  {

        internal var mName=personItem.findViewById<View>(R.id.et_name) as TextView
        internal var mAge=personItem.findViewById<View>(R.id.et_age) as TextView
        internal var mGender=personItem.findViewById<View>(R.id.et_gender) as TextView
        internal var mDep=personItem.findViewById<View>(R.id.et_dep) as TextView
        internal var mBplace=personItem.findViewById<View>(R.id.et_bplace) as TextView
        internal var mGradeB=personItem.findViewById<View>(R.id.et_Degree) as TextView
        internal var mGradeM=personItem.findViewById<View>(R.id.et_Degree1) as TextView
        internal var mGradeD=personItem.findViewById<View>(R.id.et_Degree2) as TextView
        /*

         */
        init {
            personItem.isClickable=true
            personItem.setOnClickListener(this)       // making the array elements clickable.
        }
        override fun onClick(v: View?) {
            //mainActivity.showNote(adapterPosition)
            val intentToNotePager = Intent(v!!.context, NotePagerActivity::class.java)
            v.context.startActivity(intentToNotePager)
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val personItemInflater= LayoutInflater.from(parent.context)
        val personView=personItemInflater.inflate(R.layout.person_item,parent,false)
        return  PersonViewHolder(personView)      // this function create view holder. Data is loaded and screen printed.
    }

    override fun getItemCount()=personList.size

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val personToDisplay=personList[position]
        holder.mName.text = personToDisplay.name
        holder.mAge.text=personToDisplay.age
        holder.mGender.text=personToDisplay.gender
        holder.mDep.text=personToDisplay.department
        holder.mBplace.text=personToDisplay.bornPlace

        when {

            personToDisplay.educationDegB -> holder.mGradeB.text = mainActivity.resources.getString(R.string.cbB)
            personToDisplay.educationDegM -> holder.mGradeM.text = mainActivity.resources.getString(R.string.cbM)
            personToDisplay.educationDegD -> holder.mGradeD.text = mainActivity.resources.getString(R.string.cbD)
        }


    }
}