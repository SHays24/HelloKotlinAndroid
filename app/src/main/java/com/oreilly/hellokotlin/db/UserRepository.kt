package com.oreilly.hellokotlin.db

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDAO) {
    val allUsers: Flow<List<User>> = userDao.getAllUsers()

    @WorkerThread
    suspend fun insertUser(name: String) {
        if (userDao.count(name) == 0) {
            userDao.insertUsers(User(name))
        }
    }

    suspend fun deleteUsersByName(name: String) =
        userDao.findByName(name).forEach { userDao.delete(it) }

    suspend fun deleteAllUsers() =
        userDao.deleteAll()
}