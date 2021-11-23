package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.Item;
import com.endava.groceryshopservice.entities.Product;
import com.endava.groceryshopservice.entities.User;
import com.endava.groceryshopservice.entities.dto.ItemRequestDTO;
import com.endava.groceryshopservice.entities.dto.ItemResponseDTO;
import com.endava.groceryshopservice.entities.dto.ItemToAddDeleteRequestDTO;
import com.endava.groceryshopservice.entities.dto.UserRequestDTO;
import com.endava.groceryshopservice.exceptions.InvalidQuantityException;
import com.endava.groceryshopservice.exceptions.NoItemFoundException;
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
    private final ProductServiceImpl productService;
    private final UserServiceImpl userService;

    @Override
    public List<ItemResponseDTO> findUserCart(String email) {
        return itemRepository.findByUser_Email(email)
                .stream()
                .map(Item::toItemResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addItemToCart(ItemToAddDeleteRequestDTO requestDTO) {
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
        ItemToAddDeleteRequestDTO item;
        if (requestDTO.getCartItems() != null && !requestDTO.getCartItems().isEmpty()) {
            for (ItemRequestDTO itemRequestDTO : requestDTO.getCartItems()) {
                item = ItemToAddDeleteRequestDTO.builder()
                        .quantity(itemRequestDTO.getQuantity())
                        .size(itemRequestDTO.getSize())
                        .userEmail(requestDTO.getEmail())
                        .productId(itemRequestDTO.getProductId())
                        .build();
                addItemToCart(item);
            }
        }
    }

    @Override
    public void deleteItem(ItemToAddDeleteRequestDTO itemToDeleteRequestDTO) {
        User user = userService.getByEmail(itemToDeleteRequestDTO.getUserEmail());
        Product product = productService.getById(itemToDeleteRequestDTO.getProductId());
        Item item = itemRepository.findByUserAndProductAndSize(user, product, itemToDeleteRequestDTO.getSize());
        if (item == null) {
            throw new NoItemFoundException("Could not find item ");
        }

        if (itemToDeleteRequestDTO.getQuantity() > item.getQuantity()) {
            throw new InvalidQuantityException("Requested quantity cannot be bigger than stored quantity");
        }

        if (item.getQuantity() > itemToDeleteRequestDTO.getQuantity()) {
            item.setQuantity(item.getQuantity() - itemToDeleteRequestDTO.getQuantity());
            itemRepository.save(item);
        } else {
            itemRepository.delete(item);
        }
    }
}