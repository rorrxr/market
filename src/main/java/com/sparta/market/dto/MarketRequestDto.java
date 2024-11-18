package com.sparta.market.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MarketRequestDto {

    private Long id;

    private String title;

    private String content;

    private int price;

    private String username;

}
