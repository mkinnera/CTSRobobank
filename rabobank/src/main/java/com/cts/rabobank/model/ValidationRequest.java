package com.cts.rabobank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * ValidationRequest
 */
@XmlRootElement(name = "record")
@XmlAccessorType(XmlAccessType.FIELD)
public class ValidationRequest {
    @XmlAttribute(name = "reference")
    private int transactionRef;

    private double startBalance;

    private double mutation;

    private double endBalance;
    private boolean isValid;

    private static final NumberFormat formatter = new DecimalFormat("#0.00");

    public void checkBalanceValidation() {
        this.isValid = Double.parseDouble(formatter.format((this.getStartBalance() + this.getMutation()))) == Double.parseDouble(formatter.format(this.getEndBalance()));
    }

    @JsonProperty(value = "Reference")
    public int getTransactionRef() {
        return transactionRef;
    }

    public void setTransactionRef(int transactionRef) {
        this.transactionRef = transactionRef;
    }

    @JsonProperty(value = "Start Balance")
    public double getStartBalance() {
        return startBalance;
    }

    public void setStartBalance(double startBalance) {
        this.startBalance = startBalance;
    }

    @JsonProperty(value = "Mutation")
    public double getMutation() {
        return mutation;
    }

    public void setMutation(double mutation) {
        this.mutation = mutation;
    }

    @JsonProperty(value = "End Balance")
    public double getEndBalance() {
        return endBalance;
    }

    public void setEndBalance(double endBalance) {
        this.endBalance = endBalance;
    }

    @JsonIgnore
    @JsonProperty(value = "valid")
    public boolean isValid() {
        return isValid;
    }

}
