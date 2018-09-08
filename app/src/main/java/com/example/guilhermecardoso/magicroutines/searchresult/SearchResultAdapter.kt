package com.example.guilhermecardoso.magicroutines.searchresult

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.guilhermecardoso.magicroutines.R
import com.example.guilhermecardoso.magicroutines.service.Card

class SearchResultAdapter(val context: Context, val items: List<Card>): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_cell, p0, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cardName.text = items[position].name
        holder.manaCost.text = items[position].manaCost
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val cardName = view.findViewById<TextView>(R.id.card_cell_name)
    val manaCost = view.findViewById<TextView>(R.id.card_cell_mana_cost)
}