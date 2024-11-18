package com.sparta.market.service;

import com.sparta.market.dto.MarketRequestDto;
import com.sparta.market.dto.MarketResponseDto;
import com.sparta.market.entity.Market;
import com.sparta.market.repository.MarketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MarketService {

    private final MarketRepository marketRepository;

    @Transactional
    public MarketResponseDto save(final MarketRequestDto params) {

        Market market = marketRepository.save(new Market(params));
        return new MarketResponseDto(market);
    }

    /**
     * 게시글 리스트 조회
     */
    public List<MarketResponseDto> findAll() {

        List<Market> list = marketRepository.findAll();
        return list.stream().map(MarketResponseDto::new).collect(Collectors.toList());
    }

    /**
     * 게시글 수정
     */

    @Transactional
    public MarketResponseDto update(Long id, MarketRequestDto requestDto) {
        // 게시글을 ID로 찾아서 수정
        Market market = marketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        // 게시글 수정
        market.update(requestDto);

        // 수정된 게시글 저장
//        marketRepository.save(market);

        return new MarketResponseDto(market);
    }

    @Transactional
    // 게시글 삭제
    public String delete(Long id) {
        // 게시글을 ID로 찾아서 삭제
        Market market = marketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        // 게시글 삭제
        marketRepository.delete(market);

        return "게시글 삭제 완료";
    }

    // 모든 게시글 조회
    public List<MarketResponseDto> getAllPosts() {
        List<Market> markets = marketRepository.findAll();
        return markets.stream()
                .map(MarketResponseDto::new)
                .collect(Collectors.toList());
    }

    // 특정 게시글 조회
    public MarketResponseDto getPostById(Long id) {
        Market market = marketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        return new MarketResponseDto(market);
    }

}
