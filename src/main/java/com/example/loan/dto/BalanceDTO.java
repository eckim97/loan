package com.example.loan.dto;

import lombok.*;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigDecimal;

public class BalanceDTO implements Serializable {

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class CreateRequest {
        private Long applicationId;

        private BigDecimal entryAmount;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class UpdateRequest {
        private Long applicationId;

        private BigDecimal beforeEntryAmount;

        private BigDecimal afterEntryAmount;

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class RepaymentRequest {

        public enum RepaymentType {
            ADD,
            REMOVE
        }

        private RepaymentType type;

        private BigDecimal repaymentAmount;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Response {
        private Long balanceId;

        private Long applicationId;

        private BigDecimal balance;
    }
}
