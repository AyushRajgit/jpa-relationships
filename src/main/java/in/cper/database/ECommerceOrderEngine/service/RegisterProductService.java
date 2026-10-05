package in.cper.database.ECommerceOrderEngine.service;

import in.cper.database.ECommerceOrderEngine.entity.Product;
import in.cper.database.ECommerceOrderEngine.exceptions.EmptyRequestException;
import in.cper.database.ECommerceOrderEngine.exceptions.NotFoundException;
import in.cper.database.ECommerceOrderEngine.exceptions.UnableToSaveException;
import in.cper.database.ECommerceOrderEngine.repository.RegisterProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegisterProductService {

    private RegisterProductRepository registerProductRepository;

    @Autowired
    public RegisterProductService(RegisterProductRepository registerProductRepository) {
        this.registerProductRepository = registerProductRepository;
    }

    @Transactional
    public void register(Product product) {
        Product savedProduct = registerProductRepository.save(product);
        if (savedProduct == null) {
            throw new UnableToSaveException("Product unable to save in database");
        }
    }

    @Transactional
    public void registerAll(List<Product> products) {
        if (products == null || products.isEmpty()) {
            throw new EmptyRequestException("Empty product list while registering");
        }

        for (Product product : products) {
            register(product);
        }
    }

    public Product get(int productId) {
        Product product = registerProductRepository.findById(productId).orElseThrow(
                () -> new NotFoundException("Product not found with id " + productId)
        );

        return product;
    }

    public List<Product> getAll() {
        List<Product> products = registerProductRepository.findAll();

        if (products == null) {
            throw new NotFoundException("Unable to fetch products");
        }

        return products;
    }

    @Transactional
    public Product update(Product product) {
        Product savedProduct = registerProductRepository.findById(product.getProductId()).orElseThrow(
                () -> new NotFoundException("Product not found with id " + product.getProductId() + " while updating")
        );

        savedProduct.setAvailableQuantity(product.getAvailableQuantity());
        savedProduct.setMerchantDetail(product.getMerchantDetail());
        savedProduct.setProductName(product.getProductName());
        savedProduct.setProductPrice(product.getProductPrice());
        savedProduct.setProductDescription(product.getProductDescription());

        Product savingNewProduct = registerProductRepository.save(savedProduct);
        if (savingNewProduct == null) {
            throw new UnableToSaveException("Product unable to save in database after updating");
        }

        return savingNewProduct;
    }

    @Transactional
    public void remove(int productId) {
        Product savedProduct = registerProductRepository.findById(productId).orElseThrow(
                () -> new NotFoundException("Product not found with id " + productId + " while deleting")
        );

        registerProductRepository.delete(savedProduct);
    }
}
