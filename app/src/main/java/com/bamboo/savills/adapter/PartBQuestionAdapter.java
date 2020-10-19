package com.bamboo.savills.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bamboo.savills.Module.Answers;
import com.bamboo.savills.Module.PartAQuestion;
import com.bamboo.savills.Module.PartBQuestion;
import com.bamboo.savills.R;
import com.bamboo.savills.base.utils.StringUtil;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PartBQuestionAdapter extends BaseMultiItemQuickAdapter<PartBQuestion,BaseViewHolder> {
    private Context mContext;

    public PartBQuestionAdapter( Context mContext,@Nullable List<PartBQuestion> data) {
        super(data);
        addItemType(1, R.layout.item_part_a_type1);
        addItemType(2, R.layout.item_part_a_type2);
        addItemType(3, R.layout.item_part_b_type3);
        addItemType(4, R.layout.item_part_b_type4);
        this.mContext = mContext;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper, PartBQuestion partBQuestion) {
        switch (helper.getItemViewType()){
            case 1:
                helper.setText(R.id.tv_item_part_a_type1_title,partBQuestion.getTitle());
                List<Answers> answers = partBQuestion.getAnswers();
                LinearLayout llOut = helper.getView(R.id.ll_item_part_a_type1);
                dealChoices(answers,llOut,partBQuestion);
                break;
            case 2:
                helper.setText(R.id.tv_item_part_a_type2_title,partBQuestion.getTitle());
                final EditText eQ4 = helper.getView(R.id.et_item_part_a_type2);
                eQ4.setTag(partBQuestion);
                if (StringUtil.isNotEmpty(partBQuestion.getShortAnswer())){
                    eQ4.setText(partBQuestion.getShortAnswer());
                }
                eQ4.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        PartBQuestion question = (PartBQuestion)eQ4.getTag();
                        if (StringUtil.isNotEmpty(eQ4.getText())){
                            question.setShortAnswer(eQ4.getText().toString().trim());
                        }else {
                            question.setShortAnswer("");
                        }
                    }
                });
                break;
            case 3:
                helper.setText(R.id.tv_item_part_b_type3_title,partBQuestion.getTitle());
                final EditText eQ41 = helper.getView(R.id.et_item_part_b_type3_1);
                final EditText eQ42 = helper.getView(R.id.et_item_part_b_type3_2);
                eQ41.setTag(partBQuestion);
                eQ42.setTag(partBQuestion);
                if (StringUtil.isNotEmpty(partBQuestion.getQ4f1())){
                    eQ41.setText(partBQuestion.getQ4f1());
                }
                if (StringUtil.isNotEmpty(partBQuestion.getQ4F2())){
                    eQ42.setText(partBQuestion.getQ4F2());
                }
                eQ41.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        PartBQuestion question = (PartBQuestion)eQ41.getTag();
                        if (StringUtil.isNotEmpty(eQ41.getText().toString().trim())){
                            question.setQ4f1(eQ41.getText().toString().trim());
                        }else {
                            question.setQ4f1("");
                        }
                    }
                });
                eQ42.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        PartBQuestion question = (PartBQuestion)eQ42.getTag();
                        if (StringUtil.isNotEmpty(eQ42.getText().toString().trim())){
                            question.setQ4F2(eQ42.getText().toString().trim());
                        }else {
                            question.setQ4F2("");
                        }
                    }
                });
                break;
            case 4:
                helper.setText(R.id.tv_item_part_b_type4_title,partBQuestion.getTitle());
                List<Answers> answers4 = partBQuestion.getAnswers();
                LinearLayout llOut4 = helper.getView(R.id.ll_item_part_b_type4);
                dealChoices(answers4,llOut4,partBQuestion);
                final  EditText eQ7 = helper.getView(R.id.et_item_part_b_type4);
                eQ7.setTag(partBQuestion);
                if (StringUtil.isNotEmpty(partBQuestion.getShortAnswer())){
                    eQ7.setText(partBQuestion.getShortAnswer());
                }
                eQ7.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        PartBQuestion question = (PartBQuestion)eQ7.getTag();
                        if (StringUtil.isNotEmpty(eQ7.getText().toString().trim())){
                            question.setShortAnswer(eQ7.getText().toString().trim());
                        }else {
                            question.setShortAnswer("");
                        }
                    }
                });
                break;
        }

    }
    private void dealChoices(List<Answers> answers,LinearLayout llOut,PartBQuestion partAQuestion){

        llOut.removeAllViews();
        for (int i = 0;i<answers.size();i++){
            Answers answer = partAQuestion.getAnswers().get(i);
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_choices,null);
            view.setTag(answer);
            TextView tvAnswerUn = view.findViewById(R.id.tv_choice_un_select_title);
            final EditText etUn = view.findViewById(R.id.et_choice_un_select);
            etUn.setTag(answer);
            LinearLayout llUn = view.findViewById(R.id.ll_choice_un_select_out);
            LinearLayout llSelect = view.findViewById(R.id.ll_choice_select_out);
            TextView tvAnswer = view.findViewById(R.id.tv_choice_select_title);
            final EditText etSelect = view.findViewById(R.id.et_choice_select);
            etSelect.setTag(answer);
            tvAnswer.setText(answer.getAnswer());
            tvAnswerUn.setText(answer.getAnswer());
            if (answer.isSelect()){
//                        选择了
                llSelect.setVisibility(View.VISIBLE);
                llUn.setVisibility(View.GONE);
                if (answer.isEdit()){
                    etSelect.setVisibility(View.VISIBLE);
                    if (StringUtil.isNotEmpty(answer.getEditString())){
                        etSelect.setText(answer.getEditString());
                    }
                }else {
                    etSelect.setVisibility(View.GONE);
                }
            }else {
                llSelect.setVisibility(View.GONE);
                llUn.setVisibility(View.VISIBLE);
                if (answer.isEdit()){
                    etUn.setVisibility(View.VISIBLE);
                    if (StringUtil.isNotEmpty(answer.getEditString())){
                        etUn.setText(answer.getEditString());
                    }
                }else {
                    etUn.setVisibility(View.GONE);
                }

            }
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Answers answersV = (Answers)v.getTag();
                    answersV.setSelect(!answersV.isSelect());
                    notifyDataSetChanged();
                }
            });
            etSelect.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    dealEditString(etSelect);
                }
            });
            etUn.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    dealEditString(etUn);
                }
            });
            llOut.addView(view);
        }
    }
    private void dealEditString(EditText editText){
        Answers answers =  (Answers)editText.getTag();
        if (StringUtil.isNotEmpty(editText.getText())){
            answers.setEditString(editText.getText().toString().trim());
        }else {
            answers.setEditString("");
        }
    }
}
