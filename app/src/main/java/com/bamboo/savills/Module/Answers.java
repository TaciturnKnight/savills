package com.bamboo.savills.Module;

public class Answers{
    private String answer;
    private boolean isSelect;
    //    是否最后有个输入框
    private boolean isEdit;
//    输入框输入的内容
    private String editString;

    public String getEditString() {
        return editString;
    }

    public void setEditString(String editString) {
        this.editString = editString;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }
}
