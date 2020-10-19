package com.bamboo.savills.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bamboo.savills.Module.Answers;
import com.bamboo.savills.Module.PartAQuestion;
import com.bamboo.savills.R;
import com.bamboo.savills.base.utils.StringUtil;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PartAAdapter extends BaseMultiItemQuickAdapter<PartAQuestion,BaseViewHolder> {
    private Context mContext;
    public PartAAdapter(Context mContext,@Nullable List<PartAQuestion> data) {
        super(data);
        addItemType(1, R.layout.item_part_a_type1);
        addItemType(2, R.layout.item_part_a_type2);
        addItemType(3, R.layout.item_part_a_type3);
        addItemType(4, R.layout.item_part_a_type4);
        addItemType(5, R.layout.item_part_a_type5);
        addItemType(6, R.layout.item_part_a_type6);
        addItemType(7, R.layout.item_part_a_type7);
        addItemType(8, R.layout.item_part_a_type8);
        this.mContext = mContext;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper, PartAQuestion partAQuestion) {
        switch (helper.getItemViewType()){
            case 1:
                helper.setText(R.id.tv_item_part_a_type1_title,partAQuestion.getTitle());
                List<Answers> answers = partAQuestion.getAnswers();
                LinearLayout llOut = helper.getView(R.id.ll_item_part_a_type1);
                dealChoices(answers,llOut,partAQuestion);
                break;
            case 2:
                helper.setText(R.id.tv_item_part_a_type2_title,partAQuestion.getTitle());
                final EditText eQ4 = helper.getView(R.id.et_item_part_a_type2);
                eQ4.setTag(partAQuestion);
                if (StringUtil.isNotEmpty(partAQuestion.getShortAnswer())){
                    eQ4.setText(partAQuestion.getShortAnswer());
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
                        PartAQuestion question = (PartAQuestion)eQ4.getTag();
                        if (StringUtil.isNotEmpty(eQ4.getText())){
                            question.setShortAnswer(eQ4.getText().toString().trim());
                        }else {
                            question.setShortAnswer("");
                        }
                    }
                });
                break;
            case 3:
                helper.setText(R.id.tv_item_part_a_type3_title,partAQuestion.getTitle());
                final EditText et31 = helper.getView(R.id.et_item_part_a_type3_1);
                final EditText et32 = helper.getView(R.id.et_item_part_a_type3_2);
                final EditText et33 = helper.getView(R.id.et_item_part_a_type3_3);
                final EditText et34 = helper.getView(R.id.et_item_part_a_type3_4);
                et31.setTag(partAQuestion);
                et32.setTag(partAQuestion);
                et33.setTag(partAQuestion);
                et34.setTag(partAQuestion);
                if (StringUtil.isNotEmpty(partAQuestion.getQ3f1())){
                    et31.setText(partAQuestion.getQ3f1());
                }
                if (StringUtil.isNotEmpty(partAQuestion.getQ3f2())){
                    et32.setText(partAQuestion.getQ3f2());
                }
                if (StringUtil.isNotEmpty(partAQuestion.getQ3f3())){
                    et33.setText(partAQuestion.getQ3f3());
                }
                if (StringUtil.isNotEmpty(partAQuestion.getQ3f4())){
                    et34.setText(partAQuestion.getQ3f4());
                }
                et31.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        PartAQuestion question = (PartAQuestion)et31.getTag();
                        if (StringUtil.isNotEmpty(et31.getText().toString().trim())){
                            question.setQ3f1(et31.getText().toString().trim());
                        }else {
                            question.setQ3f1("");
                        }
                    }
                });
                et32.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        PartAQuestion question = (PartAQuestion)et32.getTag();
                        if (StringUtil.isNotEmpty(et32.getText().toString().trim())){
                            question.setQ3f2(et32.getText().toString().trim());
                        }else {
                            question.setQ3f2("");
                        }
                    }
                });
                et33.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        PartAQuestion question = (PartAQuestion)et33.getTag();
                        if (StringUtil.isNotEmpty(et33.getText().toString().trim())){
                            question.setQ3f3(et33.getText().toString().trim());
                        }else {
                            question.setQ3f3("");
                        }
                    }
                });
                et34.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        PartAQuestion question = (PartAQuestion)et34.getTag();
                        if (StringUtil.isNotEmpty(et34.getText().toString().trim())){
                            question.setQ3f4(et34.getText().toString().trim());
                        }else {
                            question.setQ3f4("");
                        }
                    }
                });
                break;
            case 4:
