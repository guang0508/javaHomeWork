package com.guangxi.shop.mapper;

import com.guangxi.shop.entity.CartItemInfo;
import com.guangxi.shop.entity.Order;
import com.guangxi.shop.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface OrderMapper {
    public CartItemInfo getOrderProductInfoByCartItemId(String id);
    public int addOrder(Order order);//添加订单
    int addOrderDetail(OrderDetail orderDetail);//添加订单详情信息

    List<OrderDetail> getOrderDetailByOrderId(String orderId);

    Order getOrderById(String orderId);

    int updateOrderInfo(Order order);

    List<Order> getAllMyOrder(String id);

    int deleteOrderById(String orderId);

    int deleteOrderDetailById(String orderId);
}
