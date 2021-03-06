/*
 * Copyright JiongBull 2016
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jiongbull.repository.ui.view;

import com.jiongbull.repository.util.ThemeUtils;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Base activity.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtils.initTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        initVariables();
        initViews();
    }

    /**
     * 获取布局资源Id.
     *
     * @return 布局资源Id.
     */
    protected abstract int getLayoutResId();

    /**
     * 初始化类变量.
     */
    protected abstract void initVariables();

    /**
     * 初始化视图.
     */
    protected abstract void initViews();

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

    @Nullable
    @Override
    public Intent getSupportParentActivityIntent() {
        Intent parentIntent = getIntent();
        Intent intent = new Intent();
        if (parentIntent != null) {
            intent.setClass(this, getIntent().getClass());
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        } else {
            intent.setClass(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        return intent;
    }
}