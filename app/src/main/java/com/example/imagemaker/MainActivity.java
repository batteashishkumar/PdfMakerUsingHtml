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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

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
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);

                }





            }
        });

    }

    private void createPdf(String sometext){

        PdfWriter pdfWriter = null;

        try {
//            InputStream is = getAssets().open("sample.html");
//            InputStreamReader fis =  new InputStreamReader(is);
            String exampleString="<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <style>\n" +
                    "html,body{\n" +
                    "    width: 100%;\n" +
                    "    height: 100%;\n" +
                    "    margin: 0; /* Space from this element (entire page) and others*/\n" +
                    "    padding: 0; /*space from content and border*/\n" +
                    "    border: solid black;\n" +
                    "    border-width: thin;\n" +
                    "    overflow:hidden;\n" +
                    "    display:block;\n" +
                    "    box-sizing: border-box;\n" +
                    "}\n" +
                    "table {\n" +
                    "  font-family: arial, sans-serif;\n" +
                    "  border-collapse: collapse;\n" +
                    "  width: 100%;\n" +
                    "}\n" +
                    "\n" +
                    "td, th {\n" +
                    "  border: 1px solid #dddddd;\n" +
                    "  text-align: left;\n" +
                    "  padding: 8px;\n" +
                    "}\n" +
                    "\n" +
                    "tr:nth-child(even) {\n" +
                    "  background-color: #dddddd;\n" +
                    "}\n" +
                    "</style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\n" +
                    "<h2>HTML Table</h2>\n" +
                    "\n" +
                    "<table>\n" +
                    "    <tr>\n" +
                    "        <th>Company</th>\n" +
                    "        <th>Contact</th>\n" +
                    "        <th>Country LABEL</th>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "        <td>Alfreds Futterkiste</td>\n" +
                    "        <td>Maria Anders</td>\n" +
                    "        <td>Germany</td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "        <td>Centro comercial Moctezuma</td>\n" +
                    "        <td>Francisco Chang</td>\n" +
                    "        <td>Mexico</td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "        <td>Ernst Handel</td>\n" +
                    "        <td>Roland Mendel</td>\n" +
                    "        <td>Austria</td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "        <td>Island Trading</td>\n" +
                    "        <td>Helen Bennett</td>\n" +
                    "        <td>UK</td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "        <td>Laughing Bacchus Winecellars</td>\n" +
                    "        <td>Yoshi Tannamuri</td>\n" +
                    "        <td>Canada</td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "        <td>Magazzini Alimentari Riuniti</td>\n" +
                    "        <td>Giovanni Rovelli</td>\n" +
                    "        <td>Italy</td>\n" +
                    "    </tr>\n" +
                    "</table>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>\n";






            InputStream stream = new ByteArrayInputStream(exampleString.getBytes(StandardCharsets.UTF_8));
            InputStreamReader fis =  new InputStreamReader(stream);
            String fpath = "/sdcard/" + "ash" + ".pdf";
            File file = new File(fpath);
            Document document = new Document();

            pdfWriter=PdfWriter.getInstance(document, new FileOutputStream(file.getAbsoluteFile()));
            document.open();
            document.addAuthor("betterThanZero");
            document.addCreationDate();
            document.addProducer();
//            document.addCreator("MySampleCode.com");
//            document.addTitle("Demo for iText XMLWorker");
            document.setPageSize(PageSize.A4);
//            document.add(new Paragraph("Sigueme en Twitter!Sigueme en Twitter!Sigueme en Twitter!Sigueme en Twitter!Sigueme en Twitter!Sigueme en Twitter!Sigueme en Twitter!Sigueme en Twitter!Sigueme en Twitter!Sigueme en Twitter!Sigueme en Twitter!Sigueme en Twitter!Sigueme en Twitter!Sigueme en Twitter!Sigueme en Twitter!Sigueme en Twitter!Sigueme en Twitter!"));

//            document.add(new Paragraph("@DavidHackro"));

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
