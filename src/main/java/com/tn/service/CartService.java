package com.tn.service;

import com.tn.entity.*;
import com.tn.model.Item;
import com.tn.repository.Accountrepository;
import com.tn.repository.OrderDetailRepository;
import com.tn.repository.OrderRepository;
import com.tn.repository.Productrepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class CartService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository detailRepository;

    @Autowired
    Accountrepository accountRepository;

    @Autowired
    Productrepository productRepository;

    public List<Item> addCart(Product product, List<Item> items) {
        var find = false;
        for (Item item : items) {
            if (item.getId().equals(product.getProductId())) {
                item.setQuantity(item.getQuantity() + 1);
                item.setTotal(item.getPrice() * item.getQuantity());
                find = true;
                break;
            }
        }
        if (!find) {
            items.add(new Item(product));
        }
        return items;
    }

    public List<Item> removeCart(Long productId, List<Item> items) {
        Iterator<Item> temp=items.iterator();
        while(temp.hasNext()) {
            var item=temp.next();
            if (item.getId().equals(productId)) {
                temp.remove();
                break;
            }
        }
        return items;
    }
    public List<Item> updateCart(Map<String, Integer> updates, List<Item> items){
        for (Item item : items) {
            if (updates.containsKey(item.getId())) {
                int newQty = updates.get(item.getId());
                if (newQty > 0) {
                    item.setQuantity(newQty);
                    item.setTotal(item.getPrice() * newQty);
                }
            }
        }
        return items;
    }

    @Transactional
    public String insertOrder(Order order, List<Item> items) {
        String orderId = "HD" + (new SimpleDateFormat("ddMMyyhhmm").format(new Date()));
        order.setOrderId(orderId);
        order.setStatus(OrderStatus.ORDER_NEW);

        // Lấy người dùng từ SecurityContext
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        // Lấy tài khoản từ repository dựa trên username
        Account account = accountRepository.findByUsername(username);

        if (account == null) {
            return "Tài khoản không tồn tại";
        }

        order.setAccount(account); // Liên kết đơn hàng với tài khoản
        orderRepository.save(order);

        // Lưu chi tiết đơn hàng
        for (Item item : items) {
            // Kiểm tra xem sản phẩm có tồn tại không trước khi thêm vào OrderDetail
            Product product = productRepository.findById(item.getId()).orElse(null);

            if (product == null) {
                return "Sản phẩm không tồn tại với ID: " + item.getId();
            }

            // Tạo OrderDetail và lưu
            OrderDetail detail = new OrderDetail(item.getPrice(), item.getQuantity(), product, order);
            detailRepository.save(detail);
        }

        return "Đặt hàng thành công";
    }



}
