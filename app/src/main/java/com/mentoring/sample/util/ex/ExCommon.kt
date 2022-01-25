package com.mentoring.sample.util.ex

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.mentoring.sample.MentoringApplication
import java.io.BufferedReader
import java.io.InputStreamReader

inline fun <reified T> loadRawResource(res: Int): T =
    GsonBuilder().create().fromJson<T>(
        BufferedReader(InputStreamReader(MentoringApplication.context.resources.openRawResource(res))),
        object : TypeToken<T>() {}.type)