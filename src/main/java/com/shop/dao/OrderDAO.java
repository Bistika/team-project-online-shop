package com.shop.dao;

import com.shop.exception.ShopTechnicalException;
import com.shop.model.User;
import com.shop.model.Cart;
import java.io.File;

public class OrderDAO {
    FileEdit<Cart> fileEdit = new FileEdit<>();
    public void createOrder(Cart cart, User user) throws ShopTechnicalException {
        String filename = user.getEmail() + java.time.LocalDate.now()+ System.currentTimeMillis() + ".txt";
        File file = new File("./src/main/resources/orders/" + filename) ;
        fileEdit.write("orders/"+filename, cart);
    }
}
