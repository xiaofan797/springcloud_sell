package com.xiaofan.sell.order.client;

import com.xiaofan.sell.api.pojo.ProductInfo;
import org.springframework.cloud.openfeign.FeignClient;
        import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "PRODUCT")//要访问微服务的名称
public interface ProductClient {
    @GetMapping("/product/listForOrder")
    List<ProductInfo> listForOrder(@RequestBody List<String> productIdList);
}
