package com.maxim.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/*
* JPA 部分代码
* */
@Repository
public interface stockDayKRepository extends JpaRepository<stockDayK, Long> {

    // 这里JAP命名规则 -> 会自动统计一个trade_date列的个数
    Long countByTradeDate(LocalDate day);

    /* 对于更为复杂的SQL查询, 可以使用@SQL关键字进行查询
    @Param -> 命名参数
    ?1 ?2 -> 位置参数 & :param1 :param2 -> 直接使用参数名称作为占位符
    nativeQuery: Native Query是指直接使用数据库的原生SQL语句，而不是通过JPA或Hibernate的查询语言（如JPQL）进行查询 -> 更高的性能
    */
    @Query(value = "SELECT s FROM stockDayK s WHERE s.tradeDate = :day AND s.symbol = :symbol", nativeQuery = false)
    stockDayK getStockDayKByDayAndSymbol(@Param("day") LocalDate day, @Param("symbol") String symbol);

    @Query(value = "SELECT COUNT(*) from stockDayK where trade_date = ?1", nativeQuery = true)
    Long countByTradeDateSQL(LocalDate day);

}
