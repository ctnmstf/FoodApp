package com.cetinmustafa.foodapp.repo

import android.util.Log
import android.view.View
import androidx.navigation.Navigation
import com.cetinmustafa.foodapp.fragment.LoginFragmentDirections
import com.cetinmustafa.foodapp.fragment.RegisterFragmentDirections
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import androidx.annotation.NonNull
import com.cetinmustafa.foodapp.databinding.*
import com.cetinmustafa.foodapp.fragment.ProfileFragmentDirections

import com.google.android.gms.tasks.OnCompleteListener

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.EmailAuthProvider

import com.google.firebase.auth.AuthCredential


class AuthDaoRepository {
    private var auth: FirebaseAuth

    init {
        auth = FirebaseAuth.getInstance()
    }

    fun register(email: String, password: String, name: String, view: FragmentRegisterBinding) {
        view.progressBarRegister.visibility = View.VISIBLE
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    val user = auth.currentUser

                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(name).build()

                    user!!.updateProfile(profileUpdates)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Log.e("Register", "Success Username")
                            }
                        }
                    Log.e("Register", "Success")
                    view.progressBarRegister.visibility = View.GONE
                    val route = RegisterFragmentDirections.registerToHomeRoute()
                    Navigation.findNavController(view.root).navigate(route)
                } else {
                    Log.e("Register", "${task.exception}")
                    view.progressBarRegister.visibility = View.GONE
                    Snackbar.make(
                        view.root,
                        "Kayıt oluşturulken bir hata oluştu. Lütfen tekrar deneyin",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
    }

    fun login(email: String, password: String, view: FragmentLoginBinding) {
        view.progressBarLogin.visibility = View.VISIBLE
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    Log.e("Login", "Success")
                    view.progressBarLogin.visibility = View.GONE
                    val route = LoginFragmentDirections.loginToHomeRoute()
                    Navigation.findNavController(view.root).navigate(route)
                } else {
                    Log.e("Login", "${task.exception}")
                    view.progressBarLogin.visibility = View.GONE
                    Snackbar.make(view.root, "Email veya parola hatalı", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
    }

    fun changePassword(
        current_password: String,
        new_password: String,
        view: FragmentChangePasswordBinding
    ) {
        val user = auth.currentUser

        val credential = EmailAuthProvider
            .getCredential("${user!!.email}", current_password)

        user!!.reauthenticate(credential)
            .addOnCompleteListener {
                user!!.updatePassword(new_password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Snackbar.make(view.root, "Parola güncellendi", Snackbar.LENGTH_SHORT)
                                .show()
                        } else {
                            Log.e("Password", "${task.exception}")
                            Snackbar.make(view.root, "${task.exception}", Snackbar.LENGTH_SHORT)
                                .show()
                        }
                    }
            }
    }

    fun changeProfile(
        name: String,
        email: String,
        password: String,
        view: FragmentChangeProfileBinding
    ) {
        val user = auth.currentUser

        val credential = EmailAuthProvider
            .getCredential("${user!!.email}", password)

        user!!.reauthenticate(credential)
            .addOnCompleteListener {

                user!!.updateEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            user!!.reload()
                            Snackbar.make(view.root, "Bilgiler güncellendi", Snackbar.LENGTH_SHORT)
                                .show()
                        } else {
                            Snackbar.make(view.root, "${task.exception}", Snackbar.LENGTH_SHORT)
                                .show()
                        }
                    }

                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(name).build()

                user!!.updateProfile(profileUpdates)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            user!!.reload()
                        } else {
                            Snackbar.make(view.root, "${task.exception}", Snackbar.LENGTH_SHORT)
                                .show()
                        }
                    }

            }
    }

    fun logout(view: FragmentProfileBinding){
        FirebaseAuth.getInstance().signOut()
        val route = ProfileFragmentDirections.logOut()
        Navigation.findNavController(view.root).navigate(route)
    }
}