package br.com.viana.imoveislist.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.viana.imoveislist.model.Imovel;

/**
 * Created by Vinicius Viana on 01/05/2017.
 */

public class ImoveisDAO extends SQLiteOpenHelper {
    public ImoveisDAO(Context context) {
        super(context, "ImoveisList", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateTableImoveis =
                "CREATE TABLE Imoveis (" +
                "id INTEGER PRIMARY KEY," +
                "name TEXT NOT NULL," +
                "address TEXT NOT NULL," +
                "price TEXT NOT NULL," +
                "contact TEXT NOT NULL," +
                "note REAL NOT NULL," +
                "pathPhoto TEXT)";

        db.execSQL(sqlCreateTableImoveis);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 1:
                String sql_Version1 = "ALTER TABLE Imoveis ADD COLUMN pathPhoto TEXT";

                db.execSQL(sql_Version1);
                break;
        }
    }

    public void create(Imovel imovel){
        SQLiteDatabase database = getWritableDatabase();

        ContentValues imoveisVlues = getContentValues(imovel);

        database.insert("Imoveis", null, imoveisVlues);
    }

    public List<Imovel> read(){
        SQLiteDatabase database = getReadableDatabase();

        String sqlReadImoveis = "SELECT * FROM Imoveis";

        Cursor cursorReadImoveis = database.rawQuery(sqlReadImoveis, null);

        List<Imovel> imoveis = new ArrayList<Imovel>();
        while(cursorReadImoveis.moveToNext()){
            Imovel imovel = new Imovel();

            imovel.setId(cursorReadImoveis.getLong(cursorReadImoveis.getColumnIndex("id")));
            imovel.setName(cursorReadImoveis.getString(cursorReadImoveis.getColumnIndex("name")));
            imovel.setAddress(cursorReadImoveis.getString(cursorReadImoveis.getColumnIndex("address")));
            imovel.setPrice(cursorReadImoveis.getString(cursorReadImoveis.getColumnIndex("price")));
            imovel.setContact(cursorReadImoveis.getString(cursorReadImoveis.getColumnIndex("contact")));
            imovel.setNote(cursorReadImoveis.getDouble(cursorReadImoveis.getColumnIndex("note")));
            imovel.setPhoto(cursorReadImoveis.getString(cursorReadImoveis.getColumnIndex("pathPhoto")));

            imoveis.add(imovel);
        }

        cursorReadImoveis.close();

        return imoveis;
    }

    public void delete(Long id){
        SQLiteDatabase database = getWritableDatabase();

        String[] params = {id.toString()};
        database.delete("Imoveis", "id = ?", params);
    }

    public void update(Imovel imovel){
        SQLiteDatabase database = getWritableDatabase();

        ContentValues imoveisValues = getContentValues(imovel);

        String[] params = {imovel.getId().toString()};
        database.update("Imoveis", imoveisValues, "id = ?", params);
    }

    private ContentValues getContentValues(Imovel imovel) {
        ContentValues imoveisVlues = new ContentValues();

        imoveisVlues.put("name", imovel.getName());
        imoveisVlues.put("address", imovel.getAddress());
        imoveisVlues.put("price", imovel.getPrice());
        imoveisVlues.put("contact", imovel.getContact());
        imoveisVlues.put("note", imovel.getNote());
        imoveisVlues.put("note", imovel.getPhoto());

        return imoveisVlues;
    }
}
