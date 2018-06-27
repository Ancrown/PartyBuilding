package zhuri.com.partybuilding.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.widget.TextView;

import zhuri.com.partybuilding.R;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/25
 * 描述:
 */

public class StudyRuleDialog extends Dialog {
    private String text;
    private TextView textView;

    public StudyRuleDialog(@NonNull Context context) {
        super(context);
    }

    public StudyRuleDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_study_rule);
        textView = findViewById(R.id.dialog_text);
        textView.setText(text);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
