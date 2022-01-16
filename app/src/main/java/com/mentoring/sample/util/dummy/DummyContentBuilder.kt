package com.mentoring.sample.util.dummy

import com.mentoring.sample.R
import kotlin.random.Random


class DummyContentBuilder {

    companion object {
        const val ICON = R.drawable.ic_launcher_background
    }

    fun makeDummyContents(
        max: Int = 5,
        onClick: ((id: String) -> Unit) = {}
    ): List<DummyContents> {
        val random = Random(System.currentTimeMillis())
        return (1..max).map { index ->
            makeDummyContent(
                index.toString(),
                random.nextInt(100, 1000),
                onClick
            )
        }
    }

    private fun makeDummyContent(
        id: String,
        price: Int,
        onClick: ((id: String) -> Unit)) : DummyContents {
        return DummyContents(
            id,
            ICON,
            price,
            onClick
        )
    }
}