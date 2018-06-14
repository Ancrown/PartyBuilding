package zhuri.com.partybuilding.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 创建人:Administrator
 * 创建时间:2018/5/14
 * 描述:
 */
public class AnswerBean implements Serializable {
    //题id
    private String id;
    //题目
    private String title;
    //单选/双选
    private String singlePair;
    //选项
    private List<AnswerItemBean> optionList;
    //分析
    private String analysis;
    //正确选项
    private String correctOptions;
    //我的选项
    private String myOptions = "-1";
    //标记 是否做错 true做正确 false 做错
    private boolean errorOptions;

    private String score;

    public String getMyOptions() {
        return myOptions;
    }

    public void setMyOptions(String myOptions) {
        this.myOptions = myOptions;
    }

    public boolean isErrorOptions() {
        return errorOptions;
    }

    public void setErrorOptions(boolean errorOptions) {
        this.errorOptions = errorOptions;
    }

    public AnswerBean(String id, String title, String singlePair, List<AnswerItemBean> optionList, String analysis, String correctOptions, String score) {
        this.id = id;
        this.title = title;
        this.singlePair = singlePair;
        this.optionList = optionList;
        this.analysis = analysis;
        this.correctOptions = correctOptions;
        this.score = score;
    }

    public AnswerBean(String id, String title, String singlePair, List<AnswerItemBean> optionList, String analysis, String correctOptions, String myOptions, boolean errorOptions, String score) {
        this.id = id;
        this.title = title;
        this.singlePair = singlePair;
        this.optionList = optionList;
        this.analysis = analysis;
        this.correctOptions = correctOptions;
        this.myOptions = myOptions;
        this.errorOptions = errorOptions;
        this.score = score;
    }


    public AnswerBean(String score) {
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSinglePair() {
        return singlePair;
    }

    public void setSinglePair(String singlePair) {
        this.singlePair = singlePair;
    }

    public List<AnswerItemBean> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<AnswerItemBean> optionList) {
        this.optionList = optionList;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        analysis = analysis;
    }

    public String getCorrectOptions() {
        return correctOptions;
    }

    public void setCorrectOptions(String correctOptions) {
        this.correctOptions = correctOptions;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "AnswerBean{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", singlePair='" + singlePair + '\'' +
                ", optionList=" + optionList +
                ", Analysis='" + analysis + '\'' +
                ", correctOptions='" + correctOptions + '\'' +
                '}';
    }
}
