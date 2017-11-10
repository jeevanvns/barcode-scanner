package com.ansh.rmbarcodetest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Jeevan Gupta on 05-09-2017.
 * {@link RefundMeEditText}
 * Copyright (C) 2017 Refund.me
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


public class RefundMeEditText extends LinearLayout {

    private EditText editText;

    public RefundMeEditText(Context context) {
        super(context);
    }

    public RefundMeEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.etRefundMe);
        LayoutInflater.from(context).inflate(R.layout.row_edit_text_layout, this, true);

        //Set Input Type
        int inputType = a.getInt(R.styleable.etRefundMe_android_inputType, InputType.TYPE_CLASS_TEXT);
        //Set ImeOption
        int imeOption = a.getInt(R.styleable.etRefundMe_android_imeOptions, EditorInfo.IME_ACTION_NEXT);
        int maxTextLength = a.getInt(R.styleable.etRefundMe_android_maxLength, 0);
/*
        int minHeight = a.getDimensionPixelOffset(R.styleable.etRefundMe_android_minHeight, 0);
*/
        //Set Hint Text
        CharSequence hintText = a.getString(R.styleable.etRefundMe_android_hint);


        Drawable drawable = a.getDrawable(R.styleable.etRefundMe_android_src);


        editText = (EditText) findViewById(R.id.edit_text);
        ImageView ivIcon = (ImageView) findViewById(R.id.iv_icon);
        if (hintText != null) {
            editText.setHint(hintText.toString());
        }
        if (maxTextLength != 0) {
            setMaxLength(maxTextLength);
        }
    /*    if (minHeight != 0) {
            editText.setMinHeight(minHeight);
        }*/
        editText.setInputType(inputType);
        editText.setImeOptions(imeOption);
        if (ivIcon != null) {
            ivIcon.setImageDrawable(drawable);
        }
        a.recycle();
    }

    private void setMaxLength(int maxLength) {
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(maxLength);
        editText.setFilters(FilterArray);
    }


    public String getText() {
        return editText.getText().toString();
    }

    /**
     * set the Text
     *
     * @param value String Value That set on EditText
     */
    public void setText(String value) {
        editText.setText(value);
    }

}