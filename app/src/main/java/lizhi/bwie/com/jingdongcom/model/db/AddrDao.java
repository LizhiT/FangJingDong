package lizhi.bwie.com.jingdongcom.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Basic on 2018/3/28.
 */
public class AddrDao {
    private Context context;
    private String DB_NAME = "/aball360.db";//数据库的名字

    public AddrDao(Context context) {
        this.context = context;
    }

    /**
     * 讲assets目录下的db文件 赋值本应用的包下 并且返回db对象
     */
    public SQLiteDatabase initAddrDao() {
        File fileDir = new File(Environment.getExternalStorageDirectory(), "databases");
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }

        String dbPath = fileDir + DB_NAME;
        Log.e("TAG1", dbPath);
        if (!new File(dbPath).isFile()) {//当前手机内存不存在这个数据库...去赋值到内存中
            try {
                FileOutputStream out = new FileOutputStream(dbPath);
                InputStream in = context.getAssets().open("aball360.db");

                byte[] buffer = new byte[1024];
                int readBytes = 0;
                while ((readBytes = in.read(buffer)) != -1) {
                    out.write(buffer, 0, readBytes);
                }
                in.close();
                out.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return SQLiteDatabase.openOrCreateDatabase(dbPath, null);

    }

}
