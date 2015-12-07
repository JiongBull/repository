/*
 * -----------------------------------------------------------------
 * Copyright (C) 2015, by JiongBull, Shenzhen, All rights reserved.
 * -----------------------------------------------------------------
 *
 * File: ChapterFragment
 * Author: JiongBull
 * Create: 2015/12/3
 */
package com.jiongbull.tutorial.ui.view.book;

import com.jiongbull.tutorial.R;
import com.jiongbull.tutorial.bean.Topic;
import com.jiongbull.tutorial.constant.Extras;
import com.jiongbull.tutorial.ui.adapter.TopicAdapter;
import com.jiongbull.tutorial.ui.listener.OnItemClickListener;
import com.jiongbull.tutorial.ui.view.BaseFragment;
import com.jiongbull.tutorial.ui.widget.GridSpacingItemDecoration;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.Bind;

/**
 * Chapter fragment.
 */
public class ChapterFragment extends BaseFragment implements OnItemClickListener {

    @Bind(R.id.rylv_topics)
    RecyclerView mRylvTopics;

    private List<Topic> mTopics;
    private TopicAdapter mTopicAdapter;

    // TODO JiongBull: 2646c77e44204226a1f3ed62336dac080dba2ed4 46f2a417cb54cbb58099

    /**
     * 实例化方法.
     *
     * @param bundle 初始化参数
     * @return 实例
     */
    public static ChapterFragment newInstance(@Nullable Bundle bundle) {
        ChapterFragment fragment = new ChapterFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_chapter;
    }

    @Override
    protected void initVariables() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mTopics = bundle.getParcelableArrayList(Extras.TOPICS.getValue());
        }
        mTopicAdapter = new TopicAdapter(mParentActivity, mTopics);
    }

    @Override
    protected void initViews() {
        mRylvTopics.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mParentActivity, 2);
        mRylvTopics.setLayoutManager(layoutManager);
        mTopicAdapter.setOnItemClickListener(this);
        mRylvTopics.setAdapter(mTopicAdapter);
        mRylvTopics.addItemDecoration(new GridSpacingItemDecoration(2, 60, true));
    }

    @Override
    public void onItemClick(View v, int position) {
        Intent intent = new Intent();
        Topic topic = mTopics.get(position);
        intent.setComponent(new ComponentName(mParentActivity, topic.getActivityName()));
        intent.putExtra(Extras.NAME.getValue(), topic.getName());
        startActivity(intent);
    }
}