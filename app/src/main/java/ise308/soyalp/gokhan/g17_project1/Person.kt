package ise308.soyalp.gokhan.g17_person

import org.json.JSONException
import org.json.JSONObject
import java.lang.Boolean.FALSE

private val JSON_NAME="name"
private val JSON_AGE="age"
private val JSON_GENDER="gender"// variables for JSON File
private val JSON_DEP="department"
private val JSON_BPLACE="born place"
private val JSON_EDU_B="education lvl B"
private val JSON_EDU_M="education lvl M"
private val JSON_EDU_D="education lvl D"

class Person {
    var name: String? = null
    var age: String? = null
    var gender: String? = null
    var department: String? = null
    var bornPlace: String? = null
    var educationDegB: Boolean = false
    var educationDegM: Boolean = false
    var educationDegD: Boolean = false

    @Throws(JSONException::class)
    constructor(jsonObject: JSONObject){
        name=jsonObject.getString(JSON_NAME)
        age=jsonObject.getString(JSON_AGE)
        gender=jsonObject.getString(JSON_GENDER)
        department=jsonObject.getString(JSON_DEP)
        bornPlace=jsonObject.getString(JSON_BPLACE)
        educationDegB=jsonObject.getBoolean(JSON_EDU_B)
        educationDegM=jsonObject.getBoolean(JSON_EDU_M)
        educationDegD=jsonObject.getBoolean(JSON_EDU_D)

    }

    constructor()           // here is primary constructor

    @Throws(JSONException::class)
    fun  convertToJSON(): JSONObject{
        val jsonObject=JSONObject()
        jsonObject.put(JSON_NAME,name)
        jsonObject.put(JSON_AGE,age)
        jsonObject.put(JSON_GENDER,gender)
        jsonObject.put(JSON_DEP,department)
        jsonObject.put(JSON_BPLACE,bornPlace)
        jsonObject.put(JSON_EDU_B,educationDegB)
        jsonObject.put(JSON_EDU_M, educationDegM)
        jsonObject.put(JSON_EDU_D, educationDegD)
        return jsonObject
    }

}

