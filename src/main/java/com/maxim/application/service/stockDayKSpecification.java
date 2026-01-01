package com.maxim.application.service;

import com.maxim.application.dao.stockDayK;
import com.maxim.application.dao.stockDayK_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

public class stockDayKSpecification{
    // 以股票名称作为查询条件
    public static Specification<stockDayK> hasSymbol(String symbol){
        return new Specification<stockDayK>() {
            /*
            * root -> 代表Entity，对应SQL中的s -> 用于获取Entity的属性
            * query -> 代表正在创建的查询 select * from stockDayK s where s.symbol = ?1
            * criteriaBuilder -> 用于构造查询条件的builder where s.symbol = ?1
            * */
            @Override
            public @Nullable Predicate toPredicate(Root<stockDayK> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(stockDayK_.SYMBOL), symbol);
            };
        };
    }

    // 以股票名称作为查询条件 + 按交易日期进行排序
    public static Specification<stockDayK> hasSymbolAndSortByTradeDate(String symbol){
        return new Specification<stockDayK>() {
            @Override
            public @Nullable Predicate toPredicate(Root<stockDayK> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                query.orderBy(criteriaBuilder.asc(root.get(stockDayK_.TRADE_DATE)));
                return criteriaBuilder.equal(root.get(stockDayK_.SYMBOL), symbol);
            };
        };
    }

    // 以某日的chgRate的范围进行查询
    public static Specification<stockDayK> chgRateBetween(Double min, Double max){
        return new Specification<stockDayK>() {
            @Override
            public @Nullable Predicate toPredicate(Root<stockDayK> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.between(root.get(stockDayK_.CHG_RATE), min, max);
            };
        };
    }

}