//                第5题
                helper.setText(R.id.tv_item_part_a_type4_title,partAQuestion.getTitle());
                final EditText eQ51 = helper.getView(R.id.et_item_part_a_type4_1);
                final EditText eQ52 = helper.getView(R.id.et_item_part_a_type4_2);
                final EditText eQ53 = helper.getView(R.id.et_item_part_a_type4_3);
                eQ51.setTag(partAQuestion);
                eQ52.setTag(partAQuestion);
                eQ53.setTag(partAQuestion);
                if (StringUtil.isNotEmpty(partAQuestion.getQ5f1())){
                    eQ51.setText(partAQuestion.getQ5f1());
                }
                if (StringUtil.isNotEmpty(partAQuestion.getQ5f2())){
                    eQ52.setText(partAQuestion.getQ5f2());
                }
                if (StringUtil.isNotEmpty(partAQuestion.getQ5f3())){
                    eQ53.setText(partAQuestion.getQ5f3());
                }
                eQ51.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        PartAQuestion question = (PartAQuestion)eQ51.getTag();
                        if (StringUtil.isNotEmpty(eQ51.getText().toString().trim())){
                            question.setQ5f1(eQ51.getText().toString().trim());
                        }else {
                            question.setQ5f1("");
                        }
                    }
                });
                eQ52.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        PartAQuestion question = (PartAQuestion)eQ52.getTag();
                        if (StringUtil.isNotEmpty(eQ52.getText().toString().trim())){
                            question.setQ5f2(eQ52.getText().toString().trim());
                        }else {
                            question.setQ5f2("");
                        }
                    }
                });
                eQ53.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        PartAQuestion question = (PartAQuestion)eQ53.getTag();
                        if (StringUtil.isNotEmpty(eQ53.getText().toString().trim())){
                            question.setQ5f3(eQ53.getText().toString().trim());
                        }else {
                            question.setQ5f3("");
                        }
                    }
                });


                break;
            case 5:
                //第六题
                helper.setText(R.id.tv_item_part_a_type5_title,partAQuestion.getTitle());
                final EditText eQ6 = helper.getView(R.id.et_item_part_a_type5);
                eQ6.setTag(partAQuestion);
                if (StringUtil.isNotEmpty(partAQuestion.getQ6f1())){
                    eQ6.setText(partAQuestion.getQ6f1());
                }
                eQ6.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        PartAQuestion question =(PartAQuestion) eQ6.getTag();
                        if (StringUtil.isNotEmpty(eQ6.getText().toString().trim())){
                            question.setQ6f1(eQ6.getText().toString().trim());
                        }else {
                            question.setQ6f1("");
                        }
                    }
                });
                break;
            case 6:
//                第十题
                helper.setText(R.id.tv_item_part_a_type6_title,partAQuestion.getTitle());
                final EditText eQ101 = helper.getView(R.id.et_item_part_a_type6_1);
                final EditText eQ102 = helper.getView(R.id.et_item_part_a_type6_2);
                final EditText eQ103 = helper.getView(R.id.et_item_part_a_type6_3);
                eQ101.setTag(partAQuestion);
                eQ102.setTag(partAQuestion);
                eQ103.setTag(partAQuestion);
                if (StringUtil.isNotEmpty(partAQuestion.getQ10f1())){
                    eQ101.setText(partAQuestion.getQ10f1());
                }
                if (StringUtil.isNotEmpty(partAQuestion.getQ10f2())){
                    eQ102.setText(partAQuestion.getQ10f2());
                }
                if (StringUtil.isNotEmpty(partAQuestion.getQ10f3())){
                    eQ103.setText(partAQuestion.getQ10f3());
                }
                eQ101.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        PartAQuestion question =(PartAQuestion) eQ101.getTag();
                        if (StringUtil.isNotEmpty(eQ101.getText().toString().trim())){
                            question.setQ10f1(eQ101.getText().toString().trim());
                        }else {
                            question.setQ10f1("");
                        }
                    }
                });
                eQ102.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        PartAQuestion question =(PartAQuestion) eQ102.getTag();
                        if (StringUtil.isNotEmpty(eQ102.getText().toString().trim())){
                            question.setQ10f2(eQ102.getText().toString().trim());
                        }else {
                            question.setQ10f2("");
                        }
                    }
                });
                eQ103.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        PartAQuestion question =(PartAQuestion) eQ103.getTag();
                        if (StringUtil.isNotEmpty(eQ103.getText().toString().trim())){
                            question.setQ10f3(eQ103.getText().toString().trim());
                        }else {
                            question.setQ10f3("");
                        }
                    }
                });
                break;
            case 7:
