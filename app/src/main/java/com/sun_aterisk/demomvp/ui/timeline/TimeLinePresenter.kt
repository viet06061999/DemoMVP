package com.sun_aterisk.demomvp.ui.timeline

import com.sun_aterisk.demomvp.data.source.repository.post.PostRepository
import com.sun_aterisk.demomvp.ui.newsfeed.NewsFeedContract

class TimeLinePresenter(val postRepository: PostRepository, val timeLineView: TimeLineContract.View) :
    TimeLineContract.Presenter {
    init {
        timeLineView.presenter = this
    }

    override fun start() {
      timeLineView.setInfor(postRepository.getCurrentUser())
    }
}
