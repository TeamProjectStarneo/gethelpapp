package com.example.gethelpapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gethelpapp.db.data.InboxDao;
import com.example.gethelpapp.db.data.MessageDao;
import com.example.gethelpapp.db.data.SpecialistDao;
import com.example.gethelpapp.db.data.UserDataBase;
import com.example.gethelpapp.db.model.Inbox;
import com.example.gethelpapp.db.model.Messages;
import com.example.gethelpapp.db.model.Specialist;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MessageActivity extends AppCompatActivity {
    TextView helperNameView;
    EditText messageView;
    Button sendMessage;
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    static String name;

    InboxDao inboxDao;
    static int specialistid;
    static int userid;
    private RecyclerView messageRecyclerView;
    private MessageRecyclerAdapter messageRecyclerAdapter;
    String phone;
    List<Messages> messages = new ArrayList<>();
    Button uploadButton;
    public static final int TAKE_PHOTO = 1;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int REQUEST_CODE_STORAGE_PERMISSION = 3;
    private static final int REQUEST_CODE_SELECT_IMAGE = 4;
    private Uri imageUri;
    String phonefrom;
    Inbox inbox;
    String msgbody;
    String phonenumber;
    private String ipath;
    MessageDao messageDao;
    SpecialistDao specialistDao;
    int i = 1;
    File outputImage;
    SmsReceiver receiver = new SmsReceiver(){
        @Override
        public void onReceive(Context context,Intent intent){
            super.onReceive(context,intent);
            Log.i("THIS IS A RECIEVER MESSAGE",msgBody);
            Log.i("THIS IS A RECIEVER MESSAGE",msg_from);
            phonenumber = msg_from;
            msgbody = msgBody;
            receieveMessage();

        }
    };
    @Override
    protected void onResume(){
        super.onResume();
        registerReceiver(receiver,new IntentFilter(SMS_RECEIVED));
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(receiver);
    }
    String filename = "output_image";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Bundle extras = getIntent().getExtras();



        if (extras != null) {
            phone = extras.getString("Phone");
            name = extras.getString("Name");
            userid = extras.getInt("UserId");
            specialistid = extras.getInt("SpecialistId");
        }

        messageDao = Room.databaseBuilder(this, UserDataBase.class, "atabase.db").allowMainThreadQueries()
                .build().getMessageDao();
        specialistDao = Room.databaseBuilder(this, UserDataBase.class, "atabase.db").allowMainThreadQueries()
                .build().getSpecialistDao();
        inboxDao =Room.databaseBuilder(this, UserDataBase.class, "atabase.db").allowMainThreadQueries()
                .build().getInboxDao();
        try {
            inbox = inboxDao.getInbox(specialistid, userid);
            if(inbox==null){
                Specialist specialist = specialistDao.getSpecialist(specialistid,userid);

                inbox = new Inbox(userid,specialist.getJob(),specialist.getName(),specialistid);
                inboxDao.insert(inbox);
            }
        }catch(Exception e){

        }
        messages = messageDao.getMessages(specialistid, userid);
        messageRecyclerView = findViewById(R.id.message_recycler);
        messageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        messageRecyclerAdapter = new MessageRecyclerAdapter(this, messages);
        messageRecyclerView.setAdapter(messageRecyclerAdapter);
        Log.i("Permission check", phone);
        helperNameView = (TextView) findViewById(R.id.helperNameView);
        messageView = (EditText) findViewById(R.id.MessageField);
        uploadButton = (Button) findViewById(R.id.uploadButton);
        sendMessage = (Button) findViewById(R.id.sendButton);
        uploadButton.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MessageActivity.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.gallerybutton:
                                Intent intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                if (intent1.resolveActivity(getPackageManager()) != null) {
                                    startActivityForResult(intent1, REQUEST_CODE_SELECT_IMAGE);

                                } else {
                                    Intent intent2 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    startActivityForResult(intent2, REQUEST_CODE_SELECT_IMAGE);
                                    //startActivityForResult(intent,REQUEST_CODE_SELECT_IMAGE);
                                }
                                return true;

                            case R.id.camerabutton:
                                outputImage = new File(getExternalCacheDir(), filename + ".jpg");
                                while (outputImage.exists()) {
                                    outputImage = new File(getExternalCacheDir(), filename + i + ".jpg");
                                    i++;
                                }
                                try {
                                    if (outputImage.exists()) {
                                        outputImage.delete();
                                    }
                                    outputImage.createNewFile();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }


                                imageUri = FileProvider.getUriForFile(MessageActivity.this,
                                        "com.example.gethelpapp.FileProvider", outputImage);


                                //Open Camera activity
                                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                                startActivityForResult(intent, TAKE_PHOTO);

                            default:
                                return false;
                        }

                    }
                });
                popupMenu.show();

            }

        });
        sendMessage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
        helperNameView.setText(name);
    }






    public void receieveMessage(){
        messageDao = Room.databaseBuilder(this, UserDataBase.class, "atabase.db").allowMainThreadQueries()
                .build().getMessageDao();
        SpecialistDao specialistDao;
        specialistDao = Room.databaseBuilder(this, UserDataBase.class, "atabase.db").allowMainThreadQueries()
                .build().getSpecialistDao();
        Log.i("Phone number",phonenumber);
        int specialistId = specialistDao.getSpecialistIdFromPhone(phonenumber);
        Log.i("Specialistid from phone", String.valueOf(specialistId));
        String Message = messageView.getText().toString().trim();
        Messages message1 = new Messages(Global.userid,false,msgbody,specialistid);
        messageDao.insert(message1);
        Log.i("receivemessage sepcialistid", String.valueOf(specialistId));
        Log.i("receivemessage sepcialistid", String.valueOf(specialistid));
        messages = messageDao.getMessages(specialistid, userid);
        messageRecyclerAdapter.updateData(messages);
        messages.clear();
        messages.addAll(messageDao.getMessages(specialistid,userid));
        messageRecyclerAdapter = new MessageRecyclerAdapter(this,messages);
        messageRecyclerView.setAdapter(messageRecyclerAdapter);
        SmsManager smsManager = SmsManager.getDefault();
        Date todaysdate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(todaysdate);
        Log.i("Date",date);
        inbox.setLastmessage("Last Message Receieved: "+date);
        inboxDao.update(inbox);
        //messageRecyclerView.setAdapter(messageRecyclerAdapter);

    }

    public void sendMessage(){
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        String number= phone;
        if(permissionCheck== PackageManager.PERMISSION_GRANTED){

            MyMessage();


        }
        else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bm = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                       // bm = rotateImage(bm,90);
                        Drawable d = new BitmapDrawable(getResources(),bm);

                        Drawable d1 = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bm, 100, 100, true));
                        messageView.setCompoundDrawablesWithIntrinsicBounds(d1, null,null,null);
                        ipath = outputImage.getPath();
                        //Toast.makeText(this,"You dont have permission"+ipath,Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                break;
            case REQUEST_CODE_SELECT_IMAGE:
                if(resultCode == RESULT_OK){
                    if(data !=null){
                        imageUri = data.getData();
                        if(imageUri !=null) {
                            try{
                                Bitmap bm = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                                bm = rotateImage(bm,90);
                                Drawable d = new BitmapDrawable(getResources(),bm);

                                Drawable d1 = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bm, 100, 100, true));
                                messageView.setCompoundDrawablesWithIntrinsicBounds(d1, null,null,null);
                                ipath = outputImage.getPath();

                            }catch(Exception exception){
                                Toast.makeText(this,exception.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
                break;
            default:
                break;
        }
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
    private void SelectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);

        } else {
            Intent intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent1, REQUEST_CODE_SELECT_IMAGE);
            //startActivityForResult(intent,REQUEST_CODE_SELECT_IMAGE);
        }
    }

    public Bitmap rotateImage(Bitmap bitmap, float degree) {
        //create new matrix
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap bmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return bmp;
    }
    private void MyMessage() {





        messageDao = Room.databaseBuilder(this, UserDataBase.class, "atabase.db").allowMainThreadQueries()
                .build().getMessageDao();
        String Message = messageView.getText().toString().trim();
        Messages message = new Messages(userid,true,Message,specialistid);
        messageDao.insert(message);
        messages.clear();
        messages.addAll(messageDao.getMessages(specialistid,userid));
        messageRecyclerAdapter = new MessageRecyclerAdapter(this,messages);
        messageRecyclerView.setAdapter(messageRecyclerAdapter);
        SmsManager smsManager = SmsManager.getDefault();

        smsManager.sendTextMessage(phone,null,Message,null,null);
        Toast.makeText(this,Message,Toast.LENGTH_SHORT).show();
        if(imageUri !=null) {
            Intent smsIntent = new Intent(Intent.ACTION_SEND);
            smsIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
            smsIntent.setType("image/*");
            smsIntent.putExtra("address", phone);
            startActivity(smsIntent);
        }
        Specialist specialist;

        specialist = specialistDao.getSpecialist(specialistid,userid);
        Toast.makeText(this,specialist.getName(),Toast.LENGTH_SHORT).show();
        Date todaysdate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(todaysdate);
        Log.i("Date",date);
        inbox.setLastmessage("Last Message Receieved: "+date);
        inboxDao.update(inbox);
        //pecialist.setLastMessage(currentDate);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);

        switch(requestCode) {
            case 0:
                if(grantResults.length>=0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    MyMessage();
                }
                else{
                    Toast.makeText(this,"You dont have permission",Toast.LENGTH_SHORT).show();
                }
            case MY_CAMERA_PERMISSION_CODE:
                if (grantResults.length>=0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
                else
                {
                    Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
                }
            case REQUEST_CODE_STORAGE_PERMISSION:
            {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SelectImage();
                }else{
                    Toast.makeText(this,"permission denied",Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
    public void changeActivity(View v) {
        if(v.getId() == R.id.backButton) {
            finish();
        }
    }
}