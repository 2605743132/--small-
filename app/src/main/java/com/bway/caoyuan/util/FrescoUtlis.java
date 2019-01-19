package com.bway.caoyuan.util;


import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * Created by 小哥 on 2018/11/27.
 */

public class FrescoUtlis {
    /**
     * 基础加载图片
     * simpleDraweeView 控件
     * url 图片路径
     */
    public static void setTu(String url, SimpleDraweeView simpleDraweeView) {
        Uri uri = Uri.parse(url);
        simpleDraweeView.setImageURI(uri);
    }

    /**
     * 渐进式加载图片
     * url 图片路径
     * simpleDraweeView 控件
     */
    public static void setJianJin(String url, SimpleDraweeView simpleDraweeView) {
        Uri uri = Uri.parse(url);
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build();
        AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(simpleDraweeView.getController())
                .build();
        simpleDraweeView.setController(controller);
    }

    /**
     * 圆角图片
     * url 图片路径
     * simpleDraweeView 控件
     * radius 角度
     * color 描边线颜色
     * width 描边线宽度
     */
    public static void setYuanJiao(String url, SimpleDraweeView simpleDraweeView, float radius, int color, float width) {
        Uri uri = Uri.parse(url);
        RoundingParams roundingParams = RoundingParams.fromCornersRadius(0f);
        if (width > 0f) {
            roundingParams.setBorder(color, width);//描边线
        }
        roundingParams.setCornersRadius(radius);//总体圆角
        simpleDraweeView.getHierarchy().setRoundingParams(roundingParams);
        simpleDraweeView.setImageURI(uri);
    }

    /**
     * 圆形图片
     * url 图片路径
     * simpleDraweeView 控件
     * color 描边颜色
     * width 描边宽度
     */
    public static void setYuanQuan(String url, SimpleDraweeView simpleDraweeView, int color, float width) {
        if (url == null) {
            simpleDraweeView.setImageURI(url);
            return;
        }
        Uri uri = Uri.parse(url);
        RoundingParams roundingParams = RoundingParams.fromCornersRadius(0f);
        if (width > 0f) {
            roundingParams.setBorder(color, width);//描边线
        }
        roundingParams.setRoundAsCircle(true);//圆形
        simpleDraweeView.getHierarchy().setRoundingParams(roundingParams);
        simpleDraweeView.setImageURI(uri);
    }

    /**
     * Gif动态图片
     * url 图片路径
     * simpleDraweeView 控件
     */
    public static void setDongTu(String url, SimpleDraweeView simpleDraweeView) {
        Uri uri = Uri.parse(url);
        AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setTapToRetryEnabled(true)
                .setAutoPlayAnimations(true)
                .setOldController(simpleDraweeView.getController())
                .build();
        simpleDraweeView.setController(controller);
    }
}