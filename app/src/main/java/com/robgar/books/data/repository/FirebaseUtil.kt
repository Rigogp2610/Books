package com.robgar.books.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.lang.Exception

@ExperimentalCoroutinesApi
inline fun <reified T> DatabaseReference.listen(): Flow<T?>? =
    callbackFlow {
        val valueListener = object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                close()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                try {
                    val value = dataSnapshot.getValue(T::class.java)
                    offer(value)
                } catch (e: Exception) {
                    if (!isClosedForSend) offer(0)
                }
            }
        }
        addValueEventListener(valueListener)
        awaitClose { removeEventListener(valueListener) }
    }
