package com.example.mypokedexoficial.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokedexoficial.R
import com.example.mypokedexoficial.api.PokemonRepository
import com.example.mypokedexoficial.domain.Pokemon
import com.example.mypokedexoficial.domain.PokemonType
import com.example.mypokedexoficial.api.model.PokemonsApiResult
import com.example.mypokedexoficial.viewmodel.PokemonViewModel
import com.example.mypokedexoficial.viewmodel.PokemonViewModelFactory


class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView

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
    }



    private fun loadRecyclerView(pokemons : List<Pokemon?>) {

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PokemonAdapter(pokemons)

    }

}