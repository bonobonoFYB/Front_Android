package com.example.fybproject;

import static android.service.controls.ControlsProviderService.TAG;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fybproject.client.ServiceGenerator;
import com.example.fybproject.dto.authDTO.CheckDTO;
import com.example.fybproject.dto.authDTO.RegisterDTO;
import com.example.fybproject.interceptor.JwtToken;
import com.example.fybproject.mediator.RegisterDataMediator;
import com.example.fybproject.service.AuthService;

import java.io.IOException;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpVerificationActivity extends AppCompatActivity {
    Intent intent;

    TextView receiveCodeBtn;
    EditText inputPhone, inputCode;
    ImageView doSignupBtn;

    String pnum, randNum
            ,email, pw, name, gender, form, sholder, pelvis, leg;
    int age, height, weight;

    private AuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_verification);

        init();
        intent = getIntent();

        receiveCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputPhoneData() == 1) return;
                CheckDTO checkDTO = new CheckDTO(pnum);
                authService = ServiceGenerator.createService(AuthService.class);

                if (authService != null) {
                    authService.postCheckData(checkDTO)
                            .enqueue(new Callback<CheckDTO>() {
                                @Override
                                public void onResponse(Call<CheckDTO> call, Response<CheckDTO> response) {
                                    CheckDTO data = response.body();
                                    if (response.isSuccessful() == true) {
                                        //if (data.getStatus().equals("PHONE_NUM_ERROR"))
                                        //PHONE_CHECK_STATUS_TRUE
                                        Log.d(ContentValues.TAG, "receiveCode : 성공,\nresponseBody : " + data);
                                        randNum = data.getRandNum();

                                        Toast.makeText(getApplicationContext(), "인증번호를 발송하였습니다.", Toast.LENGTH_SHORT).show();
                                    } else {
                                        try {
                                            Log.d(ContentValues.TAG, "receiveCode : 실패,\nresponseBody() : " + data + ",\nresponse.code(): " + response.code() + ",\nresponse.errorBody(): " + response.errorBody().string());
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                                @Override
                                public void onFailure(Call<CheckDTO> call, Throwable t) {
                                    Log.d(ContentValues.TAG, "onFailure: " + t.toString());
                                }
                            });
                }
            }
        }); // 인증번호 받기

        doSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputData() == 1) return;
                RegisterDTO registerDTO = new RegisterDTO(email, pw, name, gender, height, weight, age, form, sholder, pelvis, leg);
                authService = ServiceGenerator.createService(AuthService.class);

                if (authService != null) {
                    authService.postRegisterData(registerDTO)
                            .enqueue(new Callback<RegisterDTO>() {
                                @Override
                                public void onResponse(Call<RegisterDTO> call, Response<RegisterDTO> response) {
                                    RegisterDTO data = response.body();
                                    Headers header = response.headers();
                                    if (response.isSuccessful() == true) {
                                        Log.d(ContentValues.TAG, "register : 성공,\nresponseBody : " + data + ",\njwtToken : " + header.get("Authorization"));
                                        Log.d(ContentValues.TAG, "=====================================================================");

                                        if (data.getStatus().equals("REGISTER_STATUS_TRUE")) {
                                            JwtToken.setToken(header.get("Authorization").substring(7));

                                            Intent intent = new Intent(getApplicationContext(), ProfileImageActivity.class);
                                            startActivity(intent);
                                            Toast.makeText(getApplicationContext(), "회원가입을 축하드립니다!", Toast.LENGTH_SHORT).show();
                                        }


                                    } else {
                                        try {
                                            Log.d(ContentValues.TAG, "register : 실패,\nresponseBody() : " + data + ",\nresponse.code(): " + response.code() + ",\nresponse.errorBody(): " + response.errorBody().string());
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<RegisterDTO> call, Throwable t) {
                                    Log.d(ContentValues.TAG, "onFailure: " + t.toString());
                                }
                            });
                }
            }
        }); // 회원가입 버튼


    }

    public void init() {
        doSignupBtn = findViewById(R.id.doSignupBtn);
        inputPhone = findViewById(R.id.inputPhoneForRegister);
        receiveCodeBtn = findViewById(R.id.receiveCodeBtnForRegister);
        inputCode = findViewById(R.id.inputCodeForRegister);
    }

    public int inputData() {
        if(exception() == 1) return 1;

        email = intent.getStringExtra("email");
        pw = intent.getStringExtra("pw");
        name = intent.getStringExtra("name");
        gender = intent.getStringExtra("gender");
        age = intent.getIntExtra("age", 1);
        height = intent.getIntExtra("height", 1);
        weight = intent.getIntExtra("weight", 1);
        form = intent.getStringExtra("form");
        sholder = intent.getStringExtra("sholder");
        pelvis = intent.getStringExtra("pelvis");
        leg = intent.getStringExtra("leg");

        return 0;
    }

    public int inputPhoneData() {
        if (inputPhone.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "전화번호를 입력하세요", Toast.LENGTH_SHORT).show();
            return 1;
        }
        if (inputPhone.getText().toString().length() != 11) {
            Toast.makeText(getApplicationContext(), "휴대폰 번호를 입력하세요", Toast.LENGTH_SHORT).show();
            return 1;
        }

        StringBuffer s = new StringBuffer();
        s.append(inputPhone.getText().toString());
        s.insert(3, "-");
        s.insert(8, "-");
        pnum = String.valueOf(s);
        Log.d(TAG, "휴대전화 번호 : " + pnum);
        return 0;
    }

    public int exception() {
        if (inputCode.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "인증번호를 입력하세요", Toast.LENGTH_SHORT).show();
            return 1;
        }
        if(!inputCode.getText().toString().equals(randNum)) {
            Toast.makeText(getApplicationContext(), "인증번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
            return 1;
        }
        return 0;
    }
}