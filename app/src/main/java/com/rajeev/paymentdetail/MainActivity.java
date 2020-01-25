package com.rajeev.paymentdetail;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    EditText et_name,et_bank_name,et_account_no,et_switcode,et_swcode,et_branchname;
    Button btn_submitt;
    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    String setUrl = "https://fullstackdevmob.000webhostapp.com/Register.php";
    String b_name,b_bankname,b_acc_no,b_swiftcode,b_sw2,b_branchname;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name=findViewById(R.id.benef_name);
        et_bank_name=findViewById(R.id.benef_bank_name);
        et_account_no=findViewById(R.id.benef_acc_num);
        et_switcode=findViewById(R.id.benef_swif_code);
        et_swcode=findViewById(R.id.benef_wift2);
        et_branchname=findViewById(R.id.benef_branchname);

        btn_submitt=findViewById(R.id.btn_submit);

        requestQueue = Volley.newRequestQueue(this);

        btn_submitt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b_name=et_name.getText().toString().trim();
                b_bankname=et_bank_name.getText().toString().trim();
                b_acc_no=et_account_no.getText().toString().trim();
                b_swiftcode=et_switcode.getText().toString().trim();
                b_sw2=et_swcode.getText().toString().trim();
                b_branchname=et_branchname.getText().toString().trim();

                if (b_name.isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                }else if (b_bankname.isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter Bank's Name", Toast.LENGTH_SHORT).show();
                }else if (b_acc_no.isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter Bank Account Number", Toast.LENGTH_SHORT).show();
                }else if (b_swiftcode.isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter Swift/BIC code", Toast.LENGTH_SHORT).show();
                }else if(b_swiftcode.length()<8){
                    Toast.makeText(MainActivity.this, "Invalid Swift/BIC code it must be 8 character", Toast.LENGTH_SHORT).show();
                }else if (b_sw2.isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter other code", Toast.LENGTH_SHORT).show();
                }else if(b_sw2.length()<11){
                    Toast.makeText(MainActivity.this, "Invalid other code it must be 11 character", Toast.LENGTH_SHORT).show();
                }else if (b_branchname.isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter Branch name", Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog= new ProgressDialog(MainActivity.this);
                    progressDialog.setMessage("Saving Data");
                    progressDialog.show();

                    StringRequest request = new StringRequest(Request.Method.POST, setUrl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Toast.makeText(MainActivity.this, "Data Saved"+response, Toast.LENGTH_SHORT).show();
                            progressDialog.hide();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.hide();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> data = new HashMap<String, String>();
                            data.put("name",b_name);
                            data.put("b_bankname",b_bankname);
                            data.put("b_acc_no",b_acc_no);
                            data.put("b_swiftcode",b_swiftcode);
                            data.put("b_sw2", b_sw2);
                            data.put("b_branchname",b_branchname);
                            return data;
                        }
                    };
                    requestQueue.add(request);

                }
            }
        });
    }
}
