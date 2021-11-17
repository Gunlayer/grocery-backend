package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.Item;
import com.endava.groceryshopservice.entities.Product;
import com.endava.groceryshopservice.entities.User;
import com.endava.groceryshopservice.entities.dto.ItemRequestDTO;
import com.endava.groceryshopservice.entities.dto.ItemResponseDTO;
import com.endava.groceryshopservice.entities.dto.ItemToAddRequestDTO;
import com.endava.groceryshopservice.entities.dto.UserRequestDTO;
import com.endava.groceryshopservice.exceptions.NoProductFoundException;
import com.endava.groceryshopservice.repositories.ItemRepository;
import com.endava.groceryshopservice.repositories.ProductRepository;
import com.endava.groceryshopservice.repositories.UserRepository;
import com.endava.groceryshopservice.services.ItemService;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public List<ItemResponseDTO> findUserCart(String email) {
        return itemRepository.findByUser_Email(email)
                .stream()
                .map(Item::toItemResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addItemToCart(ItemToAddRequestDTO requestDTO) {
        User user = userRepository.getByEmail(requestDTO.getUserEmail());
        Product product = productRepository.findById(requestDTO.getProductId()).orElseThrow(
                () -> new NoProductFoundException("Could not find a product with id " + requestDTO.getProductId()));
        Item existingItem = itemRepository.findByUserAndProductAndSize(user, product, requestDTO.getSize());
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + requestDTO.getQuantity());
            itemRepository.save(existingItem);
        } else {
            Item item = Item.builder()
                    .user(user)
                    .product(product)
                    .size(requestDTO.getSize())
                    .quantity(requestDTO.getQuantity())
                    .build();
            itemRepository.save(item);
        }
    }

    @Override
    public void addItems(UserRequestDTO requestDTO) {
        ItemToAddRequestDTO item;
        if (requestDTO.getCartItems() != null && !requestDTO.getCartItems().isEmpty()) {
            for (ItemRequestDTO itemRequestDTO : requestDTO.getCartItems()) {
                item = ItemToAddRequestDTO.builder()
                        .quantity(itemRequestDTO.getQuantity())
                        .size(itemRequestDTO.getSize())
                        .userEmail(requestDTO.getEmail())
                        .productId(itemRequestDTO.getProductId())
                        .build();
                addItemToCart(item);
            }
        }
    }
}