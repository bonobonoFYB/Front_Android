package com.example.fybproject.pager.fragment;

import static android.service.controls.ControlsProviderService.TAG;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.fybproject.mediator.RegisterDataMediator;
import com.example.fybproject.R;

public class SignUpFirstFragment extends Fragment {

    View view;

    EditText inputRegisterEmail, inputRegisterPw, inputRegisterPwCheck, inputRegisterName;

    ImageView hideRegisterPw, showRegisterPw, hideRegisterPwCheck, showRegisterPwCheck;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  (ViewGroup) inflater.inflate(R.layout.fragment_sign_up_first, container, false);

        init();

        inputRegisterEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "email : " + s);
                RegisterDataMediator.setEmail(s.toString());
            }
        }); // user email

        inputRegisterPw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "pw : " + s);
                RegisterDataMediator.setPw(s.toString());
            }
        }); // user password

        inputRegisterPwCheck.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "pwCheck : " + s);
                RegisterDataMediator.setPwCheck(s.toString());
            }
        }); // user password

        inputRegisterName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "name : " + s);
                RegisterDataMediator.setName(s.toString());
            }
        }); // user name

        hideRegisterPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputRegisterPw.setInputType(InputType.TYPE_CLASS_TEXT);
                hideRegisterPw.setVisibility(View.GONE);
                showRegisterPw.setVisibility(View.VISIBLE);
            }
        }); // pw 보이기

        showRegisterPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputRegisterPw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                showRegisterPw.setVisibility(View.GONE);
                hideRegisterPw.setVisibility(View.VISIBLE);
            }
        }); // pw 숨기기

        hideRegisterPwCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputRegisterPwCheck.setInputType(InputType.TYPE_CLASS_TEXT);
                hideRegisterPwCheck.setVisibility(View.GONE);
                showRegisterPwCheck.setVisibility(View.VISIBLE);
            }
        }); // pwCheck 보이기

        showRegisterPwCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputRegisterPwCheck.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                showRegisterPwCheck.setVisibility(View.GONE);
                hideRegisterPwCheck.setVisibility(View.VISIBLE);
            }
        }); // pwCheck 숨기기

        return view;
    }

    public void init() {
        inputRegisterEmail = view.findViewById(R.id.inputRegisterEmail);
        inputRegisterPw = view.findViewById(R.id.inputRegisterPw);
        inputRegisterPwCheck = view.findViewById(R.id.inputRegisterPwCheck);
        inputRegisterName = view.findViewById(R.id.inputRegisterName);
        hideRegisterPw = view.findViewById(R.id.hideRegisterPw);
        showRegisterPw = view.findViewById(R.id.showRegisterPw);
        hideRegisterPwCheck = view.findViewById(R.id.hideRegisterPwCheck);
        showRegisterPwCheck = view.findViewById(R.id.showRegisterPwCheck);
    }
}
