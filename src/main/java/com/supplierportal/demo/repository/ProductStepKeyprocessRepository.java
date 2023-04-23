package com.supplierportal.demo.repository;

import com.supplierportal.demo.model.ProductStepKeyprocess;
import org.springframework.data.repository.CrudRepository;

public interface ProductStepKeyprocessRepository extends CrudRepository<ProductStepKeyprocess, Integer> {


}


//"""
//select step_number, process.name
//from product_step_keyprocess as psk
//join product on product_id = product.id
//join product_step on product_step.product_id = product.id
//join process on psk.keyprocess_id = process.id
//where product_id = :productId
//and product_step_keyprocess.is_bottleneck = true
//order by product_step.step_number, process.name
//"""
//
//"""
////"""

//"""
//select enterprise.id, product_step.id,
//from enterprise_keyprocess_equipment as eke
//
//join product on product_id = product.id
//join product_step on product_step.product_id = product.id
//where product_id = :productId
//and product_step_keyprocess.is_bottleneck = true;
//"""