# 共享出行数据库设计
## 1、数据库设计
### 1.1、数据库基本信息
- 数据库名称: shared_transport
- 字符集: utf8

### 1.2、数据表信息

#### 表1: users（用户表）
```sql
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '用户唯一标识',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名，唯一且用于登录',
    password VARCHAR(100) NOT NULL COMMENT '用户密码，需加密存储',
    email VARCHAR(100) UNIQUE COMMENT '用户邮箱地址，唯一',
    phone VARCHAR(15) COMMENT '用户联系电话',
    register_date DATE NOT NULL DEFAULT CURRENT_DATE COMMENT '用户注册日期',
    wallet_balance DECIMAL(10, 2) DEFAULT 0.00 COMMENT '用户钱包余额，用于支付行程费用',
    id_verified TINYINT(1) DEFAULT 0 COMMENT '是否完成实名验证，0表示未验证，1表示已验证'
) COMMENT '用户表，存储平台用户的基本信息';
```
#### 表2: vehicles（车辆表）
```sql
CREATE TABLE vehicles (
    vehicle_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '车辆唯一标识',
    type ENUM('bike', 'e_bike', 'car') NOT NULL COMMENT '车辆类型，支持单车、电动车和汽车',
    license_plate VARCHAR(20) DEFAULT NULL COMMENT '车辆牌照号码，仅汽车需要',
    status ENUM('available', 'in_use', 'maintenance') DEFAULT 'available' COMMENT '车辆状态，标识是否可用',
    location VARCHAR(100) COMMENT '车辆当前所在位置',
    battery_level INT DEFAULT NULL COMMENT '电池电量百分比，仅适用于电动车和汽车',
    model VARCHAR(50) COMMENT '车辆型号，例如标准单车或具体车型'
) COMMENT '车辆表，存储平台所有车辆的详细信息';
```
#### 表3: trips（行程表）
```sql
CREATE TABLE trips (
    trip_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '行程唯一标识',
    user_id INT NOT NULL COMMENT '用户标识，关联到users表',
    vehicle_id INT NOT NULL COMMENT '车辆标识，关联到vehicles表',
    start_time DATETIME NOT NULL COMMENT '行程开始时间',
    end_time DATETIME COMMENT '行程结束时间，未完成时为空',
    start_location VARCHAR(100) COMMENT '行程起点位置',
    end_location VARCHAR(100) COMMENT '行程终点位置，未完成时为空',
    duration_minutes INT GENERATED ALWAYS AS (
        TIMESTAMPDIFF(MINUTE, start_time, end_time)
    ) VIRTUAL COMMENT '行程时长（分钟），通过开始和结束时间计算',
    rate_per_minute DECIMAL(5, 2) DEFAULT 0.50 COMMENT '每分钟计费标准',
    cost DECIMAL(10, 2) COMMENT '行程总费用，完成后计算',
    FOREIGN KEY (user_id) REFERENCES users(user_id) COMMENT '外键，关联到用户表',
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id) COMMENT '外键，关联到车辆表'
) COMMENT '行程表，记录用户的使用情况和费用详情';
```



