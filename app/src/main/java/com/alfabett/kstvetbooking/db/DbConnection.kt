package com.alfabett.kstvetbooking.db

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.alfabett.kstvetbooking.MainActivity
import com.alfabett.kstvetbooking.data.BookingDetails
import com.alfabett.kstvetbooking.data.RegUser
import com.alfabett.kstvetbooking.data.RoomDetails
import com.alfabett.kstvetbooking.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class DbConnect: ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val f_db = FirebaseDatabase.getInstance().reference
    var username:String? = null
    var adm:String? = null
    fun loginUser(reg_user:RegUser, context: Context) {
        auth.signInWithEmailAndPassword(reg_user.email, reg_user.password)
            .addOnCompleteListener { task ->
                if (task.isComplete) {
                    Toast.makeText(context, "Login Successful", Toast.LENGTH_LONG).show()
                    context.startActivity(Intent(context, MainActivity::class.java))
                } else {
                    Toast.makeText(
                        context,
                        "Invalid Email or Password\n Please try again",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    fun regUser(reg_user:RegUser, user: User, context: Context) {
        auth.createUserWithEmailAndPassword(reg_user.email, reg_user.password)
            .addOnCompleteListener {
                task ->
                if (task.isComplete) {
                    Toast.makeText(context, "User Registered Successfully", Toast.LENGTH_LONG)
                        .show()
                    addUser(user, context)
                    context.startActivity(Intent(context, MainActivity::class.java))
                } else {
                    Toast.makeText(
                        context,
                        "Registration Failed\n Please try again",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    fun addUser(user: User, context: Context){
        f_db.child("user").push().setValue(user)
            .addOnCompleteListener {
                task ->
                if (task.isSuccessful){
                    Toast.makeText(context, "${user.name} added successfully.", Toast.LENGTH_LONG)
                        .show()
                } else {
                    Toast.makeText(context, "${user.name} failed to add", Toast.LENGTH_LONG)
                        .show()
                }
            }
    }

    fun getEmptyRoom() :String{
        val rooms = mutableListOf<RoomDetails>()
        lateinit var av_room:String
        lateinit var room_id:String
        f_db.child("rooms")
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (room in snapshot.children){
                        if (room.child("bed_status").value!!.equals(false)){
                            av_room = room.child("room_name").value.toString()
                            room_id = room.key.toString()
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {

                }
            })
        return av_room
    }

    fun book_room(amount_paid:Int, context: Context){
        val simp_date = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val current_date = simp_date.format(Date())

        val calender = Calendar.getInstance()
        calender.time = simp_date.parse(current_date) as Date
        calender.add(Calendar.MONTH, 1)
        val month_later = simp_date.format(calender.time)

        val available_room = getEmptyRoom()
        val user = auth.currentUser!!.uid.toString()

        val balance  = 4000 - amount_paid

        f_db.child("book_details").push().setValue(
            BookingDetails(
                user_id = user,
                room_id = available_room,
                checkin_date = current_date,
                checkout_date = month_later,
                paid_amount = amount_paid,
                room_balance = balance
            )
        )
            .addOnCompleteListener {
                task ->
                if (task.isComplete){
                    Toast.makeText(context, "$available_room Successfully booked", Toast.LENGTH_LONG)
                        .show()
                    context.startActivity(Intent(context, MainActivity::class.java))
                }
            }
    }

    fun getUserDetails(){
        val current_user = auth.currentUser!!.uid.toString()
        lateinit var user:String
        f_db.child("user").addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (data_snapshot in snapshot.children){
                        user = data_snapshot.key.toString()
                        if (current_user.equals(user)){
                            username = data_snapshot.child("name").value.toString()
                            adm = data_snapshot.child("adm").value.toString()
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            }
        )
    }
}