package com.tuanpnmfxx00196.videoplayer;

import android.content.Intent;
import android.database.Cursor;
import android.service.autofill.UserData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText userName;
    EditText password;
    TextView txvSignUp;
    Button btnSignIn;
    UserDatabase userDatabase;
    ArrayList<User> arrayList;
    String user="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = (EditText)findViewById(R.id.userName);
        password = (EditText)findViewById(R.id.password);
        txvSignUp = (TextView)findViewById(R.id.txvSignUp);
        btnSignIn = (Button)findViewById(R.id.btnSignIn);
        DataUser();
        txvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = userName.getText().toString();
                CheckSignIn();
                userName.setText("");
                password.setText("");
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }
    public void SignUp(){
        Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
        intent.putExtra("ArrayList",arrayList);
        startActivity(intent);
    }
    public void SignIn(){
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        intent.putExtra("User",user);
        startActivity(intent);
    }
    public void DataUser(){
        userDatabase = new UserDatabase(this);
        arrayList = new ArrayList <User>();
        Cursor res = userDatabase.GetAllData();
        if(res.getCount()==0){
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }else{
            res.moveToFirst();
            while (res.isAfterLast()==false){
                User user = new User(res.getInt(0),
                        res.getString(1),
                        res.getString(2)
                );
                arrayList.add(user);
                res.moveToNext();
            }
        }
    }
    public void CheckSignIn() {
        boolean countUser = false;
        int position = 0;
        if (userName.getText().toString().length() != 0 && password.getText().toString().length() != 0) {
            for(int i=0;i<arrayList.size();i++){
                if(userName.getText().toString().equals(arrayList.get(i).userName)){
                    countUser =true;
                    position = i;
                }
            }
        }
        else{
            Toast.makeText(this, "Please input username or password",Toast.LENGTH_SHORT).show();
        }
        if (countUser){
            if(password.getText().toString().equals(arrayList.get(position).password)){
                SignIn();
                            }
            else{
                Toast.makeText(this, "Input password again",Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Username not exist",Toast.LENGTH_SHORT).show();
        }
    }

    private void startActivities(Intent intent) {
    }
}