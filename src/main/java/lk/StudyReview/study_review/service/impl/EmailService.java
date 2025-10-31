package lk.StudyReview.study_review.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lk.StudyReview.study_review.model.EmailSchedule;
import lk.StudyReview.study_review.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmailService {
@Autowired
private EmailRepository emailRepository;
    @Autowired
    private JavaMailSender sender;
    @Autowired
    private TemplateEngine templateEngine;
    public void sendSimpleMail(String to, String subject, String text)  {

        try {
            MimeMessage mimeMessage = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("vihanganirmitha200@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true); // true = HTML content

            sender.send(mimeMessage);
        }catch (Exception e){

        }

    }

    @Scheduled(cron = "0 */2 * * * *")
    public void mainMailScheduler() throws MessagingException {
        System.out.println("email triggered");
        List<EmailSchedule>  emailScheduleList = emailRepository.findByDone(false);
        for (EmailSchedule emailSchedule : emailScheduleList) {
            System.out.println("aaa");
            if(!emailSchedule.getSentDate().isAfter(LocalDateTime.now())){
                System.out.println("bb");
                sendSimpleMail(emailSchedule.getTo(), emailSchedule.getSubject(), emailSchedule.getText());
                emailSchedule.setDone(true);
                emailRepository.save(emailSchedule);
            }
        }
    }
}
