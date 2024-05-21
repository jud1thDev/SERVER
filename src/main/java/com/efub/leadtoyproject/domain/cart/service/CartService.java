package com.efub.leadtoyproject.domain.cart.service;

import com.efub.leadtoyproject.domain.cart.domain.Cart;
import com.efub.leadtoyproject.domain.cart.dto.CartItemResponseDto;
import com.efub.leadtoyproject.domain.cart.dto.CartResponseDto;
import com.efub.leadtoyproject.domain.cart.repository.CartRepository;
import com.efub.leadtoyproject.domain.cartitem.domain.CartItem;
import com.efub.leadtoyproject.domain.cartitem.repository.CartItemRepository;
import com.efub.leadtoyproject.domain.item.domain.Item;
import com.efub.leadtoyproject.domain.item.repository.ItemRepository;
import com.efub.leadtoyproject.domain.member.domain.Member;
import com.efub.leadtoyproject.domain.member.repository.MemberRepository;
import com.efub.leadtoyproject.domain.product.domain.Product;
import com.efub.leadtoyproject.domain.product.repository.ProductRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final CartItemRepository cartItemRepository;
    // test를 위해 임시로 멤버 호출
    private final MemberRepository memberRepository;

    // 장바구니 조회
    @Transactional(readOnly = true)
    public CartResponseDto getCartDetails(Long memberId) {
        Cart cart = cartRepository.findCartByMemberId(memberId)
                .orElseThrow(() -> new RuntimeException("장바구니를 찾을 수 없습니다."));

        List<CartItemResponseDto> cartItemDtos = cart.getCartItems().stream()
                .map(cartItem -> new CartItemResponseDto(
                        cartItem.getItem().getProduct().getCategory().getProductType().name(), //TODO enum 확인
                        cartItem.getItem().getProduct().getProductId(),
                        cartItem.getCartItemId(),
                        cartItem.getItem().getProduct().getProductName(),
                        cartItem.getItem().getProduct().getInfo(),
                        cartItem.getItem().getPrice(),
                        cartItem.getItem().getLocation(),
                        cartItem.getItem().getStatus().name() //TODO enum 확인
                )).collect(Collectors.toList());

        return new CartResponseDto(cartItemDtos.size(), cart.getTotalPrice(), cartItemDtos);
    }

    // 장바구니 물건 추가
    public void addItemToCart(Long memberId, Long itemId) {
        Cart cart = cartRepository.findCartByMemberId(memberId).orElseGet(() -> {
            Member member = memberRepository.findById(memberId)
                    .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 멤버입니다."));
            Cart newCart = new Cart(member);
            return cartRepository.save(newCart);
        });
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 물건입니다."));

        // 장바구니 아이템 생성 및 추가
        CartItem newCartItem = new CartItem(cart, item);
        cart.getCartItems().add(newCartItem);
        cart.addCartItem(1L, item.getPrice()); // 현재 아이템을 하나만 추가하고 있기 때문에 count=1로 고정

        // 장바구니 정보 업데이트
        cartRepository.save(cart);
    }

    // 장바구니 물건 삭제
    public void deleteItemToCart(Long memberId, Long cartItemId) {
        Cart cart = cartRepository.findCartByMemberId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 멤버입니다."));
        CartItem cartItem = cartItemRepository.findByCartItemId(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("장바구니 아이템을 찾을 수 없습니다."));
        if (!cart.getCartItems().contains(cartItem)) {
            throw new IllegalArgumentException("이 장바구니에 해당 아이템이 존재하지 않습니다.");
        }

        // 아이템 삭제
        cart.getCartItems().remove(cartItem);
        cartItemRepository.delete(cartItem);

        // 장바구니 총합 및 수량 업데이트
        cart.deleteCartItem(1L, cartItem.getItem().getPrice());
        cartRepository.save(cart);
    }
}
