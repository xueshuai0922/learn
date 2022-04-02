package com.xs.util;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xs.enetity.Lis_ApplySheetList_Mdl;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.RowMetaAndData;
import org.pentaho.di.core.exception.KettleStepException;
import org.pentaho.di.core.plugins.PluginFolder;
import org.pentaho.di.core.plugins.StepPluginType;
import org.pentaho.di.core.row.RowMetaInterface;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.job.entries.trans.JobEntryTrans;
import org.pentaho.di.job.entry.JobEntryCopy;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.RowListener;
import org.pentaho.di.trans.step.StepInterface;
import org.pentaho.di.trans.step.StepMetaDataCombi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xueshuai
 * @date 2021/7/18 17:58
 * @description kjb工具类
 */
public class KettleUtils {
    /**
     * 执行kjb
     *
     * @param filename
     * @param params
     */
    public static void runKjb(String filename, Map<String, String> params) {
        try {
            StepPluginType.getInstance().getPluginFolders().
                    add(new PluginFolder(
                            "D:\\kettle\\data-integration\\plugins", false, true));
            KettleEnvironment.init();
            JobMeta jm = new JobMeta(filename, null);
            Job job = new Job(null, jm);
            if (params != null) {
                params.forEach((key, value) -> {
                    try {
                        job.setVariable(key, value);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
            job.start();
            LogJobEntryListener logJobEntryListener = new LogJobEntryListener();
            //增加监听器
            job.addJobEntryListener(logJobEntryListener);
            //获取结果
            List<JobEntryCopy> jobCopies = jm.getJobCopies();
            final List<RowMetaAndData> rows  = new ArrayList<RowMetaAndData>();
            for (JobEntryCopy jobCopy : jobCopies) {


                if("根据入参执行查询返回".equals(jobCopy.getName())){
                    JobEntryTrans entry = (JobEntryTrans)jobCopy.getEntry();

                    Trans trans = entry.getTrans();
                    RowListener rowListner = new RowListener() {

                        public void rowWrittenEvent(RowMetaInterface rowMeta, Object[] row)
                                throws KettleStepException {
                            rows.add(new RowMetaAndData(rowMeta, row));
                        }

                        public void rowReadEvent(RowMetaInterface arg0, Object[] arg1)
                                throws KettleStepException {

                        }

                        public void errorRowWrittenEvent(RowMetaInterface arg0,
                                                         Object[] arg1) throws KettleStepException {

                        }
                    };
                    List<StepMetaDataCombi> steps = trans.getSteps();
                    String stepname = "";
                    for (StepMetaDataCombi step : steps) {
                        if ("JsonOutput".equals(step.stepMeta.getStepID())) {
                            stepname=step.stepMeta.getName();
                        }
                    }

                    //JSON output
                    StepInterface stepInterface = trans.findRunThread(stepname);
                    stepInterface.addRowListener(rowListner);
                }
            }

            job.waitUntilFinished();
            String json = null;
            RowMetaAndData rmad = rows.get(rows.size() - 1);
            json = "[" + rmad.getString("resultJson", null) + "]";
            // 打印JSON串
            System.out.println("【json】------->" + json);
            System.out.println(filename + "文件执行完毕！");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(filename + "文件执行异常！");
        }
    }

    /**
     * 执行转换ktr
     *
     * @param filename
     * @param params
     */
    public static void runKtr(String filename, Map<String, String> params) {
        try {
            //todo 打包jar后classpath文件读取
//            BufferedReader in = new BufferedReader(new InputStreamReader(
//            KettleUtils.class.getClassLoader().getResourceAsStream("plugin\\")));
//            StringBuffer buffer = new StringBuffer();
//            String line = "";
//            while ((line = in.readLine()) != null){
//                buffer.append(line);
//            }
//            String path = buffer.toString();
            StepPluginType.getInstance().getPluginFolders().
                    add(new PluginFolder(
                            "D:\\kettle\\data-integration\\plugins", false, true));

            KettleEnvironment.init();
            TransMeta metaData = new TransMeta(filename);
            Trans trans = new Trans(metaData);
            if (params != null) {
                params.forEach((key, value) -> {
                    try {
                        trans.setVariable(key, value);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
            trans.execute(null);
            final List<RowMetaAndData> rows = new ArrayList<RowMetaAndData>();
            RowListener rowListner = new RowListener() {

                public void rowWrittenEvent(RowMetaInterface rowMeta, Object[] row)
                        throws KettleStepException {
                    rows.add(new RowMetaAndData(rowMeta, row));
                }

                public void rowReadEvent(RowMetaInterface arg0, Object[] arg1)
                        throws KettleStepException {

                }

                public void errorRowWrittenEvent(RowMetaInterface arg0,
                                                 Object[] arg1) throws KettleStepException {

                }
            };
            List<StepMetaDataCombi> steps = trans.getSteps();
            String stepname = "";
            for (StepMetaDataCombi step : steps) {
                String stepID = step.stepMeta.getStepID();
                if ("JsonOutput".equals(stepID) || "XMLJoin".equals(stepID)) {
                    stepname=step.stepMeta.getName();
                }
            }
            //JSON output
            StepInterface stepInterface = trans.findRunThread(stepname);
            stepInterface.addRowListener(rowListner);
            // 等待执行完毕
            trans.waitUntilFinished();

            String json = null;
            RowMetaAndData rmad = rows.get(rows.size() - 1);
            json = "[" + rmad.getString("resultJson", null) + "]";
            // 打印JSON串
            System.out.println("【json】------->" + json);

//            //todo json-->实体类
            JSONArray objects = JSONUtil.parseArray(json);
            JSONObject jsonObject = (JSONObject) objects.get(0);
            JSONArray data = (JSONArray) jsonObject.get("data");
            List<Lis_ApplySheetList_Mdl> lisApplySheetListMdls = JSONUtil.toList(data, Lis_ApplySheetList_Mdl.class);
            lisApplySheetListMdls.forEach(System.out::println);


            if (trans.getErrors() > 0) {
                System.out.print("Error Executing transformation");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        simpleJson();
//        bloodReturnJson();
//        bloodInsert();
//        webservice();
//        hl7();
        jsonService();
//        webService();


    }

    private static void simpleJson() {
        String fileName = "简单-获取病人信息.ktr";
        String filePath = "D:\\winning_git\\lis文档\\Kettle数据接口研究\\json\\";
        HashMap<String, String> hashMap = new HashMap<>();
        //todo 模拟标准入参
        hashMap.put("json", "{\n" +
                "\"HOSPITAL_CODE\":\"99999\",\n" +
                "\"PATIENT_TYPE\":\"1\",\n" +
                "\"PATIENT_ID\":\"19421\",\n" +
                "\"REGISTER_ID\":\"\",\n" +
                "\"BABY_ID\":\"0\"\n" +
                "}");
//        {
//            "HOSPITAL_CODE": "99999",
//            "PATIENT_TYPE": "1",
//            "PATIENT_ID": "19421",
//            "REGISTER_ID": "",
//            "BABY_ID": "0"
//        }
        hashMap.put("url", "http://172.17.1.211:20036/api/clf/getpatientinfo");
        hashMap.put("http-method", "POST");
        runKtr(filePath + fileName, hashMap);
        //         fileName = "json作业演示.kjb";
        //        runKjb(filePath + fileName, hashMap);
    }

    private static void bloodReturnJson() {
        String fileName = "输血申请单.ktr";
        String filePath = "D:\\winning_git\\lis文档\\Kettle数据接口研究\\json\\";

        runKtr(filePath + fileName, null);
        //         fileName = "json作业演示.kjb";
        //        runKjb(filePath + fileName, hashMap);
    }

    private static void bloodInsert() {
        String fileName = "java调用输血申请单.ktr";
        String filePath = "D:\\winning_git\\lis文档\\Kettle数据接口研究\\json\\";
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("json", "{\n" +
                "\"HOSPITAL_CODE\":\"99999\",\n" +
                "\"PATIENT_TYPE\":\"1\",\n" +
                "\"PATIENT_ID\":\"19421\",\n" +
                "\"REGISTER_ID\":\"\",\n" +
                "\"BABY_ID\":\"0\"\n" +
                "}");
        hashMap.put("url", "http://127.0.0.1:20170/lis/samplelz/common/getApplyInfo");
        hashMap.put("http-method", "POST");
        runKtr(filePath + fileName, hashMap);
    }


    private static void webservice() {
        String fileName = "java调用webservice.ktr";
        String filePath = "D:\\winning_git\\lis文档\\Kettle数据接口研究\\webservice\\";
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("youbian", "32822");
        runKtr(filePath + fileName, hashMap);
    }


    private static void hl7() {
        String fileName = "java调用hl7.ktr";
        String filePath = "D:\\winning_git\\lis文档\\Kettle数据接口研究\\hl7\\";
        HashMap<String, String> hashMap = new HashMap<>();
        //这里注意，hl7格式的东西，换行了进行变量传送会出现问题，这里需要将换行符去掉
        hashMap.put("hl7", "MSH|^~\\&|H01||C13||20170417082206||ORM^O01|2017041708220617041000138810|P|2.4|||NE|AL|CHN|PID|||000000672850||张三^||1993-11-20 00:00:00.0|F|||^||^^^^^^^^^|||O|||321282199311205225||||||||||||||||||||||PV1||H|健康服务部^^^TEDAICH|||健康服务部^^^TEDAICH|||||||||||000498|99^自费|170410001388^^^^某某医院|||||||||||||||||||||||||20170417082206||ORC|NW|170410504932||170410001388^10||N|||20170417082206|000498^张|||健康服务部|OBR||17041000138810||Y0102032^*核素-14C尿素呼气试验^C14^碳14^Y0102^其他部位^Y1^ECT^99^~E0100014~120.0~1.0~碳--14呼气试验查幽门菌|||||||||||||0407^核医学科||N|120.0||NTE|1|P||DG1|||^^ICD-10|||A||||||||||^|");
        runKtr(filePath + fileName, hashMap);
    }

//------------------------------------------接口服务-----------------------------------------------------------------------

    /**
     * 组成json输出
     */
    private static void jsonService() {
        String fileName = "获取报告和结果信息.kjb";
        String filePath = "D:\\winning_git\\lis文档\\Kettle数据接口研究\\lis服务提供接口\\";
        String finalName = filePath+fileName;
        HashMap<String, String> hashMap = new HashMap<>();
        runKtr(finalName,hashMap);
    }

    /**
     * 组成xml输出
     */
    private static void webService() {
        String fileName = "xml接口服务.ktr";
        String filePath = "D:\\winning_git\\lis文档\\Kettle数据接口研究\\lis服务提供接口\\";
        String finalName = filePath+fileName;
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("reportno","608965");
        runKtr(finalName,hashMap);
    }

}
