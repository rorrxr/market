package com.sparta.market.controller;

import com.sparta.market.dto.MarketRequestDto;
import com.sparta.market.dto.MarketResponseDto;
import com.sparta.market.service.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MarketController {

    @Autowired
    private final MarketService marketService;

    @GetMapping("/post")
    public String post() {
        return "post";
    }

    @GetMapping("/posts")
    public List<MarketResponseDto> getAllPosts() {
        return marketService.getAllPosts();
    }

    @GetMapping("/post/{id}")
    public MarketResponseDto getPostById(@PathVariable Long id) {
        return marketService.getPostById(id);
    }


    // 판매 게시글 작성
    @PostMapping("/post")
    public MarketResponseDto createPost(@RequestBody MarketRequestDto requestDto) {
        return marketService.save(requestDto);
    }

    // 게시글 수정
    @PutMapping("/post/{id}")
    public MarketResponseDto updatePost(@PathVariable Long id, @RequestBody MarketRequestDto requestDto) {
        return marketService.update(id, requestDto);
    }

    // 게시글 삭제
    @DeleteMapping("/post/{id}")
    public String deletePost(@PathVariable Long id) {
        return marketService.delete(id);
    }
}
