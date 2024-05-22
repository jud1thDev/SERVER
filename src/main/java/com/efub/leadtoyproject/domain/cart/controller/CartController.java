package com.efub.leadtoyproject.domain.cart.controller;

import com.efub.leadtoyproject.domain.cart.dto.CartResponseDto;
import com.efub.leadtoyproject.domain.cart.dto.MessageResponseDto;
import com.efub.leadtoyproject.domain.cart.service.CartService;
import com.efub.leadtoyproject.domain.member.domain.Member;
import com.efub.leadtoyproject.global.login.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final AuthUtils authUtils;

    /*** 장바구니 물품 전체조회*/
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public CartResponseDto getCartByMemberId() {
        Member currentMember = authUtils.getMember();
        if (currentMember == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
        }
        return cartService.getCartDetails(currentMember.getMemberId());
    }

    /** 장바구니 물품 추가*/
    @PostMapping("/products/{itemId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public MessageResponseDto addCartItem(@PathVariable final Long itemId) {
        Member currentMember = authUtils.getMember();
        if (currentMember == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
        }
        cartService.addItemToCart(currentMember.getMemberId(), itemId);
        return new MessageResponseDto("상품이 장바구니에 추가되었습니다.");
    }

    /** 장바구니 물품 삭제*/
    @DeleteMapping("/cart-item/{cartItemId}")
    @ResponseStatus(value = HttpStatus.OK)
    public MessageResponseDto deleteCartItem(@PathVariable final Long cartItemId) {
        Member currentMember = authUtils.getMember();
        if (currentMember == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
        }
        cartService.deleteItemToCart(currentMember.getMemberId(), cartItemId);
        return new MessageResponseDto("상품이 장바구니에서 삭제되었습니다.");
    }
}
