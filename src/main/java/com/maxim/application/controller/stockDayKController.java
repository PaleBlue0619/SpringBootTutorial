package com.maxim.application.controller;

import com.maxim.application.dao.stockDayK;
import com.maxim.application.dto.stockDayKDTO;
import com.maxim.application.service.stockDayKService;
import com.maxim.application.Response;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;


@RestController
public class stockDayKController {
    @Autowired
    private stockDayKService stockDayKServiceObj;

    /* Get 方法*/
    @GetMapping("/stockDayK/count/by-day")
    public Response<Long> getStockNumByDay(@PathParam("day") String day){ // 用@Pathparam
        LocalDate date = LocalDate.parse(day);
        return stockDayKServiceObj.getStockNumByDay(date);
    }
    @GetMapping("/stockDayK/count/by-day-symbol")
    public Response<Long> getStockNumByDayAndSymbol(@PathParam("day") String day, @PathParam("symbol") String symbol){
        LocalDate date = LocalDate.parse(day);
        return stockDayKServiceObj.getStockNumByDayAndSymbol(date, symbol);
    }
    @GetMapping("/stockDayK/search/by-id") // GetMapping 的路径不能冲突!
    public Response<stockDayKDTO> getStockDayKById(@RequestParam("id") long recordId) {
        return stockDayKServiceObj.getStockDayKById(recordId);
    }
    @GetMapping("/stockDayK/search/by-day-symbol")
    public Response<stockDayKDTO> getStockDayByDayAndSymbol(
            @RequestParam("day") String day,
            @RequestParam("symbol") String symbol){
        LocalDate date = LocalDate.parse(day);
        return stockDayKServiceObj.getStockDayKByDayAndSymbol(date, symbol);
    }

    /* Post 方法*/
    @PostMapping("/stockDayK/add")
    public Response<String> addStockDayK(@RequestBody stockDayKDTO stockDayKDTO){
        // 在service层会做些校验 -> 这个数据能否插入数据库
        return stockDayKServiceObj.addStockDayK(stockDayKDTO);
    }
    @PostMapping("/stockDayK/adds")
    public Response<String> addStockDayKs(@RequestBody Collection<stockDayKDTO> stockDayKDTOs){
        // 在service层会做些校验 -> 筛选这些数据中能插入数据库的部分进行插入
        return stockDayKServiceObj.addStockDayKs(stockDayKDTOs);
    }

    /* Delete 方法 */
    @DeleteMapping("/stockDayK/delete/by-id")
    public Response<String> deleteStockDayKById(@RequestParam("id") long recordId){
        return stockDayKServiceObj.deleteStockDayKById(recordId);
    }
    @DeleteMapping("/stockDayK/delete/by-day-symbol")
    public Response<String> deleteStockDayKByDayAndSymbol(@RequestParam("day") String day, @RequestParam("symbol") String symbol){
        LocalDate date = LocalDate.parse(day);
        return stockDayKServiceObj.deleteStockDayKBySymbolAndDay(date, symbol);
    }

}
