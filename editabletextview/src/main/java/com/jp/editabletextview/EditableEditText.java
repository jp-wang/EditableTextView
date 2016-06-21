package com.jp.editabletextview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author jpwang
 * @since 06/20/2016
 */
class EditableEditText extends EditText {

    private OnEditTextListener listener;

    public interface OnEditTextListener {
        void onEditTextKeyboardDismissed();

        void onEditTextKeyboardDone();
    }

    public EditableEditText(Context context) {
        super(context);

        init();
    }

    public EditableEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public EditableEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    if (listener != null)
                        listener.onEditTextKeyboardDone();

                    return true;
                }
                return false;
            }
        });
    }

    public void setOnKeyboardDismissedListener(OnEditTextListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

            if (listener != null)
                listener.onEditTextKeyboardDismissed();

            return false;
        }

        return super.dispatchKeyEvent(event);
    }

}