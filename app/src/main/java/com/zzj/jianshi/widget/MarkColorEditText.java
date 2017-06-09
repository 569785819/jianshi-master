package com.zzj.jianshi.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatEditText;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.MotionEvent;

import timber.log.Timber;

/**
 * Created by zhuzhejun on 2017/5/22.
 */

public class MarkColorEditText extends AppCompatEditText {
    public MarkColorEditText(Context context) {
        super(context);
    }

    public MarkColorEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarkColorEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getTouchedText((int) event.getRawX(), (int) event.getRawY());
                break;
        }
        return super.onTouchEvent(event);
    }

    private int lineHeight;
    private int[] location = new int[2];

    private int getTouchedText(int x, int y) {
        Layout layout = getLayout();
        if (lineHeight <= 0) {
            Rect bound = new Rect();
            layout.getLineBounds(0, bound);
            lineHeight = bound.height();
            getLocationOnScreen(location);
        }
        Timber.e("lineHeight : " + lineHeight);

        int line = 0;
        for (int i = 0; i < layout.getLineCount(); i++) {
            if (y >= layout.getLineTop(i) + location[1] && y <= layout.getLineTop(i) + location[1] + lineHeight) {
                line = i;
                break;
            }
        }
        Timber.e("touch line : " + line);

        int lineStart = layout.getLineStart(line);
        int lineEnd = line < layout.getLineCount() - 1 ? layout.getLineStart(line + 1) : layout.getLineEnd(line);
        int position = 0;
        for (int i = lineStart; i < lineEnd; i++) {
            float lx = layout.getPrimaryHorizontal(i);
            float rx = layout.getSecondaryHorizontal(i);
            if (x >= lx + location[0] && x <= rx + location[0]) {
                position = i;
                break;
            }
        }
        Timber.e("touch position : " + position);

        Timber.e("touch char : " + getText().toString().charAt(position));

        return position;
    }


}
