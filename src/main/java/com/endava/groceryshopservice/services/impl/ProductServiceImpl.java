package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.Product;
import com.endava.groceryshopservice.entities.Views;
import com.endava.groceryshopservice.exceptions.NoProductFoundException;
import com.endava.groceryshopservice.repositories.ProductRepository;
import com.endava.groceryshopservice.services.ProductService;
import com.endava.groceryshopservice.services.ViewsService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ViewsService viewsService;

    @Override
    public Page<Product> getAll(String name, Pageable page) {
        if (name.isEmpty())
            return productRepository.findAll(page);
        return productRepository.findByNameContainsIgnoreCase(name, page);
    }

    @Override
    public long getCountAll(String name) {
        if (name.isEmpty())
            return productRepository.count();
        return productRepository.countByNameContainsIgnoreCase(name);
    }

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            product.setViews(new Views(product, 0));
        } else {
            Product existingProduct = productRepository.findById(product.getId()).orElseThrow(
                    () -> new NoProductFoundException(String.format("Could not find a product with specified id[%d]", product.getId())));
            product.setViews(existingProduct.getViews());
            product.setProductsReviews(existingProduct.getProductsReviews());
        }
        return productRepository.save(product);
    }

    @Override
    public Product deleteById(long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new NoProductFoundException(String.format("Could not find a product with specified id[%d]", id)));
        productRepository.delete(product);
        return product;
    }

    @Override
    public List<Product> getMostViewed(int productsAmount) {
        List<Views> views = viewsService.getMostViewed(productsAmount);
        return views.stream()
                .map(Views::getProduct)
                .collect(Collectors.toList());
    }

    @Override
    public Product getById(long id) {
        Product res = productRepository.findById(id).orElseThrow(
                () -> new NoProductFoundException(String.format("Could not find a product with specified id[%d]", id)));
        viewsService.increaseViewNumber(res.getViews());
        return res;
    }

    @Override
    public void setRatingForProduct(long productId) {
        productRepository.setRatingForProduct(productId);
    }
}