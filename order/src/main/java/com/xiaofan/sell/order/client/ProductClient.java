package com.xiaofan.sell.order.client;

import com.xiaofan.sell.api.pojo.ProductInfo;
import com.xiaofan.sell.order.dto.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
        import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "PRODUCT")//要访问微服务的名称
public interface ProductClient {
    @PostMapping("/product/listForOrder")
    List<ProductInfo> listForOrder(@RequestBody List<String> productIdList);

    /**
     * 扣减库存
     */
    @PostMapping("/product/decrStock")
    String decrStock(@RequestBody List<CartDTO> cartDTOList);

}
