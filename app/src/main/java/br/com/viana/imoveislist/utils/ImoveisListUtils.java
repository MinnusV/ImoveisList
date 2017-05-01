package br.com.viana.imoveislist.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import br.com.viana.imoveislist.R;
import br.com.viana.imoveislist.model.Imovel;

/**
 * Created by Vinicius Viana on 29/04/2017.
 */

public class ImoveisListUtils {

    private EditText txtNome;
    private EditText txtEndereco;
    private EditText txtPreco;
    private EditText txtContato;
    private RatingBar ratingNote;
    private ImageView image;
    private Imovel imovel;

    public ImoveisListUtils(AppCompatActivity appCompatActivity){
        txtNome = (EditText) appCompatActivity.findViewById(R.id.imoveisList_name);
        txtEndereco = (EditText) appCompatActivity.findViewById(R.id.imoveisList_address);
        txtPreco = (EditText) appCompatActivity.findViewById(R.id.imoveisList_price);
        txtContato = (EditText) appCompatActivity.findViewById(R.id.imoveisList_contact);
        ratingNote = (RatingBar) appCompatActivity.findViewById(R.id.imoveisList_note);
        image = (ImageView) appCompatActivity.findViewById(R.id.imoveisList_photo);
        imovel = new Imovel();
    }

    public Imovel buildImovelForInsert() throws Exception{

        if (txtNome.getText().toString().equals("")){
            throw new Exception("Campo nome obrigatório");
        }
        if (txtEndereco.getText().toString().equals("")){
            throw new Exception("Campo nome obrigatório");
        }
        if (txtPreco.getText().toString().equals("")){
            throw new Exception("Campo nome obrigatório");
        }
        if (txtContato.getText().toString().equals("")){
            throw new Exception("Campo nome obrigatório");
        }

        String nome = txtNome.getText().toString();
        String endereco = txtEndereco.getText().toString();
        String preco = txtPreco.getText().toString();
        String contato = txtContato.getText().toString();
        Float nota = ratingNote.getRating();
        String pathPhoto = image.getTag().toString();

        this.imovel.setName(nome);
        this.imovel.setAddress(endereco);
        this.imovel.setPrice(preco);
        this.imovel.setContact(contato);
        this.imovel.setNote(Double.valueOf(nota));
        this.imovel.setPhoto(pathPhoto);

        return this.imovel;
    }

    public void buildEditImovel(Imovel editImovel){
        txtNome.setText(editImovel.getName());
        txtEndereco.setText(editImovel.getAddress());
        txtPreco.setText(editImovel.getPrice());
        txtContato.setText(editImovel.getContact());
        ratingNote.setRating(editImovel.getNote().floatValue());

        if (editImovel.getPhoto() != null){
            Bitmap bitmap = BitmapFactory.decodeFile(editImovel.getPhoto());
            Bitmap bitmapReduce = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            image.setImageBitmap(bitmapReduce);
            image.setTag(editImovel.getPhoto());
        }

        this.imovel = editImovel;
    }

}
