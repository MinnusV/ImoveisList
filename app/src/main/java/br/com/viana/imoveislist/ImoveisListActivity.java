package br.com.viana.imoveislist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import br.com.viana.imoveislist.model.Imovel;

public class ImoveisListActivity extends AppCompatActivity {

    ListView imoveisListView;
    Imovel imovel;

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
                //intentImovelInsert.putExtra("imovel", imovel);
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

    }
}
