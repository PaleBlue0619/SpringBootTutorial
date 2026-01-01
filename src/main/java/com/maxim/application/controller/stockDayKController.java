package com.maxim.application.controller;

import com.maxim.application.dao.stockDayK;
import com.maxim.application.dto.stockDayKDTO;
import com.maxim.application.service.stockDayKService;
import com.maxim.application.Response;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;


@RestController
public class stockDayKController {
    @Autowired
    private stockDayKService stockDayKServiceObj;

    /* Get 方法*/
    @GetMapping("/stockDayK/count")
    public Response<Long> getStockNumByDay(@PathParam("day") String day){ // 用@Pathparam
        LocalDate date = LocalDate.parse(day);
        return stockDayKServiceObj.getStockNumByDaySQL(date);
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
}
