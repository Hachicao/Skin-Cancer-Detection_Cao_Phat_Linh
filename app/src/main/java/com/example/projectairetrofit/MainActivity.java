package com.example.projectairetrofit;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.label.Category;
import org.tensorflow.lite.task.vision.detector.Detection;
import org.tensorflow.lite.task.vision.detector.ObjectDetector;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText edt_url;
    ImageView screeview;
    ImageButton btn_takePhoto;
    Spinner spinner;
    TextView tv_cancerName, tv_Pro;
    ProgressBar pbLoad;
    ImageButton btn_home, btn_profile;

    int image_test[] = {R.drawable.img19, R.drawable.img17, R.drawable.img18,
            R.drawable.img22, R.drawable.img23, R.drawable.img25, R.drawable.img26,
            R.drawable.img27, R.drawable.img29, R.drawable.img30, R.drawable.img32,
            R.drawable.img33, R.drawable.img40, R.drawable.img41, R.drawable.img42,
            R.drawable.img43, R.drawable.img44, R.drawable.img45, R.drawable.img46,
            R.drawable.img47, R.drawable.img48, R.drawable.img49, R.drawable.img50};
    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK && result != null) {
//                Bundle extras = result.getData().getExtras();
//                Bitmap imageBitmap = (Bitmap) extras.get("data");

                Bitmap imageBitmap = BitmapFactory.decodeFile(currentPhotoPath);
                imageBitmap = Bitmap.createScaledBitmap(imageBitmap, imageBitmap.getWidth() / 3, imageBitmap.getHeight() / 3, true);

                System.out.print("hello***********");
                System.out.println("With : " + imageBitmap.getWidth() + " " + "height " + imageBitmap.getHeight());
//                Bitmap resized_outputbitmap2 = Bitmap.createScaledBitmap(imageBitmap, 1200, 900, true);

//                imageBitmap = getSampleImage(R.drawable.img19);
                Bitmap outputbitmap = imageBitmap.copy(Bitmap.Config.ARGB_8888, true);
                System.out.println("Withoutputbitmap: " + outputbitmap.getWidth() + " " + "heightoutputbitmap " + outputbitmap.getHeight());

//                Bitmap resized_outputbitmap = Bitmap.createScaledBitmap(outputbitmap, 1872 / 3, 4160 / 3, true);
                Bitmap resize = Bitmap.createScaledBitmap(outputbitmap, (int) (1872 / 2.6), (int) (4160 / 2.67), true);

                byte[] bytes = bitmapToBytes(imageBitmap);
                String base64 = bytesToBase64(bytes);

                detect(base64, new CustomCallback() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onSuccess(Result value) {
                        if (value != null) {
                            System.out.print("---------------If-----------");
//                            screeview.setImageBitmap(outputbitmap);
                            screeview.setImageBitmap(resize);


//                            Rect tagSize = new Rect(0, 0, 0, 0);
                            String name = value.getPlacement();
                            tv_cancerName.setText(name);


                            float score = Float.parseFloat(value.getScore());
                            tv_Pro.setText(Math.round(score * 100) + "%");

                        } else {
                            System.out.print("---------------else-----------");
//                            Rect tagSize = new Rect(0, 0, 0, 0);
                            String name = "Unknown Cancer";
                            tv_cancerName.setText(name);
                            tv_Pro.setText("0%");

//                            screeview.setImageBitmap(outputbitmap);
                            screeview.setImageBitmap(resize);

                        }
                    }

                    @Override
                    public void onFailure() {

                    }
                });

            }
        }
    });

    public void detect(String base64, CustomCallback customCallback) {
        if (edt_url.getText().toString().equals("")) {
            edt_url.setText("");
            Toast.makeText(MainActivity.this, "Please Enter the URL!", Toast.LENGTH_LONG).show();
        } else {
            DemoService demoService = DemoRetrofit.getInstance(edt_url.getText().toString()).create(DemoService.class);
            demoService.getimage(base64, spinner.getSelectedItem().toString()).enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    customCallback.onSuccess(response.body());
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    System.out.print(t.getMessage());
                }
            });
        }


    }

    public static byte[] bitmapToBytes(Bitmap photo) {
        System.out.println("With" + photo.getWidth() + "height" + photo.getHeight());
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public static String bytesToBase64(byte[] bytes) {
        final String base64 = Base64.encodeToString(bytes, 0);
        return base64;
    }

    private Bitmap getSampleImage(int resId) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable = false;
        return BitmapFactory.decodeResource(getResources(), resId, options);
    }

    private File createImageFile() throws IOException {

        long timeStamp = System.currentTimeMillis();
        String imageFileName = "NAME_" + timeStamp;
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        return image;
    }

    // function that will take picture from intent.
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startForResult.launch(takePictureIntent);
    }

    private String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        screeview = findViewById(R.id.screeView);
        btn_takePhoto = findViewById(R.id.btn_takePic);
        tv_cancerName = findViewById(R.id.tv_name);
        tv_Pro = findViewById(R.id.tv_pro);
        edt_url = findViewById(R.id.edt_url);
        screeview.setImageBitmap(getSampleImage(R.drawable.white));
