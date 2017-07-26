package com.nightfury.pdfcreator;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;



import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;



import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button bt_pdf;

    String name, regdID, password, email, mobile, college, date, volunteerID, ieee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_pdf = (Button) findViewById(R.id.bt_pdf);
        password="i.fest2017"; name="ABCSDJK";email = "abcfh@gmail.com";mobile="892802789046";college="ABCFG";ieee = "1234567890";volunteerID="dgfhjkk";regdID="1234567890"; date="25/07/17";
        bt_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPdf();
            }
        });
    }

    private void createPdf() {
            Document doc = new Document();


            try {
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/i.fest.volunteer";

                File dir = new File(path);
                if (!dir.exists())
                    dir.mkdirs();

                Log.d("PDFCreator", "PDF Path: " + path);


                File file = new File(dir, "i.Fest_Registration_Deatils_2017.pdf");
                FileOutputStream fOut = new FileOutputStream(file);

                PdfWriter.getInstance(doc, fOut);

                //open the document
                doc.open();


                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Bitmap bitmap = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.ifestlogow);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                Image myImg = Image.getInstance(stream.toByteArray());
                // myImg.setAlignment(Image.ALIGN_LEFT);

                ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                Bitmap bitmap2 = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.ieeelogo);
                bitmap2.compress(Bitmap.CompressFormat.PNG, 100, stream2);
                Image myImg2 = Image.getInstance(stream2.toByteArray());
                // myImg2.setAlignment(Image.RIGHT);

                ByteArrayOutputStream stream3 = new ByteArrayOutputStream();
                Bitmap bitmap3 = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.ieeelogo);
                bitmap3.compress(Bitmap.CompressFormat.PNG, 100, stream3);
                Image myImg3 = Image.getInstance(stream3.toByteArray());
                // myImg2.setAlignment(Image.RIGHT);


                PdfPTable table = new PdfPTable(3);
                table.setSpacingAfter(10);
                table.setWidthPercentage(100);

                PdfPCell cell = new PdfPCell();
                Paragraph p = new Paragraph();
                p.add(new Chunk(myImg, 0, 0));
                p.setAlignment(Paragraph.ALIGN_LEFT | Paragraph.ALIGN_BOTTOM);
                cell.addElement(p);
                cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                table.addCell(cell);

                PdfPCell cell2 = new PdfPCell();
                Paragraph p2 = new Paragraph();
                p2.add(new Chunk(myImg2, 0, 0));
                p2.setAlignment(Paragraph.ALIGN_CENTER);
                cell2.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                cell2.addElement(p2);
                table.addCell(cell2);


                PdfPCell cell3 = new PdfPCell();
                Paragraph p3 = new Paragraph();
                p3.add(new Chunk(myImg3, 0, 0));
                p3.setAlignment(Paragraph.ALIGN_RIGHT);
                cell3.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                cell3.addElement(p2);
                table.addCell(cell3);

                doc.add(table);

                Font paraFont = new Font(Font.FontFamily.COURIER, 20, Font.BOLD, BaseColor.RED);
                Chunk p4Text = new Chunk("Hello "+name, paraFont);
                Paragraph p4 = new Paragraph(p4Text);
                p4.setAlignment(Paragraph.ALIGN_LEFT);
                doc.add(p4);

                paraFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL, BaseColor.BLACK);
                Chunk p5Text = new Chunk("\nWe are pleased to inform you that you have been successfully registered for i.Fest'17.", paraFont);
                Paragraph p5 = new Paragraph(p5Text);
                p5.setAlignment(Paragraph.ALIGN_LEFT);
                doc.add(p5);

                Phrase ph6 = new Phrase();
                ph6.add( new Chunk("Registration ID: ", new Font(Font.FontFamily.COURIER, 14, Font.BOLD, BaseColor.BLACK)) );
            ph6.add(new Chunk(regdID, new Font(Font.FontFamily.COURIER, 14, Font.NORMAL, BaseColor.BLUE)));
            doc.add(ph6);

            paraFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL, BaseColor.BLACK);
            Chunk p7Text = new Chunk("\nPlease login to our website and register for events of your interest.", paraFont);
            Paragraph p7 = new Paragraph(p7Text);
            p7.setAlignment(Paragraph.ALIGN_LEFT);
            doc.add(p7);

            Chunk chunk = new Chunk("ieee.daiict.ac.in/ifest17", new Font(Font.FontFamily.COURIER, 14, Font.UNDERLINE, BaseColor.BLUE));
            chunk.setAnchor("http://ieee.daiict.ac.in/ifest17");
            Phrase ph8 = new Phrase("Website: ");
            ph8.add(chunk);
            doc.add(ph8);

            paraFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL, BaseColor.BLACK);
            Chunk p9Text = new Chunk("\nYour Registration Details are as follows:", paraFont);
            Paragraph p9 = new Paragraph(p9Text);
            p9.setAlignment(Paragraph.ALIGN_LEFT);
            doc.add(p9);


            Phrase ph10 = new Phrase();
            ph10.add(new Chunk("Name: ", new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK )));
            ph10.add(new Chunk(name, new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD | Font.UNDERLINE, BaseColor.RED )));
            ph10.add(new Chunk("\nEmail: ", new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK )));
            ph10.add(new Chunk(email, new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD | Font.UNDERLINE, BaseColor.RED )));
            ph10.add(new Chunk("\nPhone Number: ", new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK )));
            ph10.add(new Chunk(mobile, new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD | Font.UNDERLINE, BaseColor.RED )));
            ph10.add(new Chunk("\nCollege: ", new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK )));
            ph10.add(new Chunk(college, new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD | Font.UNDERLINE, BaseColor.RED )));
            ph10.add(new Chunk("\nCollected By: ", new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK )));
            ph10.add(new Chunk(volunteerID, new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD | Font.UNDERLINE, BaseColor.RED )));
            ph10.add(new Chunk("\nIEEE Member: ", new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK )));
            ph10.add(new Chunk(ieee, new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD | Font.UNDERLINE, BaseColor.RED )));
            doc.add(ph10);



            Phrase ph11 = new Phrase();
            ph11.add( new Chunk("\n\nFor any queries conatct us at ", new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK)) );
            ph11.add(new Chunk("ifest17.daiict@gmail.com\n", new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL, BaseColor.BLUE)));
            doc.add(ph11);

            chunk = new Chunk("http://www.facebook.com/ifestdaiict", new Font(Font.FontFamily.COURIER, 14, Font.UNDERLINE, BaseColor.BLUE));
            chunk.setAnchor("http://www.facebook.com/ifestdaiict");
            Phrase ph12 = new Phrase();
            ph12.add(new Chunk("\nVisit us on Facebook at:   ", new Font(Font.FontFamily.TIMES_ROMAN, 14,Font.BOLD, BaseColor.BLACK)));
            ph12.add(chunk);
            doc.add(ph12);

            chunk = new Chunk("http://www.instagram.com/i.fest", new Font(Font.FontFamily.COURIER, 14, Font.UNDERLINE, BaseColor.BLUE));
            chunk.setAnchor("http://www.instagram.com/i.fest");
            Phrase ph13 = new Phrase();
            ph13.add(new Chunk("\nVisit us on Instagram at: ", new Font(Font.FontFamily.TIMES_ROMAN, 14,Font.BOLD, BaseColor.BLACK)));
            ph13.add(chunk);
            doc.add(ph13);


            paraFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL, BaseColor.BLACK);
            Chunk p14Text = new Chunk("\nGreetiing,\nTeam i.Fest'17.", paraFont);
            Paragraph p14 = new Paragraph(p14Text);
            p14.setAlignment(Paragraph.ALIGN_LEFT);
            doc.add(p14);


        } catch (DocumentException de) {
            Log.e("PDFCreator", "DocumentException:" + de);
        } catch (IOException e) {
            Log.e("PDFCreator", "ioException:" + e);
        } finally {
            doc.close();
        }
    }

}