select enterprise.id, enterprise.name, ps.step_number, process.name,
array_agg(eke.equipment_id),
sum(eke.quantity) as totalnumber,
sum(eke.capacity_per_hour * eke.quantity)/sum(eke.quantity) as aveca
from enterprise_keyprocess_equipment as eke
join product_step_keyprocess as psk on eke.product_step_keyprocess_id = psk.id
join product_step as ps on psk.product_step_id = ps.id
join process on psk.keyprocess_id = process.id
join enterprise on eke.enterprise_id = enterprise.id
join equipment on eke.equipment_id = equipment.id
where ps.product_id = 1 and psk.is_bottleneck = true
group by enterprise.id, enterprise.name, ps.step_number, process.name
;


select enterprise.id, enterprise.name, ps_step_number, ps_process_name,
array_agg(equipment.name
sum(eke.quantity) as totalnumber,
sum(eke.capacity_per_hour * eke.quantity)/sum(eke.quantity) as aveca
from
(
    select ps.id as ps_id, ps.step_number as ps_step_number, process.name as ps_process_name
    from product_step_keyprocess as psk
    join product_step as ps on psk.product_step_id = ps.id
    join process on psk.keyprocess_id = process.id
    where ps.product_id = 1 and psk.is_bottleneck = true
) as bottleneck_ps
join enterprise_keyprocess_equipment as eke on bottleneck_ps.ps_id = eke.product_step_keyprocess_id
join enterprise on eke.enterprise_id = enterprise.id
join equipment on eke.equipment_id = equipment.id
group by enterprise.id, enterprise.name, ps_step_number, ps_process_name;


select enterprise.name, step.id, step.name, step.step_number,
array_agg(equipment.name), sum(eke.quantity) as totalnumber,
sum(eke.capacity_per_hour*eke.quantity)/sum(eke.quantity) as aveca
from enterprise_keyprocess_equipment as eke
join enterprise on enterprise_id = enterprise.id
join product_keyprocess on eke.product_keyprocess_id = product_keyprocess.id
join step on step_id = step.id
join equipment on equipment_id = equipment.id
where product_keyprocess.is_bottleneck = true
group by enterprise.name, step.id, step.name, step.step_number
order by enterprise.name, step_number;