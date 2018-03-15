package com.example.weibinwang.myapplication.Model.DatabaseService;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.weibinwang.myapplication.Bean.Restaurant;
import com.example.weibinwang.myapplication.Bean.VoteDej;
import com.example.weibinwang.myapplication.Model.DatabaseHandler.DBHelper;
import com.example.weibinwang.myapplication.Model.DatabaseOutils.VoteDejOpe;

import java.util.List;

/**
 * Created by Eliass on 28/02/2018.
 */

public class VoteDejService implements VoteDejOpe {
    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private ContentValues values;

    public VoteDejService (DBHelper dbHelper){this.dbHelper = dbHelper;}

    @Override
    public long addVoteDej(VoteDej votedej) {
        database = dbHelper.getWritableDatabase();
        values = new ContentValues();
        values.put(DBHelper.COLNUM_VDTYPE, votedej.getType().toString());
        values.put(DBHelper.COLNUM_VDOWNER, votedej.getOwner().getId());
        values.put(DBHelper.COLNUM_VDPURPOSE,votedej.getPurpose().getId());
        long id = database.insert(DBHelper.TABLE_VOTEDEJ_NAME, null, values);
        database.close();
        return id;
    }

    @Override
    public void updateVoteDej(VoteDej votedej) {
        database = dbHelper.getWritableDatabase();
        values = new ContentValues();
        values.put(DBHelper.COLNUM_VDTYPE, votedej.getType().toString());
        values.put(DBHelper.COLNUM_VDOWNER, votedej.getOwner().getId());
        values.put(DBHelper.COLNUM_VDPURPOSE,votedej.getPurpose().getId());
        database.update(DBHelper.TABLE_VOTEDEJ_NAME,values,DBHelper.VOTEDEJ_ID + "= ?", new String[]{String.valueOf(votedej.getId())});
        database.close();
    }
    /*
        * delete votedej by name from database
        * @param name of votedej
        * */
    @Override
    public void deleteVoteDej(long id) {
        database = dbHelper.getWritableDatabase();
        database.delete(DBHelper.TABLE_VOTEDEJ_NAME, DBHelper.VOTEDEJ_ID + " = ?", new String[]{String.valueOf(id)});
        database.close();

    }
    /*
        * Update votedej information
        * @param votedej
        * */
    @Override
    public VoteDej queryVoteDejById(long id) {
        database = dbHelper.getWritableDatabase();
        String sql = "select * from "+DBHelper.TABLE_VOTEDEJ_NAME+" where "+DBHelper.VOTEDEJ_ID + " = ?";
        Cursor cursor = database.rawQuery(sql, new String[](id));

        VoteDej votedej =  null;
        while(cursor.moveToNext()){
            votedej = new VoteDej();
            votedej.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.VOTEDEJ_ID)));
            votedej.setOwner(cursor.getString(cursor.getColumnIndex(DBHelper.COLNUM_VDOWNER)));
            votedej.setPurpose(cursor.getString(cursor.getColumnIndex(DBHelper.COLNUM_RADDRESS)));
            votedej.setType(tranformStringToType(cursor.getString(cursor.getColumnIndex(DBHelper.COLNUM_RTYPE))) );
        }
        return null;
    }

    @Override
    public List<VoteDej> queryVoteDejByPurpose(String restaurantName) {
        return null;
    }
}
