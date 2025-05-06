package com.tn.model;
import com.tn.entity.Product;
import lombok.Data;

@Data
public class Item {
    private Long id;
    private String productname;
    private String Image;
    private float price;
    private int quantity;
    private float total;
    public Item() {
        // TODO Auto-generated constructor stub
    }

    public Item(Product p) {
        this.id = p.getProductId();
        this.productname = p.getProductname();
        this.Image = p.getImage().split(",")[0];
        this.price = p.getPrice();
        this.quantity = 1;
        this.total = p.getPrice()*quantity;
    }
    // Hàm tính toán tổng giá khi thay đổi số lượng
    public void updateTotal() {
        this.total = this.price * this.quantity;
    }
}