#### 表4: payments（支付表）
```sql
CREATE TABLE payments (
    payment_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '支付记录唯一标识',
    user_id INT NOT NULL COMMENT '用户标识，关联到users表',
    trip_id INT NOT NULL COMMENT '行程标识，关联到trips表',
    amount DECIMAL(10, 2) NOT NULL COMMENT '支付金额',
    payment_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '支付日期和时间',
    method ENUM('credit_card', 'we_chat', 'alipay', 'apple_pay') COMMENT '支付方式',
    transaction_status ENUM('pending', 'completed', 'failed') DEFAULT 'pending' COMMENT '交易状态',
    FOREIGN KEY (user_id) REFERENCES users(user_id) COMMENT '外键，关联到用户表',
    FOREIGN KEY (trip_id) REFERENCES trips(trip_id) COMMENT '外键，关联到行程表'
) COMMENT '支付表，记录行程支付的详细信息';

```
#### 表5: maintenance_logs（维修日志表）
```sql
CREATE TABLE maintenance_logs (
    log_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '维修日志唯一标识',
    vehicle_id INT NOT NULL COMMENT '车辆标识，关联到vehicles表',
    report_date DATE NOT NULL COMMENT '报修日期',
    repair_date DATE COMMENT '维修完成日期',
    issue_description TEXT COMMENT '车辆问题描述',
    status ENUM('reported', 'in_progress', 'fixed') DEFAULT 'reported' COMMENT '维修状态',
    repair_cost DECIMAL(10, 2) COMMENT '维修费用',
    repair_by VARCHAR(50) COMMENT '维修人员姓名',
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id) COMMENT '外键，关联到车辆表'
) COMMENT '维修日志表，记录车辆故障与维修情况';
```
####  表6: stations（站点表）
```sql
CREATE TABLE stations (
    station_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '站点唯一标识',
    station_name VARCHAR(50) NOT NULL COMMENT '站点名称',
    capacity INT NOT NULL COMMENT '站点最大容纳车辆数量',
    current_vehicles INT DEFAULT 0 COMMENT '当前站点的车辆数量',
    location VARCHAR(100) COMMENT '站点所在位置（地址或描述）'
) COMMENT '站点表，存储共享单车和汽车停放站点的信息';
```
### 1.3、测试数据
```sql
-- 插入用户数据
INSERT INTO users (username, password, email, phone, wallet_balance, id_verified)
VALUES
    ('张三', 'hashed_password1', 'zhangsan@example.com', '13800000001', 100.50, 1),
    ('李四', 'hashed_password2', 'lisi@example.com', '13800000002', 50.00, 1),
    ('王五', 'hashed_password3', 'wangwu@example.com', '13800000003', 30.00, 0),
    ('赵六', 'hashed_password4', 'zhaoliu@example.com', '13800000004', 200.00, 1),
    ('钱七', 'hashed_password5', 'qianqi@example.com', '13800000005', 15.00, 0),
    ('孙八', 'hashed_password6', 'sunba@example.com', '13800000006', 75.00, 1),
    ('周九', 'hashed_password7', 'zhoujiu@example.com', '13800000007', 90.00, 1),
    ('吴十', 'hashed_password8', 'wushi@example.com', '13800000008', 10.00, 0),
    ('郑十一', 'hashed_password9', 'zheng11@example.com', '13800000009', 60.00, 1),
    ('冯十二', 'hashed_password10', 'feng12@example.com', '13800000010', 5.00, 0);
-- 插入车辆数据
INSERT INTO vehicles (type, license_plate, status, location, battery_level, model)
VALUES
    ('单车', NULL, 'available', '站点A', NULL, '标准单车'),
    ('单车', NULL, 'available', '站点B', NULL, '标准单车'),
    ('单车', NULL, 'maintenance', '站点C', NULL, '标准单车'),
    ('电动车', NULL, 'available', '站点A', 90, '电动单车2023'),
    ('电动车', NULL, 'in_use', '站点B', 75, '电动单车2023'),
    ('电动车', NULL, 'maintenance', '站点C', 50, '电动单车2023'),
    ('汽车', '京A12345', 'available', '站点A', 100, '汽车A款'),
    ('汽车', '京B67890', 'in_use', '站点B', 85, '汽车B款'),
    ('汽车', '京C11223', 'maintenance', '站点C', 60, '汽车C款'),
    ('汽车', '京D44556', 'available', '站点B', 95, '汽车D款');

-- 插入行程数据
INSERT INTO trips (user_id, vehicle_id, start_time, end_time, start_location, end_location, cost)
VALUES
    (1, 1, '2024-12-19 08:00:00', '2024-12-19 08:30:00', '站点A', '站点B', 15.00),
    (2, 2, '2024-12-19 09:00:00', '2024-12-19 09:20:00', '站点B', '站点C', 10.00),
    (3, 3, '2024-12-19 10:00:00', '2024-12-19 10:50:00', '站点C', '站点A', 25.00),
    (4, 4, '2024-12-19 11:00:00', '2024-12-19 11:40:00', '站点A', '站点B', 20.00),
    (5, 5, '2024-12-19 12:00:00', NULL, '站点B', NULL, NULL),
    (6, 6, '2024-12-19 13:00:00', '2024-12-19 13:15:00', '站点C', '站点A', 7.50),
    (7, 7, '2024-12-19 14:00:00', NULL, '站点A', NULL, NULL),
    (8, 8, '2024-12-19 15:00:00', '2024-12-19 15:45:00', '站点B', '站点C', 22.50),
    (9, 9, '2024-12-19 16:00:00', '2024-12-19 16:10:00', '站点C', '站点A', 5.00),
    (10, 10, '2024-12-19 17:00:00', '2024-12-19 17:30:00', '站点A', '站点B', 15.00);
-- 插入支付数据
INSERT INTO payments (user_id, trip_id, amount, method, transaction_status)
VALUES
    (1, 1, 15.00, 'we_chat', 'completed'),
    (2, 2, 10.00, 'credit_card', 'completed'),
    (3, 3, 25.00, 'alipay', 'completed'),
    (4, 4, 20.00, 'apple_pay', 'completed'),
    (5, 5, NULL, 'we_chat', 'pending'),
    (6, 6, 7.50, 'we_chat', 'completed'),
    (7, 7, NULL, 'credit_card', 'pending'),
    (8, 8, 22.50, 'alipay', 'completed'),
    (9, 9, 5.00, 'we_chat', 'completed'),
    (10, 10, 15.00, 'credit_card', 'completed');
-- 插入站点数据
INSERT INTO stations (station_name, capacity, current_vehicles, location)
VALUES 
('Station A', 50, 10, '黄山'), -- 容量50辆，当前存10辆
('Station B', 30, 15, '泰山'), 
('Station C', 20, 5, '珠穆朗玛峰');
-- 插入维修日志数据
INSERT INTO maintenance_logs (vehicle_id, report_date, repair_date, issue_description, status, repair_cost, repair_by)
VALUES
    (3, '2024-12-18', '2024-12-19', 'Flat tire', 'fixed', 10.00, 'John'),
    (6, '2024-12-17', '2024-12-18', 'Battery malfunction', 'fixed', 50.00, 'Alice'),
    (9, '2024-12-16', NULL, 'Engine issue', 'reported', NULL, NULL),
    (3, '2024-12-15', '2024-12-16', 'Brake issue', 'fixed', 20.00, 'Bob'),
    (6, '2024-12-14', '2024-12-15', 'Battery overheating', 'fixed', 30.00, 'John'),
    (9, '2024-12-13', '2024-12-14', 'AC not working', 'fixed', 15.00, 'Alice'),
    (6, '2024-12-12', '2024-12-13', 'Battery replacement', 'fixed', 100.00, 'Bob'),
    (9, '2024-12-11', NULL, 'Transmission issue', 'reported', NULL, NULL),
    (3, '2024-12-10', '2024-12-11', 'Gear malfunction', 'fixed', 40.00, 'John'),
    (6, '2024-12-09', '2024-12-10', 'Battery calibration', 'fixed', 25.00, 'Alice');
-- 
```

