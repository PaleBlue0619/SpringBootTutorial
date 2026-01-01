package com.maxim.application.service;

import com.maxim.application.Response;
import com.maxim.application.dao.stockDayK;
import com.maxim.application.dto.stockDayKDTO;

import java.time.LocalDate;
import java.util.Collection;

public interface stockDayKServiceImpl {
    /* GetMethod */
    Response<Long> getStockNumByDayAndSymbol(LocalDate day, String symbol);

    Response<stockDayKDTO> getStockDayKById(long id);

    Response<stockDayKDTO>getStockDayKByDayAndSymbol(LocalDate day, String symbol);

    Response<Long> getStockNumByDay(LocalDate day);

    /* PostMethod */
    Response<String> addStockDayK(stockDayKDTO stockDayKDTO);

    Response<String> addStockDayKs(Collection<stockDayKDTO> stockDayKDTOs);

    /* DelMethod */
    Response<String> deleteStockDayKById(Long id);

    Response<String> deleteStockDayKBySymbolAndDay(LocalDate day, String symbol);
}
