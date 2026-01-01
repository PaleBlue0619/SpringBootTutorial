package com.maxim.application.converter;

import com.maxim.application.dao.stockDayK;
import com.maxim.application.dto.stockDayKDTO;

import java.time.LocalDateTime;

public class stockDayKConverter {
    public stockDayKConverter(){}

    public static stockDayKDTO toDTO(stockDayK stockDayK) {
        stockDayKDTO dto = new stockDayKDTO();
        dto.setSymbol(stockDayK.getSymbol());
        dto.setTradeDate(stockDayK.getTradeDate());
        dto.setOpen(stockDayK.getOpen());
        dto.setClose(stockDayK.getClose());
        dto.setHigh(stockDayK.getHigh());
        dto.setLow(stockDayK.getLow());
        dto.setVolume(stockDayK.getVolume());
        dto.setAmount(stockDayK.getAmount());
        dto.setMv(stockDayK.getMv());
        dto.setCmv(stockDayK.getCmv());
        dto.setTurnoverRate(stockDayK.getTurnoverRate());
        dto.setChgRate(stockDayK.getChgRate());
        dto.setChgAmount(stockDayK.getChgAmount());
        dto.setPe(stockDayK.getPe());
        dto.setPb(stockDayK.getPb());
        dto.setPreClose(stockDayK.getPreClose());
        return dto;
    }

    public static stockDayK toDAO(stockDayKDTO dto, LocalDateTime createTime, LocalDateTime updateTime){
        stockDayK dao = new stockDayK();
        dao.setSymbol(dto.getSymbol());
        dao.setTradeDate(dto.getTradeDate());
        dao.setOpen(dto.getOpen());
        dao.setClose(dto.getClose());
        dao.setHigh(dto.getHigh());
        dao.setLow(dto.getLow());
        dao.setVolume(dto.getVolume());
        dao.setAmount(dto.getAmount());
        dao.setMv(dto.getMv());
        dao.setCmv(dto.getCmv());
        dao.setTurnoverRate(dto.getTurnoverRate());
        dao.setChgRate(dto.getChgRate());
        dao.setChgAmount(dto.getChgAmount());
        dao.setPe(dto.getPe());
        dao.setPb(dto.getPb());
        dao.setPreClose(dto.getPreClose());
        dao.setCreateTime(createTime);
        dao.setUpdateTime(updateTime);
        return dao;
    }
}
