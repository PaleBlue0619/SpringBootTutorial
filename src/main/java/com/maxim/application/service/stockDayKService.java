package com.maxim.application.service;
import com.maxim.application.Response;
import com.maxim.application.dao.stockDayK;
import com.maxim.application.dto.stockDayKDTO;
import com.maxim.application.converter.stockDayKConverter;
import com.maxim.application.dao.stockDayKRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Service
public class stockDayKService implements stockDayKServiceImpl{
    private static final Logger logger = LoggerFactory.getLogger(stockDayKService.class);

    @Autowired
    private stockDayKRepository stockDayKRepository;

    @Override
    public Response<stockDayKDTO> getStockDayKById(long id){
         logger.info("查询ID为 {} 的股票数据", id);
         stockDayK obj = stockDayKRepository.findById(id).orElse( null);
         if (obj != null) {
            return Response.newSuccess(stockDayKConverter.convertStockDayK(obj));
         }
         return Response.newEmpty();
    }

    @Override
    public Response<stockDayKDTO> getStockDayKByDayAndSymbol(LocalDate day, String symbol){
        logger.info("查询日期 {} 的股票 {}", day, symbol);
        stockDayK obj = stockDayKRepository.getStockDayKByDayAndSymbol(day, symbol);
        logger.info("对象属性: {} {} {} {}", obj.getSymbol(),  obj.getOpen(), obj.getClose(), obj.getVolume());
        // 返回 某日某只股票数据对象
        return Response.newSuccess(stockDayKConverter.convertStockDayK(obj));
    }

    @Override
    public Response<Long> getStockNumByDaySQL(LocalDate day){
        logger.info("查询日期 {} 的股票数量", day);
        return Response.newSuccess(stockDayKRepository.countByTradeDateSQL(day));
    }

//    @Override
//    public Collection<stockDayK> getStockDayKByDayAndSymbol(LocalDate day, String symbol){
//        return null;
//    }
}
