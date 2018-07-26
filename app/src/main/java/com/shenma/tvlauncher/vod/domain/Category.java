package com.shenma.tvlauncher.vod.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by mingyang.wu on 17/11/8.
 */

public class Category {

    public static final int MOVIE = 1;
    public static final int TVPLAY = 2;
    public static final int COMIC = 3;
    public static final int TVSHOW = 4;
    public static final int INDEX = 10;

    public static int CategoryFromString(String category) {
        switch (category.toUpperCase()) {
            case "MOVIE":
                return MOVIE;
            case "TVPLAY":
                return TVPLAY;
            case "COMIC":
                return COMIC;
            case "TVSHOW":
                return TVSHOW;
            default:
                return MOVIE;
        }
    }

    public static class CategoryExtent {
        public String cate;
        public String type;
        public String area;
        public String star;
        public String year;
        public String state;
        public String language;
        public String version;

    }

    public String list_id;
    public String list_name;
    public CategoryExtent list_extend;


    public HashMap<String, String> getExtendValues() {

//        Gson gson=new Gson();

//        CategoryExtent categoryExtent=new Gson().fromJson(list_extend,CategoryExtent.class);

        CategoryExtent categoryExtent = list_extend;

        String value = categoryExtent.cate;

        List<String> list = Arrays.asList(value.split("\r\n"));

        HashMap<String, String> categoryMap = new LinkedHashMap<>();
        for (String cateKV : list) {
            String[] cateArray = cateKV.split("=");

            categoryMap.put(cateArray[0], cateArray[1]);

        }

        return categoryMap;


    }

    public static int idFromAchor(String achor) {
        String[] params = achor.split("-");

        int cid = Category.MOVIE;
        for (int i = 0; i < params.length - 1; i++) {
            if (params[i].equals("cid")) {
                cid = Integer.valueOf(params[i + 1]);
                break;
            }
        }
        return cid;

    }


}
