package com.tuanpnmfxx00196.videoplayer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {
    EditText userNameReg;
    EditText passwordReg;
    EditText passwordCon;
    Button btnSignUp;
    UserDatabase userDatabase;
    Intent intent;
    ArrayList<User>arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userNameReg = (EditText)findViewById(R.id.userNamReg);
        passwordReg = (EditText)findViewById(R.id.passwordReg);
        passwordCon = (EditText)findViewById(R.id.passwordCon);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        intent = getIntent();
        arrayList = (ArrayList<User>)intent.getSerializableExtra("ArrayList");
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckRegister();
            }
        });
    }
    public void SignUpClick(){
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
    }
    public void CheckRegister() {
        boolean checkUser = true;
        for (int i = 0; i < arrayList.size(); i++) {
            if (userNameReg.getText().toString().equals(arrayList.get(i).userName)) {
                checkUser = false;
            }
        }
        if (checkUser) {
            if (userNameReg.getText().length() != 0 && passwordReg.getText().toString().equals(passwordCon.getText().toString())) {
                userDatabase = new UserDatabase(this);
                userDatabase.insertData(userNameReg.getText().toString(), passwordReg.getText().toString());
                SignUpClick();
            } else if (userNameReg.getText().toString().length() == 0) {
                Toast.makeText(this, "Please input username", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Check password", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Already registered", Toast.LENGTH_SHORT).show();
        }
    }

}
