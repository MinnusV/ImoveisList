package br.com.viana.imoveislist;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import br.com.viana.imoveislist.DAO.ImoveisDAO;
import br.com.viana.imoveislist.adapter.ImovelAdapter;
import br.com.viana.imoveislist.model.Imovel;

public class ImoveisListActivity extends AppCompatActivity {

    ListView imoveisListView;
    Imovel imovel;

    @Override
    protected void onResume() {
        super.onResume();
        buildImoveisList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imoveis_list);

        imoveisListView = (ListView) findViewById(R.id.imoveisList_ListViewImoveis);

        imoveisListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Imovel imovel = (Imovel) imoveisListView.getItemAtPosition(position);
                Intent intentImovelInsert = new Intent(ImoveisListActivity.this, ImoveisNewActivity.class);
                intentImovelInsert.putExtra("imovel", imovel);
                startActivity(intentImovelInsert);
            }
        });

        Button newButton = (Button) findViewById(R.id.imoveisInsert_buttonNew);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImovelNew = new Intent(ImoveisListActivity.this, ImoveisNewActivity.class);
                startActivity(intentImovelNew);
            }
        });

        registerForContextMenu(imoveisListView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo adapterMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
        imovel = (Imovel) imoveisListView.getItemAtPosition(adapterMenuInfo.position);
        final ImoveisDAO imoveisDAO = new ImoveisDAO(ImoveisListActivity.this);

        MenuItem deleteMenuItem = menu.add("Deletar");
        MenuItem locateMenuItem = menu.add("Localização");
        MenuItem callPhone = menu.add("Ligar");

        buildRemoveItem(imovel, imoveisDAO, deleteMenuItem);
        buildLocateImovel(imovel, locateMenuItem);
        buildCallPhone(callPhone);

    }

    private void buildCallPhone(final MenuItem callPhone) {
        callPhone.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ActivityCompat.requestPermissions(ImoveisListActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 123);

                return false;
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 123){
            if (ActivityCompat.checkSelfPermission(ImoveisListActivity.this, Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED){
                Intent callPhone = new Intent(Intent.ACTION_CALL);
                callPhone.setData(Uri.parse("tel:" + imovel.getContact()));
                startActivity(callPhone);
            }
        }
    }

    private void buildLocateImovel(Imovel imovel, MenuItem locateMenuItem) {
        Intent locate = new Intent(Intent.ACTION_VIEW);
        locate.setData(Uri.parse("geo:0,0?q=" + imovel.getAddress()));
        locateMenuItem.setIntent(locate);
    }

    private void buildRemoveItem(final Imovel imovel, final ImoveisDAO imoveisDAO, MenuItem deleteMenuItem) {
        deleteMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                imoveisDAO.delete(imovel.getId());
                imoveisDAO.close();

                buildImoveisList();

                return false;
            }
        });
    }

    private void buildImoveisList() {

        ImoveisDAO imoveisDAO = new ImoveisDAO(ImoveisListActivity.this);
        List<Imovel> imoveisList = imoveisDAO.read();
        imoveisDAO.close();

        ImovelAdapter imoveisListViewAdapter = new ImovelAdapter(this, imoveisList);
        imoveisListView.setAdapter(imoveisListViewAdapter);
    }
}
