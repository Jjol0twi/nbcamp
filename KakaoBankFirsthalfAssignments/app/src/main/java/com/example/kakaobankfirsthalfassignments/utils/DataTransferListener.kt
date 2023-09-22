package com.example.kakaobankfirsthalfassignments.utils

import com.example.kakaobankfirsthalfassignments.model.SearchResultModel

interface DataTransferListener {
    fun onDataTransfer(data: ArrayList<SearchResultModel>)
}