package br.com.viana.imoveislist;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import br.com.viana.imoveislist.model.Imovel;

public class ImoveisListActivity extends AppCompatActivity {

    ListView imoveisListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imoveis_list);

        imoveisListView = (ListView) findViewById(R.id.imoveisList_ListViewImoveis);

        imoveisListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Imovel imovel = (Imovel) imoveisListView.getItemAtPosition(position);
                Intent intentImovelInsert = new Intent(ImoveisListActivity.this, ImoveisInsertActivity.class);
                //intentImovelInsert.putExtra("imovel", (Parcelable) imovel);
                startActivity(intentImovelInsert);
            }
        });

        Button newButton = (Button) findViewById(R.id.imoveisInsert_buttonNew);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImovelNew = new Intent(ImoveisListActivity.this, ImoveisInsertActivity.class);
                startActivity(intentImovelNew);
            }
        });

    }
}
