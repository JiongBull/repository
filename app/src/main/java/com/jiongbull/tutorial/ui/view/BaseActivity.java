/*
 * -----------------------------------------------------------------
 * Copyright (C) 2015, by SF-Express, Shenzhen, All rights reserved.
 * -----------------------------------------------------------------
 *
 * File: BaseActivity
 * Author: JiongBull
 * Create: 2015/9/22
 */
package com.jiongbull.tutorial.ui.view;

import com.sf.module.edms.helper.R;
import com.sf.module.edms.helper.uitl.ThemeUtils;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Activity基类.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initTheme();
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        initVariables();
        initToolBar();
        initViews();
    }

    /**
     * 获取布局资源Id.
     *
     * @return 布局资源Id.
     */
    protected abstract int getLayoutResId();

    /**
     * 初始化操作栏.
     */
    protected abstract void initToolBar();

    /**
     * 初始化类变量.
     */
    protected abstract void initVariables();

    /**
     * 初始化视图.
     */
    protected abstract void initViews();

    protected void initToolBar(@NonNull String title) {
        mToolbar.setTitle(title);
        setSupportActionBar(mToolbar);
    }

    /**
     * 淡化SystemBar.
     */
    public void dimSystemBar() {
        int uiOptions = View.SYSTEM_UI_FLAG_LOW_PROFILE;
        manageSystemBar(uiOptions);
    }

    /**
     * 显示SystemBar.
     */
    public void revealSystemBar() {
        int uiOptions = 0;
        manageSystemBar(uiOptions);
    }

    /**
     * 隐藏StatusBar.
     */
    public void hideStatusBar() {
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        manageSystemBar(uiOptions);
    }

    /**
     * 管理系统栏.
     *
     * @param uiOptions 系统栏的状态
     */
    private void manageSystemBar(int uiOptions) {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(uiOptions);
    }

    /**
     * 设置主题.
     */
    private void initTheme() {
        ThemeUtils.Theme theme = ThemeUtils.getCurTheme();
        ThemeUtils.setTheme(this, theme);
    }

    @Nullable
    @Override
    public Intent getSupportParentActivityIntent() {
        Intent intent = new Intent();
        intent.setClass(this, getIntent().getClass());
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return intent;
    }
}