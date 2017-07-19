package com.cily.utils.app.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;

import com.cily.utils.base.StrUtils;
import com.cily.utils.base.time.TimeUtils;
import com.cily.utils.log.L;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * user:cily
 * time:2017/2/23
 * desc:图片工具
 */

public class ImageTools {
    public static long FILE_MAX_SIZE = 204800;    //200K

    /**
     * 图片压缩，默认压缩到200K以内
     * @param path
     * @return
     */
    public File scal(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }

        String tempFilePath = createImageFile();
        if (TextUtils.isEmpty(tempFilePath)) {
            return null;
        }

        File outputFile = new File(path);
        long fileSize = outputFile.length();
        if (fileSize > FILE_MAX_SIZE) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);
            int width = options.outWidth;
            int height = options.outHeight;

            double scale = Math.sqrt((float) fileSize / FILE_MAX_SIZE);
            options.outWidth = (int) (width / scale);
            options.outHeight = (int) (height / scale);
            options.inSampleSize = (int) (scale + 0.5);
            options.inJustDecodeBounds = false;
            Bitmap bm = null;

            try {
                bm = BitmapFactory.decodeFile(path, options);
            } catch (OutOfMemoryError e) {
                L.printException(e);
            }

            outputFile = new File(tempFilePath);
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(outputFile);
                bm.compress(Bitmap.CompressFormat.JPEG, 80, fos);
            } catch (FileNotFoundException e) {
                L.printException(e);
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        L.printException(e);
                    }
                }
            }

            if (bm != null && !bm.isRecycled()) {
                bm.recycle();
            } else {
                File temp = outputFile;
                outputFile = new File(tempFilePath);
                copyFileUsingFileChannels(temp, outputFile);
            }

            fileSize = outputFile.length();
        }
        return outputFile;
    }

    private String createImageFile() {
        String timeStamp = TimeUtils.milToStr(System.currentTimeMillis(), "yyyyMMdd_HHmm");
        String imageFileName = StrUtils.join("image_", timeStamp, "_");
        File fileDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File img = null;
        try {
            img = File.createTempFile(imageFileName, ".jpg", fileDir);
            return img.getAbsolutePath();
        } catch (IOException e) {
            L.printException(e);
        }
        return null;
    }

    private void copyFileUsingFileChannels(File source, File dest) {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            try {
                inputChannel = new FileInputStream(source).getChannel();
                outputChannel = new FileOutputStream(dest).getChannel();
                outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
            } catch (IOException e) {
                L.printException(e);
            }
        } finally {
            try {
                inputChannel.close();
                outputChannel.close();
            } catch (IOException e) {
                L.printException(e);
            }
        }
    }
}
