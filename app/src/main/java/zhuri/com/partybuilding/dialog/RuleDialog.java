package zhuri.com.partybuilding.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import zhuri.com.partybuilding.R;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/25
 * 描述:
 */

public class RuleDialog extends Dialog {


    ImageView ruleCha;
    TextView ruleTitle;
    TextView ruleText;

    private String title;
    private String text;

    public RuleDialog(@NonNull Context context) {
        super(context);
    }

    public RuleDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_rule);
        ruleCha = findViewById(R.id.rule_cha);
        ruleTitle = findViewById(R.id.rule_title);
        ruleText = findViewById(R.id.rule_text);
        ruleTitle.setText(title);
        ruleText.setText(text);
        //使textView中的文本可以滑动
        ruleText.setMovementMethod(ScrollingMovementMethod.getInstance());

        ruleCha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();

            }
        });
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getText() {

        return text;
    }

    public void setText(String text) {

        this.text = text;
    }

}
