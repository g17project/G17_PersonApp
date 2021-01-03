package ise308.yazanel.elifnur.g17_person

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import java.lang.Exception

private const val TAG = "NotePagerActivity"
var personList: ArrayList<Person>? = null
private var jsonSerializer: JSONSerializer? = null

class NotePagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_pager)

        jsonSerializer= JSONSerializer("Person", applicationContext)
        try {
            personList=jsonSerializer!!.load()
        }catch (e: Exception){                              // it load film information from JSON file and write in filmList Array
            personList= ArrayList()
            Log.e(TAG,"Error loading films..")
        }

        var personFragmentList = java.util.ArrayList<Fragment>()
        for (person in personList!!){
            personFragmentList.add(ShowNoteFragment.newInstance(person))
        }

        val pageAdapter = NotePagerAdapter(supportFragmentManager, personFragmentList)
        findViewById<ViewPager>(R.id.pager_notes).adapter = pageAdapter

    }
    class NotePagerAdapter(supportFragmentManager: FragmentManager, private val personFragmentList: ArrayList<Fragment>) :
        FragmentPagerAdapter(supportFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int) = personFragmentList[position]

        override fun getCount() = personFragmentList.size

    }

}