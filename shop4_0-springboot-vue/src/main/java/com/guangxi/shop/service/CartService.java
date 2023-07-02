package com.guangxi.shop.service;

import com.guangxi.shop.entity.Attribute;
import com.guangxi.shop.entity.CartItem;
import com.guangxi.shop.entity.CartItemInfo;
import com.guangxi.shop.entity.Product;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;


public interface CartService {
    //获取所有购物车项
    public List<CartItemInfo> getAllCartItem(HttpServletRequest request);
    //根据商品id添加购物车
    int addCartItem(HttpServletRequest request, String productId, Attribute attribute);
    //获取购物车商品信息
    List<Product> getProductInfo(List<CartItem> cartItemList);
    //获取购物车商品规格
    List<Attribute> getProductSpecs(List<CartItem> cartItemList);
    //根据id移除购物车项
    int deleteCartItemById(String id);
    //某项数量减一
    int decCount(String id);
    //某项数量加一
    int incCount(String id);
    //自定义修改数量
    int setCount(String id, BigDecimal newCount);

    BigDecimal getTotalPrice(List<String> cartIdList);
}
