package br.com.viana.imoveislist.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.viana.imoveislist.R;
import br.com.viana.imoveislist.model.Imovel;

/**
 * Created by Vinicius Viana on 01/05/2017.
 */

public class ImovelAdapter extends BaseAdapter {
    private final Context context;
    private final List<Imovel> imovels;

    public ImovelAdapter(Context context, List<Imovel> imovels) {
        this.context = context;
        this.imovels = imovels;
    }

    @Override
    public int getCount() {
        return this.imovels.size();
    }

    @Override
    public Object getItem(int position) {
        return this.imovels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.imovels.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Imovel imovel = new Imovel();

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = convertView;
        if(view ==  null){
            view = inflater.inflate(R.layout.list_imoveis_item, parent, false);
        }

        TextView imovelName = (TextView) view.findViewById(R.id.imovelList_textViewName);
        imovelName.setText(imovel.getName());

        TextView imovelPrice = (TextView) view.findViewById(R.id.imovelList_textViewPrice);
        imovelPrice.setText(imovel.getPrice());

        ImageView imovelPhoto = (ImageView) view.findViewById(R.id.imovelList_imageViewPhoto);
        if (imovel.getPhoto() != null){
            Bitmap bitmap = BitmapFactory.decodeFile(imovel.getPhoto());
            Bitmap bitmapReduce = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            imovelPhoto.setScaleType(ImageView.ScaleType.FIT_XY);
            imovelPhoto.setImageBitmap(bitmapReduce);
        }

        return view;
    }
}
