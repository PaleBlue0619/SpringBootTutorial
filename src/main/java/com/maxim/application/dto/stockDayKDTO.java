package com.maxim.application.dto;

import jakarta.persistence.Id;

import java.time.LocalDate;

/*
* DTO：最终返回给前端的对象
* */
public class stockDayKDTO{
    private long id; // 自增主键
    private String symbol;
    private LocalDate tradeDate;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Double preClose;
    private Double chgAmount;
    private Double chgRate;
    private Long volume;
    private Double amount;
    private Double turnoverRate;
    private Double pe;
    private Double pb;
    private Double mv;
    private Double cmv;
    public stockDayKDTO(){};

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getChgAmount() {
        return chgAmount;
    }

    public void setChgAmount(Double chgAmount) {
        this.chgAmount = chgAmount;
    }

    public Double getChgRate() {
        return chgRate;
    }

    public void setChgRate(Double chgRate) {
        this.chgRate = chgRate;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getCmv() {
        return cmv;
    }

    public void setCmv(Double cmv) {
        this.cmv = cmv;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getMv() {
        return mv;
    }

    public void setMv(Double mv) {
        this.mv = mv;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getPb() {
        return pb;
    }

    public void setPb(Double pb) {
        this.pb = pb;
    }

    public Double getPe() {
        return pe;
    }

    public void setPe(Double pe) {
        this.pe = pe;
    }

    public Double getPreClose() {
        return preClose;
    }

    public void setPreClose(Double preClose) {
        this.preClose = preClose;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public LocalDate getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(LocalDate tradeDate) {
        this.tradeDate = tradeDate;
    }

    public Double getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(Double turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }
}