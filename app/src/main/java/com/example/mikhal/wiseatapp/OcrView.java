package com.example.mikhal.wiseatapp;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.FileNotFoundException;


/****************************************************OcrView.java**********************************************************
 This class is responsible for taking a picture and convert it to text (OCR) - in this class we use Google Vision API.
 **************************************************************************************************************************/

public class OcrView extends AppCompatActivity {

    private Button picBtn;
    private Button idBtn;
    private Button backBtn;
    private Button checkBtn;
    private boolean firsTime=true;
    private int res1,res2;
    private ImageView im=null;
    private Uri photoURI;
    private Intent intent;
    private String resText;
    private String subStr;
    private Button rotateB;
    private int isRotated = 0;

    final private int REQUEST_CODE_ASK_PERMISSIONS_CAMERA = 100;
    final private int REQUEST_CODE_ASK_PERMISSIONS_EXTERNAL_STORAGE = 200;
    final private int REQUEST_BOTH = 300;


    //loads when the screen load
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr_view);

        try {
            im = (ImageView) findViewById(R.id.imageView);
            rotateB = (Button) findViewById(R.id.rotateB);
            backBtn = (Button) findViewById(R.id.backB);
            checkBtn =(Button) findViewById(R.id.resB);


            checkPermission();

            picBtn = (Button) findViewById(R.id.picBtn);


            checkBtn.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             if (im != null) {
                                                 check();
                                             } else {
                                                 resText="Take a picture and try again";
                                                 Toast.makeText(getApplicationContext(),resText,Toast.LENGTH_LONG).show();
                                                 startActivity(new Intent(getApplicationContext(), OcrView.class));
                                             }
                                         }
                                     });

            picBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    resText="";
                    initiateRotation();


                    if (Build.VERSION.SDK_INT >= 23) {
                        if (shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA) || shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                            checkPermission();
                        } else {
                            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                            String fileName = "IMG_1.jpg";
                            ContentValues values = new ContentValues();
                            values.put(MediaStore.Images.Media.TITLE, fileName);
                            values.put(MediaStore.Images.Media.DESCRIPTION, "Image capture by camera");
                            photoURI=null;
                            photoURI = getContentResolver().insert(
                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

                            startActivityForResult(intent, 100);
                        }
                    }
                }
            });

            idBtn = (Button) findViewById(R.id.idBtn);
            idBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    identifyText();
                }
            });

            backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), HomePage.class));

                }
            });

            rotateB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    im.setRotation(im.getRotation()+90);
                    isRotated++;
                }
            });

        }
        catch (Exception e)
        {
            resText="Couldn't Find Ingredients Or The End Of The List.\n" + "Please Try Again.";
            showMessage("Identified Text",resText);
            startActivity(new Intent(getApplicationContext(), OcrView.class));
        }
    }

    //intiaite rotation to "start point"
    void initiateRotation()
    {
        for(int i=0;i<isRotated;i++)
        {
            im.setRotation(im.getRotation()-90);
        }
        isRotated=0;
        im = (ImageView) findViewById(R.id.imageView);
    }

    //start after taking the picture
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        try{
            if (requestCode == 100) {
                if (resultCode == RESULT_OK) {

                    im.setImageURI(photoURI);
                }
            }
        }
        catch(Exception e)
        {
            resText="Couldn't Find Ingredients Or The End Of The List.\n" + "Please Try Again.";
            showMessage("Identified Text",resText);
            startActivity(new Intent(getApplicationContext(), OcrView.class));
        }
    }

    //ask for permissions to storage and camera
    public void checkPermission() {

        try{
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
        catch(Exception e)
        {
            resText="You Need To Allow Camera And Storage Permissions";
            Toast.makeText(getApplicationContext(),resText,Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        try{
            if(firsTime)
            {
                firsTime=false;

                res1 = grantResults[0]; //camera
                res2 = grantResults[1]; //storage


                switch (requestCode) {
                    case REQUEST_CODE_ASK_PERMISSIONS_CAMERA:
                        if (res1 == PackageManager.PERMISSION_GRANTED) {
                            // Permission Granted
                        }
                        break;

                    case REQUEST_CODE_ASK_PERMISSIONS_EXTERNAL_STORAGE:
                        if (res2 == PackageManager.PERMISSION_GRANTED) {
                            // Permission Granted
                        }
                        break;

                    case REQUEST_BOTH:
                        if (res1 == PackageManager.PERMISSION_GRANTED && res2 == PackageManager.PERMISSION_GRANTED) {
                            // Permission Granted
                        }
                        break;

                    default:
                        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                }
            }
        }
        catch (Exception e)
        {
            resText="You Need To Allow Camera And Storage Permissions";
            Toast.makeText(getApplicationContext(),resText,Toast.LENGTH_LONG).show();
        }
    }

    //popup alerts about permissions
    public AlertDialog.Builder getErrorDialog(String message, Context context, final int indicator) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        try{
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
        catch(Exception e)
        {
            resText="You Need To Allow Camera And Storage Permissions";
            Toast.makeText(getApplicationContext(),resText,Toast.LENGTH_LONG).show();
            return null;
        }
    }

    //identify text from the picture
    public void identifyText()
    {
        try {
            TextRecognizer txtRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
            if (!txtRecognizer.isOperational()) {
                // Shows if your Google Play services is not up to date or OCR is not supported for the device
                Toast.makeText(this, "Not Up To Date Play Services", Toast.LENGTH_SHORT).show();
            } else {
                Bitmap bitmap = null;
                bitmap = decodeBitmapUri(this, photoURI);
                Matrix matrix = new Matrix();
                Bitmap rotatedBitmap = null;
                for (int i = 0; i < isRotated; i++) {
                    matrix.preRotate(90);
                    rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                }
                if (isRotated > 0)
                    bitmap = rotatedBitmap;
                if (bitmap == null) {
                    resText="Please Take A Picture Again.";
                    Toast.makeText(getApplicationContext(),resText,Toast.LENGTH_LONG).show();
                    return;
                }
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
                int indexOfIng3 = ingStr.indexOf("/Ingredients:");
                int indexOfIng4 = ingStr.indexOf("/INGREDIENTS:");
                int indexOfEnd = ingStr.indexOf(".");

                if(indexOfIng3 != -1 && indexOfEnd != -1)
                {
                    subStr = ingStr.substring(indexOfIng3, indexOfEnd);
                    subStr = subStr.substring(13, subStr.length());

                    resText=subStr;
                    SearchIngredients.isOCR = true;
                    SearchIngredients.ocrString = subStr;
                    im = (ImageView) findViewById(R.id.imageView);
                    showMessage("Identified Text",resText);

                }else if(indexOfIng4 != -1 && indexOfEnd != -1)
                {
                    subStr = ingStr.substring(indexOfIng4, indexOfEnd);
                    subStr = subStr.substring(13, subStr.length());

                    resText=subStr;
                    SearchIngredients.isOCR = true;
                    SearchIngredients.ocrString = subStr;
                    im = (ImageView) findViewById(R.id.imageView);
                    showMessage("Identified Text",resText);

                }
                else if (indexOfIng1 != -1 && indexOfEnd != -1) {

                    subStr = ingStr.substring(indexOfIng1, indexOfEnd);
                    subStr = subStr.substring(12, subStr.length());

                    resText=subStr;
                    SearchIngredients.isOCR = true;
                    SearchIngredients.ocrString = subStr;
                    im = (ImageView) findViewById(R.id.imageView);
                    showMessage("Identified Text",resText);


                } else if (indexOfIng2 != -1 && indexOfEnd != -1) {
                    subStr = ingStr.substring(indexOfIng2, indexOfEnd);
                    subStr = subStr.substring(12, subStr.length());

                    resText=subStr;
                    SearchIngredients.isOCR = true;
                    SearchIngredients.ocrString = subStr;
                    im = (ImageView) findViewById(R.id.imageView);
                    showMessage("Identified Text",resText);
                }
                else {
                    resText="Couldn't Find Ingredients Or The End Of The List.\n" + "Please Try Again.";
                    showMessage("Identified Text",resText);
            }

            }
        } catch (Exception e) {
            resText="Something Went Wrong!\n"+"Please Take a Picture Again.";
            Toast.makeText(getApplicationContext(),resText,Toast.LENGTH_LONG).show();
        }
    }

    //check text for results
    public void check() {

        try {
            TextRecognizer txtRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
            if (!txtRecognizer.isOperational()) {
                // Shows if your Google Play services is not up to date or OCR is not supported for the device
                Toast.makeText(this, "Not Up To Date Play Services", Toast.LENGTH_SHORT).show();
            } else {
                Bitmap bitmap = null;
                bitmap = decodeBitmapUri(this, photoURI);
                Matrix matrix = new Matrix();
                Bitmap rotatedBitmap = null;
                for (int i = 0; i < isRotated; i++) {
                    matrix.preRotate(90);
                    rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                }
                if (isRotated > 0)
                    bitmap = rotatedBitmap;
                if (bitmap == null) {
                    resText="Please Take A Picture First.";
                    Toast.makeText(getApplicationContext(),resText,Toast.LENGTH_LONG).show();
                    return;
                }
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
                int indexOfIng3 = ingStr.indexOf("/Ingredients:");
                int indexOfIng4 = ingStr.indexOf("/INGREDIENTS:");
                int indexOfEnd = ingStr.indexOf(".");

                if(indexOfIng3 != -1 && indexOfEnd != -1)
                {
                    subStr = ingStr.substring(indexOfIng3, indexOfEnd);
                    subStr = subStr.substring(13, subStr.length());

                    resText=subStr;
                    SearchIngredients.isOCR = true;
                    SearchIngredients.ocrString = subStr;
                    im = (ImageView) findViewById(R.id.imageView);

                    startActivity(new Intent(getApplicationContext(), SearchIngredients.class));

                }else if(indexOfIng4 != -1 && indexOfEnd != -1)
                {
                    subStr = ingStr.substring(indexOfIng4, indexOfEnd);
                    subStr = subStr.substring(13, subStr.length());

                    resText=subStr;
                    SearchIngredients.isOCR = true;
                    SearchIngredients.ocrString = subStr;
                    im = (ImageView) findViewById(R.id.imageView);

                    startActivity(new Intent(getApplicationContext(), SearchIngredients.class));
                }
               else if (indexOfIng1 != -1 && indexOfEnd != -1) {

                    subStr = ingStr.substring(indexOfIng1, indexOfEnd);
                    subStr = subStr.substring(12, subStr.length());

                    resText=subStr;
                    SearchIngredients.isOCR = true;
                    SearchIngredients.ocrString = subStr;
                    im = (ImageView) findViewById(R.id.imageView);

                    startActivity(new Intent(getApplicationContext(), SearchIngredients.class));

                } else if (indexOfIng2 != -1 && indexOfEnd != -1) {
                    subStr = ingStr.substring(indexOfIng2, indexOfEnd);
                    subStr = subStr.substring(12, subStr.length());

                    resText=subStr;
                    SearchIngredients.isOCR = true;
                    SearchIngredients.ocrString = subStr;
                    im = (ImageView) findViewById(R.id.imageView);

                    startActivity(new Intent(getApplicationContext(), SearchIngredients.class));

                }
                else {
                    resText="Couldn't Find Ingredients Or The End Of The List." + "Please Try Again.";
                    showMessage("Identified Text",resText);
                    startActivity(new Intent(getApplicationContext(), OcrView.class));
                }

            }
            } catch (Exception e) {
                resText="Please Take a Picture Again";
                Toast.makeText(getApplicationContext(),resText,Toast.LENGTH_LONG).show();

            }

    }

    //converts to bitmap in order to enable rotation
    private Bitmap decodeBitmapUri(Context ctx, Uri uri) throws FileNotFoundException {

        try{
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
        catch (Exception e)
        {
            resText="Please Take A Picture First.";
            Toast.makeText(getApplicationContext(),resText,Toast.LENGTH_LONG).show();
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        //   startActivity(new Intent(getApplicationContext(), HomePage.class));
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
