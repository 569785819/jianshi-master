package com.zzj.jianshi.widget;

import android.content.Context;
import android.view.View;

import com.zzj.jianshi.utils.DisplayUtil;

import java.lang.ref.WeakReference;

import javax.inject.Singleton;

/**
 * Created by zhuzhejun on 2017/5/19.
 */
@Singleton
public class ActivityTransControl {
    private static final int DEFAULT_START_RADIUS = 10;
    private static WeakReference<View> sTransView;

    public static void clear() {
        if (sTransView != null) {
            sTransView.clear();
            sTransView = null;
        }
    }

    public static void setTransView(View transView) {
        sTransView = new WeakReference<>(transView);
    }

    public static ClipInfo getStartTransInfo(Context context) {
        ClipInfo clipInfo = new ClipInfo();
        clipInfo.radius = DEFAULT_START_RADIUS;
        if (sTransView != null && sTransView.get() != null) {
            int[] location = new int[2];
            sTransView.get().getLocationOnScreen(location);
            clipInfo.x = location[0] + (sTransView.get().getWidth() / 2);
            clipInfo.y = location[1] + (sTransView.get().getHeight() / 2);
            if (clipInfo.x != 0 && clipInfo.y != 0) {
                return clipInfo;
            }
        }
        //default
        clipInfo.x = DisplayUtil.getScreenWidth(context) / 2;
        clipInfo.y = DisplayUtil.getScreenHeight(context) / 2;
        return clipInfo;
    }
}
