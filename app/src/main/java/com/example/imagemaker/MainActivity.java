package com.example.imagemaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
EditText et;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et=findViewById(R.id.et);
        btn=findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    createPdf(et.getText().toString());
                } else {
                    // Request permission from the user
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);

                }





            }
        });

    }

    private void createPdf(String sometext){
//        PdfDocument document = new PdfDocument();
//        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();
//        PdfDocument.Page page = document.startPage(pageInfo);
//
//        Canvas canvas = page.getCanvas();
//        Paint paint = new Paint();
//        paint.setColor(Color.BLACK);
//        canvas.drawText(sometext,10, 25, paint);
//        canvas.drawText(sometext,15, 50, paint);
//        Paint myPaint = new Paint();
//        myPaint.setColor(Color.rgb(0, 0, 0));
//        myPaint.setStrokeWidth(1);
//        myPaint.setStyle(Paint.Style.STROKE);
//        canvas.drawRect(10, 10, 10, 10, myPaint);
////        Bitmap icon = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.icon);
////        Bitmap mBitmap = Bitmap.createScaledBitmap(icon,300, 100,true);
////        canvas.drawBitmap(mBitmap,0,0,paint);
//        document.finishPage(page);
//        String directory_path = Environment.getExternalStorageDirectory().getPath() + "/mypdf/";
//        File file = new File(directory_path);
//        String targetPdf = directory_path+"test-2.pdf";
//        File filePath = new File(targetPdf);



//        if (!file.exists()) {
//            file.mkdirs();
//        }

//        try {
//            document.writeTo(new FileOutputStream(filePath));
//            Toast.makeText(this, "Done", Toast.LENGTH_LONG).show();
//        } catch (IOException e) {
//            Log.e("main", "error "+e.toString());
//            Toast.makeText(this, "Something wrong: " + e.toString(),  Toast.LENGTH_LONG).show();
//        }
//        // close the document
//        document.close();

        PdfWriter pdfWriter = null;

        try {
            InputStream is = getAssets().open("sample.html");
            InputStreamReader fis =  new InputStreamReader(is);




            String fpath = "/sdcard/" + "ash" + ".pdf";
            File file = new File(fpath);
            Document document = new Document();

            pdfWriter=PdfWriter.getInstance(document, new FileOutputStream(file.getAbsoluteFile()));
            document.open();
            document.addAuthor("betterThanZero");
            document.addCreationDate();
            document.addProducer();
            document.addCreator("MySampleCode.com");
            document.addTitle("Demo for iText XMLWorker");
            document.setPageSize(PageSize.A4);
            document.add(new Paragraph("Sigueme en Twitter!Sigueme en Twitter!Sigueme en Twitter!Sigueme en Twitter!Sigueme en Twitter!Sigueme en Twitter!Sigueme en Twitter!Sigueme en Twitter!Sigueme en Twitter!Sigueme en Twitter!Sigueme en Twitter!Sigueme en Twitter!Sigueme en Twitter!Sigueme en Twitter!Sigueme en Twitter!Sigueme en Twitter!Sigueme en Twitter!"));

            document.add(new Paragraph("@DavidHackro"));

            XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
            worker.parseXHtml(pdfWriter, document, fis);
            document.close();
            pdfWriter.close();
            Intent i=new Intent(this,Pdfviewer.class);
            startActivity(i);

        }
        catch (Exception e){
            e.printStackTrace();
        }











    }


}
