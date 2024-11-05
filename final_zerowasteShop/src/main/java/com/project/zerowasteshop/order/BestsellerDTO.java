package com.project.zerowasteshop.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BestsellerDTO {
    private Integer product_num;
    private Integer total_sales;
}
