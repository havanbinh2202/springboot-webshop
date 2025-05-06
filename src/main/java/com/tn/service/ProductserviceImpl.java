package com.tn.service;

import com.tn.entity.Product;
import com.tn.repository.Productrepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductserviceImpl implements Productservice {

    private Productrepository productrepo;

    public ProductserviceImpl(Productrepository productrepo) {
        this.productrepo = productrepo;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = productrepo.findAll();
        return products;
     }
    public Product getById(Long productId) {
        // Tìm sản phẩm bằng productId, bạn có thể điều chỉnh nếu bạn dùng kiểu dữ liệu khác
        return productrepo.findById(productId).orElse(null);  // Nếu không tìm thấy trả về null
    }


}

