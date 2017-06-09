package com.zzj.jianshi.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.TextView;

import com.wingjay.jianshi.R;

/**
 * Created by zhuzhejun on 2017/5/22.
 */

public class VerticalTitleTextView extends AppCompatTextView {
    public VerticalTitleTextView(Context context) {
        super(context);
    }

    public VerticalTitleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VerticalTitleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (TextUtils.isEmpty(text)) {
            super.setText(text, type);
            return;
        }
        text = changedVerticalStr(text);
        String str = text.toString();
        if (str.contains(" ")) {
            SpannableStringBuilder style = new SpannableStringBuilder(str);
            style.setSpan(new AbsoluteSizeSpan((int) (getTextSize() + 5)), 0, str.indexOf(" "), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            style.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.bright_red)), str.indexOf(" "), str.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            super.setText(style, TextView.BufferType.SPANNABLE);
            return;
        }
        super.setText(text, type);
    }

    private CharSequence changedVerticalStr(CharSequence text) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = text.length();
        for (int i = 0; i < length; i++) {
            CharSequence sequence = text.toString().subSequence(i, i + 1);
            stringBuffer.append(sequence);
            if (i < length - 1) {
                stringBuffer.append("\n");
            }
        }
        return stringBuffer;
    }
}
