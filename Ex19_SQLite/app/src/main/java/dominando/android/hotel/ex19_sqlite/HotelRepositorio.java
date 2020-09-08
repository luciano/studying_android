package dominando.android.hotel.ex19_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Luciano on 20/10/2015.
 */
public class HotelRepositorio {

    private HotelSQLHelper helper;

    public HotelRepositorio(Context context) {
        helper = new HotelSQLHelper(context);
    }

    private long inserir(Hotel hotel) {

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(HotelSQLHelper.COLUNA_NOME , hotel.nome);
        contentValues.put(HotelSQLHelper.COLUNA_ENDERECO , hotel.endereco);
        contentValues.put(HotelSQLHelper.COLUNA_ESTRELAS , hotel.estrelas);

        long id = db.insert(HotelSQLHelper.TABELA_HOTEL, null, contentValues);

        if (id != -1)
            hotel.id = id;

        db.close();
        return id;
    }

    private int atualizar(Hotel hotel) {

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(HotelSQLHelper.COLUNA_NOME , hotel.nome);
        contentValues.put(HotelSQLHelper.COLUNA_ENDERECO , hotel.endereco);
        contentValues.put(HotelSQLHelper.COLUNA_ESTRELAS , hotel.estrelas);

        int linhasAfetadas = db.update(
                HotelSQLHelper.TABELA_HOTEL,
                contentValues,
                HotelSQLHelper.COLUNA_ID + " = ?",
                new String[]{String.valueOf(hotel.id)});
        db.close();
        return linhasAfetadas;
    }

    public void salvar(Hotel hotel) {
        if (hotel.id == 0)
            inserir(hotel);
        else
            atualizar(hotel);
    }

    public int excluir(Hotel hotel) {
        SQLiteDatabase db = helper.getWritableDatabase();

        int linhasAfetadas = db.delete(
                HotelSQLHelper.TABELA_HOTEL,
                HotelSQLHelper.COLUNA_ID + " = ?",
                new String[]{String.valueOf(hotel.id)}
        );

        db.close();
        return linhasAfetadas;
    }

    public List<Hotel> buscarHotel(String filtro) {

        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "SELECT * FROM " + HotelSQLHelper.TABELA_HOTEL;
        String[] argumentos = null;

        if (filtro != null) {
            sql += " WHERE " + HotelSQLHelper.COLUNA_NOME + " LIKE ?";
            argumentos = new String[]{filtro};
        }

        sql += " ORDER BY " + HotelSQLHelper.COLUNA_NOME;

        Cursor cursor = db.rawQuery(sql, argumentos);

        List<Hotel> hoteis = new ArrayList<>();

        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndex(HotelSQLHelper.COLUNA_ID));

            String nome = cursor.getString(
                    cursor.getColumnIndex(HotelSQLHelper.COLUNA_NOME));

            String endereco = cursor.getString(
                    cursor.getColumnIndex(HotelSQLHelper.COLUNA_ENDERECO));

            float estrelas = cursor.getFloat(
                    cursor.getColumnIndex(HotelSQLHelper.COLUNA_ESTRELAS));

            Hotel hotel = new Hotel(id, nome, endereco, estrelas);
            hoteis.add(hotel);
        }

        cursor.close();
        db.close();
        return hoteis;
    }
}
