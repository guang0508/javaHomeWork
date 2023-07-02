package com.guangxi.shop.controller;

import com.guangxi.shop.Result.R;
import com.guangxi.shop.entity.*;
import com.guangxi.shop.service.OrderService;
import com.guangxi.shop.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/OrderController")
public class OrderController {

    @Autowired
    public OrderService orderService;

    @RequestMapping("/createOrder")
    public R createOrder(HttpServletRequest request, @RequestBody List<String> cartItemIdList){
        String orderId = orderService.addOrder(cartItemIdList,request);
        if(orderId.length()>0){
            return R.ok().data("orderId",orderId);
        }else {
            return R.error().message("创建失败，请稍后重试");
        }
    }

    @RequestMapping("/orderInfo/{orderId}")
    public R confirmPageOrderInfo(@PathVariable String orderId){
        List<OrderItemInfo> orderItemInfoList = orderService.getOrderItemInfoByOrderId(orderId);
        Order order = orderService.getOrderById(orderId);
        if(orderItemInfoList.size()>0){
            return R.ok().data("orderItemInfoList",orderItemInfoList).data("totalPrice",order.getPrice());
        }else{
            return R.error().message("获取失败");
        }
    }

        @RequestMapping("/confirmOrder/{orderId}")
    public R confirmPageOrderInfo(@RequestBody OrderInfo orderInfo,@PathVariable String orderId){
        Order order = new Order();
        order.setId(orderId);
        order.setAddress(orderInfo.getAddress());
        order.setTelephone(orderInfo.getTelephone());
        order.setAddressee(orderInfo.getName());
        order.setRemark(orderInfo.getRemark());
        order.setUpdateTime(DateTimeUtil.getCurrentTimeByTimestamp());
        int success = orderService.updateOrderInfo(order);
        if(success>0){
            return R.ok().message("已下单，请等待卖家发货");
        }else{
            return R.error().message("下单失败，请稍后重试");
        }
    }

    @RequestMapping("/getAllOrder")
    public R getAllOrder(HttpServletRequest request){
        List<Order> orderList =  orderService.getAllOrder(request);
        for (int i = 0; i < orderList.size(); i++) {
            List<OrderItemInfo> orderItemInfoList = orderService.getOrderItemInfoByOrderId(orderList.get(i).getId());
            orderList.get(i).setOrderItemInfoByOrderId(orderItemInfoList);
        }
        return R.ok().data("orderList",orderList);
    }

    @RequestMapping("/cancelOrder/{orderId}")
    public R cancelOrder(@PathVariable String orderId){
        int success = orderService.deleteOrder(orderId);
        if(success>0){
            return R.ok().message("订单已取消");
        }else{
            return R.error().message("取消失败,请稍后再试");
        }
    }
}
