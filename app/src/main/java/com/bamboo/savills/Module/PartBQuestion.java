package com.bamboo.savills.Module;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class PartBQuestion implements MultiItemEntity {
//    1:选择题 2简答题 3 第四题
    private int type;
    private String title;
    private List<Answers> answers;
    private String shortAnswer;
    private String q4f1;
    private String q4F2;
    private String no;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
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

    public String getQ4f1() {
        return q4f1;
    }

    public void setQ4f1(String q4f1) {
        this.q4f1 = q4f1;
    }

    public String getQ4F2() {
        return q4F2;
    }

    public void setQ4F2(String q4F2) {
        this.q4F2 = q4F2;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
