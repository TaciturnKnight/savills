package com.bamboo.savills.Module;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class PartAQuestion implements MultiItemEntity {
//    1.选择题（1a 1b 1c 2 8 9 12） 2.简答题（4） 填空题 3：（3）4（5）5（6）6（10）7（11）
    private int  type ;
//    问题
    private String title ;
//    选项
    private List<Answers> answers;
//    简答题答案
    private String shortAnswer;
//    题号
    private String qNo;

    //    第三题 type 3 4个空
    private String q3f1;
    private String q3f2;
    private String q3f3;
    private String q3f4;
    //    第五题 type 4 3个空
    private String q5f1;
    private String q5f2;
    private String q5f3;
    //    第6题 type 5 1个空
    private String q6f1;
    //    第7题 type 8 1个空
    private String q7f1;
    //    第10题 type 6 3个空
    private String q10f1;
    private String q10f2;
    private String q10f3;
    //    第11题 type 7 4个空
    private String q11f1;
    private String q11f2;
    private String q11f3;
    private String q11f4;

//    是都是单选
    private boolean isSimgle;
//    是否是必填项 初始赋值的时候不一定会设置 最后提交答案的时候 需要分情况验证
    private boolean isRequired;

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }

    public boolean isSimgle() {
        return isSimgle;
    }

    public void setSimgle(boolean simgle) {
        isSimgle = simgle;
    }

    public String getQ3f1() {
        return q3f1;
    }

    public void setQ3f1(String q3f1) {
        this.q3f1 = q3f1;
    }

    public String getQ3f2() {
        return q3f2;
    }

    public void setQ3f2(String q3f2) {
        this.q3f2 = q3f2;
    }

    public String getQ3f3() {
        return q3f3;
    }

    public void setQ3f3(String q3f3) {
        this.q3f3 = q3f3;
    }

    public String getQ3f4() {
        return q3f4;
    }

    public void setQ3f4(String q3f4) {
        this.q3f4 = q3f4;
    }

    public String getQ5f1() {
        return q5f1;
    }

    public void setQ5f1(String q5f1) {
        this.q5f1 = q5f1;
    }

    public String getQ5f2() {
        return q5f2;
    }

    public void setQ5f2(String q5f2) {
        this.q5f2 = q5f2;
    }

    public String getQ5f3() {
        return q5f3;
    }

    public void setQ5f3(String q5f3) {
        this.q5f3 = q5f3;
    }

    public String getQ6f1() {
        return q6f1;
    }

    public void setQ6f1(String q6f1) {
        this.q6f1 = q6f1;
    }

    public String getQ7f1() {
        return q7f1;
    }

    public void setQ7f1(String q7f1) {
        this.q7f1 = q7f1;
    }

    public String getQ10f1() {
        return q10f1;
    }

    public void setQ10f1(String q10f1) {
        this.q10f1 = q10f1;
    }

    public String getQ10f2() {
        return q10f2;
    }

    public void setQ10f2(String q10f2) {
        this.q10f2 = q10f2;
    }

    public String getQ10f3() {
        return q10f3;
    }

    public void setQ10f3(String q10f3) {
        this.q10f3 = q10f3;
    }

    public String getQ11f1() {
        return q11f1;
    }

    public void setQ11f1(String q11f1) {
        this.q11f1 = q11f1;
    }

    public String getQ11f2() {
        return q11f2;
    }

    public void setQ11f2(String q11f2) {
        this.q11f2 = q11f2;
    }

    public String getQ11f3() {
        return q11f3;
    }

    public void setQ11f3(String q11f3) {
        this.q11f3 = q11f3;
    }

    public String getQ11f4() {
        return q11f4;
    }

    public void setQ11f4(String q11f4) {
        this.q11f4 = q11f4;
    }

    public String getqNo() {
        return qNo;
    }

    public void setqNo(String qNo) {
        this.qNo = qNo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Answers> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answers> answers) {
        this.answers = answers;
    }

    public String getShortAnswer() {
        return shortAnswer;
    }

    public void setShortAnswer(String shortAnswer) {
        this.shortAnswer = shortAnswer;
    }

    @Override
    public int getItemType() {
        return getType();
    }
}
