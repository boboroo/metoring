package com.mentoring.sample.data.api

import com.mentoring.sample.util.dummy.DummyContents
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface MainNetworkApi {
    @GET("/ui/app/test/homework.json")
    fun getMainData(): Single<List<DummyContents>>
}