package br.com.viana.imoveislist.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;

import br.com.viana.imoveislist.R;
import br.com.viana.imoveislist.model.Imovel;

import static android.R.attr.checked;

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
    private RadioGroup statusImovel;
    private RadioButton btnAvenda, btnVendido;
    private Imovel imovel;

    public ImoveisListUtils(AppCompatActivity appCompatActivity) {
        txtNome = (EditText) appCompatActivity.findViewById(R.id.imoveisList_name);
        txtEndereco = (EditText) appCompatActivity.findViewById(R.id.imoveisList_address);
        txtPreco = (EditText) appCompatActivity.findViewById(R.id.imoveisList_price);
        txtContato = (EditText) appCompatActivity.findViewById(R.id.imoveisList_contact);
        ratingNote = (RatingBar) appCompatActivity.findViewById(R.id.imoveisList_note);
        image = (ImageView) appCompatActivity.findViewById(R.id.imoveisList_photo);
        statusImovel = (RadioGroup) appCompatActivity.findViewById(R.id.imoveisList_RadioGroup);
        btnAvenda = (RadioButton) appCompatActivity.findViewById(R.id.imoveisList_A_Venda);
        btnVendido = (RadioButton) appCompatActivity.findViewById(R.id.imoveisList_Vendido);
        imovel = new Imovel();
    }

    public Imovel buildImovelForInsert() throws Exception {

        if (txtNome.getText().toString().equals("")) {
            throw new Exception("Campo 'nome' obrigatório");
        }
        if (txtEndereco.getText().toString().equals("")) {
            throw new Exception("Campo 'endereço' obrigatório");
        }
        if (txtPreco.getText().toString().equals("")) {
            throw new Exception("Campo 'preço' obrigatório");
        }
        if (txtContato.getText().toString().equals("")) {
            throw new Exception("Campo 'contato' obrigatório");
        }
        if (btnAvenda.isChecked() == false && btnVendido.isChecked() == false){
            throw new Exception("Campo 'status' obrigatório");
        }

        String nome = txtNome.getText().toString();
        String endereco = txtEndereco.getText().toString();
        String preco = txtPreco.getText().toString();
        String contato = txtContato.getText().toString();
        Float nota = ratingNote.getRating();
        String pathPhoto = image.getTag().toString();
        String aVendaCheck, vendidoCheck;

        int idRadioButton = statusImovel.getCheckedRadioButtonId();

        if (idRadioButton == R.id.imoveisList_A_Venda) {
            aVendaCheck = "A Venda";
            this.imovel.setStatus(aVendaCheck);
        }else if (idRadioButton == R.id.imoveisList_Vendido) {
            vendidoCheck = "Vendido";
            this.imovel.setStatus(vendidoCheck);
        }

        this.imovel.setName(nome);
        this.imovel.setAddress(endereco);
        this.imovel.setPrice(preco);
        this.imovel.setContact(contato);
        this.imovel.setNote(Double.valueOf(nota));
        this.imovel.setPhoto(pathPhoto);

        return this.imovel;
    }

    public void buildEditImovel(Imovel editImovel) {
        txtNome.setText(editImovel.getName());
        txtEndereco.setText(editImovel.getAddress());
        txtPreco.setText(editImovel.getPrice());
        txtContato.setText(editImovel.getContact());
        ratingNote.setRating(editImovel.getNote().floatValue());

        if (editImovel.getPhoto() != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(editImovel.getPhoto());
            Bitmap bitmapReduce = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            image.setImageBitmap(bitmapReduce);
            image.setTag(editImovel.getPhoto());
        }

        if (btnAvenda.isChecked() || btnVendido.isChecked()){
            if (editImovel.getStatus().equals("A Venda")) {
                btnAvenda.setChecked(true);
            }
            if (editImovel.getStatus().equals("Vendido")) {
                btnVendido.setChecked(true);
            }
        }

        this.imovel = editImovel;
    }

}
