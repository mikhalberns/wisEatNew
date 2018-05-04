package com.example.mikhal.wiseatapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.PixelFormat;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.cast.CastRemoteDisplayLocalService;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;


public class OcrView extends AppCompatActivity {

    Button picBtn;
    Button checkBtn;
    boolean firsTime=true;
    int res;
    ImageView im=null;
    Bitmap bitmap=null;
    TextView resText;


    final private int REQUEST_CODE_ASK_PERMISSIONS_CAMERA = 100;
    final private int REQUEST_CODE_ASK_PERMISSIONS_EXTERNAL_STORAGE = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr_view);

        im = (ImageView)findViewById(R.id.imageView);

        checkPermission();

        picBtn= (Button)findViewById(R.id.picBtn);
        picBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23) {
                    if (shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA))
                    {
                        checkPermission();
                    }
                    else
                    {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent,0);
                    }
                }
            }
        });

        checkBtn = (Button)findViewById(R.id.checkBtn);
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(im != null)
                {
                    check();
                }
                else
                {
                    Toast.makeText(getApplication(), "Take a picture and try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

        resText = (TextView)findViewById(R.id.resText);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        bitmap = (Bitmap)data.getExtras().get("data");

        im.setImageBitmap(bitmap);

    }

    public void checkPermission()
    {
        // For Check Camera Permission
        if (Build.VERSION.SDK_INT >= 23) {
            int hasPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA);
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                if (shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA)) {
                    // Display UI and wait for user interaction
                    getErrorDialog("You need to allow Camera permission." +
                            "\nIf you disable this permission, You will not able to add attachment.", this, true).show();
                } else {
                    requestPermissions(new String[]{android.Manifest.permission.CAMERA}, REQUEST_CODE_ASK_PERMISSIONS_CAMERA);
                }
                return;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(firsTime) {
            firsTime = false;
            res = grantResults[0];

            switch (requestCode) {
                case REQUEST_CODE_ASK_PERMISSIONS_CAMERA:
                    if (res == PackageManager.PERMISSION_GRANTED) {
                        // Permission Granted
                        Toast.makeText(this, "Permission Grant", Toast.LENGTH_SHORT).show();
                    }
                    break;

                default:
                    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }

    public AlertDialog.Builder getErrorDialog(String message, Context context, final boolean isFromCamera) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(getString(R.string.app_name)).setMessage(message);
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                if (Build.VERSION.SDK_INT >= 23) {
                    if(isFromCamera){
                        requestPermissions(new String[]{android.Manifest.permission.CAMERA},
                                REQUEST_CODE_ASK_PERMISSIONS_CAMERA);
                    }else {
                        requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                                REQUEST_CODE_ASK_PERMISSIONS_EXTERNAL_STORAGE);
                    }
                }

            }
        });
        return alertDialog;
    }

    public void check()
    {

        TextRecognizer txtRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
        if (!txtRecognizer.isOperational())
        {
            // Shows if your Google Play services is not up to date or OCR is not supported for the device
            Toast.makeText(this, "Not Up To Date Play Services", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Good", Toast.LENGTH_SHORT).show();
            // Set the bitmap taken to the frame to perform OCR Operations.

            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            SparseArray items = txtRecognizer.detect(frame);
            StringBuilder strBuilder = new StringBuilder();
            for (int i = 0; i < items.size(); i++)
            {
                TextBlock item = (TextBlock)items.valueAt(i);
                strBuilder.append(item.getValue());
                strBuilder.append("/");
                // The following Process is used to show how to use lines & elements as well
                for (i = 0; i < items.size(); i++) {
                    item = (TextBlock) items.valueAt(i);
                    strBuilder.append(item.getValue());
                    strBuilder.append("/");
                }
            }

            resText.setText(strBuilder.toString());
        }
    }


}
