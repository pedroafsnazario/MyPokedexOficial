package com.example.mypokedexoficial.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mypokedexoficial.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var currentUsername: String
    private lateinit var currentPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        // Obter o nome de usuário e senha do intent extra
        currentUsername = intent.getStringExtra("username") ?: ""
        currentPassword = intent.getStringExtra("password") ?: ""

        // Exibir o nome de usuário e senha na tela
        binding.usernameTextView.text = "Username: $currentUsername"
        binding.passwordTextView.text = "Password: $currentPassword"

        // Configurar o botão de salvar mudanças
        binding.saveChangesButton.setOnClickListener {
            val newUsername = binding.usernameEditText.text.toString()
            val newPassword = binding.passwordEditText.text.toString()
            updateUser(newUsername, newPassword)
        }

        // Configurar o botão de logout
        binding.logoutButton.setOnClickListener {
            logout()
        }

        // Configurar o botão de voltar para a MainActivity
        binding.backToMainButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("username", currentUsername)
            intent.putExtra("password", currentPassword)
            startActivity(intent)
            finish()
        }
    }

    private fun updateUser(newUsername: String, newPassword: String) {
        val finalUsername = if (newUsername.isNotEmpty()) newUsername else currentUsername
        val finalPassword = if (newPassword.isNotEmpty()) newPassword else currentPassword

        if (newUsername.isEmpty() && newPassword.isEmpty()) {
            Toast.makeText(this, "Please enter a new username or password", Toast.LENGTH_SHORT).show()
            return
        }

        val result = databaseHelper.updateUser(currentUsername, currentPassword, finalUsername, finalPassword)
        if (result) {
            Toast.makeText(this, "User updated successfully", Toast.LENGTH_SHORT).show()
            currentUsername = finalUsername
            currentPassword = finalPassword
            binding.usernameTextView.text = "Username: $currentUsername"
            binding.passwordTextView.text = "Password: $currentPassword"
            binding.usernameEditText.text.clear()
            binding.passwordEditText.text.clear()
        } else {
            Toast.makeText(this, "Failed to update user", Toast.LENGTH_SHORT).show()
        }
    }

    private fun logout() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }
}
