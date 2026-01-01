package com.maxim.application.dao;

import jakarta.persistence.*;
//import javax.persistence.Entity;
//import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

/*
用类去映射数据库的表
@Id
@Column: Java该成员字段对应数据库中哪一列
*/
@Entity
@Table(name="stockDayK")
public class stockDayK {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // 自增主键

    @Column(name="symbol")
    private String symbol;
    @Column(name="trade_date")
    private LocalDate tradeDate;
    @Column(name="open_price")
    private Double open;
    @Column(name="high_price")
    private Double high;
    @Column(name="low_price")
    private Double low;
    @Column(name="close_price")
    private Double close;
    @Column(name="pre_close")
    private Double preClose;
    @Column(name="change_amount")
    private Double chgAmount;
    @Column(name="change_rate")
    private Double chgRate;
    @Column(name="volume")
    private Long volume;
    @Column(name="amount")
    private Double amount;
    @Column(name="turnover_rate")
    private Double turnoverRate;
    @Column(name="pe_ratio")
    private Double pe;
    @Column(name="pb_ratio")
    private Double pb;
    @Column(name="market_cap")
    private Double mv;
    @Column(name="circulating_market_cap")
    private Double cmv;
    @Column(name="create_time")
    private LocalDateTime createTime;
    @Column(name="update_time")
    private LocalDateTime updateTime;

    public stockDayK() {
    }

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

    public Double getCmv() {
        return cmv;
    }

    public void setCmv(Double cmv) {
        this.cmv = cmv;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
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

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }
}
