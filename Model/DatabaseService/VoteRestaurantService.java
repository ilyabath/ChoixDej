package com.example.weibinwang.myapplication.Model.DatabaseService;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.weibinwang.myapplication.Bean.VoteDej;
import com.example.weibinwang.myapplication.Bean.VoteNote;
import com.example.weibinwang.myapplication.Model.DatabaseHandler.DBHelper;
import com.example.weibinwang.myapplication.Model.DatabaseOutils.VoteRestaurantOpe;

import java.util.List;

/**
 * Created by Eliass on 28/02/2018.
 */

public class VoteRestaurantService implements VoteRestaurantOpe {
    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private ContentValues values;

    public VoteRestaurantService (DBHelper dbHelper){this.dbHelper = dbHelper;}

    @Override
    public long addVoteNote(VoteNote votenote) {
        database = dbHelper.getWritableDatabase();
        values = new ContentValues();
        values.put(DBHelper.COLNUM_VNNOTE, votenote.getNote().toString());
        values.put(DBHelper.COLNUM_VNOWNER, votenote.getOwner().getId());
        values.put(DBHelper.COLNUM_VNPURPOSE,votenote.getPurpose().getId());
        long id = database.insert(DBHelper.TABLE_VOTENOTE_NAME, null, values);
        database.close();
        return id;
    }

    @Override
    public void updateVoteNote(VoteNote votenote) {
        database = dbHelper.getWritableDatabase();
        values = new ContentValues();
        values.put(DBHelper.COLNUM_VNNOTE, votenote.getType().toString());
        values.put(DBHelper.COLNUM_VNOWNER, votenote.getOwner().getId());
        values.put(DBHelper.COLNUM_VNPURPOSE,votenote.getPurpose().getId());
        database.update(DBHelper.TABLE_VOTENOTE_NAME,values,DBHelper.VOTENOTE_ID + "= ?", new String[]{String.valueOf(votenote.getId())});
        database.close();
    }
    /*
        * delete votedej by name from database
        * @param name of votedej
        * */
    @Override
    public void deleteVoteNote(long id) {
        database = dbHelper.getWritableDatabase();
        database.delete(DBHelper.TABLE_VOTENOTE_NAME, DBHelper.VOTENOTE_ID + " = ?", new String[]{String.valueOf(id)});
        database.close();

    }
    /*
        * Update votenote information
        * @param votenote
        * */
    @Override

    public VoteNote queryVoteNoteById(long id) {
        database = dbHelper.getWritableDatabase();
        String sql = "select * from "+DBHelper.TABLE_VOTENOTE_NAME+" where "+DBHelper.VOTENOTE_ID + " = ?";
        Cursor cursor = database.rawQuery(sql, new String[](id));

        VoteNote votenote =  null;
        while(cursor.moveToNext()){
            votenote = new VoteNote();
            votenote.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.VOTENOTE_ID)));
            votenote.setOwner(cursor.getString(cursor.getColumnIndex(DBHelper.COLNUM_VNOWNER)));
            votenote.setPurpose(cursor.getString(cursor.getColumnIndex(DBHelper.COLNUM_RADDRESS)));
            votenote.setType(tranformStringToType(cursor.getString(cursor.getColumnIndex(DBHelper.COLNUM_RTYPE))) );
        }
        return null;
    }

    @Override
    public List<VoteNote> queryVoteNoteByPurpose(String restaurantName) {
        return null;
    }
}
