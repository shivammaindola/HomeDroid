package com.example.sneha.googlesignin;

import android.content.Context;
import android.graphics.Typeface;

import java.lang.reflect.Field;

public class ReplaceFont {
    public static void replaceDefaultFont(Context context, String fontReplaced, String fontCustom){
        Typeface customTypeFace = Typeface.createFromAsset(context.getAssets(), fontCustom);
        replaceFont(fontReplaced, customTypeFace);
    }

    private static void replaceFont(String fontReplaced, Typeface customTypeFace) {
        try {
            Field myField = Typeface.class.getDeclaredField(fontReplaced);
            myField.setAccessible(true);
            myField.set(null, customTypeFace);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
