package com.alpha.sanjeev.vehiclesecuritymanager;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class sign_up_screen extends AppCompatActivity implements View.OnClickListener

{
    private static final String REGISTER_URL = "http://getsanjeev.esy.es/pankaj.php";

    public static final String KEY_USERNAME = "User_name";
    public static final String KEY_PASSWORD = "Password";
    public static final String KEY_EMAIL = "Email_ID";
    public static final String KEY_MODEL = "Vehicle_model";
    public static final String KEY_VEHICLE_ID = "Vehicle_ID";

    EditText alpha,beeta,gamma,delta;
    String my_ID , my_pass, my_model, my_name, my_veh_ID;
    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_screen);
        alpha = (EditText) findViewById(R.id.mail_et);
        beeta = (EditText) findViewById(R.id.name_et);
        gamma = (EditText) findViewById(R.id.model_et);
        delta = (EditText) findViewById(R.id.pass_et);
        login =(Button) findViewById(R.id.log_in_btn);
        login.setOnClickListener(this);
    }

    private void registerUser()
    {
        Log.e("in reg_user","xhdhch");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(sign_up_screen.this,response,Toast.LENGTH_LONG).show();
                        Log.e("insan", response.toString());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(sign_up_screen.this,error.toString(),Toast.LENGTH_LONG).show();
                        Log.e("shobhit", error.toString());
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USERNAME,my_name);
                params.put(KEY_PASSWORD,my_pass);
                params.put(KEY_EMAIL, my_ID);
                params.put(KEY_MODEL, my_model);
                params.put(KEY_VEHICLE_ID, my_veh_ID);
                Log.e("sanjeev","human in india");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        Log.e("sanjej_pankaj","why nit me");
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId() == R.id.log_in_btn)
        {
            Log.e("assdc","dghashas");
            my_ID = alpha.getText().toString();
            my_name = beeta.getText().toString();
            my_model = gamma.getText().toString();
            my_pass = delta.getText().toString();

            if (!my_ID.contains("@") || (my_ID.length() == 0)) {
                Toast.makeText(sign_up_screen.this, "Invalid Email ID", Toast.LENGTH_SHORT).show();
            } else if (my_pass.length() < 5) {
                Toast.makeText(sign_up_screen.this, "Password too short", Toast.LENGTH_SHORT).show();
            } else if (my_model.length() == 0)
                Toast.makeText(sign_up_screen.this, "Enter vehicle model", Toast.LENGTH_SHORT).show();
            else if (!my_ID.contains(".com"))
                Toast.makeText(sign_up_screen.this, "Enter valid ID", Toast.LENGTH_SHORT).show();
            else {
                SharedPreferences mango = getSharedPreferences("mango", MODE_PRIVATE);
                my_veh_ID = mango.getString("vehicle_id", "xyz");
                SharedPreferences.Editor my_editor = mango.edit();
                my_editor.putBoolean("check", true);
                my_editor.putString("ID", my_ID);
                my_editor.putString("name", my_name);
                my_editor.putString("model", my_model);
                my_editor.putString("password", my_pass);
                my_editor.commit();

                registerUser();
                Intent intent = new Intent(sign_up_screen.this, vehicle_list.class);
                startActivity(intent);
                finish();

            }
        }
    }


}



