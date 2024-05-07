package com.tn.service;

import com.tn.entity.Category;
import com.tn.repository.Categoryrepository;
import com.tn.repository.Productrepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryserviceImpl implements Categoryservice{

    private Categoryrepository categoryrepo;

    public CategoryserviceImpl(Categoryrepository categoryrepo) {
        this.categoryrepo = categoryrepo;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = categoryrepo.findAll();
        return categories;
    }
}
