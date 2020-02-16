//package com.dhr.service;
//
//import com.dhr.service.impl.MailService;
//import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.Context;
//
//import javax.annotation.Resource;
//import javax.mail.MessagingException;
//
///**
// * donghuarui
// * 2018年11月24日17:52:05
// *
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ServiceTest {
//
//    @Resource
//    private MailService mailService;
//    @Resource
//    private TemplateEngine templateEngine;
//
//    @Test
//    public void mailtest(){
//        mailService.sendSimpleMail("840028548@qq.com","第一封邮件","大家好,这是我的第一封邮件");
//    }
//
//    @Test
//    public void htmlmailtest() throws MessagingException{
//        String content = "<html>\n"+"<body>\n"+"<h3>这是我的第一封html邮件</h3>\n"+"</body>\n"+"</html>";
//        mailService.sendHtmlMail("840028548@qq.com","第二封邮件",content);
//    }
//
//    @Test
//    public void attachmentstest() throws MessagingException{
//        String filePath = "D:\\Pictures\\iCloud Photos/t01490aefc562b32684.jpg";
//        mailService.sendAttachmentsMail("840028548@qq.com","第三封邮件","这是一封带附件的邮件",filePath);
//    }
//
//    @Test
//    public void inlineResourcetest(){
//        String imgPath = "D:\\Pictures\\iCloud Photos/t01490aefc562b32684.jpg";
//        String rscId = "001";
//        String content = "<html>\n"+"<body>\n"+"Hello,tianqin<img src=\'cid:"+rscId+"\'></img>\n"+"</body>\n"+"</html>";
//        mailService.sendInlineResourceMail("1328050445@qq.com","刚刚弄的一个发邮件的系统",content,imgPath,rscId);
//    }
//
////    @Test
////    public void templateEnginetest() throws MessagingException {
////        Context context = new Context();
////        context.setVariable("id","006");
////        String emailContent=templateEngine.process("emailTemplate",context);
////        mailService.sendHtmlMail("840028548@qq.com","这是一个模板邮件",emailContent);
////    }
//}
