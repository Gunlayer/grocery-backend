package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.Product;
import com.endava.groceryshopservice.entities.Views;
import com.endava.groceryshopservice.exceptions.NoProductFoundException;
import com.endava.groceryshopservice.repositories.ProductRepository;
import com.endava.groceryshopservice.services.ProductService;
import com.endava.groceryshopservice.services.ViewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ViewsService viewsService;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product save(Product product) {
        product.setViews(new Views(product, 0));
        return productRepository.save(product);
    }

    @Override
    public List<Product> getMostViewed(int number) {
        List<Views> views = viewsService.getMostViewed(number);
        return views.stream().map(Views::getProduct).collect(Collectors.toList());
    }

    @Override
    public Product getById(long id) {
        Product res = productRepository.findById(id).orElseThrow(
                () -> new NoProductFoundException(String.format("Could not find a product with specified id[%d]", id)));
        viewsService.increaseViewNumber(res.getViews());
        return res;
    }
}