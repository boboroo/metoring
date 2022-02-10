package com.mentoring.sample.data.api

import com.shiny.shopping.data.models.ListUIData
import io.reactivex.Single
import retrofit2.http.GET

interface MainNetworkApi {
    @GET("/ui/app/test/homework.json")
    fun getMainData(): Single<List<ListUIData>>
}