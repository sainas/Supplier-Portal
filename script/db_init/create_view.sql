DROP VIEW IF EXISTS enterprise_equipment_details;

CREATE VIEW enterprise_equipment_details AS
select enterprise.id as "企业ID",
enterprise.name as "企业名称",
product.name as "产品名称",
ps.step_number as "步骤序号",
ps.name as "步骤名称",
process.name as "工艺名称",
psk.is_bottleneck as "是否瓶颈",
equipment.name as "设备名称",
eke.quantity as "设备数量",
eke.capacity_per_hour as "设备每小时产能"
from enterprise_keyprocess_equipment as eke
join product_step_keyprocess as psk on eke.product_step_keyprocess_id = psk.id
join product_step as ps on psk.product_step_id = ps.id
join product as product on ps.product_id = product.id
join process on psk.keyprocess_id = process.id
join enterprise on eke.enterprise_id = enterprise.id
join equipment on eke.equipment_id = equipment.id
order by enterprise.id, product.id, ps.step_number, process.name, equipment.name;


DROP VIEW IF EXISTS product_process_details;

CREATE VIEW product_process_details AS
select psk.id as "产品工艺ID",
product.name as "产品名称",
ps.step_number as "步骤序号",
ps.name as "步骤名称",
process.name as "工艺名称",
psk.is_bottleneck as "是否瓶颈"
from product_step_keyprocess as psk
join product_step as ps on psk.product_step_id = ps.id
join product as product on ps.product_id = product.id
join process on psk.keyprocess_id = process.id
order by product.id, ps.step_number, process.name;
