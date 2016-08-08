package com.alpha.sanjeev.vehiclesecuritymanager;

/**
 * Created by sanjeev on 10/7/16.
 */
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;



public class vehicle_list extends Activity

{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle_list);
        final RadioButton epsilon = (RadioButton) findViewById(R.id.vehicle_1);
        SharedPreferences mango = getSharedPreferences("mango",MODE_PRIVATE);
        String vehicle_name = mango.getString("vehicle_name","sanjeev");
        epsilon.setText(vehicle_name);

        Button next_button = (Button) findViewById(R.id.next_button);
        next_button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

                if(epsilon.isChecked())
                {
                    Intent intent = new Intent(vehicle_list.this,qr_scan_1.class);
                    startActivity(intent);

                }
                else
                    Toast.makeText(vehicle_list.this, "Enter Car Model", Toast.LENGTH_SHORT).show();
            }

        });

    }
}
