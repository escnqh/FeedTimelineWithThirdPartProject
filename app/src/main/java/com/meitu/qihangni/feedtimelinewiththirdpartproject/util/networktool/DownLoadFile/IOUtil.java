package com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.DownLoadFile;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author nqh 2018/7/30
 */
public class IOUtil {
    public static void closeAll(Closeable... closeables) {
        if (closeables == null) {
            return;
        }
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
