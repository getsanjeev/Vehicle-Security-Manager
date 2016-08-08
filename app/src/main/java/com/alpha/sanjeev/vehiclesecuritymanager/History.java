package com.alpha.sanjeev.vehiclesecuritymanager;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class History extends AppCompatActivity implements View.OnClickListener {

    public static final String JSON_URL = "http://getsanjeev.esy.es/sanjeev.php";
    private Button buttonGet;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("Sanjeev","hindu");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        buttonGet = (Button) findViewById(R.id.history_btn);
        buttonGet.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listView);
    }

    private void sendRequest(){
        Log.e("send requests"," just reached");

        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("response",response);
                        showJSON(response);
                        Log.e("response", "k bad 44");
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("fgscvxghws","ggtgsh");
                        Toast.makeText(History.this,error.getMessage(),Toast.LENGTH_LONG).show();

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Log.e("ridhwan",StringRequest.class.toString());
        requestQueue.add(stringRequest);
        Log.e("requestq", "me add ho gya");
    }

    private void showJSON(String json){
        Log.e("pankaj",json.toString());
        parse_history pj = new parse_history(json);
        pj.parseJSON();
        String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};
        Log.e("tanishq",pj.my_data.toString());
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item, pj.my_data);
        Log.e("kalpana","chawla");
        listView.setAdapter(adapter);
        Log.e("raam","sugreve");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.history_btn:
                Log.e("hello","physics");
                sendRequest();
                Log.e("shobhit","human");
                break;

        }
    }
}
