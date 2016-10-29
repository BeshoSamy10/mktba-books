package com.example.bisho.mktba;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Request_Activity extends AppCompatActivity implements View.OnClickListener {

    EditText name;
    EditText address;
    EditText phone;
    EditText age;
    Button submit;

    String bookName = "إسم الكتاب : ";
    String auther   ;
    String subject  ;
    String code     ;
    String entering ;
    String copies   ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_);

        Intent intent = getIntent();
        bookName += intent.getStringExtra("name");
        auther   = intent.getStringExtra("auther");
        subject  = intent.getStringExtra("subject");
        code     = intent.getStringExtra("code");
        entering = intent.getStringExtra("entering");
        copies   = intent.getStringExtra("copies");

        name    = (EditText) findViewById(R.id.editText2);
        address = (EditText) findViewById(R.id.editText3);
        phone   = (EditText) findViewById(R.id.editText4);
        age     = (EditText) findViewById(R.id.editText5);
        submit  = (Button) findViewById(R.id.button2);
        submit.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(name.getText().toString().isEmpty()) Toast.makeText(this,"أدخل إسمك",Toast.LENGTH_SHORT).show();
        else if (address.getText().toString().isEmpty())Toast.makeText(this,"أدخل العنوان",Toast.LENGTH_SHORT).show();
        else if (phone.getText().toString().isEmpty())Toast.makeText(this,"أدخل التليفون",Toast.LENGTH_SHORT).show();
        else if (age.getText().toString().isEmpty())Toast.makeText(this,"أدخل السن",Toast.LENGTH_SHORT).show();
        else {
            String body = "الاسم : "+ name.getText().toString() + "\n" +
                    "العنوان : "+ address.getText().toString() + "\n" +
                    "التليفون : "+ phone.getText().toString() + "\n" +
                    "السن : "+ age.getText().toString() + "\n" + "\n\n"+
                    bookName + "\n" + auther + "\n" + subject + "\n"+
                    code + "\n" + entering + "\n" + copies + "\n" ;
            Intent i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","mktbt.st.mark@gmail.com", null));
            i.putExtra(Intent.EXTRA_SUBJECT,"إستعارة");
            i.putExtra(Intent.EXTRA_TEXT, body);
            startActivity(Intent.createChooser(i, "إختار إيميل لتقديم طلب الإستعارة ..."));
        }
    }
}
