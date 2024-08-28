package com.example.abc_restaurant_system.service.impl;

import com.example.abc_restaurant_system.dto.CategoryDto;
import com.example.abc_restaurant_system.entity.Category;
import com.example.abc_restaurant_system.entity.Role;
import com.example.abc_restaurant_system.entity.User;
import com.example.abc_restaurant_system.repository.CategoryRepository;
import com.example.abc_restaurant_system.repository.UserRepository;
import com.example.abc_restaurant_system.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public String createCategory(CategoryDto categoryDto) {

        User user = userRepository.findByUserName(categoryDto.getUserName());

        if(user!= null){

            if(!categoryRepository.existsByName(categoryDto.getName())) {

                Set<Role> roleSet = new HashSet<>();
                roleSet = user.getRoles();
                Role role = roleSet.iterator().next();

                if (role.getRoleName().equals("ROLE_ADMIN")) {

                    Category category = new Category();
                    category.setName(categoryDto.getName());
                    category.setDescription(categoryDto.getDescription());
                    category.setIsActive(categoryDto.getIsActivate());
                    category.setCreatedDate(new Date());
                    category.setCreatedUser(user);
                    categoryRepository.save(category);

                    return "Success created";

                } else {
                    return "You don't have permission create category";
                }
            }else {
                return "category name is already exists";
            }

        }else {
            return "user name is invalid";
        }

    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public String updateCategory(CategoryDto categoryDto,Integer id) {
        User user = userRepository.findByUserName(categoryDto.getUserName());

        Category category = categoryRepository.findById(id).get();

        if(user!= null){

            if(!categoryRepository.existsByName(categoryDto.getName())||category.getName().equals(categoryDto.getName())) {

                Set<Role> roleSet = new HashSet<>();
                roleSet = user.getRoles();
                Role role = roleSet.iterator().next();

                if (role.getRoleName().equals("ROLE_ADMIN")) {


                    category.setName(categoryDto.getName());
                    category.setDescription(categoryDto.getDescription());
                    category.setIsActive(categoryDto.getIsActivate());
                    category.setCreatedDate(new Date());
                    category.setCreatedUser(user);
                    categoryRepository.save(category);

                    return "Success Updated";

                } else {
                    return "You don't have permission create category";
                }
            }else {
                return "category name is already exists";
            }

        }else {
            return "user name is invalid";
        }
    }

    @Override
    public List<Category> getAllActive() {
        return categoryRepository.findAllByIsActive(true);
    }
}
