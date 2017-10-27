package com.mobile.localdb.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseOperations {
    private static DatabaseOperations singleton;
    private SQLiteDatabase db;

    static {
        singleton = new DatabaseOperations();
    }

    public static DatabaseOperations getInstance() {
        return singleton;
    }

    public void init(SQLiteDatabase db) {
        this.db = db;
        this.db.execSQL("DROP TABLE IF EXISTS costs;"); // TODO: remove when finished
        this.db.execSQL("CREATE TABLE IF NOT EXISTS costs(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name CHAR(100) NOT NULL," +
                "is_asset INTEGER NOT NULL," +
                "amount REAL NOT NULL," +
                "version INTEGER);");
    }

    public void createCost(String name, boolean isAsset, double amount) {
        db.execSQL("INSERT INTO costs ('name', 'is_asset', 'amount', 'version') VALUES ('" +
                name + "', " +
                (isAsset ? 1 : 0) + ", " +
                String.valueOf(amount) + ", null);");
    }

    public void removeCost(int id) {
        db.execSQL("DELETE FROM costs WHERE id = " + String.valueOf(id) + ";");
    }

    public List<Cost> getData() {
        List<Cost> result = new ArrayList<Cost>();

        Cursor cursor = db.rawQuery("SELECT * FROM costs;", null);
        if (cursor.moveToFirst()) {
            do {
                Cost cost = new Cost(cursor.getInt(0), cursor.getString(1),
                        cursor.getInt(2) == 1, cursor.getDouble(3), cursor.getInt(4));
                result.add(cost);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return result;
    }
}
