package com.isoft.nbawebsite.payment;

import com.isoft.nbawebsite.commons.data.AbstractEntity;
import com.isoft.nbawebsite.constants.PaymentStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data @Document
public class Payment extends AbstractEntity {
    private String paymentRef;
    private BigDecimal amount;
    private String userEmail;
    private String paymentDescription;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}
