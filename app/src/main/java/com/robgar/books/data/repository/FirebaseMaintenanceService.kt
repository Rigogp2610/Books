package com.robgar.books.data.repository

import com.google.firebase.database.FirebaseDatabase

object FirebaseMaintenanceService {

    private const val MAINTENANCE = "maintenance"

    fun getMaintenance() = FirebaseDatabase.getInstance()
        .getReference(MAINTENANCE).listen<Int>()
}