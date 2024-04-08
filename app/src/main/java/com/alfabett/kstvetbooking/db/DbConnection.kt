package com.alfabett.kstvetbooking.db

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.alfabett.kstvetbooking.LoginActivity
import com.alfabett.kstvetbooking.MainActivity
import com.alfabett.kstvetbooking.data.BookingDetails
import com.alfabett.kstvetbooking.data.PaymentData
import com.alfabett.kstvetbooking.data.RegUser
import com.alfabett.kstvetbooking.data.RoomDetails
import com.alfabett.kstvetbooking.data.StudentDetails
import com.alfabett.kstvetbooking.data.User
import com.alfabett.kstvetbooking.data.UserProfile
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
    var email:String? = null
    var phone: String? = null
    var amount_paid:String? = null
    var balance:String? = null
    var checkin:String? = null
    var checkout:String? = null
    var booked_room:String? = null
    var rooms:String? = null

    var av_room: MutableState<List<RoomDetails>> = mutableStateOf(emptyList<RoomDetails>())
    var user_detais: MutableState<StudentDetails?> = mutableStateOf(null)
    var booking_detais: MutableState<PaymentData?> = mutableStateOf(null)
    var profile_details: MutableState<UserProfile?> = mutableStateOf(null)

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
                if (task.isSuccessful) {
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
        val current_uid = auth.currentUser!!.uid
        f_db.child("user").child(current_uid).setValue(user)
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

    fun getEmptyRoom():String {
        val rooms: MutableList<RoomDetails> = mutableListOf<RoomDetails>()
        f_db.child("rooms")
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
//                    var rooms = mutableListOf<RoomDetails>()
                    for (room:DataSnapshot in snapshot.children){
                        val avaroom = room.getValue(RoomDetails::class.java)
                        if (avaroom?.bed_status == "not booked"){
                            rooms.add(avaroom)
                        }
                    }
                    av_room.value = rooms
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })

        return "B1R1B2"
    }

    fun book_room(amount_paid:Int, user_id:String, context: Context){
        val simp_date = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val current_date = simp_date.format(Date())

        val calender = Calendar.getInstance()
        calender.time = simp_date.parse(current_date) as Date
        calender.add(Calendar.MONTH, 1)
        val month_later = simp_date.format(calender.time)
        getEmptyRoom()
        val balance  = 4000 - amount_paid

        f_db.child("book_details").child(user_id).setValue(
            BookingDetails(
                user_id = user_id,
                room_id = av_room.value.firstOrNull()?.room_name,
                checkin_date = current_date,
                checkout_date = month_later,
                paid_amount = amount_paid,
                room_balance = balance
            )
        )
            .addOnCompleteListener {
                task ->
                if (task.isComplete){
                    Toast.makeText(context, "Successfully booked", Toast.LENGTH_LONG)
                        .show()
                    context.startActivity(Intent(context, MainActivity::class.java))
                }
            }
    }

    fun getUserDetails(userId:String): StudentDetails{

        f_db.child("user").child(userId).addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    username = snapshot.child("name").getValue(String::class.java)
                    adm = snapshot.child("adm").getValue(String::class.java)
                }
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            }
        )
        user_detais.value = StudentDetails(username, adm)
        return user_detais.value!!
    }

    fun getBookingDetails(user_id:String):PaymentData{
        f_db.child("book_details").child(user_id).addValueEventListener(
            object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    amount_paid = snapshot.child("paid_amount").getValue(Int::class.java).toString()
                    balance = snapshot.child("room_balance").getValue(Int::class.java).toString()
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            }
        )

        booking_detais.value = PaymentData(amount_paid, balance)
        return booking_detais.value!!
    }

    fun getUserProfile(userId:String): UserProfile? {

        f_db.child("user").child(userId).addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    username = snapshot.child("name").getValue(String::class.java)
                    adm = snapshot.child("adm").getValue(String::class.java)
                    email = snapshot.child("email").getValue(String::class.java)
                    phone = snapshot.child("phone").getValue(String::class.java)
                }
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            }
        )

        f_db.child("book_details").child(userId).addValueEventListener(
            object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    checkin = snapshot.child("checkin_date").getValue(String::class.java)
                    checkout = snapshot.child("checkout_date").getValue(String::class.java)
                    booked_room = snapshot.child("checkin_date").getValue(String::class.java)
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            }
        )
        profile_details.value = UserProfile(username, email, adm, phone, checkin, checkout, booked_room)
        return profile_details.value!!
    }

    fun userLogout(context:Context){
        auth.signOut()
        context.startActivity(
            Intent(context, LoginActivity::class.java)
        )
    }

}