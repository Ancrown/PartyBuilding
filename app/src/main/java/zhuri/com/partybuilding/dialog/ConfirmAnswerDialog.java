package zhuri.com.partybuilding.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;

import zhuri.com.partybuilding.R;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/11
 * 描述:
 */

public class ConfirmAnswerDialog extends Dialog {
    public ConfirmAnswerDialog(@NonNull Context context) {
        super(context);
    }

    public ConfirmAnswerDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_confirm_answer);
    }
}