//                第11题
                helper.setText(R.id.tv_item_part_a_type7_title,partAQuestion.getTitle());
                final EditText eQ111 = helper.getView(R.id.et_item_part_a_type7_1);
                final EditText eQ112 = helper.getView(R.id.et_item_part_a_type7_2);
                final EditText eQ113 = helper.getView(R.id.et_item_part_a_type7_3);
                final EditText eQ114 = helper.getView(R.id.et_item_part_a_type7_4);
                eQ111.setTag(partAQuestion);
                eQ112.setTag(partAQuestion);
                eQ113.setTag(partAQuestion);
                eQ114.setTag(partAQuestion);
                if (StringUtil.isNotEmpty(partAQuestion.getQ11f1())){
                    eQ111.setText(partAQuestion.getQ11f1());
                }
                if (StringUtil.isNotEmpty(partAQuestion.getQ11f2())){
                    eQ112.setText(partAQuestion.getQ11f2());
                }
                if (StringUtil.isNotEmpty(partAQuestion.getQ11f3())){
                    eQ113.setText(partAQuestion.getQ11f3());
                }
                if (StringUtil.isNotEmpty(partAQuestion.getQ11f4())){
                    eQ114.setText(partAQuestion.getQ11f4());
                }
                eQ111.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        PartAQuestion question =(PartAQuestion) eQ111.getTag();
                        if (StringUtil.isNotEmpty(eQ111.getText().toString().trim())){
                            question.setQ11f1(eQ111.getText().toString().trim());
                        }else {
                            question.setQ11f1("");
                        }
                    }
                });
                eQ112.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        PartAQuestion question =(PartAQuestion) eQ112.getTag();
                        if (StringUtil.isNotEmpty(eQ112.getText().toString().trim())){
                            question.setQ11f2(eQ112.getText().toString().trim());
                        }else {
                            question.setQ11f2("");
                        }
                    }
                });
                eQ113.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        PartAQuestion question =(PartAQuestion) eQ113.getTag();
                        if (StringUtil.isNotEmpty(eQ113.getText().toString().trim())){
                            question.setQ11f3(eQ113.getText().toString().trim());
                        }else {
                            question.setQ11f3("");
                        }
                    }
                });
                eQ114.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        PartAQuestion question =(PartAQuestion) eQ114.getTag();
                        if (StringUtil.isNotEmpty(eQ114.getText().toString().trim())){
                            question.setQ11f4(eQ114.getText().toString().trim());
                        }else {
                            question.setQ11f4("");
                        }
                    }
                });
                break;
            case 8:
                helper.setText(R.id.tv_item_part_a_type8_title,partAQuestion.getTitle());
                List<Answers> answers8 = partAQuestion.getAnswers();
                LinearLayout llOut8 = helper.getView(R.id.ll_item_part_a_type8);
                dealChoices(answers8,llOut8,partAQuestion);
                final EditText et71 = helper.getView(R.id.et_q7_1);
                et71.setTag(partAQuestion);
                if (StringUtil.isNotEmpty(partAQuestion.getQ7f1())){
                    et71.setText(partAQuestion.getQ7f1());
                }
                et71.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                       PartAQuestion question = (PartAQuestion) et71.getTag();
                        if (StringUtil.isNotEmpty(et71.getText())){
                            question.setQ7f1(et71.getText().toString().trim());
                        }else {
                            question.setQ7f1("");
                        }
                    }
                });
                break;
        }

    }
    private void dealChoices(List<Answers> answers,LinearLayout llOut,PartAQuestion partAQuestion){

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
