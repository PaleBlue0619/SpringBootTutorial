package com.maxim.application.service;

import com.maxim.application.Response;
import com.maxim.application.dao.pageStockDayKRequest;
import com.maxim.application.dto.stockDayKDTO;
import org.springframework.data.domain.Page;
import java.time.LocalDate;
import java.util.Collection;

public interface stockDayKService {
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

    /* UpdateMethod*/
    Response<stockDayKDTO> updateStockDayK(LocalDate day, String symbol,
                                           Double open, Double close, Double high, Double low,
                                           Long volume, Double amount, Double pe, Double pb,
                                           Double turnoverRate, Double chgRate, Double chgAmount,
                                           Double preClose, Double cmv, Double mv);

    /* Specification */
    Response<Page<stockDayKDTO>> filterStockDayKs(pageStockDayKRequest request);


}
