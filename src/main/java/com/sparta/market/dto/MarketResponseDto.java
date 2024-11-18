package com.sparta.market.dto;

import com.sparta.market.entity.Market;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor      // 추가
public class MarketResponseDto {

    private Long id;

    private String title;

    private String content;

    private int price;

    private String username;

    public MarketResponseDto(Market market) {
        this.id = market.getId();
        this.title = market.getTitle();
        this.content = market.getContent();
        this.price = market.getPrice();
        this.username = market.getUsername();
    }

}
