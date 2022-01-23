package com.mentoring.sample.ui.model

import com.mentoring.sample.util.dummy.DummyContents
import com.mentoring.sample.util.dummy.DummyImageContents

sealed class UIModel {

    data class DummyUIModel(
        val data: DummyContents,
        val viewtype: Int
    ) : UIModel()

    data class ImageUIModel(
        val data : DummyImageContents,
        val viewtype: Int
    ) : UIModel()

}