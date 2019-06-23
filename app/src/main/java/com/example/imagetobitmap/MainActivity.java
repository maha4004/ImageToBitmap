package com.example.imagetobitmap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
ImageView imageView,bitmapb;

MainActivity activity;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView= (ImageView) findViewById(R.id.imageView);
        imageView.buildDrawingCache();
        bitmapb=(ImageView)findViewById(R.id.bitmap);
    //    bitmap.setImageBitmap(getBitmapFromDrawable(R.drawable.ic_launcher_background));
        //imageView.buildDrawingCache();
      //  Bitmap bitmap = imageView.getDrawingCache();
      //  imageView.setImageBitmap(bitmap);

        imageView.setDrawingCacheEnabled(true);
        imageView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        imageView.layout(0, 0,
                imageView.getMeasuredWidth(), imageView.getMeasuredHeight());
        imageView.buildDrawingCache(true);
        Bitmap bitmap = Bitmap.createBitmap(imageView.getDrawingCache());
        imageView.setDrawingCacheEnabled(false);
        bitmapb.setImageBitmap(bitmap);

        File file = createImageFile(this);
        if (file != null) {
            FileOutputStream fout;
            try {
                fout = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 70, fout);
                fout.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Uri uri=Uri.fromFile(file);
            TextView textView =(TextView)findViewById(R.id.textTst);
            textView.setText("jjjjkjkjj"+uri);
        }


    }



    public File createImageFile(MainActivity mainActivity) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File mFileTemp = null;
        String root= this.getDir("my_sub_dir", Context.MODE_PRIVATE).getAbsolutePath();
        File myDir = new File(root + "/Img");
        if(!myDir.exists()){
            myDir.mkdirs();
        }
        try {
            mFileTemp=File.createTempFile(imageFileName,".jpg",myDir.getAbsoluteFile());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return mFileTemp;
    }



}
