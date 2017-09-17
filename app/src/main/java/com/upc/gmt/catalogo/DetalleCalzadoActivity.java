package com.upc.gmt.catalogo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.upc.gmt.comercialgb.R;

import java.io.ByteArrayOutputStream;

public class DetalleCalzadoActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_calzado);

        LinearLayout lyDetalleCalzado = (LinearLayout) findViewById(R.id.lyDetalleCalzado);
        for (int i = 0; i <= 4; i++) {
            imageView = new ImageView(this);
            imageView.setId(i);
            imageView.setClickable(true);
            imageView.setAdjustViewBounds(true);
            imageView.setPadding(2, 2, 2, 2);
            if(i>3){
                imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.calzado_verde));
            }else if(i>1){
                imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.calzado_amarillo));
            }else{
                imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.calzado_rojo));
            }
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setTag(imageView.getDrawable());
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    int id = v.getId();
//                    ImageView iv = (ImageView) v.findViewById(id);
//                    imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
//                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    Intent i = new Intent(getApplicationContext(), FullScreenImageActivity.class);
                    BitmapDrawable d = (BitmapDrawable) v.getTag();
                    Bitmap bmp = d.getBitmap();
//                    Bitmap bmp = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    i.putExtra("nombre", "Calzado Demo "+byteArray.length);
                    i.putExtra("imagen", byteArray);
                    startActivity(i);
                }
            });

            lyDetalleCalzado.addView(imageView);
        }

//        lyDetalleCalzado.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int id = v.getId();
//                ImageView iv = (ImageView) v.findViewById(R.id);
//                iv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
//                iv.setScaleType(ImageView.ScaleType.FIT_XY);
//            }
//        });

    }
}
