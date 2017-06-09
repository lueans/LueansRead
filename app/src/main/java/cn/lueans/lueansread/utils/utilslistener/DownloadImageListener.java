package cn.lueans.lueansread.utils.utilslistener;

import java.io.File;

/**
 * Created by 24277 on 2017/3/29.
 * 下载图片监听接口
 */

public interface DownloadImageListener {
    void onSuccess();
    void onProgress(int progress, File positionFile);
    void onfial(Exception e);
}
