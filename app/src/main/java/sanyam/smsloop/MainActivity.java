package sanyam.smsloop;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button;
    EditText et1,et2;
    EditText number;
    NumberPicker numpi;

    //    int mNum = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                        Manifest.permission.SEND_SMS)){
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.SEND_SMS},1);
                }else{
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.SEND_SMS},1);
                }
        }else{
            //do noth
        }
        button = (Button)findViewById(R.id.button);
        et1=(EditText)findViewById(R.id.editText3);//number
        et2=(EditText)findViewById(R.id.editText4);//text
        numpi=(NumberPicker)findViewById(R.id.np);
        numpi.setMinValue(2);
        numpi.setMaxValue(20);
//        numpi.setOnValueChangedListener(onValueChangeListener);

//        number = (EditText) findViewById(R.id.number);
//        mNum = Integer.parseInt(number.getText().toString());
        //String z = number.getText().toString();
        button.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                int i = 0;
                String number4 = et1.getText().toString();
                String sms = et2.getText().toString();
                SmsManager smsManager = SmsManager.getDefault();
               while(i<numpi.getValue()) {

                    smsManager.sendTextMessage(number4,null,sms,null,null);
                    Toast.makeText(MainActivity.this,"sms sent to number times:"+numpi.getValue(),Toast.LENGTH_SHORT).show();
                    i--;
                }

            }
        });
    }
//    @Override

    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode){
            case 1 :{
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this,"PERMISSION GRANTED",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this,"NO PERMISSION GRANTED",Toast.LENGTH_SHORT).show();

                }
                return;
            }
        }
    }


}

