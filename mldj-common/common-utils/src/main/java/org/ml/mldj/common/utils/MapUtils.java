package org.ml.mldj.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MapUtils {
    /**
     *
     * @param latitude  纬度
     * @param longitude 经度
     * @return 经度在前，纬度在后，经度和纬度用","分割，经纬度小数点后不得超过6位。格式为x1,y1|x2,y2|x3,y3
     */
    public static String PointToString(BigDecimal latitude, BigDecimal longitude) {
        StringBuilder sb = new StringBuilder();
        String latitudeStr = latitude.setScale(6, RoundingMode.DOWN).toPlainString();
        String longitudeStr = longitude.setScale(6, RoundingMode.DOWN).toPlainString();
        return sb.append(longitudeStr).append(",").append(latitudeStr).toString();
    }
}
