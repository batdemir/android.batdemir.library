package com.android.batdemir.library.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.text.HtmlCompat;

import com.android.batdemir.library.ui.ui_test.activities.MainActivity;

public class MyApplication extends Application {

    private static final String TAG = "Application Warning:\t";
    private static MyApplication myApplication = null;
    private ActivityLifecycleCallbacks activityLifecycleCallbacks;

    public static MyApplication getInstance() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (myApplication == null) myApplication = this;
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        activityLifecycleCallbacks = getActivityLifecycleCallbacks();
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
        Log.d(TAG, "Created");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d(TAG, "Low Memory");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
        Log.d(TAG, "Terminated");
    }

    private ActivityLifecycleCallbacks getActivityLifecycleCallbacks() {
        return new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                setExceptionHandler();
            }

            @Override
            public void onActivityStarted(Activity activity) {
                //Not implemented
            }

            @Override
            public void onActivityResumed(Activity activity) {
                //Not implemented
            }

            @Override
            public void onActivityPaused(Activity activity) {
                //Not implemented
            }

            @Override
            public void onActivityStopped(Activity activity) {
                //Not implemented
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                //Not implemented
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                //Not implemented
            }
        };
    }

    @SuppressLint("NewApi")
    private void setExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<div style=\"text-align: start\"");
            stringBuilder.append("<p>Son Yapılan İşlem Başarız Oldu</p>");
            stringBuilder.append("<p>Beklenmedik Hata Üzerine Kapatıldı.</p>");
            stringBuilder.append("<p>Lütfen Yöneticiniz İle Görüşünüz.</p>");
            stringBuilder.append("<p>Exception: ").append(e.getMessage()).append("</p>");
            for (int i = 0; i < e.getStackTrace().length; i++) {
                if (!e.getStackTrace()[i].isNativeMethod()) {
                    stringBuilder.append("<p>------------------------------------------------------------").append("</p>");
                    stringBuilder.append("<p>FileName: ").append(e.getStackTrace()[i].getFileName()).append("</p>");
                    stringBuilder.append("<p>MethodName: ").append(e.getStackTrace()[i].getMethodName()).append("</p>");
                    stringBuilder.append("<p>LineNumber: ").append(e.getStackTrace()[i].getLineNumber()).append("</p>");
                }
            }
            stringBuilder.append("</div>");

            Intent crashedIntent = new Intent(MyApplication.getInstance().getBaseContext(), MainActivity.class);
            crashedIntent.putExtra("CRASH_REPORT", Html.fromHtml(stringBuilder.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY).toString());
            crashedIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

            PendingIntent pendingIntent = PendingIntent.getActivity(MyApplication.getInstance().getBaseContext(), 0, crashedIntent, PendingIntent.FLAG_ONE_SHOT);
            AlarmManager alarmManager = (AlarmManager) MyApplication.getInstance().getBaseContext().getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 100, pendingIntent);

            System.exit(2);
        });
    }
}
