package com.example.mypokedexoficial.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mypokedexoficial.R
import com.example.mypokedexoficial.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obter o nome de usuário e senha do intent extra
        val username = intent.getStringExtra("username")
        val password = intent.getStringExtra("password")

        // Exibir o nome de usuário e senha na tela
        binding.usernameTextView.text = "Username: $username"
        binding.passwordTextView.text = "Password: $password"

        // Configurar o botão de logout
        binding.logoutButton.setOnClickListener {
            logout()
        }
        val backToMainButton = findViewById<Button>(R.id.backToMainButton)
        backToMainButton.setOnClickListener {
            // Redirecionar para a MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Finalizar a ProfileActivity após voltar para a MainActivity
        }
    }

    private fun logout() {
        // Implementar lógica para limpar qualquer estado de autenticação
        // e redirecionar para a tela de login
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }
}
