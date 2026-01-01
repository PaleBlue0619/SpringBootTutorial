package com.maxim.application.service;

import com.maxim.application.Response;
import com.maxim.application.dao.stockDayK;
import com.maxim.application.dto.stockDayKDTO;

import java.time.LocalDate;
import java.util.Collection;

public interface stockDayKServiceImpl {

    public Response<stockDayKDTO> getStockDayKById(long id);

    public Response<stockDayKDTO>getStockDayKByDayAndSymbol(LocalDate day, String symbol);

    public Response<Long> getStockNumByDaySQL(LocalDate day);


}
