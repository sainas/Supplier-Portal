package com.supplierportal.demo.repository;

import com.supplierportal.demo.model.EnterpriseKeyprocessEquipment;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface EnterpriseKeyprocessEquipmentRepository extends JpaRepository<EnterpriseKeyprocessEquipment, Integer> {
    @Query(value = "select enterprise.id as enterprise_id, enterprise.name as enterprise_name, " +
            "ps.step_number as step_number, process.name as process_name, " +
            "array_agg(equipment.name) as equipment_names, " +
            "sum(eke.quantity) as total_count, " +
            "sum(eke.capacity_per_hour * eke.quantity)/sum(eke.quantity) as avg_capacity " +
            "from enterprise_keyprocess_equipment as eke " +
            "join product_step_keyprocess as psk on eke.product_step_keyprocess_id = psk.id " +
            "join product_step as ps on psk.product_step_id = ps.id " +
            "join process on psk.keyprocess_id = process.id " +
            "join enterprise on eke.enterprise_id = enterprise.id " +
            "join equipment on eke.equipment_id = equipment.id " +
            "where ps.product_id = 1 and psk.is_bottleneck = true " +
            "group by enterprise.id, enterprise.name, ps.step_number, process.name " +
            "order by enterprise.name", nativeQuery = true)
    List<Tuple> findEnterpriseIdAndProductStepIdByProductIdAndIsBottleneckIsTrue(@Param("productId") Integer productId);

}
