package com.example.gethelpapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gethelpapp.db.data.SpecialistDao;
import com.example.gethelpapp.db.data.UserDataBase;
import com.example.gethelpapp.db.model.Specialist;
import com.example.gethelpapp.db.model.User;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AddHelperActivity extends AppCompatActivity {
    Button saveButton;
    EditText emailField, phoneField, addressField, nameField, jobField;
    private SpecialistDao specialistDao;
    static int userId;
    private static final int REQUEST_CODE_STORAGE_PERMISSION = 1;
    private static final int REQUEST_CODE_SELECT_IMAGE = 2;
    private ImageView imageSelected;
    Uri selectedImageUri;
    String imagepath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_helper);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getInt("UserId");
        }
        imageSelected = findViewById(R.id.helperImage);
        Log.i("test", String.valueOf(userId));
        nameField = (EditText) findViewById(R.id.nameField1);
        emailField = (EditText) findViewById(R.id.emailField1);
        jobField = (EditText) findViewById(R.id.jobField1);
        addressField = (EditText) findViewById(R.id.addressField1);
        phoneField = (EditText) findViewById(R.id.phoneField1);

        saveButton = (Button) findViewById(R.id.addButton1);

        specialistDao = Room.databaseBuilder(this, UserDataBase.class, "atabase.db").allowMainThreadQueries().build().getSpecialistDao();


        findViewById(R.id.changeProfileImage).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(
                        getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            AddHelperActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE_STORAGE_PERMISSION

                    );

                } else {
                    SelectImage();
                }
            }
        });


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String userName = editTextUsername.getText().toString().trim();
                // String uri = "@drawable/placeholder_helper";

                String email = emailField.getText().toString().trim();
                String name = nameField.getText().toString().trim();
                String phone = phoneField.getText().toString().trim();
                String address = addressField.getText().toString().trim();
                String job = jobField.getText().toString().trim();
                String image = imagepath.toString();

                Specialist specialist = new Specialist(userId, image, name, phone, email, address, job);

                specialistDao.insert(specialist);
                Intent moveToMenu = new Intent(AddHelperActivity.this, MenuActivity.class);
                startActivity(moveToMenu);


            }

        });
    }


    public void SelectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);

        } else {
            Intent intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent1, REQUEST_CODE_SELECT_IMAGE);
            //startActivityForResult(intent,REQUEST_CODE_SELECT_IMAGE);
        }
    }

    private void insertIntPrivateStorage(String name, String path) throws IOException {
        FileOutputStream fos  = openFileOutput(name,MODE_APPEND);

        File file = new File(path);

        byte[] bytes = getBytesFromFile(file);

        fos.write(bytes);
        fos.close();
        imagepath = getFilesDir() +"/"+name;
        Toast.makeText(getApplicationContext(),"File saved in :"+ getFilesDir() + "/"+name,Toast.LENGTH_SHORT).show();

    }

    private byte[] getBytesFromFile(File file) throws IOException

    {
        byte[] data = FileUtils.readFileToByteArray(file);
        return data;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,@NonNull int[] grantResults){
            super.onRequestPermissionsResult(requestCode,permissions,grantResults);

            if(requestCode ==REQUEST_CODE_STORAGE_PERMISSION && grantResults.length > 0) {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SelectImage();
                }else{
                    Toast.makeText(this,"permission denied",Toast.LENGTH_SHORT).show();
                }
            }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == RESULT_OK){
            if(data !=null){
                selectedImageUri = data.getData();
                if(selectedImageUri !=null) {
                    try{
                        InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        imageSelected.setImageBitmap(bitmap);
                        String path = getRealPathFromURI(this,selectedImageUri);
                        String name = getFileName(selectedImageUri);
                        insertIntPrivateStorage(name,path);

                    }catch(Exception exception){
                        Toast.makeText(this,exception.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
    private String getFileName(Uri uri)
    {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    private String getRealPathFromURI(Context context, Uri uri)
    {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, proj, null, null,
                null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        return null;
    }
    public void changeActivity(View v) {
        if(v.getId() == R.id.appHeader) {
            Intent i = new Intent(this, MenuActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
        if(v.getId() == R.id.backButton) {
            Intent i = new Intent(this, MenuActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
        if(v.getId() == R.id.saveButton) {
            Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}