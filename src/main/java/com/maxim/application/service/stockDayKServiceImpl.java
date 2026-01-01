package com.maxim.application.service;
import com.maxim.application.Response;
import com.maxim.application.dao.pageStockDayKRequest;
import com.maxim.application.dao.stockDayK;
import com.maxim.application.dto.stockDayKDTO;
import com.maxim.application.converter.stockDayKConverter;
import com.maxim.application.dao.stockDayKRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
public class stockDayKServiceImpl implements stockDayKService {
    private static final Logger logger = LoggerFactory.getLogger(stockDayKServiceImpl.class);

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
    @Transactional
    public Response<String> deleteStockDayKById(Long id){
        /* 先校验是否存在于数据库中 */
        stockDayK obj = stockDayKRepository.findById(id).orElse(null);
        if (obj == null){
            return Response.newEmpty("ID为 %s 的股票数据不存在".formatted(id));
        }
        stockDayKRepository.deleteById(id);
        return Response.newSuccess("ID为 %s 的股票数据删除成功".formatted(id));
    }

    @Override
    @Transactional  // 添加事务注解 -> JPA的删除操作必须在事务中进行, 以确保数据一致性 & 后续若需恢复按照事务维度进行回滚
    public Response<String> deleteStockDayKBySymbolAndDay(LocalDate day, String symbol){
        /* 先校验是否存在于数据库中 */
        Long count =  stockDayKRepository.countByTradeDateAndSymbol(day, symbol);
        if (count == 0){
             return Response.newEmpty("日期 %s 的股票 %s 数据不存在".formatted(day, symbol));
        }
        stockDayKRepository.deleteByTradeDateAndSymbol(day, symbol);
        return Response.newSuccess("日期 %s 的股票 %s 数据删除成功".formatted(day, symbol));
    }

    @Override
    @Transactional
    public Response<stockDayKDTO> updateStockDayK(LocalDate day, String symbol,
                                                  Double open, Double close, Double high, Double low,
                                                  Long volume, Double amount, Double pe, Double pb,
                                                  Double turnoverRate, Double chgRate, Double chgAmount,
                                                  Double preClose, Double cmv, Double mv){
        LocalDateTime updateTime = LocalDateTime.now();
        /* 先校验是否存在于数据库中 */
        Long count = stockDayKRepository.countByTradeDateAndSymbol( day, symbol);
        if (count == 0){
            return Response.newEmpty( "日期 %s 的股票 %s 数据不存在".formatted(day, symbol));
        }
        stockDayK obj = stockDayKRepository.getStockDayKByDayAndSymbol(day, symbol);
        obj.setUpdateTime(updateTime); // 更新时间

        /* 检查各属性是否为空 */
        if (open != null && open > 0){
            obj.setOpen( open);
        }
        if (close != null && close > 0){
            obj.setClose( close);
        }
        if (high != null && high > 0){
            obj.setHigh( high);
        }
        if (low != null && low > 0){
            obj.setLow( low);
        }
        if (volume != null && volume > 0){
            obj.setVolume( volume);
        }
        if (amount != null && amount > 0){
            obj.setAmount( amount);
        }
        if (pe != null && pe > 0){
            obj.setPe( pe);
        }
        if (pb != null && pb > 0){
            obj.setPb( pb);
        }
        if (turnoverRate != null && turnoverRate > 0){
            obj.setTurnoverRate( turnoverRate);
        }
        if (chgRate != null && chgRate > 0){
            obj.setChgRate( chgRate);
        }
        if (chgAmount != null && chgAmount > 0){
            obj.setChgAmount( chgAmount);
        }
        if (preClose != null && preClose > 0){
              obj.setPreClose( preClose);
        }
        if (cmv != null && cmv > 0){
            obj.setCmv( cmv);
        }
        if (mv != null && mv > 0){
            obj.setMv( mv);
        }
        /* 保存至数据库 */
        stockDayKRepository.save(obj);

        return Response.newSuccess(stockDayKConverter.toDTO(obj));
    }

    /* Specification */
    @Override
    public Response<Page<stockDayKDTO>> filterStockDayKs(pageStockDayKRequest request) {
        // 创建分页请求对象
        Pageable pageable = PageRequest.of(
            request.getPageNumber(),
            request.getPageSize(),
            // 按交易日期降序排列，确保最新的数据在前面
            org.springframework.data.domain.Sort.by(
                org.springframework.data.domain.Sort.Direction.DESC,
                "tradeDate"
            )
        );

        // 构建查询条件
        Specification<stockDayK> specification = Specification.where(
            stockDayKSpecification.hasSymbolAndSortByTradeDate(request.getSymbol())
        );

        // 执行分页查询
        org.springframework.data.domain.Page<stockDayK> pageResult =
            stockDayKRepository.findAll(specification, pageable);

        // 将结果转换为DTO并返回
        List<stockDayKDTO> dtoList = pageResult.getContent()
            .stream()
            .map(stockDayKConverter::toDTO)
            .collect(java.util.stream.Collectors.toList());

        // 创建分页结果对象（如果Response支持分页结果）
        org.springframework.data.domain.Page<stockDayKDTO> pageResultDTO =
            new org.springframework.data.domain.PageImpl<>(
                dtoList,
                pageResult.getPageable(),
                pageResult.getTotalElements()
            );

        return Response.newSuccess(pageResultDTO);
    }


}