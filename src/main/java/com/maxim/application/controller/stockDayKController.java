package com.maxim.application.controller;

import com.maxim.application.dao.pageStockDayKRequest;
import com.maxim.application.dto.stockDayKDTO;
import com.maxim.application.service.stockDayKServiceImpl;
import com.maxim.application.Response;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;


@RestController
public class stockDayKController {
    @Autowired
    private stockDayKServiceImpl stockDayKServiceImplObj;

    /* Get 方法*/
    @GetMapping("/stockDayK/count/by-day")
    public Response<Long> getStockNumByDay(@PathParam("day") String day){ // 用@Pathparam
        LocalDate date = LocalDate.parse(day);
        return stockDayKServiceImplObj.getStockNumByDay(date);
    }
    @GetMapping("/stockDayK/count/by-day-symbol")
    public Response<Long> getStockNumByDayAndSymbol(@PathParam("day") String day, @PathParam("symbol") String symbol){
        LocalDate date = LocalDate.parse(day);
        return stockDayKServiceImplObj.getStockNumByDayAndSymbol(date, symbol);
    }
    @GetMapping("/stockDayK/search/by-id") // GetMapping 的路径不能冲突!
    public Response<stockDayKDTO> getStockDayKById(@RequestParam("id") long recordId) {
        return stockDayKServiceImplObj.getStockDayKById(recordId);
    }
    @GetMapping("/stockDayK/search/by-day-symbol")
    public Response<stockDayKDTO> getStockDayByDayAndSymbol(
            @RequestParam("day") String day,
            @RequestParam("symbol") String symbol){
        LocalDate date = LocalDate.parse(day);
        return stockDayKServiceImplObj.getStockDayKByDayAndSymbol(date, symbol);
    }

    /* Post 方法*/
    @PostMapping("/stockDayK/add")
    public Response<String> addStockDayK(@RequestBody stockDayKDTO stockDayKDTO){
        // 在service层会做些校验 -> 这个数据能否插入数据库
        return stockDayKServiceImplObj.addStockDayK(stockDayKDTO);
    }
    @PostMapping("/stockDayK/adds")
    public Response<String> addStockDayKs(@RequestBody Collection<stockDayKDTO> stockDayKDTOs){
        // 在service层会做些校验 -> 筛选这些数据中能插入数据库的部分进行插入
        return stockDayKServiceImplObj.addStockDayKs(stockDayKDTOs);
    }

    /* Delete 方法 */
    @DeleteMapping("/stockDayK/delete/by-id")
    public Response<String> deleteStockDayKById(@RequestParam("id") long recordId){
        return stockDayKServiceImplObj.deleteStockDayKById(recordId);
    }
    @DeleteMapping("/stockDayK/delete/by-day-symbol")
    public Response<String> deleteStockDayKByDayAndSymbol(@RequestParam("day") String day, @RequestParam("symbol") String symbol){
        LocalDate date = LocalDate.parse(day);
        return stockDayKServiceImplObj.deleteStockDayKBySymbolAndDay(date, symbol);
    }

    /* Update 方法 */
    @PutMapping("/stockDayK/put/by-day-symbol")
    public Response<stockDayKDTO> updateStockDayKByDayAndSymbol(@RequestParam(name = "day", required = true) String day,
                                                                @RequestParam(name = "symbol", required = true) String symbol,
                                                          @RequestParam(name = "open", required = false) Double open,
                                                          @RequestParam(name = "high", required = false) Double high,
                                                          @RequestParam(name = "low", required = false) Double low,
                                                          @RequestParam(name = "close", required = false) Double close,
                                                          @RequestParam(name = "volume", required = false) Long volume,
                                                          @RequestParam(name = "amount", required = false) Double amount,
                                                          @RequestParam(name = "pe", required = false) Double pe,
                                                          @RequestParam(name = "pb", required = false) Double pb,
                                                          @RequestParam(name = "turnoverRate", required = false) Double turnoverRate,
                                                          @RequestParam(name = "chgRate", required = false) Double chgRate,
                                                          @RequestParam(name = "chgAmount", required = false) Double chgAmount,
                                                          @RequestParam(name = "preClose", required = false) Double preClose,
                                                          @RequestParam(name = "cmv", required = false) Double cmv,
                                                          @RequestParam(name = "mv", required = false) Double mv
                                                          ){
        LocalDate date = LocalDate.parse(day);
        return stockDayKServiceImplObj.updateStockDayK(date, symbol,
                open, close, high, low, volume, amount, pe, pb,
                turnoverRate, chgRate, chgAmount, preClose, cmv, mv);
    }

    /* Specification 方法 */
    /* Specification 方法 - 高级查询和分页 */
    @GetMapping("/stockDayK/search")
    public Response<Page<stockDayKDTO>> searchStockDayKs(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String symbol) {
            pageStockDayKRequest request = new pageStockDayKRequest();
            request.setPageNumber(pageNumber);
            request.setPageSize(pageSize);
            request.setSymbol(symbol);
            return stockDayKServiceImplObj.filterStockDayKs(request);
    }
}
