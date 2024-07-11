package com.dogpamines.dogseek.common.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String email, String subject, String token) {
        try {

            String realSubject = "[DOGSEEK] " + subject + " 인증번호를 안내 드립니다.";
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setFrom(fromEmail);
            helper.setTo(email);
            helper.setSubject(realSubject);

            String emailContent = buildEmailContent(email, token, subject);
            helper.setText(emailContent, true);

            mailSender.send(mimeMessage);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private String buildEmailContent(String email, String token, String subject) {
        String htmlContent = String.format("""
                <!DOCTYPE html>
                <html lang="ko">
                <head>
                    <meta charset="UTF-8">
                    <title>Email Verification</title>
                </head>
                <body style="font-family: Noto Sans KR, Poppins; line-height: 1.6;">
                    <div class="email-container" style="max-width: 570px;
                            padding: 20px;
                            border: 1px solid #ddd;
                            border-radius: 5px;
                            background-color: #fff;
                            box-shadow: 0 0 10px rgba(0,0,0,0.1);">
                        <div class="email-header" style="display: flex;
                            background-color: #f8f9fa;
                            padding: 10px;
                            text-align: center;
                            border-bottom: 1px solid #ddd;">
                            <img src="https://avatars.githubusercontent.com/u/170599791?s=200&v=4" alt="logo Image"
                                style="max-width: 100%%;
                            width: 50px;
                            height: auto;
                            margin-right: 40px;
                            margin-left: 20px;">
                            <h1 style="margin: 0;
                            margin-top: 10px;
                            font-size: 24px;
                            color: #333;">DogSeek 이메일 인증 안내</h1>
                        </div>
                        <div class="email-body" style="padding: 30px;
                            font-size: 14px;
                            color: #555;">
                            <span style="font-weight: 400;
                            color: #000000;">안녕하세요, <span type=text class="email" style="font-weight: bold;">%s<span> 고객님.<span><br/>
                            <span style="font-weight: 400;
                            color: #000000;">'<strong>%s</strong>'를 위해 이메일 인증을 진행합니다.</span><br/>
                            <span style="font-weight: 400;
                            color: #000000;">아래 발급된 이메일 인증번호를 복사하거나 직접 입력하여 인증을 완료해주세요.<span><br/>
                            <span style="font-weight: 400;
                            color: #000000;">개인정보 보호를 위해 인증번호는 5분 간 유효합니다.<span><br/>
                            <div class="content" style="margin-top: 20px;
                            margin-bottom: 20px;">
                                <hr/>
                                <p style="margin-right: 20px; font-size: 16px;">인증번호 &nbsp;&nbsp;&nbsp;<strong class="number" style="font-size: 18px;">%s</strong></p>
                                <hr/>
                            </div>
                        </div>
                        <div class="email-footer" style="padding: 10px;
                            text-align: center;
                            color: #777;
                            font-size: 12px;">
                            <p style="margin-right: 20px;">이 메일은 자동으로 발송된 메일입니다. 궁금하신 사항은 DogSeek으로 문의 바랍니다.</p>
                        </div>
                    </div>
                </body>
                </html>
                """, email, subject, token);

        return htmlContent;
    }
}
