package com.dhr.service.impl;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * Class description
 *
 *
 * @version        $version$, $date$, 18/12/16
 * @author         donghuarui.
 */
@Service
public class MailService {

    /** Field description */
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    /** Field description */
    @Value("${spring.mail.username}")
    private String from;

    /** Field description */
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * Method description
     *
     *
     * @param to
     * @param subject
     * @param content
     * @param fillPath
     *
     * @throws MessagingException
     */
    public void sendAttachmentsMail(String to, String subject, String content, String fillPath)
            throws MessagingException {
        MimeMessage       message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper  = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        helper.setFrom(from);

        FileSystemResource file     = new FileSystemResource(new File(fillPath));
        String             fileName = file.getFilename();

        helper.addAttachment(fileName, file);
        javaMailSender.send(message);
    }

    /**
     * Method description
     *
     *
     * @param to
     * @param subject
     * @param content
     *
     * @throws MessagingException
     */
    public void sendHtmlMail(String to, String subject, String content) throws MessagingException {
        MimeMessage       message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper  = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        helper.setFrom(from);
        javaMailSender.send(message);
    }

    /**
     * Method description
     *
     *
     * @param to
     * @param subject
     * @param content
     * @param rscPath
     * @param rscId
     */
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
        logger.info("发送邮件开始", to, subject, content, rscPath, rscId);

        MimeMessage       message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper  = null;

        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            helper.setFrom(from);

            FileSystemResource res = new FileSystemResource(new File(rscPath));

            helper.addInline(rscId, res);
            javaMailSender.send(message);
            logger.info("发送邮件成功");
        } catch (MessagingException e) {
            logger.error("发送邮件失败", e);
        }
    }

    /**
     * Method description
     *
     *
     * @param to
     * @param subject
     * @param content
     */
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        message.setFrom(from);
        javaMailSender.send(message);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
