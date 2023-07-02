package com.guangxi.shop.mapper;

import com.guangxi.shop.entity.CartItem;
import com.guangxi.shop.entity.CartItemInfo;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface CartMapper {

    //添加一个购物车项
    public int addCartItem(CartItem cartItem);

    //通过用户id获取所有的购物车项集合
    public List<CartItemInfo> getAllCartItemList(String uId);
    //通过id删除某购物车项
    int deleteCartItemById(String id);
    //通过id查询购物车项
    CartItem getCartItemById(String id);
    //获取购物车总价
    BigDecimal getTotalPrice(String id);

    int decCount(String id);

    int incCount(String id);

    int setCount(String id, BigDecimal newCount);
}