### 1.4、视图

#### 视图: available_vehicles
此视图用于查询所有可用车辆，包括单车、电动车和汽车。
```sql
CREATE VIEW available_vehicles AS
SELECT 
    vehicle_id, 
    type, 
    location, 
    battery_level 
FROM 
    vehicles 
WHERE 
    status = 'available';
```

#### 视图: user_expenses
此视图用于查询用户消费记录，包括行程费用、维修费用和钱包余额变动。
```sql
CREATE VIEW user_expenses AS
SELECT 
    u.username, 
    COUNT(t.trip_id) AS total_trips, 
    SUM(t.cost) AS total_cost 
FROM 
    users u 
JOIN trips t ON u.user_id = t.user_id 
GROUP BY u.user_id;
```

#### 站点与车辆分布统计
```sql
SELECT 
    s.station_name, 
    s.capacity, 
    s.current_vehicles 
FROM 
    stations s 
JOIN vehicles v ON v.location = s.station_name 
GROUP BY s.station_id;
```
## 2、方案撰写
### 2.1、注意的问题
- 表结构设计需满足共享单车和共享汽车的业务需求，字段名需语义明确。
- 考虑到单车无需车牌号，字段如license_plate设置为可空。
- 车辆状态字段(status)帮助追踪车辆是否可用或需维护。
- 表与表之间的外键关联应避免循环依赖。
- 数据安全与隐私保护需要注意，例如对用户的敏感信息加密存储。

