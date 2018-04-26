package com.cn.james;

/**
 * Created by Administrator on 2017/9/13.
 */
public interface MailService {
    public void sendSimpleMail(String to, String subject, String content);
    void sendHtmlMail(String to, String subject, String content);
    void sendAttachmentsMail(String to, String subject, String content, String filePath);
    void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);
    void sendTenpalteMail(String to, String subject, String id);
    public void out(int i);
}
