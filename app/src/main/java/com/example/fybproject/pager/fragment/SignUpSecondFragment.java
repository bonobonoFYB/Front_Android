package com.example.fybproject.pager.fragment;

import static android.service.controls.ControlsProviderService.TAG;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.example.fybproject.mediator.RegisterDataMediator;
import com.example.fybproject.R;

public class SignUpSecondFragment extends Fragment {
    View view;

    EditText inputRegisterAge, inputRegisterHeight, inputRegisterWeight;
    RadioGroup genderRG;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  (ViewGroup) inflater.inflate(R.layout.fragment_sign_up_second, container, false);

        init();

        inputRegisterAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "age : " + s);
                RegisterDataMediator.setAge(s.toString());
            }
        }); // user age

        inputRegisterHeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "height : " + s);
                RegisterDataMediator.setHeight(s.toString());
            }
        }); //user height

        inputRegisterWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "weight : " + s);
                RegisterDataMediator.setWeight(s.toString());
            }
        }); // user weight

        genderRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.maleBtn:
                        Log.d(TAG, "gender : 남자");
                        RegisterDataMediator.setGender("M");
                        break;
                    case R.id.femaleBtn:
                        Log.d(TAG, "gender : 여자");
                        RegisterDataMediator.setGender("W");
                        break;
                }
            }
        }); // user gender

        return view;
    }

    public void init() {
        inputRegisterAge = view.findViewById(R.id.inputRegisterAge);
        genderRG = view.findViewById(R.id.genderRG);
        inputRegisterHeight = view.findViewById(R.id.inputRegisterHeight);
        inputRegisterWeight = view.findViewById(R.id.inputRegisterWeight);
    }
}
