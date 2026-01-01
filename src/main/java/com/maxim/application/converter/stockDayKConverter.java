package com.maxim.application.converter;

import com.maxim.application.dao.stockDayK;
import com.maxim.application.dto.stockDayKDTO;

public class stockDayKConverter {
    public stockDayKConverter(){}

    public static stockDayKDTO convertStockDayK(stockDayK stockDayK) {
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
}
