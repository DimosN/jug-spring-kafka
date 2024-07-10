package ru.alfabank.joker.kafka.boot.template;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.alfabank.joker.kafka.boot.template.service.PaymentService;

@Slf4j
@EnableScheduling
@SpringBootApplication
@RequiredArgsConstructor
public class KafkaTemplateBootApp {

    private final PaymentService paymentService;

    public static void main(String[] args) {
        SpringApplication.run(KafkaTemplateBootApp.class, args);
    }

    @Scheduled(fixedDelay = 5000)
    void doYourJob() {
        for (int i = 0; i < 10; ++i) {
            paymentService.acceptPayment();
        }
        log.info("Sent new batch");
    }
}
