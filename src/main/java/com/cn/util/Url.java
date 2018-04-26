package com.cn.util;

import org.stringtemplate.v4.ST;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.UUID;

/**
 * Created by Administrator on 2017/9/5.
 */
public class Url {
    private static String str="INSERT INTO `sms_mt_task` VALUES ('$ids$','6f6dfbb568374da6b0d8e90b5f81fbd3', '29b3020ab6d34796b1633e36fe374b96', '�Ʒ��У����', '402', '88', '13330501305', 2, 0.06," +
            "                '2017-9-12 11:59:48', '2017-9-12 11:59:52', '2017-9-12 11:59:55'," +
            "                 'WZ19422', 1,'-7662069532617034185',0," +
            "                 '����̫��ѵ����λѧԱ����̫����ְ�ư��ʵ�ٰ࿪ʼ�������绰��63121777��63140135����ַ��֤ȯ������¥5¥��̫��ѵ�������ġ��ض�·47�š�', 'TELECOM', 0.036, NULL)";


    public static void main(String[] args) {
        //System.out.println(dopost());
        Long start=System.currentTimeMillis();
        for (int i = 0; i <100 ; i++) {
            System.out.println(getSql());
            System.out.println("\n");
        }
        System.out.println(System.currentTimeMillis()-start);
    }
    public static String getSql(){
        String ids= UUID.randomUUID().toString();
        ST st = new ST(str, '$', '$');
        st.add("ids",ids);
        return st.render();
    }
    public static void doget(){
        try {
            URL url = new URL("http://127.0.0.1:9801/recharge/callback/mye");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            InputStreamReader in=new InputStreamReader(con.getInputStream());
            BufferedReader reader=new BufferedReader(in);
            StringBuffer buffer=new StringBuffer();
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            System.out.println(buffer.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String dopost() {
        String result = null;
        try {
            File file = new File("D:\\test.html");
            URL url = new URL("http://127.0.0.1:9801/recharge/callback/upload");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST"); // ��Post��ʽ�ύ����Ĭ��get��ʽ
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false); // post��ʽ����ʹ�û���
            // ��������ͷ��Ϣ
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Charset", "UTF-8");
            // ���ñ߽�
            String BOUNDARY = "----------" + System.currentTimeMillis();
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
            // ����������Ϣ
            // ��һ���֣�
            StringBuilder sb = new StringBuilder();
            sb.append("--"); // �����������
            sb.append(BOUNDARY);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data;name=\"file\";filename=\""
                    + file.getName() + "\"\r\n");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");
            byte[] head = sb.toString().getBytes("utf-8");
            // ��������
            OutputStream out = new DataOutputStream(con.getOutputStream());
            // �����ͷ
            out.write(head);
            // �ļ����Ĳ���
            // ���ļ������ļ��ķ�ʽ ���뵽url��
            DataInputStream in = new DataInputStream(new FileInputStream(file));
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            in.close();
            // ��β����
            byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// ����������ݷָ���
            out.write(foot);
            out.flush();
            out.close();
            StringBuffer buffer = new StringBuffer();
            BufferedReader reader = null;
            try {
            // ����BufferedReader����������ȡURL����Ӧ
                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                if (result == null) {
                    result = buffer.toString();
                }
            } catch (IOException e) {
                System.out.println("����POST��������쳣��" + e);
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    reader.close();
                }

            }
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
