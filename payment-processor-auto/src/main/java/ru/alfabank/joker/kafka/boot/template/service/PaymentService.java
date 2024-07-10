package ru.alfabank.joker.kafka.boot.template.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.alfabank.joker.kafka.boot.template.dto.OtpDto;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class PaymentService {

    private final KafkaTemplate<String, OtpDto> kafkaTemplate;

    public void acceptPayment() {
        OtpDto otpDto = this.preparePayment();
        this.sendPushAsync(otpDto);
    }


    private void sendPushAsync(OtpDto otpDto) {
        kafkaTemplate.sendDefault(otpDto);
    }


    private OtpDto preparePayment() {
        return OtpDto.builder()
                .sender("payment-processor")
                .userId("me")
                .code("my-secret-code")
                .expireTime(LocalDateTime.now().plusMinutes(1))
                .build();
    }
}
