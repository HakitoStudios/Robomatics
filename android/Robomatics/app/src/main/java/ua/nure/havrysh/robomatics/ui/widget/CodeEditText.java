package ua.nure.havrysh.robomatics.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;

public class CodeEditText extends AppCompatEditText {
    public CodeEditText(Context context) {
        super(context);
        init();
    }

    public CodeEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CodeEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // TODO: 18.05.17 Uncomment
       /* addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = "int";
                int index = s.toString().indexOf(str);
                while (index >= 0) {
                    s.setSpan(
                            new ForegroundColorSpan(Color.CYAN),
                            index,
                            index + str.length(),
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    index = s.toString().indexOf(str, index + 1);
                }
            }
        });*/
    }
}
