package com.libraryapp.librarymanagementapp.dto.member.response;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class GetFinesByIdResponse {
    @Positive
    private int amount;

    @NotBlank
    private String reason;

    private boolean isPaid;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public GetFinesByIdResponse(int amount, String reason, boolean isPaid) {
        this.amount = amount;
        this.reason = reason;
        this.isPaid = isPaid;
    }
}