### 2.2、数据库操作命令
1 创建相应数据库的命令
```sql
CREATE DATABASE shared_transport CHARACTER SET utf8;
```
2 创建表的命令
```sql
CREATE TABLE vehicles (
    vehicle_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '车辆唯一标识',
    type ENUM('bike', 'e_bike', 'car') NOT NULL COMMENT '车辆类型，支持单车、电动车和汽车',
    license_plate VARCHAR(20) DEFAULT NULL COMMENT '车辆牌照号码，仅汽车需要',
    status ENUM('available', 'in_use', 'maintenance') DEFAULT 'available' COMMENT '车辆状态，标识是否可用',
    location VARCHAR(100) COMMENT '车辆当前所在位置',
    battery_level INT DEFAULT NULL COMMENT '电池电量百分比，仅适用于电动车和汽车',
    model VARCHAR(50) COMMENT '车辆型号，例如标准单车或具体车型'
) COMMENT '车辆表，存储平台所有车辆的详细信息';
```
3 插入和修改数据的命令
```sql
-- 插入数据
INSERT INTO vehicles (type, license_plate, status, location)
VALUES
    ('bike', NULL, 'available', 'Station A'),
    ('car', 'ABC1234', 'available', 'Station B');

-- 修改数据
UPDATE vehicles
SET status = 'in_use', location = 'Station C'
WHERE vehicle_id = 1;
```
4 视图的创建命令
视图：active_trips
```sql
CREATE VIEW active_trips AS
SELECT
    t.trip_id,
    u.username AS user_name,
    v.type AS vehicle_type,
    v.location AS current_location,
    t.start_time,
    t.start_location
FROM
    trips t
        JOIN users u ON t.user_id = u.user_id
        JOIN vehicles v ON t.vehicle_id = v.vehicle_id
WHERE
    t.end_time IS NULL;
```
5 复杂查询语句

结果含义: 查询出消费总金额超过100且单次行程费用高于10的用户，按总消费金额降序排列，返回前5名。
```sql
SELECT 
    u.username, 
    COUNT(t.trip_id) AS total_trips, 
    SUM(t.cost) AS total_spent 
FROM 
    users u 
JOIN trips t ON u.user_id = t.user_id 
WHERE t.cost > 10 
GROUP BY u.user_id 
HAVING total_spent > 100 
ORDER BY total_spent DESC 
LIMIT 5;
```

6 跨表连接查询

结果含义:
- 交叉连接: 生成所有用户与车辆的笛卡尔积。
- 内连接: 查询用户的行程及使用车辆的类型和起点位置。

交叉连接:
```sql
SELECT u.username, v.type, v.location 
FROM users u, vehicles v;
```

内连接:
```sql
SELECT 
    u.username, 
    t.start_location, 
    v.type AS vehicle_type 
FROM 
    users u 
JOIN trips t ON u.user_id = t.user_id 
JOIN vehicles v ON t.vehicle_id = v.vehicle_id;
```

### 2.3 数据库安全管理涉及的方面
1、数据加密：数据库中的敏感信息如密码、手机号等应加密存储，以防止数据泄露。
2、权限管理：数据库中的用户应具有不同的权限，以限制其操作范围。例如，普通用户仅能查看自己的数据，管理员可以查看所有数据。
3、日志记录: 跟踪用户登录和敏感操作的日志。
4、数据备份和恢复：定期备份数据库，以防止数据丢失。
5、系统监控：监控数据库运行情况，及时处理异常情况。
6、防注入攻击: 使用预处理语句避免SQL注入。

