package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    private static final String SUBJECT = "Tasks: Once a day email";

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

//    @Scheduled(cron = "0 0 10 * * *")
//    @Scheduled(fixedDelay = 100000)
    public void sendInformationEmail() {
        long size = taskRepository.count();
        StringBuilder message = new StringBuilder("in database u got: ");
        message.append(size).append(" tasks");
        if (size > 1) {
            message.append("s");
        }
        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                message.toString(),
                null
        ));
    }
}
