package com.guangxi.shop.service.impl;

import com.google.gson.Gson;
import com.guangxi.shop.entity.*;
import com.guangxi.shop.mapper.AttributeMapper;
import com.guangxi.shop.mapper.CartMapper;
import com.guangxi.shop.mapper.OrderMapper;
import com.guangxi.shop.mapper.ProductMapper;
import com.guangxi.shop.service.CartService;
import com.guangxi.shop.service.OrderService;
import com.guangxi.shop.util.DateTimeUtil;
import com.guangxi.shop.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    public OrderMapper orderMapper;
    @Autowired
    public CartMapper cartMapper;
    @Autowired
    public ProductMapper productMapper;
    @Autowired
    public AttributeMapper attributeMapper;
    @Autowired
    private CartService cartService;
    @Override
    public String addOrder(List<String> catItemIdList,HttpServletRequest request) {
        StringBuffer id = UUIDUtil.getIdByMyLength(12);//设置orderId
        String orderId = id.toString();

        String userInfo = null; //设置userId
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

 //将商品信息添加至订单详情表
        for (String cartItemId : catItemIdList) {
            CartItem cartItem = cartMapper.getCartItemById(cartItemId);
            String specsId = cartItem.getSpecsId();
            String productId = cartItem.getProductId();
            Timestamp time = DateTimeUtil.getCurrentTimeByTimestamp();
            orderMapper.addOrderDetail(new OrderDetail(UUIDUtil.getIdByMyLength(8).toString(),orderId,productId,specsId,time,time));
        }

        Timestamp time = DateTimeUtil.getCurrentTimeByTimestamp();
        BigDecimal totalPrice = cartService.getTotalPrice(catItemIdList);
        Order order = new Order(orderId, user.getId(), null, null,null,null, totalPrice,0, 0, 0, time, time);
        int success = orderMapper.addOrder(order);//将订单添加至订单表
        if(success>0){
            return orderId;
        }else {
            return null;
        }
    }

    @Override
    public List<OrderItemInfo> getOrderItemInfoByOrderId(String orderId) {
        List<OrderItemInfo> orderInfoList = new ArrayList<>();
        List<OrderDetail> orderDetailList = orderMapper.getOrderDetailByOrderId(orderId);

        for (OrderDetail orderDetail : orderDetailList) {
            Product productInfo = productMapper.getProductById(orderDetail.getProductId());
            Attribute specsInfo = attributeMapper.getSpecsById(orderDetail.getSpecId());
            OrderItemInfo orderItemInfo = new OrderItemInfo(orderId, productInfo.getName(), productInfo.getCover(), productInfo.getPrice(), specsInfo.getCount(), specsInfo.getSize(), specsInfo.getColor(), productInfo.getShopName());
            orderInfoList.add(orderItemInfo);
        }

        return orderInfoList;
    }

    @Override
    public Order getOrderById(String orderId) {
        Order order = orderMapper.getOrderById(orderId);
        return order;
    }

    @Override
    public int updateOrderInfo(Order order) {
        int success = orderMapper.updateOrderInfo(order);
        return success;
    }

    @Override
    public List<Order> getAllOrder(HttpServletRequest request) {
        String userInfo = null; //设置userId
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

        List<Order> orderList = orderMapper.getAllMyOrder(user.getId());
        return orderList;
    }

    @Override
    public int deleteOrder(String orderId) {
        int success =  orderMapper.deleteOrderById(orderId);
        List<OrderDetail> orderDetailList = orderMapper.getOrderDetailByOrderId(orderId);
        for (OrderDetail orderDetail : orderDetailList) {
            attributeMapper.deleteSpecsById(orderDetail.getSpecId());
        }
        int success2 = orderMapper.deleteOrderDetailById(orderId);
        return success;
    }


}
