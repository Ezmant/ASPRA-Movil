package com.example.aspra_app.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.aspra_app.models.Usuario;

public class UserDAO {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public UserDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    // Agregar un nuevo usuario
    public long addUser(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, usuario.getNombre());
        values.put(DatabaseHelper.COLUMN_EMAIL, usuario.getEmail());
        values.put(DatabaseHelper.COLUMN_PHONE, usuario.getPhone());
        values.put(DatabaseHelper.COLUMN_PASS, usuario.getPass());
        return database.insert(DatabaseHelper.TABLA_USUARIOS, null, values);
    }

    // Obtener un usuario por su DNI (o ID en tu caso)
    public Usuario getUsuarioporemail(String email) {
        Usuario usuario = null;
        Cursor cursor = database.query(DatabaseHelper.TABLA_USUARIOS, null, DatabaseHelper.COLUMN_EMAIL + " = ?",
                new String[]{email}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            usuario = cursorToUser(cursor);
        }

        if (cursor != null) {
            cursor.close();
        }

        return usuario;
    }

    // Actualizar un usuario
    public int modificarUsuario(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, usuario.getNombre());
        values.put(DatabaseHelper.COLUMN_EMAIL, usuario.getEmail());
        values.put(DatabaseHelper.COLUMN_PHONE, usuario.getPhone());
        values.put(DatabaseHelper.COLUMN_PASS, usuario.getPass());

        return database.update(DatabaseHelper.TABLA_USUARIOS, values,
                DatabaseHelper.COLUMN_EMAIL + " = ?", new String[]{usuario.getEmail()});
    }

    // Eliminar un usuario por su email
    public void borrarUsuario(String email) {
        database.delete(DatabaseHelper.TABLA_USUARIOS, DatabaseHelper.COLUMN_EMAIL + " = ?", new String[]{email});
    }

    private Usuario cursorToUser(Cursor cursor) {
        Usuario usuario = new Usuario();
        usuario.setNombre(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME)));
        usuario.setEmail(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EMAIL)));
        usuario.setPhone(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PHONE)));
        usuario.setPass(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PASS)));
        return usuario;
        }
    }