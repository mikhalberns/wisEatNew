package com.example.mikhal.wiseatapp;

import android.*;
import android.Manifest;
import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.cast.CastRemoteDisplayLocalService;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class OcrView extends AppCompatActivity {

    Button picBtn;
    Button checkBtn;
    boolean firsTime=true;
    int res1,res2;
    ImageView im=null;
    Uri photoURI;
    Intent intent;
    String picPath;
    TextView resText;
    String subStr;

    final private int REQUEST_CODE_ASK_PERMISSIONS_CAMERA = 100;
    final private int REQUEST_CODE_ASK_PERMISSIONS_EXTERNAL_STORAGE = 200;
    final private int REQUEST_BOTH = 300;


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
                    if (shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA) || shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE))
                    {
                        checkPermission();
                    }
                    else {
                        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                        //File photoFile = getOutputMediaFile();
                       // photoURI = Uri.fromFile(photoFile);
                        String fileName = "IMG_1.jpg";
                        ContentValues values = new ContentValues();
                        values.put(MediaStore.Images.Media.TITLE, fileName);
                        values.put(MediaStore.Images.Media.DESCRIPTION,"Image capture by camera");
                        photoURI = getContentResolver().insert(
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT,photoURI);
                        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

                        startActivityForResult(intent, 100);
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

        resText = (TextView) findViewById(R.id.resText);
    }

    private File getOutputMediaFile(){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "WisEatPics");

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        picPath = mediaStorageDir.getPath()+File.separator+"IMG_1.jpg";
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_1.jpg");
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {

                im.setImageURI(photoURI);
            }
        }
    }

    public void checkPermission() {
        // For Check Camera Permission
       if (Build.VERSION.SDK_INT >= 23) {
            int hasCameraPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA);
            int hasStoragePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if(hasCameraPermission != PackageManager.PERMISSION_GRANTED && hasStoragePermission != PackageManager.PERMISSION_GRANTED)
            {
                if (shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA)) {
                    // Display UI and wait for user interaction
                    getErrorDialog("You need to allow Camera & Storage permissions." +
                            "\nIf you disable this permission, You will not able to add attachment.", this, 2).show();
                } else {
                    requestPermissions(new String[]{android.Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_BOTH);
                }
                return;
            }

            if (hasCameraPermission != PackageManager.PERMISSION_GRANTED) {
                if (shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA)) {
                    // Display UI and wait for user interaction
                    getErrorDialog("You need to allow Camera permission." +
                            "\nIf you disable this permission, You will not able to add attachment.", this, 0).show();
                } else {
                    requestPermissions(new String[]{android.Manifest.permission.CAMERA}, REQUEST_CODE_ASK_PERMISSIONS_CAMERA);
                }
                return;
            }
            if (hasStoragePermission != PackageManager.PERMISSION_GRANTED) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    // Display UI and wait for user interaction
                    getErrorDialog("You need to allow Storage permission." +
                            "\nIf you disable this permission, You will not able to add attachment.", this, 1).show();
                } else {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_PERMISSIONS_EXTERNAL_STORAGE);
                }
                return;
            }
            return;
       }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if(firsTime)
        {
            firsTime=false;

            res1 = grantResults[0]; //camera
            res2 = grantResults[1]; //storage


            switch (requestCode) {
                case REQUEST_CODE_ASK_PERMISSIONS_CAMERA:
                    if (res1 == PackageManager.PERMISSION_GRANTED) {
                        // Permission Granted
                        Toast.makeText(this, "Permission Grant Camera", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case REQUEST_CODE_ASK_PERMISSIONS_EXTERNAL_STORAGE:
                    if (res2 == PackageManager.PERMISSION_GRANTED) {
                        // Permission Granted
                        Toast.makeText(this, "Permission Grant Storage", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case REQUEST_BOTH:
                    if (res1 == PackageManager.PERMISSION_GRANTED && res2 == PackageManager.PERMISSION_GRANTED) {
                        // Permission Granted
                        Toast.makeText(this, "Permission Grant Camera Storage", Toast.LENGTH_SHORT).show();
                    }
                    break;

                default:
                    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }

    public AlertDialog.Builder getErrorDialog(String message, Context context, final int indicator) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(getString(R.string.app_name)).setMessage(message);
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //indicator=0 - only camera
                //indicator=1 - only storage
                //indicator=2 - both
                dialog.dismiss();
                if (Build.VERSION.SDK_INT >= 23) {
                    if(indicator == 0){
                        requestPermissions(new String[]{android.Manifest.permission.CAMERA},
                                REQUEST_CODE_ASK_PERMISSIONS_CAMERA);
                    }
                    else if(indicator == 1) {
                        requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                                REQUEST_CODE_ASK_PERMISSIONS_EXTERNAL_STORAGE);
                    }
                    else if(indicator == 2){
                        requestPermissions(new String[]{Manifest.permission.CAMERA, android.Manifest.permission.READ_EXTERNAL_STORAGE},
                                REQUEST_BOTH);
                    }
                }
            }
        });
        return alertDialog;
    }

    public void check()
    {
        try {
            TextRecognizer txtRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
            if (!txtRecognizer.isOperational()) {
                // Shows if your Google Play services is not up to date or OCR is not supported for the device
                Toast.makeText(this, "Not Up To Date Play Services", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    Bitmap bitmap = decodeBitmapUri(this, photoURI);
                    Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                    SparseArray items = txtRecognizer.detect(frame);
                    StringBuilder strBuilder = new StringBuilder();

                    for (int i = 0; i < items.size(); i++) {
                        TextBlock item = (TextBlock) items.valueAt(i);
                        strBuilder.append(item.getValue());
                        strBuilder.append("/");
                        // The following Process is used to show how to use lines & elements as well
                        for (i = 0; i < items.size(); i++) {
                            item = (TextBlock) items.valueAt(i);
                            strBuilder.append(item.getValue());
                            strBuilder.append("/");
                        }
                    }

                    String ingStr = strBuilder.toString();
                    int indexOfIng1 = ingStr.indexOf("Ingredients:");
                    int indexOfIng2 = ingStr.indexOf("INGREDIENTS:");
                    int indexOfEnd = ingStr.indexOf(".");

                    if (indexOfIng1 != -1 && indexOfEnd != -1) {

                        subStr = ingStr.substring(indexOfIng1, indexOfEnd);
                        subStr = subStr.substring(12, subStr.length());
                        resText.setText(subStr);
                        SearchIngredients.isOCR = true;
                        SearchIngredients.ocrString = subStr;
                        startActivity(new Intent(getApplicationContext(), SearchIngredients.class));
                    } else if (indexOfIng2 != -1 && indexOfEnd != -1) {
                        subStr = ingStr.substring(indexOfIng2, indexOfEnd);
                        subStr = subStr.substring(12, subStr.length());
                        resText.setText(subStr);
                        SearchIngredients.isOCR = true;
                        SearchIngredients.ocrString = subStr;
                        startActivity(new Intent(getApplicationContext(), SearchIngredients.class));
                    } else {
                        resText.setText("Couldn't Find Ingredients Or The End Of The List." + "Please Try Again.");
                    }

                } catch (IOException e) {
                    Toast.makeText(getApplication(), "Please Take a Picture Again", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, OcrView.class));
                }
            }
        }
        catch(Exception e)
        {
            Toast.makeText(getApplication(), "Please Take a Picture Again", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, OcrView.class));
        }

    }


    private Bitmap decodeBitmapUri(Context ctx, Uri uri) throws FileNotFoundException {
        int targetW = 600;
        int targetH = 600;
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(ctx.getContentResolver().openInputStream(uri), null, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;

        return BitmapFactory.decodeStream(ctx.getContentResolver()
                .openInputStream(uri), null, bmOptions);
    }

}
