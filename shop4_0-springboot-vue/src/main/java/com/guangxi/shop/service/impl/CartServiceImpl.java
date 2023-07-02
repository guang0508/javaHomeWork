package com.guangxi.shop.service.impl;

import com.google.gson.Gson;
import com.guangxi.shop.entity.*;
import com.guangxi.shop.mapper.AttributeMapper;
import com.guangxi.shop.mapper.CartMapper;
import com.guangxi.shop.mapper.ProductMapper;
import com.guangxi.shop.service.CartService;
import com.guangxi.shop.util.DateTimeUtil;
import com.guangxi.shop.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private AttributeMapper attributeMapper;
    @Override
    public List getAllCartItem(HttpServletRequest request) {
        User user = null;
        String userInfo = null;
        String token = request.getHeader("token");
        if(token!=null){
            Jedis jedis = new Jedis("127.0.0.1");
            userInfo = jedis.get(token);
            if(userInfo!=null&&!"".equals(userInfo)){
                Gson gson = new Gson();
                user = gson.fromJson(userInfo,User.class);
            }
        }
        List allCartItemListAndTotalPrice = new ArrayList();
        List<CartItemInfo> allCartItemList = cartMapper.getAllCartItemList(user.getId());
        BigDecimal totalPrice = cartMapper.getTotalPrice(user.getId());
        allCartItemListAndTotalPrice.add(allCartItemList);
        allCartItemListAndTotalPrice.add(totalPrice);
        return allCartItemListAndTotalPrice;
    }

    @Override
    public int addCartItem(HttpServletRequest request, String productId, Attribute attribute) {
        String userInfo = null;
        User user = null;
        String token = request.getHeader("token");
        if(token!=null){
            Jedis jedis = new Jedis("127.0.0.1");
            userInfo = jedis.get(token);
            if(userInfo!=null&&!"".equals(userInfo)){
                Gson gson = new Gson();       //将从redis获取的到json字符串转换为user对象
                user = gson.fromJson(userInfo, User.class);
            }
        }
        Product product = productMapper.getProductById(productId);
        String attributeId = UUIDUtil.getIdByMyLength(8).toString();
        String cartId = UUIDUtil.getIdByMyLength(8).toString();
        String userId = user.getId();
        BigDecimal price = product.getPrice();
        Timestamp currentTIme = DateTimeUtil.getCurrentTimeByTimestamp();

        Attribute attribute1 = new Attribute(attributeId, productId, attribute.getColor(), attribute.getSize(), attribute.getCount());
        attributeMapper.addSpecs(attribute1);
        CartItem cartItem = new CartItem(cartId, userId, productId, price, attributeId,currentTIme, currentTIme);
        int success = cartMapper.addCartItem(cartItem);
        return success;
    }

    @Override
    public List<Product> getProductInfo(List<CartItem> cartItemList) {
        List<Product> productInfoList = new ArrayList<>();
        for (CartItem cartItem : cartItemList) {
            String productId = cartItem.getProductId();
            Product productInfo = productMapper.getProductById(productId);
            productInfoList.add(productInfo);
        }
        return productInfoList;
    }

    @Override
    public List<Attribute> getProductSpecs(List<CartItem> cartItemList) {
        List<Attribute> productSpecsList = new ArrayList<>();
        for (CartItem cartItem : cartItemList) {
            String specsId = cartItem.getSpecsId();
            Attribute productSpecs = attributeMapper.getSpecsById(specsId);
            productSpecsList.add(productSpecs);
        }
        return productSpecsList;
    }

    @Override
    public int deleteCartItemById(String id) {
        //CartItem cartItem = cartMapper.getCartItemById(id);
        int success = cartMapper.deleteCartItemById(id);
        //int success2 = attributeMapper.deleteSpecsById(specsId);
        if(success>0){
            return success;
        }else{
            return 0;
        }
    }

    @Override
    public int decCount(String id) {
        int success = cartMapper.decCount(id);
        if(success>0){
            return success;
        }else {
            return 0;
        }
    }

    @Override
    public int incCount(String id) {
        int success = cartMapper.incCount(id);
        if(success>0){
            return success;
        }else {
            return 0;
        }
    }

    @Override
    public int setCount(String id, BigDecimal newCount) {
        int success = cartMapper.setCount(id,newCount);
        if(success>0){
            return success;
        }else {
            return 0;
        }
    }

    @Override
    public BigDecimal getTotalPrice(List<String> cartIdList) {
        BigDecimal totalPrice = new BigDecimal(0);
        for (String id : cartIdList) {
            CartItem cartItem = cartMapper.getCartItemById(id);
            Attribute specsById = attributeMapper.getSpecsById(cartItem.getSpecsId());
            int count = specsById.getCount();
            BigDecimal price = cartItem.getPrice().multiply(BigDecimal.valueOf(count));
            totalPrice = totalPrice.add(price);
        }
        return totalPrice;
    }
}
