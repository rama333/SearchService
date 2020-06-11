package com.labs.robots.find.view;

import com.labs.robots.find.model.Feed;

import java.util.List;

public interface FeedView {
    void onFeedSuccess(List<Feed> userModel);
    void onFeedFailed(String error);
}
