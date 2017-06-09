package cn.lueans.lueansread.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import cn.lueans.lueansread.utils.utilslistener.DownloadImageListener;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.observers.Observers;
import rx.schedulers.Schedulers;

/**
 * Created by 24277 on 2017/3/29.
 */

public class DownloadImageUtils {

    private static final String PROGRESS_KEY ="progress_key";
    private static final String FIlE_KEY ="file_key";

    public static void downloadImageList(final Context context, final String[] urls, final String title, final DownloadImageListener listener){
        Observable.create(new Observable.OnSubscribe<HashMap<String,Object>>() {
            @Override
            public void call(Subscriber<? super HashMap<String, Object>> subscriber) {
                Bitmap bitmap;
                File appDir;
                String fileName;
                File file;
                HashMap<String,Object> mMap = null;
                FileOutputStream fos = null;
                for (int i = 0; i < urls.length; i++) {
                    try {
                        bitmap = Glide.with(context)
                                .load(urls[i])
                                .asBitmap()
                                .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                                .get();
                        appDir = new File(Environment.getExternalStorageDirectory(),"lueansRead");
                        if (!appDir.exists()) {
                            appDir.mkdir();
                        }
                        fileName = title+System.currentTimeMillis()+ ".jpg";
                        file = new File(appDir, fileName);
                        fos = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                        fos.flush();
                        mMap = new HashMap<String, Object>();
                        mMap.put(PROGRESS_KEY,i*100/urls.length);
                        mMap.put(FIlE_KEY,file);
                        subscriber.onNext(mMap);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        if (fos != null){
                            try {
                                fos.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                subscriber.onCompleted();

            }
        })
                .subscribeOn(Schedulers.io()) //事件发送，即图片加载在IO线程
                .observeOn(AndroidSchedulers.mainThread())  //事件处理，即图片显示在UI线程
                .subscribe(new Observer<HashMap<String, Object>>() {
                    @Override
                    public void onCompleted() {
                        listener.onSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onfial(new Exception(e.toString()));
                    }

                    @Override
                    public void onNext(HashMap<String, Object> stringObjectHashMap) {
                        int progress = (int)stringObjectHashMap.get(PROGRESS_KEY);
                        File file = (File) stringObjectHashMap.get(FIlE_KEY);
                        //通知图库更新
                        Uri uri = Uri.fromFile(file);
                        Intent scannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
                        context.sendBroadcast(scannerIntent);
                        //通知界面
                        listener.onProgress(progress,file);
                    }
                });
    }

}
