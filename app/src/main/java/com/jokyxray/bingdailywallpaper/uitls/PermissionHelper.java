package com.jokyxray.bingdailywallpaper.uitls;

import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.content.PermissionChecker;

public class PermissionHelper {
    public static boolean isGranted(Context context, String permission){
        return PermissionChecker.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }
}
