package br.com.viana.imoveislist;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

import br.com.viana.imoveislist.model.Imovel;
import br.com.viana.imoveislist.utils.ImoveisListUtils;

public class ImoveisNewActivity extends AppCompatActivity {

    private ImoveisListUtils imoveisListUtils;
    String pathPhoto;
    private final static int CODE_PHOTO = 357;
    ImageView imageViewPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imoveis_new);

        imoveisListUtils = new ImoveisListUtils(ImoveisNewActivity.this);

        final Intent intent = getIntent();
        Imovel editImovel = (Imovel) intent.getSerializableExtra("imovel");

        if (editImovel != null){
            imoveisListUtils.buildEditImovel(editImovel);
        }

        Button btnPhoto = (Button) findViewById(R.id.imoveisList_buttonPhoto);
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCaptureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                pathPhoto = getExternalFilesDir(null) +  "/" +System.currentTimeMillis() + ".jpg";
                File filePhoto = new File(pathPhoto);
                intentCaptureImage.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(filePhoto));
                startActivityForResult(intentCaptureImage, CODE_PHOTO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CODE_PHOTO && resultCode == Activity.RESULT_OK){
            imageViewPhoto = (ImageView) findViewById(R.id.imoveisList_photo);
            Bitmap bitmap = BitmapFactory.decodeFile(pathPhoto);
            Bitmap bitmapReduce = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            imageViewPhoto.setScaleType(ImageView.ScaleType.FIT_XY);
            imageViewPhoto.setImageBitmap(bitmapReduce);
            imageViewPhoto.setTag(pathPhoto);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.this_menu_insert, menu);

        return super.onCreateOptionsMenu(menu);
    }
}