//        pbLoad= findViewById(R.id.pb_load_main);
        btn_home= findViewById(R.id.btn_home);
        btn_profile= findViewById(R.id.btn_profile);
        btn_takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dispatchTakePictureIntent();
                String fileName = "photo";
                File storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);


                try {
                    File imageFile = File.createTempFile(fileName, ".jpg", storageDirectory);
                    currentPhotoPath = imageFile.getAbsolutePath();
                    Uri imageUri = FileProvider.getUriForFile(MainActivity.this, "com.example.projectairetrofit.fileprovider", imageFile);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

                    startForResult.launch(intent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });
        //get the spinner from the xml.
        spinner = findViewById(R.id.spinner1);
//create a list of items for the spinner.
        String[] items = new String[]{"0.9", "0.85", "0.8", "0.75", "0.7", "0.6", "0.5"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        spinner.setAdapter(adapter);

//spinner image user can choose the image from local
        Spinner spin_image = (Spinner) findViewById(R.id.spinner_img);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), image_test);
        spin_image.setAdapter(customAdapter);
        spin_image.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                screeview.setImageBitmap(getSampleImage(image_test[position]));
//                Bitmap imageBitmap = getSampleImage(image_test[position]);
//                imageBitmap = Bitmap.createScaledBitmap(imageBitmap, imageBitmap.getWidth() / 3, imageBitmap.getHeight() / 3, true);

//                System.out.print("hello***********");
//                System.out.println("With : " + imageBitmap.getWidth() + " " + "height " + imageBitmap.getHeight());
//                Bitmap resized_outputbitmap2 = Bitmap.createScaledBitmap(imageBitmap, 1200, 900, true);

                Bitmap imageBitmap = getSampleImage(image_test[position]);
                Bitmap outputbitmap = imageBitmap.copy(Bitmap.Config.ARGB_8888, true);
                System.out.println("Withoutputbitmap: " + outputbitmap.getWidth() + " " + "heightoutputbitmap " + outputbitmap.getHeight());

                Bitmap resized_outputbitmap = Bitmap.createScaledBitmap(outputbitmap, 1872 / 3, 4160 / 3, true);
                Bitmap resize = Bitmap.createScaledBitmap(outputbitmap, 624, 800, true);

                byte[] bytes = bitmapToBytes(imageBitmap);
                String base64 = bytesToBase64(bytes);

                detect(base64, new CustomCallback() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onSuccess(Result value) {
                        if (value != null) {
                            System.out.print("---------------If-----------");
                            screeview.setImageBitmap(outputbitmap);
//                            screeview.setImageBitmap(resize);

//                            Rect tagSize = new Rect(0, 0, 0, 0);
                            String name = value.getPlacement();
                            tv_cancerName.setText(name);

////                            int scaledSize = getResources().getDimensionPixelSize(R.dimen.font_size);
//                            paint.setTextSize(30);
//                            Typeface typeface = getResources().getFont(R.font.roboto_thin);
//
                            float score = Float.parseFloat(value.getScore());
                            tv_Pro.setText(Math.round(score * 100) + "%");

                        } else {
                            System.out.print("---------------else-----------");
//                            Rect tagSize = new Rect(0, 0, 0, 0);
                            String name = "Unknown Cancer";
                            tv_cancerName.setText(name);
                            tv_Pro.setText("0%");

                            screeview.setImageBitmap(outputbitmap);
//                            screeview.setImageBitmap(resize);
                        }
                    }

                    @Override
                    public void onFailure() {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);
            screeview.setImageBitmap(bitmap);
        } else {
            System.out.println(requestCode + "REquescode------");
        }
    }


}