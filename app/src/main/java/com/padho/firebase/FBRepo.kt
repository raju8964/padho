package com.padho.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.padho.data.Common
import com.padho.ui.components.paperView.data.PaperModel
import com.padho.utils.Constants
import retrofit2.Response
import javax.inject.Inject


class FBRepo @Inject constructor() {
    var fireStore = FirebaseFirestore.getInstance()
    var firebaseDatabase = FirebaseDatabase.getInstance().reference
    var auth = FirebaseAuth.getInstance()


    ///// signin
    fun signInWithEmailAndPassword(callBack: (String) -> Unit): Boolean {
        return try {
            auth.signInWithEmailAndPassword(Common.demoEmail, Common.demoPassword)
                .addOnCompleteListener {
                    callBack.invoke(Common.loginSuccess)
                }.addOnFailureListener {
                    it.localizedMessage?.let { it1 -> callBack.invoke(it1.toString()) }
                }
            true
        } catch (e: Exception) {
            e.localizedMessage?.let { it1 -> callBack.invoke(it1.toString()) }
            false
        }
    }

    /// add all model paper
     fun getModelPaper( response: (data:Any,isSuccess:Boolean) -> Unit) {
        var list=ArrayList<String>()
        firebaseDatabase.child(Constants.BaseUrl)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (snapshot in dataSnapshot.children) {
                        list.add(snapshot.key.toString())
                    }
                    response.invoke(list,true)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    response.invoke(list,false)
                }
            })
    }

    /// get paper
    fun getPaper(path:String, response: (data:Any,isSuccess:Boolean) -> Unit) {
        var list=ArrayList<PaperModel>()
        firebaseDatabase.child(Constants.BaseUrl).child(path)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (snapshot in dataSnapshot.children) {
                        var data=PaperModel()
                        data.title=snapshot.child("Title").value.toString()
                        data.imageUrl=snapshot.child("Paper_link").value.toString()
                        list.add(data)
                    }
                    response.invoke(list,true)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    response.invoke(list,false)
                }
            })
    }


}