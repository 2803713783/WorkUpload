package com.siyi.common.utils.file;

import java.util.HashMap;
import java.util.Map;

/**
 * 媒体类型工具类
 *
 * @author siyi
 */
public class MimeTypeUtils {
    public static final String IMAGE_PNG = "image/png";

    public static final String IMAGE_JPG = "image/jpg";

    public static final String IMAGE_JPEG = "image/jpeg";

    public static final String IMAGE_BMP = "image/bmp";

    public static final String IMAGE_GIF = "image/gif";

    public static final String[] IMAGE_EXTENSION = {"bmp", "gif", "jpg", "jpeg", "png", "ico"};

    public static final String[] FLASH_EXTENSION = {"swf", "flv"};

    public static final String[] CODE_EXTENSION = {"html", "htm", "css", "js", "ts", "vue", "sql", "scss", "json", "gson"};
    public static final String[] MEDIA_EXTENSION = {"swf", "flv", "mp3", "wav", "wma", "wmv", "mid", "avi", "mpg",
            "asf", "rm", "rmvb"};

    public static final String[] VIDEO_EXTENSION = {"mp4", "avi", "rmvb"};

    public static final String[] DEFAULT_ALLOWED_EXTENSION = {
            //代码
            "html", "htm", "js", "ts", "css", "vue", "sql", "scss", "json", "gson",
            // 图片
            "bmp", "gif", "jpg", "jpeg", "png", "ico", "image/gif",
            // word excel powerpoint
            "doc", "docx", "xls", "xlsx", "ppt", "pptx", "html", "htm", "txt",
            // 压缩文件
            "rar", "zip", "gz", "bz2",
            // 视频格式
            "mp4", "avi", "rmvb",
            // pdf
            "pdf"};

    public static String getExtension(String prefix) {
        switch (prefix) {
            case IMAGE_PNG:
                return "png";
            case IMAGE_JPG:
                return "jpg";
            case IMAGE_JPEG:
                return "jpeg";
            case IMAGE_BMP:
                return "bmp";
            case IMAGE_GIF:
                return "gif";
            default:
                return "";
        }
    }

    public static final Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>() {{
        put("jpg", "image");
        put("pdf", "document");
        put("mp3", "audio");
        put("mp4", "video");
        put("html", "document");
        put("htm", "document");
        put("css", "document");
        put("js", "document");
        put("ts", "document");
        put("vue", "document");
        put("sql", "document");
        put("scss", "document");
        put("json", "document");
        put("gson", "document");
        // ...
    }};
}
