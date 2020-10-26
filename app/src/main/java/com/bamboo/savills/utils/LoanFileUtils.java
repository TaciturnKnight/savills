package com.bamboo.savills.utils;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.util.Arrays;

import androidx.core.content.FileProvider;

/**
 * Created by tong on 2020/1/8.
 */
public class LoanFileUtils {
    public static final String FILETYPE_PICTURE = ".jpg";
    public static final String FILETYPE_VIDEO = ".mp4";
    public static final String[] pics = new String[]{"jpg", "png", "JPEG", "gif", "GIF"};
    public static final String[] videos = new String[]{"mp4", "avi", "3gp"};
    public static final String[] words = new String[]{"doc", "docx"};
    public static final String[] excels = new String[]{"xls", "xlsx"};
    public static final String[] pdfs = new String[]{"pdf", "ppt", "pptx"};
    public static final String[] txts = new String[]{"txt", "TXT"};
    public static final String[] rars = new String[]{"rar", "zip"};
    public static String currentUserCode;

    public static Uri fileToUri(Context mContext, File file) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //7.0以后
            return FileProvider.getUriForFile(mContext, mContext.getPackageName() + ".fileprovider", file);
        } else {
            return Uri.fromFile(file);
        }
    }

    public static String uri2File(Activity mContext, Uri uri) {
        if (mContext == null || uri == null)
            return null;
        if (DocumentsContract.isDocumentUri(mContext, uri)) {
            if (isExternalStorageDocument(uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(uri)) {
                String id = DocumentsContract.getDocumentId(uri);
                Log.i("文件", "文件id为" + id);
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(mContext, contentUri, null, null);
            } else if (isMediaDocument(uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                String[] split = docId.split(":");
                String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(mContext, contentUri, selection, selectionArgs);
            }
        } // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();
            String path = getDataColumn(mContext, uri, null, null);
            Log.i("文件", "文件路径为" + path);
            return path;
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public static File createNewFile(Context mContext, String fileType) {
        File fileDir = mContext.getExternalFilesDir("savills");
        String picName = String.valueOf(System.currentTimeMillis());
        try {
            File resultFile = File.createTempFile(picName, fileType, fileDir);
            if (!resultFile.getParentFile().exists()) {
                resultFile.getParentFile().mkdirs();
            }
            return resultFile;
        } catch (Exception e) {
            return null;
        }
    }

    public static File createNewFile(Context mContext) {
        File dir = mContext.getExternalFilesDir("download");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String fileName = String.valueOf(System.currentTimeMillis());
        File resultFile = new File(dir, fileName);
        return resultFile;
    }

    //    public String createFileName(){
//
//    }

    public static boolean isPic(String type) {
        return Arrays.asList(pics).contains(type);
    }

    public static boolean isVideo(String type) {
        return Arrays.asList(videos).contains(type);
    }

    public static boolean isPdf(String type) {
        return Arrays.asList(pdfs).contains(type);
    }

    public static boolean isWord(String type) {
        return Arrays.asList(words).contains(type);
    }

    public static boolean isExcel(String type) {
        return Arrays.asList(excels).contains(type);
    }

    public static boolean isTxt(String type) {
        return Arrays.asList(txts).contains(type);
    }

    public static boolean isRar(String type) {
        return Arrays.asList(rars).contains(type);
    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        String column = MediaStore.Images.Media.DATA;
        String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
}