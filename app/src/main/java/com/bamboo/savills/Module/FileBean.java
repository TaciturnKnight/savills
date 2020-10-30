package com.bamboo.savills.Module;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import static com.bamboo.savills.adapter.FileListAdapter.getVideoThumb;

/**
 * Created by tong on 2020/10/30.
 */
public class FileBean {
    private String filePath;
    private boolean isPic;
    private Bitmap bitmap;
    public String getFilePath() {
        return filePath;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
        boolean isPic = filePath.endsWith("jpg")
                || filePath.endsWith("jpeg")
                || filePath.endsWith("JPEG")
                || filePath.endsWith("PNG")
                || filePath.endsWith("png");
        setPic(isPic);
        setBitmap(isPic ? BitmapFactory.decodeFile(filePath) : getVideoThumb(filePath));
    }

    public boolean isPic() {
        return isPic;
    }

    public void setPic(boolean pic) {
        isPic = pic;
    }
}
