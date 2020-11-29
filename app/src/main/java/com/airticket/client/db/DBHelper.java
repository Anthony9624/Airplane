package com.airticket.client.db;

import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.widget.Toast;

import com.airticket.client.App;
import com.airticket.client.Constans;

import org.litepal.LitePal;

import java.util.List;

/**
 * class desc :
 *
 * @author :
 */
public class DBHelper {

    public static String DB_PATH = "";

    /**
     * 获取可读写的数据库
     */
    public SQLiteDatabase getWritableDatabase() {
        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }


    /**
     * 获取仅可读的数据库
     */
    public SQLiteDatabase getReadableDatabase() {
        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READONLY);
    }

    private static final String TAG = "DBHelper";

    //user表操作--------------------------------------------------------------------------------------

    public static long userFind(String token) {
        List<User> allSongs = LitePal.findAll(User.class);
        for (User user : allSongs) {
            if (user.getToken().equals(token)) {
                Constans.USER_NAME = user.getUsername();
                return user.getId();
            }
        }
        return 0;
    }

    public static User userFind(int id) {
        return LitePal.find(User.class, id);
    }

    public static String userLoginOrRegister(String account, String password) {
        String token = "";
        List<User> allSongs = LitePal.findAll(User.class);
        for (User user : allSongs) {
            if (user.getUsername().equals(account)) {
                if (user.getPassword().equals(password)) {
                    token = user.getToken();
                    Constans.USER_NAME = account;
                    return token;
                } else {
                    Toast.makeText(App.getContext(), "密码错误", Toast.LENGTH_SHORT).show();
                    return token;
                }
            }
        }
        if (TextUtils.isEmpty(token)) {
            User user = new User();
            user.setUsername(account);
            user.setPassword(password);
            token = String.valueOf((Math.random() * 9 + 1) * 100000);
            user.setToken(token);
            user.save();
        }
        Constans.USER_NAME = account;
        return token;
    }

    public static String userFindPassword(String account) {
        List<User> allSongs = LitePal.findAll(User.class);
        for (User user : allSongs) {
            if (user.getUsername().equals(account)) {
                return user.getPassword();
            }
        }
        return "";
    }
}
