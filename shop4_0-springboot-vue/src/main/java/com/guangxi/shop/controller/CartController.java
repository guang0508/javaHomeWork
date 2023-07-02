package com.guangxi.shop.controller;

import com.google.gson.Gson;
import com.guangxi.shop.Result.R;
import com.guangxi.shop.entity.Attribute;
import com.guangxi.shop.entity.CartItem;
import com.guangxi.shop.entity.CartItemInfo;
import com.guangxi.shop.entity.Product;
import com.guangxi.shop.service.CartService;
import com.guangxi.shop.service.ProductService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/CartController")
@CrossOrigin
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;

    @RequestMapping("/addCartItem/{productId}")
    public R addCartItem(HttpServletRequest request,@PathVariable String productId,@RequestBody Attribute attribute){
        Product product = productService.selectProductById(productId);
        int i = cartService.addCartItem(request, productId, attribute);
        if (i>0){
            return R.ok().message("添加成功");
        }else{
            return R.error().message("失败");
        }
    }

    @RequestMapping("/getCartItemList")
    public R getCartItemList(HttpServletRequest request){
        List allCartItemListAndTotalPrice = cartService.getAllCartItem(request);
        List<CartItem> cartItemList = (List<CartItem>) allCartItemListAndTotalPrice.get(0);
        BigDecimal totalPrice = (BigDecimal) allCartItemListAndTotalPrice.get(1);
        if (cartItemList.size()>0){
            return R.ok().data("cartItemList",cartItemList).data("totalPrice",totalPrice);
        }else{
            return R.error().message("购物车为空,快去看看吧~");
        }
    }

    @RequestMapping("/deleteCartItem/{id}")
    public R deleteCartItem(@PathVariable String id){
        int success = cartService.deleteCartItemById(id);
        if (success>0){
            return R.ok().message("移除购物车成功");
        }else{
            return R.error().message("移除失败，请稍后再试");
        }
    }

    @RequestMapping("/updateCount/decCount/{id}")
    public R decCount(@PathVariable String id){
        int success = cartService.decCount(id);
        if (success>0){
            return R.ok();
        }else{
            return R.error().message("修改失败，请稍后再试");
        }
    }

    @RequestMapping("/updateCount/incCount/{id}")
    public R incCount(@PathVariable String id){
        int success = cartService.incCount(id);
        if (success>0){
            return R.ok();
        }else{
            return R.error().message("修改失败，请稍后再试");
        }
    }
    @RequestMapping("/updateCount/setCount/{id}/{newCount}")
    public R incCount(@PathVariable String id,@PathVariable BigDecimal newCount){
        int success = cartService.setCount(id,newCount);
        if (success>0){
            return R.ok();
        }else{
            return R.error().message("修改失败，请稍后再试");
        }
    }

    @RequestMapping("/getTotalPrice")
    public R getTotalPrice(@RequestBody List<String> cartIdList){
        BigDecimal totalPrice = cartService.getTotalPrice(cartIdList);
        if (totalPrice!=null){
            return R.ok().data("totalPrice",totalPrice);
        }else{
            return R.ok().data("totalPrice",0);
        }
    }

    @RequestMapping("/deleteCartItem")
    public R deleteCartItemByIdList(@RequestBody List<String> cartItemIdList){
        int success = 0;
        for (String id : cartItemIdList) {
            success = cartService.deleteCartItemById(id);
        }
        if(success>0){
            return R.ok();
        }else{
            return R.error();
        }
    }

}
