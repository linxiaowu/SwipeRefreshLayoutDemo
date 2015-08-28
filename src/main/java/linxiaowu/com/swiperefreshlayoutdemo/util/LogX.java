package linxiaowu.com.swiperefreshlayoutdemo.util;

import android.util.Log;


public class LogX extends Thread {
    /**
     * 日志名
     */
    @SuppressWarnings("unused")
    private static final String TAG = "== LogTrace ==";

    /**
     * 写入的文件名
     */
    private static final boolean logEnabled = true;

    public static void e(Object object, String err) {
        if (logEnabled) {
            if (err != null) {
                Log.e(getPureClassName(object), err);
            }

        }
    }

    public static void d(Object object, String debug) {
        if (logEnabled) {
            Log.d(getPureClassName(object), debug);
        }
    }

    public static void i(Object object, String info) {
        if (logEnabled) {
            Log.i(getPureClassName(object), info);
        }
    }

    public static void w(Object object, String info) {
        if (logEnabled) {
            Log.w(getPureClassName(object), info);
        }
    }

    /**
     * 可跳转的LOG日志
     *
     * @param object 日志发起类
     * @param tr     定位行异常
     */
    public static void jw(Object object, Throwable tr) {
        if (logEnabled) {
            Log.w(getPureClassName(object), "", filterThrowable(tr));
        }
    }

    /**
     * 可跳转的LOG日志
     *
     * @param object 日志发起类
     * @param tr     定位行异常
     */
    public static void je(Object object, Throwable tr) {
        if (logEnabled) {
            Log.e(getPureClassName(object), "", filterThrowable(tr));
        }
    }

    private static Throwable filterThrowable(Throwable tr) {
        StackTraceElement[] ste = tr.getStackTrace();
        tr.setStackTrace(new StackTraceElement[]{ste[0]});
        return tr;
    }

    private static String getPureClassName(Object object) {
        if (object == null) {
            return "pureClass";
            // Log.e(TAG, "getPureClassName() : object is null.");
        }
        String name = object.getClass().getName();
        if ("java.lang.String".equals(name)) {
            return object.toString();
        }
        int idx = name.lastIndexOf('.');
        if (idx > 0) {
            return name.substring(idx + 1);
        }
        return name;
    }
}

