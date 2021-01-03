package ise308.yazanel.elifnur.g17_person

import android.app.Activity
import android.app.ProgressDialog.show
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var animFadeIn: Animation
    private lateinit var animFadeOut: Animation
    private lateinit var animFadeInOut: Animation

    private lateinit var animZoomIn: Animation
    private lateinit var animZoomOut: Animation

    private lateinit var animLeftRight: Animation
    private lateinit var animRightLeft: Animation
    private lateinit var animTopBottom: Animation

    private lateinit var animBounce: Animation
    private lateinit var animFlash: Animation

    private lateinit var animRotateLeft: Animation
    private lateinit var animRotateRight: Animation

    private var personList : ArrayList<Person>?=null
    private var jsonsSerializer : JSONSerializer?=null  // here are our variables
    private var showDividers: Boolean = false
    private var rvPersons: RecyclerView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        animFadeIn = AnimationUtils.loadAnimation(
                this, R.anim.fade_in)
        animFadeOut = AnimationUtils.loadAnimation(
                this, R.anim.fade_out)
        animFadeInOut = AnimationUtils.loadAnimation(
                this, R.anim.fade_in_out)

        animZoomIn = AnimationUtils.loadAnimation(
                this, R.anim.zoom_in)
        findViewById<Button>(R.id.zoom_in).setOnClickListener(this)
        animZoomOut = AnimationUtils.loadAnimation(
                this, R.anim.zoom_out)
        findViewById<Button>(R.id.zoom_out).setOnClickListener(this)

        animLeftRight = AnimationUtils.loadAnimation(
                this, R.anim.left_right)
        animRightLeft = AnimationUtils.loadAnimation(
                this, R.anim.right_left)
        animTopBottom = AnimationUtils.loadAnimation(
                this, R.anim.top_bot)

        animBounce = AnimationUtils.loadAnimation(
                this, R.anim.bounce)
        animFlash = AnimationUtils.loadAnimation(
                this, R.anim.flash)

        animRotateLeft = AnimationUtils.loadAnimation(
                this, R.anim.rotate_left)
        animRotateRight = AnimationUtils.loadAnimation(
                this, R.anim.rotate_right)

        

    val actionBar=supportActionBar
        actionBar!!.title=resources.getString(R.string.app_name)

        initializePersons()

        jsonsSerializer= JSONSerializer("Person", applicationContext)
        try {
            personList=jsonsSerializer!!.load()
        }catch (e: Exception){                              // it load film information from JSON file and write in filmList Array
            personList= ArrayList()
            Log.e("asd","Error loading films..")
        }

        rvPersons=findViewById<View>(R.id.rv_persons) as RecyclerView  // create and definition RecyclerView
        val fabNewPerson=findViewById<FloatingActionButton>(R.id.fab_new_person)   //definition and decleration of floating action button
        val personAdapter=PersonAdapter(personList!!, this)  // assignment from filmList to Adapter
        val layoutManager= LinearLayoutManager(applicationContext)
        rvPersons!!.layoutManager=layoutManager
        rvPersons!!.itemAnimator= DefaultItemAnimator()   //configurations for RecyclerView
        rvPersons!!.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        rvPersons!!.adapter=personAdapter


        fabNewPerson.setOnClickListener {
            val newPersonDialog= NewPersonDialog()
            newPersonDialog.show(supportFragmentManager, "165")           // overriding floating action button its create,
            // new dialog
        }


    }

    override fun onResume() {
        super.onResume()
        val prefs = getSharedPreferences("Person Note", Context.MODE_PRIVATE)
        showDividers = prefs.getBoolean("dividers", true)
        if (showDividers) {                         // this function using get shared preferences
            rvPersons!!.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        } else {
            if (rvPersons!!.itemDecorationCount > 0)
                rvPersons!!.removeItemDecorationAt(0)
        }

    }

    private fun initializePersons() {    // here this function using for adding notes manually
        personList=ArrayList<Person>()
        //filmList!!.add(Film("The Godfather", "Crime/Drama","9,2","English"))
        //filmList!!.add(Film("The Dark Knight",   "Action/Crime/Drama", "9,0",   "English"))
        //filmList!!.add(Film("12 Angry Men",  "Crime/Drama", "9,0", "English"))
        //filmList!!.add(Film("Interstellar",  "Adventure/Sci-Fi", "8,6",  "English"))
        //filmList!!.add(Film("Avengers: Endgame",  "Adventure", "8,4",  "English"))
        //filmList!!.add(Film( "DAĞ 2",  "Action/War", "8,8",  "Turkish"))
        //filmList!!.add(Film( "Ayla",  "Biography/Drama/History", "8,4",  "Turkish"))
        //filmList!!.add(Film( "Babam ve Oğlum",  "Family","8,3",  "Turkish"))
        //filmList!!.add(Film("Pardon", "Comedy", "8,2",  "Turkish"))
        //filmList!!.add(Film("G.O.R.A",  "Adventure/Comedy/Sci-Fi", "8,0",  "Turkish"))
        //filmList!!.add(Film("The Godfather", "Crime/Drama", "9,2", "English"))
        //filmList!!.add(Film(  "Schindler's List",   "Biography/History", "8,9",   "German"))


    }



    fun createNewPerson(newPerson: Person ){
        personList!!.add(newPerson)  // it add coming parameter to film List array
    }

    fun showNote(adapterPosition: Int) {
        val showPersonDialog=ShowPersonDialog()
        showPersonDialog.setPerson(personList!![adapterPosition])         // this function using for showing notes in the list
        showPersonDialog.show(supportFragmentManager,"174")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)        // this function override on create option menu, declaration for created type of menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id=item.itemId
        val b = when (id){          // overriding on option item selected function. Its taking item ids and creating intent type of
            R.id.settings -> {               // SettingActivity class.
                val intentToSettings = Intent(this, SettingsActivity::class.java)
                startActivity(intentToSettings)
                true        // starting SettingActivity class
            }
            else -> super.onOptionsItemSelected(item)
        }
        return b
    }

    private fun saveNotes(){
        try {
            jsonsSerializer!!.save(this.personList!!)
        }catch (e:Exception){
            Log.e("asf","Error loading persons..")  // This function saving Notes in the array list as a Json File
        }
    }

    override fun onPause() {
        super.onPause()         // overriding on pause function, its calling save notes function for saving json last states of the program
        saveNotes()
        Toast.makeText(this, "Notes saved to JSON File", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.zoom_in -> {
                rvPersons!!.startAnimation(animZoomIn)
            }
        }
        when (v?.id){
            R.id.zoom_out ->{
                rvPersons!!.startAnimation(animZoomOut)
            }
        }
    }

}