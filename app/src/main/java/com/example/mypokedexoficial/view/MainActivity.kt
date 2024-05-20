package com.example.mypokedexoficial.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokedexoficial.R
import com.example.mypokedexoficial.api.PokemonRepository
import com.example.mypokedexoficial.domain.Pokemon
import com.example.mypokedexoficial.domain.PokemonType
import com.example.mypokedexoficial.api.model.PokemonsApiResult
import com.example.mypokedexoficial.databinding.ActivityLoginBinding
import com.example.mypokedexoficial.viewmodel.PokemonViewModel
import com.example.mypokedexoficial.viewmodel.PokemonViewModelFactory


class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    private lateinit var username: String
    private lateinit var password: String

    val viewModel by lazy {
        ViewModelProvider(this, PokemonViewModelFactory())
            .get(PokemonViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById<RecyclerView>(R.id.rvPokemons)

        viewModel.pokemons.observe(this, Observer {
            loadRecyclerView(it)
        })

        // Obter os dados do usuário das SharedPreferences
        val sharedPref = getSharedPreferences("user_credentials", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "")
        val password = sharedPref.getString("password", "")

//        username = intent.getStringExtra("username") ?: ""
//        password = intent.getStringExtra("password") ?: ""

        val profileButton = findViewById<Button>(R.id.profileButton)
        profileButton.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("username", username)
            intent.putExtra("password", password)
            startActivity(intent)
        }
    }



    private fun loadRecyclerView(pokemons : List<Pokemon?>) {

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PokemonAdapter(pokemons)

    }

}