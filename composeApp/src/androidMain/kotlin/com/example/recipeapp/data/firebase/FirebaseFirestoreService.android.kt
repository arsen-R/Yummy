package com.example.recipeapp.data.firebase

import android.util.Log
import com.example.recipeapp.domain.model.User
import com.example.recipeapp.domain.util.Constants
import com.google.firebase.firestore.FirebaseFirestore

//actual class FirebaseFirestoreService(
//    private val firestore: FirebaseFirestore
//) {
//    actual fun addUserToDatabase(user: User, onSuccess: () -> Unit, onFailure: () -> Unit) {
//        firestore.collection(Constants.USERS_COLLECTION_PATH)
//            .document(user.uid)
//            .set(user)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    onSuccess()
//                    Log.i(
//                        FirebaseFirestoreService::class.simpleName,
//                        "User added to Firestore database"
//                    )
//                }
//            }
//            .addOnFailureListener {
//                onFailure()
//            }
//    }
//}