package com.example.gethelpapp;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gethelpapp.db.data.SpecialistDao;
import com.example.gethelpapp.db.data.UserDao;
import com.example.gethelpapp.db.data.UserDataBase;
import com.example.gethelpapp.db.model.Specialist;
import com.example.gethelpapp.db.model.User;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class EditHelperActivity extends AppCompatActivity {

    TextView nameField,jobField,emailField,phoneField,addressField;
    Button saveButton;
    private SpecialistDao specialistDao;
    private static final int REQUEST_CODE_STORAGE_PERMISSION = 1;
    private static final int REQUEST_CODE_SELECT_IMAGE = 2;
    static int userId;
    static int specialistId;
    ImageView image;
    Uri selectedImageUri;
    String imagepath;
    Specialist specialist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_helper);
        Bundle extras = getIntent().getExtras();

        if(extras !=null){
            userId = extras.getInt("UserId");
            specialistId = extras.getInt("SpecialistId");
        }

        nameField = (EditText) findViewById(R.id.nameField);
        jobField = (EditText) findViewById(R.id.jobField);
        emailField = (EditText) findViewById(R.id.emailField);
        addressField = (EditText) findViewById(R.id.addressField);
        phoneField = (EditText) findViewById(R.id.phoneField);


        saveButton = (Button) findViewById(R.id.saveButton);
        specialistDao = Room.databaseBuilder(this, UserDataBase.class, "atabase.db").allowMainThreadQueries()
                .build().getSpecialistDao();
        specialist = specialistDao.getSpecialist(specialistId,userId);
        image = (ImageView) findViewById(R.id.helperImage);
        String path = specialist.getImage();
        File f=new File(path);
        Bitmap b = null;
        try {
            b = BitmapFactory.decodeStream(new FileInputStream(f));
            image.setImageBitmap(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        findViewById(R.id.changeProfileImage).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(
                        getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            EditHelperActivity.this,
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

                //    String email = emailField.getText().toString().trim();
                //   String password = registerPassword.getText().toString().trim();
                //  String passwordConf = registerConfirmPassword.getText().toString().trim();

                String name = nameField.getText().toString();
                String email = emailField.getText().toString().trim();
                String address = addressField.getText().toString().trim();
                String phone = phoneField.getText().toString().trim();
                String job = jobField.getText().toString().trim();
                String image = null;
                if(imagepath!=null) {
                    image = imagepath;
                }
                if(name.length()>1) {

                    specialist.setName(name);
                }
                if(job.length()>1) {

                    specialist.setJob(job);
                }
                if(email.length()>1) {

                    specialist.setEmail(email);
                }
                if(address.length()>1) {

                    specialist.setAddress(address);
                }
                if(phone.length()>1) {

                    specialist.setPhone(phone);
                }
                if(image!=null){
                    specialist.setImage(image);
                }


                Log.d("test","test");

                //Log.d("test",user.getEmail());
                specialistDao.update(specialist);
                //userDao.insert(user);
                setResult(RESULT_OK);
                finish();


            }
        });

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

    private void SelectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent,REQUEST_CODE_SELECT_IMAGE);

        }
        else{
            Intent intent1 = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent1,REQUEST_CODE_SELECT_IMAGE);
            //startActivityForResult(intent,REQUEST_CODE_SELECT_IMAGE);
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
                        image.setImageBitmap(bitmap);
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
            finish();
        }
        if(v.getId() == R.id.saveButton) {
            Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}