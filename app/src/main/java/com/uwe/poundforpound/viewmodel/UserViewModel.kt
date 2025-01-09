package com.uwe.poundforpound.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.uwe.poundforpound.data.AppDatabase
import com.uwe.poundforpound.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val userDao = AppDatabase.getDatabase(application).userDao()

    /**
     * Registers a new user by inserting it into the database.
     * @param user The user to register.
     * @param onResult Callback to return whether the operation was successful.
     */
    fun registerUser(user: User, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val userId = withContext(Dispatchers.IO) {
                userDao.insertUser(user)
            }
            onResult(userId > 0)
        }
    }

    /**
     * Logs in a user by checking their email and password in the database.
     * @param email The user's email.
     * @param password The user's password.
     * @param onResult Callback to return the user object if found, or null if not.
     */
    fun loginUser(email: String, password: String, onResult: (User?) -> Unit) {
        viewModelScope.launch {
            val user = withContext(Dispatchers.IO) {
                userDao.loginUser(email, password)
            }
            onResult(user)
        }
    }

    /**
     * Updates user details, such as weight, height, age, or gender.
     * @param user The user with updated details.
     * @param onResult Callback to confirm the update operation was successful.
     */
    fun updateUser(user: User, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                userDao.updateUser(user)
            }
            onResult(true)
        }
    }

    fun getUserById(userId: Int, onResult: (User?) -> Unit) {
        viewModelScope.launch {
            val user = withContext(Dispatchers.IO) {
                userDao.getUserById(userId)
            }
            onResult(user)
        }
    }
}

