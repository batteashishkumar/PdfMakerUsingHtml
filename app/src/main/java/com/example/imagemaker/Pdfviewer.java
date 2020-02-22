package com.example.imagemaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;

import java.io.File;
import java.util.List;

public class Pdfviewer extends AppCompatActivity {
    public static final String SAMPLE_FILE = "ash.pdf"; //your file path
    PDFView pdfView;
    Integer pageNumber = 0;
    String pdfFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfviewer);
        pdfView= (PDFView)findViewById(R.id.pdfView);
//        displayFromAsset(SAMPLE_FILE);
        pdfView.fromAsset("sample.pdf").load();
        findViewById(R.id.btnshare).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File outputFile = new File(Environment.getExternalStoragePublicDirectory
                        (Environment.DIRECTORY_DOWNLOADS), "sample.pdf");


                Uri uri = FileProvider.getUriForFile(Pdfviewer.this, Pdfviewer.this.getPackageName() + ".provider", outputFile);

                Intent share = new Intent();
                share.setAction(Intent.ACTION_SEND);
                share.setType("application/pdf");
                share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                share.putExtra(Intent.EXTRA_STREAM, uri);
//                share.setPackage("com.whatsapp");

                startActivity(share);




//                File pdf = new File(path);
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_SEND);
//                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(pdf));
//                intent.setType("application/pdf");
//                QtNative.activity().startActivity(intent);
//




            }
        });





    }
//    private void displayFromAsset(String assetFileName) {
//        pdfFileName = assetFileName;
//
//        pdfView.fromAsset(SAMPLE_FILE)
//                .defaultPage(pageNumber)
//                .enableSwipe(true)
//                .swipeHorizontal(false)
////                .onPageChange(this)
//                .enableAnnotationRendering(true)
////                .onLoad(this)
//                .scrollHandle(new DefaultScrollHandle(this))
//                .load();
//    }


}
