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
import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class stockDayKService implements stockDayKServiceImpl{
    private static final Logger logger = LoggerFactory.getLogger(stockDayKService.class);

    @Autowired
    private stockDayKRepository stockDayKRepository;

    /* Get 方法 */
    @Override
    public Response<Long> getStockNumByDay(LocalDate day){
        logger.info("查询日期 {} 的股票数量", day);
        return Response.newSuccess(stockDayKRepository.countByTradeDate(day));
    }

    @Override
    public Response<Long> getStockNumByDayAndSymbol(LocalDate day, String symbol){
        logger.info("查询日期 {} 的股票 {} 的数量", day, symbol);
        return Response.newSuccess(stockDayKRepository.countByTradeDateAndSymbol(day, symbol));
    }


    @Override
    public Response<stockDayKDTO> getStockDayKById(long id){
         logger.info("查询ID为 {} 的股票数据", id);
         stockDayK obj = stockDayKRepository.findById(id).orElse( null);
         if (obj != null) {
            return Response.newSuccess(stockDayKConverter.toDTO(obj));
         }
         return Response.newEmpty("ID为 %s 的股票信息为空".formatted(id));
    }

    @Override
    public Response<stockDayKDTO> getStockDayKByDayAndSymbol(LocalDate day, String symbol){
        logger.info("查询日期 {} 的股票 {}", day, symbol);
        stockDayK obj = stockDayKRepository.getStockDayKByDayAndSymbol(day, symbol);
        logger.info("对象属性: {} {} {} {}", obj.getSymbol(), obj.getOpen(), obj.getClose(), obj.getVolume());
        // 返回 某日某只股票数据对象
        return Response.newSuccess(stockDayKConverter.toDTO(obj));
    }

    /* Post 方法 */
    @Override
    public Response<String> addStockDayK(stockDayKDTO dto){
        logger.info("添加股票数据: {} {} {} {}", dto.getSymbol(),  dto.getOpen(), dto.getClose(), dto.getVolume());
        long count = stockDayKRepository.countByTradeDateAndSymbol(dto.getTradeDate(), dto.getSymbol());
        if (count > 0) {
            return Response.newEmpty("%s-%s股票数据已存在".formatted(dto.getSymbol(), dto.getTradeDate()));
        }
        stockDayK obj = stockDayKConverter.toDAO(dto, LocalDateTime.now(), LocalDateTime.now());
        stockDayKRepository.save( obj); // 保存
        return Response.newSuccess("%s-%s添加成功".formatted(dto.getSymbol(), dto.getTradeDate()));
    }
    // 批量插入
    @Override
    public Response<String> addStockDayKs(Collection<stockDayKDTO> dtos){
        logger.info("批量添加股票数据: {}", dtos.size());
        /* 先进行筛选 -> Java ioStream */
        Collection<stockDayK> filterDTOs = dtos.stream()
                .filter(dto -> stockDayKRepository.countByTradeDateAndSymbol(dto.getTradeDate(), dto.getSymbol()) == 0)
                .map(dto -> stockDayKConverter.toDAO(dto, LocalDateTime.now(), LocalDateTime.now()))
                .toList();
        if (filterDTOs.size() == 0){
            return Response.newEmpty("该批股票日K数据均已在数据库中存在");
        }
        stockDayKRepository.saveAll(filterDTOs);
        if (filterDTOs.size() == dtos.size()){
            return Response.newSuccess("批量添加成功");
        }else{
            return Response.newSuccess("批量添加成功, 忽略 %s 条数据".formatted(dtos.size() - filterDTOs.size()));
        }
    }

    /* Delete 方法 */
    @Override
    public Response<String> deleteStockDayKById(Long id){
        /* 先校验是否存在于数据库中 */
        stockDayK obj = stockDayKRepository.findById(id).orElse(null);
        if (obj == null){
            return Response.newEmpty("ID为 %s 的股票数据不存在".formatted(id));
        }
        stockDayKRepository.deleteById(id);
        return Response.newSuccess("ID为 %s 的股票数据删除成功");
    }

    @Override
    public Response<String> deleteStockDayKBySymbolAndDay(LocalDate day, String symbol){
        /* 先校验是否存在于数据库中 */
        Long count =  stockDayKRepository.countByTradeDateAndSymbol(day, symbol);
        if (count == 0){
             return Response.newEmpty("日期 %s 的股票 %s 数据不存在".formatted(day, symbol));
        }
        stockDayKRepository.deleteByTradeDateAndSymbol(day, symbol);
        return Response.newSuccess("日期 %s 的股票 %s 数据删除成功".formatted(day, symbol));
    }
}
