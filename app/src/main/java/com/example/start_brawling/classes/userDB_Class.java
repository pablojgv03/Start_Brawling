package com.example.start_brawling.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class userDB_Class {
    Context c;
    User_Class u;
    ArrayList<User_Class> lista;
    SQLiteDatabase sql;
    String bd ="BDUsuarios";
    String tabla ="create table if not exists usuario ( id integer primary key autoincrement, usuario text, pass text, nombre text, ap text)";
    public userDB_Class(Context c){
        this.c = c;
        sql = c.openOrCreateDatabase(bd,c.MODE_PRIVATE, null);
        sql.execSQL(tabla);
        u=new User_Class();
    }
    public boolean insertUser(User_Class u){
        //I CHECK THAT THE USER DOES NOT EXIST
        if(buscar(u.getUserName())==0){
            ContentValues cv = new ContentValues();
            cv.put("usuario", u.getUserName());
            cv.put("pass", u.getPassword());
            cv.put("nombre", u.getName());
            cv.put("ap", u.getSurname());
            return (sql.insert("usuario", null, cv)>0);
        }else{ //IF IT EXIST I RETURN FALSE
            return false;
        }
    }
    //SEARCH FOR A USER WHIT THIS USERNAME
    public int buscar(String u){
        int x=0;
        lista=selectUsuarios();
        for(User_Class us:lista){
            if(us.getUserName().equals(u)){
                x++;
            }
        }
        //RETURN THE NUMBER OF USERS WITH THE WRITTEN NAME
        return x;
    }
    public ArrayList<User_Class> selectUsuarios(){
        ArrayList<User_Class> lista = new ArrayList<User_Class>();
        lista.clear();
        Cursor cr=sql.rawQuery("select * from usuario", null);
        if(cr!=null&&cr.moveToFirst()){
            do{
                User_Class u = new User_Class();
                u.setId(cr.getInt(0));
                u.setUserName(cr.getString(1));
                u.setPassword(cr.getString(2));
                u.setName(cr.getString(3));
                u.setSurname(cr.getString(4));
                lista.add(u);
            }while(cr.moveToNext());
        }
        return lista;
    }
    public int login(String u, String p){
        int a=0;
        Cursor cr=sql.rawQuery("select * from usuario", null);
        if(cr!=null&&cr.moveToFirst()){
            do{
                if(cr.getString(1).equals(u)&&cr.getString(2).equals(p)){
                    a++;
                }
            }while(cr.moveToNext());
        }
        return a;
    }
    public User_Class getUserByName(String u, String p){
        lista = selectUsuarios();
        for(User_Class us:lista){
            if(us.getUserName().equals(u)&&us.getPassword().equals(p)){
                return us;
            }
        }
        return null;
    }

    public User_Class getUserById(int id){
        lista = selectUsuarios();
        for(User_Class us:lista){
            if(us.getId()==id){
                return us;
            }
        }
        return null;
    }
}
