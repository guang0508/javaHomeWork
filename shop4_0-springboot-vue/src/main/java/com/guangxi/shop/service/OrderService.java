package com.guangxi.shop.service;

import com.guangxi.shop.entity.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderService {
    String addOrder(List<String> catItemIdList,  HttpServletRequest request); //添加订单

    List<OrderItemInfo> getOrderItemInfoByOrderId(String orderId);

    Order getOrderById(String orderId);

    int updateOrderInfo(Order order);

    List<Order> getAllOrder(HttpServletRequest request);

    int deleteOrder(String orderId);
}
