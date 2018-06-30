package zhuri.com.partybuilding.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.base.IOIBaseAdapter;
import zhuri.com.partybuilding.bean.AnswerItemBean;
import zhuri.com.partybuilding.util.AppUtils;
import zhuri.com.partybuilding.util.StringUtil;


/**
 * 创建人:Administrator
 * 创建时间:2018/5/14
 * 描述: 题的选项
 */
public class AnswerItemAdapter extends IOIBaseAdapter<AnswerItemBean> {


    public AnswerItemAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_answer_item, null);
            holder.img = (ImageView) convertView.findViewById(R.id.item_answer_img);
            holder.text = (TextView) convertView.findViewById(R.id.item_answer_text);
            holder.ll = (LinearLayout) convertView.findViewById(R.id.item_answer_rl);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        final AnswerItemBean bean = list.get(position);
        if (bean.getType() == 0) {
            holder.img.setImageDrawable(context.getResources().getDrawable(R.drawable.unchecked));
            holder.ll.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.xian_light_gray));
        } else if (bean.getType() == 1) {
            holder.img.setImageDrawable(context.getResources().getDrawable(R.drawable.select));
            holder.ll.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.xian_red));
        }
        holder.text.setText(StringUtil.getLetter()[position] + "." + bean.getText());
        //设置是否点击

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean.isOnClick()) {
                    if (onClickLL != null) {
                        onClickLL.onLL(position, bean.getId());
                    }
                }
            }
        });

        return convertView;
    }

    public class Holder {
        private ImageView img;
        private TextView text;
        private LinearLayout ll;
    }


    private OnClickLL onClickLL;


    public void setOnClickLL(OnClickLL onClickLL) {
        this.onClickLL = onClickLL;
    }

    public interface OnClickLL {
        void onLL(int position, String id);
    }
}
