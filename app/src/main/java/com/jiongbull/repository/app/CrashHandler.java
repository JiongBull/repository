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

package com.jiongbull.repository.app;

import com.jiongbull.jlog.JLog;

import android.content.Context;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * 崩溃异常猎人.
 */
public class CrashHandler implements UncaughtExceptionHandler {

    private Context mContext;

    public CrashHandler(final Context context) {
        mContext = context;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        JLog.e(ex, "崩溃, 严重错误");
    }
}