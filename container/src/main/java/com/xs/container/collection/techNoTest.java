package com.xs.container.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class techNoTest {
    final static Logger logger = LoggerFactory.getLogger(techNoTest.class);

    public static void main(String[] args) {
        Long startTs = System.currentTimeMillis();
        List<Integer> a = getTechNoListByString("3,5,3,1-100000");
        logger.info("getTechNoListByString方法：个数[{}],,耗时[{}]",a.size(),System.currentTimeMillis() - startTs);

        startTs = System.currentTimeMillis();
        List<Integer> c = getTechNosByIinteger("3,5,3,1-100000");
        logger.info("getTechNosByIinteger方法：个数[{}],,耗时[{}]",c.size(),System.currentTimeMillis() - startTs);



        startTs = System.currentTimeMillis();
        List<Integer> b = getTechNosByStram("3,5,3,1-100000");
        logger.info("getTechNosByStram方法：个数[{}],,耗时[{}]",b.size(),System.currentTimeMillis() - startTs);
    }


    //获取样本号列表
    public static   List<Integer> getTechNoListByString(String techno) {

        ArrayList<Integer> techNoList = new ArrayList<>();
        if (isEmpty(techno)) {
            return techNoList;
        }
        String[] techNoArray = techno.replaceAll("，", ",").split(",");
        if (isEmpty(techNoArray)) {
            return techNoList;
        }
        HashSet<Integer> integerHashSet = new HashSet<>();
        int size = techNoArray.length;
        String techNoStr = "";
        for (int i = 0; i < size; i++) {
            if (containsIgnoreCase(techNoArray[i], "-")) {
                String[] itemArray = techNoArray[i].split("-");
                if (isEmpty(itemArray[0]) || isEmpty(itemArray[1])) {
                    continue;
                }
                int prePart = valueOfInt(itemArray[0]);
                int subPart = valueOfInt(itemArray[1]);
                for (int j = prePart; j < subPart; j++) {
                    if (techNoList.contains(j)) continue;
                    techNoList.add(j);
                }
            } else {
                if (techNoList.contains(techNoArray[i])) continue;
                techNoList.add(valueOfInteger(techNoArray[i]));
            }
        }
        return techNoList;
    }

    //获取样本号列表
    public static   List<Integer> getTechNosByIinteger(String techno) {
        ArrayList<Integer> techNoList = new ArrayList<>();
        String[] techNoStrs = new String[]{};
        if (isEmpty(techno)) {
            return techNoList;
        }
        String[] techNoArray = techno.replaceAll("，", ",").split(",");
        if (isEmpty(techNoArray)) {
            return techNoList;
        }
        int size = techNoArray.length;
        for (int i = 0; i < size; i++) {
            if (containsIgnoreCase(techNoArray[i], "-")) {
                String[] itemArray = techNoArray[i].split("-");
                if (isEmpty(itemArray[0]) || isEmpty(itemArray[1])) {
                    continue;
                }
                int prePart = valueOfInt(itemArray[0]);
                int subPart = valueOfInt(itemArray[1]);
                while(prePart<=subPart){
                    prePart++;
                    if (techNoList.contains(prePart)) continue;
                    techNoList.add(prePart);
                }
            } else {
                if (techNoList.contains(techNoArray[i])) continue;
                techNoList.add(valueOfInteger(techNoArray[i]));
            }
        }
        return techNoList;
    }
    //获取样本号列表
    public static  List<Integer> getTechNosByStram(String techno) {
        List<Integer> techNoList = new ArrayList<>();
        Set<Integer> tehNoSet = new HashSet<>();
        if (isEmpty(techno)) {
            return techNoList;
        }
        String[] techNoArray = techno.replaceAll("，", ",").split(",");
        if (isEmpty(techNoArray)) {
            return techNoList;
        }
        int size = techNoArray.length;
        for (String s : techNoArray) {
            if (s.indexOf("-") > 0) {
                tehNoSet.addAll(buildTechNoSet(s));
            } else {
                tehNoSet.add(Integer.valueOf(s));
            }
        }
        return new ArrayList<Integer>(tehNoSet);
    }

    //生成有序数字
    public static List<Integer> buildTechNoSet(String techNoStr){
        String [] arry = techNoStr.split("-");
        if (isEmpty(arry[0]) || isEmpty(arry[1])) {
            return new ArrayList<>();
        }
        int start=Integer.parseInt(arry[0]);
        int end=Integer.parseInt(arry[1]);
        List<Integer> list = Stream.iterate(start, item -> item+1)
                .limit(end)
//                .parallel()
                .collect(Collectors.toList());
        return list;
    }





    /**
     * String类型转Integer类型
     */
    public static Integer valueOfInteger(String str) {
        return valueOfInteger(str,true);
    }

    public static Integer valueOfInteger(String str, boolean defaultIsNull) {
        Integer rs = defaultIsNull? null:0;
        if(isNotEmpty(str)) {
            try {
                rs = Integer.valueOf(str);
            } catch (NumberFormatException e) {
                logger.error("原因[{}]异常，异常信息[{}]，参数[{}]",
                        "winning.lis.common.utils.StringUtils.valueOfInteger",
                        e.getMessage(),
                        str, e);
            }
        }
        return rs;
    }

    /*
     * 减少装箱
     * */
    public static int valueOfInt(String str) {
        int rs = 0;
        if(isNotEmpty(str)) {
            try {
                rs = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                logger.error("原因[{}]异常，异常信息[{}]，参数[{}]",
                        "winning.lis.common.utils.StringUtils.valueOfInteger",
                        e.getMessage(),
                        str, e);
            }
        }
        return rs;
    }

    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence str) {
        return !isEmpty(str);
    }

    public static boolean containsIgnoreCase(String str, String testStr) {
        if (null == str) {
            return null == testStr;
        } else {
            return str.toLowerCase().contains(testStr.toLowerCase());
        }
    }

    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }
}
