package com.alpha.sanjeev.vehiclesecuritymanager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class qr_scan_1 extends Activity

{
    SurfaceView cameraView;
    TextView barcodeInfo;
    BarcodeDetector barcodeDetector;
    CameraSource cameraSource;
    String vehicle_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_scan_1);
        cameraView = (SurfaceView)findViewById(R.id.camera_view);
        barcodeInfo = (TextView)findViewById(R.id.code_info);
        barcodeDetector =
                new BarcodeDetector.Builder(this)
                        .setBarcodeFormats(Barcode.QR_CODE)
                        .build();

        cameraSource = new CameraSource
                .Builder(this, barcodeDetector)
                .setRequestedPreviewSize(10, 10).setAutoFocusEnabled(true)
                .build();
        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    cameraSource.start(cameraView.getHolder());
                } catch (IOException ie) {
                    Log.e("CAMERA SOURCE", ie.getMessage());
                } catch (SecurityException sc) {
                    Log.e("error", "security");
                }
            }

            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {

            public void release() {

            }

            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                if (barcodes.size() != 0) {
                    barcodeInfo.post(new Runnable() {    // Use the post method of the TextView
                        public void run() {
                            barcodeInfo.setText(    // Update the TextView
                                    barcodes.valueAt(0).displayValue

                            );
                        }
                    });
                }
            }
        });

        vehicle_id  = barcodeInfo.getText().toString();
        SharedPreferences mango = getSharedPreferences("mango",MODE_PRIVATE);
        SharedPreferences.Editor my_editor = mango.edit();
        my_editor.putString("vehicle_id",vehicle_id);
        my_editor.commit();

        Button my_btn = (Button)findViewById(R.id.button_1);
        my_btn.setOnClickListener((new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(qr_scan_1.this,tab_view.class);

                startActivity(intent);}
            }));
    }

}
