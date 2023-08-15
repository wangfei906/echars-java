package com.miaomiao;

import java.io.*;
import java.util.UUID;

/**
 * 生成拓扑图
 *
 * @author wf
 * @since 2023年8月15日
 */
@SuppressWarnings("all")
public class GenertorEchartsPic {


    private static final String JSpath = "C:\\Users\\admin\\Downloads\\genertorEchartsPhoto-master\\phantomjs-2.1.1-windows\\echarts-convert\\echarts-convert.js";

    public static void main(String[] args) {

        String options = "{\"title\":{\"text\":\"\",\"subtext\":\"\",\"x\":\"CENTER\"},\"tooltip\":{\"show\":true},\"legend\":{\"data\":[\"直接访问\",\"邮件营销\",\"联盟广告\",\"视频广告\",\"搜索引擎\"]},\"series\":[{\"name\":\"访问来源\",\"type\":\"pie\",\"radius\":\"55%\",\"center\":[\"50%\",\"60%\"],\"label\":{\"formatter\":\" {b}: {c}个，占比{d}%\"},\"data\":[{\"value\":335,\"name\":\"直接访问\"},{\"value\":310,\"name\":\"邮件营销\"},{\"value\":234,\"name\":\"联盟广告\"},{\"value\":135,\"name\":\"视频广告\"},{\"value\":1548,\"name\":\"搜索引擎\"}]}]}";
        String picPath = generateEChart(options);

    }

    /*
     * 主程序
     */
    public static String generateEChart(String options) {
        String dataPath = writeFile(options);
        String fileName = UUID.randomUUID().toString() + ".png";
        String path = "/temp/" + fileName;
        try {
            File file = new File(path);     //文件路径
            if (!file.exists()) {
                File dir = new File(file.getParent());
                dir.mkdirs();
                file.createNewFile();
            }
            String cmd = "C:\\Users\\admin\\Downloads\\genertorEchartsPhoto-master\\phantomjs-2.1.1-windows\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe " + JSpath + " -infile " + dataPath + " -outfile " + path;//生成命令行
//            String cmd = "/opt/spinfo/rvaservices/rva-custom-check-service/wf/phantomjs-2.1.1-linux-x86_64/bin/phantomjs " + JSpath + " -infile " + dataPath + " -outfile " + path;//生成命令行
            Runtime.getRuntime().exec(cmd);
//            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            String line = "";
//            while ((line = input.readLine()) != null) {
//            }
//            input.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return path;
    }

    /*
     *
     * options生成文件存储
     */
    public static String writeFile(String options) {
        String dataPath = "/temp/" + UUID.randomUUID().toString().substring(0, 8) + ".json";
        try {
            /* option写入文本文件 用于执行命令*/
            File writename = new File(dataPath);
            if (!writename.exists()) {
                File dir = new File(writename.getParent());
                dir.mkdirs();
                writename.createNewFile(); //
            }
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            out.write(options);
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后关闭文件
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataPath;
    }
}
