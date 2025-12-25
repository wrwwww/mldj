-- ================================
-- 乐尚代驾初始化数据库
-- Author: ChatGPT
-- ================================

CREATE DATABASE IF NOT EXISTS mailang DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_general_ci;
USE mailang;

-- 优惠券信息表
CREATE TABLE `coupon_info`
(
    `id`               bigint NOT NULL COMMENT '主键ID',
    `coupon_type`      int            DEFAULT NULL COMMENT '优惠卷类型 1 现金券 2 折扣',
    `name`             varchar(255)   DEFAULT NULL COMMENT '优惠卷名称',
    `amount`           decimal(10, 2) DEFAULT NULL COMMENT '优惠金额',
    `discount`         decimal(10, 2) DEFAULT NULL COMMENT '折扣：取值[1 到 10]',
    `condition_amount` decimal(10, 2) DEFAULT NULL COMMENT '使用门槛 0->没门槛',
    `publish_count`    int            DEFAULT NULL COMMENT '发行数量',
    `per_limit`        int            DEFAULT NULL COMMENT '每人限领张数',
    `use_count`        int            DEFAULT NULL COMMENT '已使用数量',
    `receive_count`    int            DEFAULT NULL COMMENT '领取数量',
    `expire_time`      datetime       DEFAULT NULL COMMENT '过期时间',
    `description`      varchar(255)   DEFAULT NULL COMMENT '优惠券描述',
    `status`           int            DEFAULT NULL COMMENT '状态[0-未发布，1-已发布，-1-已过期]',
    `create_time`      datetime       DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`      datetime       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`       tinyint(1)     DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='优惠券信息表';

-- 用户优惠券表
CREATE TABLE `customer_coupon`
(
    `id`           BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `coupon_id`    BIGINT COMMENT '优惠券ID',
    `customer_id`  BIGINT COMMENT '客户ID',
    `status`       INT COMMENT '状态：0-未使用，1-已使用，2-已过期',
    `receive_time` DATETIME COMMENT '领取时间',
    `used_time`    DATETIME COMMENT '使用时间',
    `order_id`     BIGINT COMMENT '订单ID',
    `expire_time`  DATETIME COMMENT '过期时间',
    `create_time`  DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`   TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户优惠券表';

-- 客户车辆信息表
CREATE TABLE `customer_car`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `customer_id` BIGINT COMMENT '客户ID',
    `license`     VARCHAR(50) COMMENT '车牌号',
    `brand`       VARCHAR(100) COMMENT '车辆品牌',
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='客户车辆信息表';

-- 客户信息表
CREATE TABLE `customer_info`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `wx_open_id`  VARCHAR(100) COMMENT '微信OpenID',
    `nickname`    VARCHAR(100) COMMENT '昵称',
    `gender`      VARCHAR(10) COMMENT '性别',
    `avatar_url`  VARCHAR(255) COMMENT '头像URL',
    `phone`       VARCHAR(30) COMMENT '手机号',
    `status`      INT COMMENT '状态：0-禁用，1-正常',
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='客户信息表';

-- 客户登录日志表
CREATE TABLE `customer_login_log`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `customer_id` BIGINT COMMENT '客户ID',
    `ipaddr`      VARCHAR(64) COMMENT '登录IP地址',
    `status`      TINYINT COMMENT '登录状态：0-失败，1-成功',
    `msg`         VARCHAR(255) COMMENT '登录消息',
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='客户登录日志表';

-- 订单任务关联表
CREATE TABLE `order_job`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `order_id`    BIGINT COMMENT '订单ID',
    `job_id`      BIGINT COMMENT '任务ID',
    `parameter`   VARCHAR(500) COMMENT '任务参数',
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单任务关联表';

-- XXL-JOB日志表
CREATE TABLE `xxl_job_log`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `job_id`      BIGINT COMMENT '任务ID',
    `status`      INT COMMENT '执行状态：0-失败，1-成功',
    `error`       VARCHAR(1000) COMMENT '错误信息',
    `times`       INT COMMENT '执行耗时（毫秒）',
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='XXL-JOB任务日志表';

-- 司机账户表
CREATE TABLE `driver_account`
(
    `id`                  BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `driver_id`           BIGINT COMMENT '司机ID',
    `total_amount`        DECIMAL(18, 2) COMMENT '账户总额',
    `lock_amount`         DECIMAL(18, 2) COMMENT '冻结金额',
    `available_amount`    DECIMAL(18, 2) COMMENT '可用金额',
    `total_income_amount` DECIMAL(18, 2) COMMENT '累计收入',
    `total_pay_amount`    DECIMAL(18, 2) COMMENT '累计支出',
    `create_time`         DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`         DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`          TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='司机账户表';

-- 司机账户明细表
CREATE TABLE `driver_account_detail`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `driver_id`   BIGINT COMMENT '司机ID',
    `content`     VARCHAR(255) COMMENT '交易内容',
    `trade_type`  VARCHAR(50) COMMENT '交易类型',
    `amount`      DECIMAL(18, 2) COMMENT '交易金额',
    `trade_no`    VARCHAR(100) COMMENT '交易流水号',
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='司机账户明细表';

-- 司机人脸识别记录表
CREATE TABLE `driver_face_recognition`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `driver_id`   BIGINT COMMENT '司机ID',
    `face_date`   DATETIME COMMENT '人脸识别时间',
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='司机人脸识别记录表';

-- 司机信息表
CREATE TABLE `driver_info`
(
    `id`                        BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `wx_open_id`                VARCHAR(100) COMMENT '微信OpenID',
    `nickname`                  VARCHAR(100) COMMENT '昵称',
    `avatar_url`                VARCHAR(255) COMMENT '头像URL',
    `phone`                     VARCHAR(30) COMMENT '手机号',
    `name`                      VARCHAR(100) COMMENT '真实姓名',
    `gender`                    VARCHAR(10) COMMENT '性别',
    `birthday`                  DATETIME COMMENT '生日',
    `idcard_no`                 VARCHAR(30) COMMENT '身份证号码',
    `idcard_address`            VARCHAR(255) COMMENT '身份证地址',
    `idcard_expire`             DATETIME COMMENT '身份证有效期',
    `idcard_front_url`          VARCHAR(255) COMMENT '身份证正面照片',
    `idcard_back_url`           VARCHAR(255) COMMENT '身份证背面照片',
    `idcard_hand_url`           VARCHAR(255) COMMENT '手持身份证照片',
    `driver_license_class`      VARCHAR(20) COMMENT '驾驶证准驾车型',
    `driver_license_no`         VARCHAR(50) COMMENT '驾驶证号码',
    `driver_license_expire`     DATETIME COMMENT '驾驶证有效期',
    `driver_license_issue_date` DATETIME COMMENT '驾驶证初次领证日期',
    `driver_license_front_url`  VARCHAR(255) COMMENT '驾驶证正面照片',
    `driver_license_back_url`   VARCHAR(255) COMMENT '驾驶证背面照片',
    `driver_license_hand_url`   VARCHAR(255) COMMENT '手持驾驶证照片',
    `contact_name`              VARCHAR(100) COMMENT '紧急联系人姓名',
    `contact_phone`             VARCHAR(30) COMMENT '紧急联系人电话',
    `contact_relationship`      VARCHAR(50) COMMENT '紧急联系人关系',
    `face_model_id`             VARCHAR(100) COMMENT '人脸模型ID',
    `job_no`                    VARCHAR(50) COMMENT '工号',
    `order_count`               INT COMMENT '接单数量',
    `score`                     DECIMAL(18, 2) COMMENT '评分',
    `auth_status`               INT COMMENT '认证状态：0-未认证，1-已认证',
    `status`                    INT COMMENT '状态：0-禁用，1-正常',
    `create_time`               DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`               DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`                TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='司机信息表';

-- 司机登录日志表
CREATE TABLE `driver_login_log`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `driver_id`   BIGINT COMMENT '司机ID',
    `ipaddr`      VARCHAR(64) COMMENT '登录IP地址',
    `status`      TINYINT COMMENT '登录状态：0-失败，1-成功',
    `msg`         VARCHAR(255) COMMENT '登录消息',
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='司机登录日志表';

-- 司机设置表
CREATE TABLE `driver_set`
(
    `id`              BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `driver_id`       BIGINT COMMENT '司机ID',
    `service_status`  INT COMMENT '服务状态：0-下班，1-上班',
    `order_distance`  DECIMAL(18, 2) COMMENT '接单距离（公里）',
    `accept_distance` DECIMAL(18, 2) COMMENT '可接单距离（公里）',
    `is_auto_accept`  INT COMMENT '是否自动接单：0-否，1-是',
    `create_time`     DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`      TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='司机设置表';

-- 订单账单表
CREATE TABLE `order_bill`
(
    `id`                         BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `order_id`                   BIGINT COMMENT '订单ID',
    `fee_rule_id`                BIGINT COMMENT '费用规则ID',
    `total_amount`               DECIMAL(18, 2) COMMENT '总金额',
    `pay_amount`                 DECIMAL(18, 2) COMMENT '支付金额',
    `distance_fee`               DECIMAL(18, 2) COMMENT '里程费',
    `wait_fee`                   DECIMAL(18, 2) COMMENT '等待费',
    `toll_fee`                   DECIMAL(18, 2) COMMENT '路桥费',
    `parking_fee`                DECIMAL(18, 2) COMMENT '停车费',
    `other_fee`                  DECIMAL(18, 2) COMMENT '其他费用',
    `long_distance_fee`          DECIMAL(18, 2) COMMENT '长途费',
    `favour_fee`                 DECIMAL(18, 2) COMMENT '优惠金额',
    `reward_fee`                 DECIMAL(18, 2) COMMENT '奖励金额',
    `reward_rule_id`             BIGINT COMMENT '奖励规则ID',
    `coupon_amount`              DECIMAL(18, 2) COMMENT '优惠券金额',
    `base_distance`              DECIMAL(18, 2) COMMENT '基础里程（公里）',
    `base_distance_fee`          DECIMAL(18, 2) COMMENT '基础里程费',
    `exceed_distance`            DECIMAL(18, 2) COMMENT '超出里程（公里）',
    `exceed_distance_price`      DECIMAL(18, 2) COMMENT '超出里程单价',
    `base_wait_minute`           INT COMMENT '基础等待时间（分钟）',
    `exceed_wait_minute`         INT COMMENT '超出等待时间（分钟）',
    `exceed_wait_minute_price`   DECIMAL(18, 2) COMMENT '超出等待时间单价',
    `base_long_distance`         DECIMAL(18, 2) COMMENT '基础长途里程（公里）',
    `exceed_long_distance`       DECIMAL(18, 2) COMMENT '超出长途里程（公里）',
    `exceed_long_distance_price` DECIMAL(18, 2) COMMENT '超出长途里程单价',
    `create_time`                DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`                DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`                 TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单账单明细表';

-- 订单评价表
CREATE TABLE `order_comment`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `order_id`    BIGINT COMMENT '订单ID',
    `driver_id`   BIGINT COMMENT '司机ID',
    `customer_id` BIGINT COMMENT '客户ID',
    `rate`        INT COMMENT '评分：1-5星',
    `remark`      VARCHAR(500) COMMENT '评价内容',
    `status`      INT COMMENT '状态：0-待审核，1-已审核，2-已屏蔽',
    `instance_id` VARCHAR(100) COMMENT '审核实例ID',
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单评价表';

-- 订单信息表
CREATE TABLE `order_info`
(
    `id`                    BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `customer_id`           BIGINT COMMENT '客户ID',
    `order_no`              VARCHAR(100) COMMENT '订单编号',
    `start_location`        VARCHAR(255) COMMENT '起点位置',
    `start_point_longitude` DECIMAL(18, 6) COMMENT '起点经度',
    `start_point_latitude`  DECIMAL(18, 6) COMMENT '起点纬度',
    `end_location`          VARCHAR(255) COMMENT '终点位置',
    `end_point_longitude`   DECIMAL(18, 6) COMMENT '终点经度',
    `end_point_latitude`    DECIMAL(18, 6) COMMENT '终点纬度',
    `expect_distance`       DECIMAL(18, 2) COMMENT '预计里程（公里）',
    `real_distance`         DECIMAL(18, 2) COMMENT '实际里程（公里）',
    `expect_amount`         DECIMAL(18, 2) COMMENT '预计金额',
    `real_amount`           DECIMAL(18, 2) COMMENT '实际金额',
    `favour_fee`            DECIMAL(18, 2) COMMENT '优惠金额',
    `driver_id`             BIGINT COMMENT '司机ID',
    `accept_time`           DATETIME COMMENT '接单时间',
    `arrive_time`           DATETIME COMMENT '到达时间',
    `start_service_time`    DATETIME COMMENT '开始服务时间',
    `end_service_time`      DATETIME COMMENT '结束服务时间',
    `pay_time`              DATETIME COMMENT '支付时间',
    `cancel_rule_id`        BIGINT COMMENT '取消规则ID',
    `car_license`           VARCHAR(50) COMMENT '车牌号',
    `car_type`              VARCHAR(100) COMMENT '车型',
    `car_front_url`         VARCHAR(255) COMMENT '车前照片',
    `car_back_url`          VARCHAR(255) COMMENT '车后照片',
    `transaction_id`        VARCHAR(100) COMMENT '交易流水号',
    `status`                INT COMMENT '订单状态',
    `remark`                VARCHAR(500) COMMENT '备注',
    `create_time`           DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`           DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`            TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单信息表';

-- 订单监控表
CREATE TABLE `order_monitor`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `order_id`    BIGINT COMMENT '订单ID',
    `file_num`    INT COMMENT '文件数量',
    `audit_num`   INT COMMENT '审核数量',
    `is_alarm`    INT COMMENT '是否报警：0-否，1-是',
    `status`      INT COMMENT '监控状态',
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单监控表';

-- 订单分润表
CREATE TABLE `order_profit_sharing`
(
    `id`              BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `order_id`        BIGINT COMMENT '订单ID',
    `rule_id`         BIGINT COMMENT '分润规则ID',
    `order_amount`    DECIMAL(18, 2) COMMENT '订单金额',
    `payment_rate`    DECIMAL(18, 4) COMMENT '支付费率',
    `payment_fee`     DECIMAL(18, 2) COMMENT '支付手续费',
    `driver_tax_rate` DECIMAL(18, 4) COMMENT '司机税率',
    `driver_tax_fee`  DECIMAL(18, 2) COMMENT '司机税费',
    `platform_income` DECIMAL(18, 2) COMMENT '平台收入',
    `driver_income`   DECIMAL(18, 2) COMMENT '司机收入',
    `status`          INT COMMENT '分润状态：0-待分润，1-已分润',
    `create_time`     DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`      TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单分润明细表';

-- 订单状态日志表
CREATE TABLE `order_status_log`
(
    `id`           BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `order_id`     BIGINT COMMENT '订单ID',
    `order_status` INT COMMENT '订单状态',
    `operate_time` DATETIME COMMENT '操作时间',
    `create_time`  DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`   TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单状态变更日志表';

-- 订单轨迹表
CREATE TABLE `order_track`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `order_id`    BIGINT COMMENT '订单ID',
    `driver_id`   BIGINT COMMENT '司机ID',
    `customer_id` BIGINT COMMENT '客户ID',
    `longitude`   VARCHAR(50) COMMENT '经度',
    `latitude`    VARCHAR(50) COMMENT '纬度',
    `speed`       VARCHAR(50) COMMENT '速度（km/h）',
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单轨迹记录表';

-- 支付信息表
CREATE TABLE `payment_info`
(
    `id`               BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `customer_open_id` VARCHAR(100) COMMENT '客户OpenID',
    `driver_open_id`   VARCHAR(100) COMMENT '司机OpenID',
    `order_no`         VARCHAR(100) COMMENT '订单编号',
    `pay_way`          INT COMMENT '支付方式：1-微信，2-支付宝',
    `transaction_id`   VARCHAR(100) COMMENT '交易流水号',
    `amount`           DECIMAL(18, 2) COMMENT '支付金额',
    `content`          VARCHAR(255) COMMENT '支付内容',
    `payment_status`   INT COMMENT '支付状态：0-待支付，1-支付成功，2-支付失败',
    `callback_time`    DATETIME COMMENT '回调时间',
    `callback_content` TEXT COMMENT '回调内容',
    `create_time`      DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`      DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`       TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='支付信息表';

-- 分润信息表
CREATE TABLE `profitsharing_info`
(
    `id`              BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `driver_id`       BIGINT COMMENT '司机ID',
    `order_no`        VARCHAR(100) COMMENT '订单编号',
    `transaction_id`  VARCHAR(100) COMMENT '交易流水号',
    `out_trade_no`    VARCHAR(100) COMMENT '商户订单号',
    `amount`          VARCHAR(50) COMMENT '分润金额',
    `state`           VARCHAR(50) COMMENT '分润状态',
    `respone_content` TEXT COMMENT '响应内容',
    `create_time`     DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`      TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='分润信息表';

-- 取消规则表
CREATE TABLE `cancel_rule`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `name`        VARCHAR(100) COMMENT '规则名称',
    `rule`        TEXT COMMENT '规则内容',
    `status`      INT COMMENT '状态：0-禁用，1-启用',
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单取消规则表';

-- 费用规则表
CREATE TABLE `fee_rule`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `name`        VARCHAR(100) COMMENT '规则名称',
    `rule`        TEXT COMMENT '规则内容',
    `status`      INT COMMENT '状态：0-禁用，1-启用',
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='费用规则表';

-- 分润规则表
CREATE TABLE `profit_sharing_rule`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `name`        VARCHAR(100) COMMENT '规则名称',
    `rule`        TEXT COMMENT '规则内容',
    `status`      INT COMMENT '状态：0-禁用，1-启用',
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='分润规则表';

-- 奖励规则表
CREATE TABLE `reward_rule`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `name`        VARCHAR(100) COMMENT '规则名称',
    `rule`        TEXT COMMENT '规则内容',
    `status`      INT COMMENT '状态：0-禁用，1-启用',
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='奖励规则表';

-- 部门表
CREATE TABLE `sys_dept`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `name`        VARCHAR(100) COMMENT '部门名称',
    `parent_id`   BIGINT COMMENT '父部门ID',
    `tree_path`   VARCHAR(255) COMMENT '部门路径',
    `sort_value`  INT COMMENT '排序值',
    `leader`      VARCHAR(100) COMMENT '负责人',
    `phone`       VARCHAR(30) COMMENT '联系电话',
    `status`      INT COMMENT '状态：0-禁用，1-启用',
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='部门表';

-- 系统登录日志表
CREATE TABLE `sys_login_log`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `username`    VARCHAR(100) COMMENT '用户名',
    `ipaddr`      VARCHAR(64) COMMENT '登录IP地址',
    `status`      INT COMMENT '登录状态：0-失败，1-成功',
    `msg`         VARCHAR(255) COMMENT '登录消息',
    `access_time` DATETIME COMMENT '访问时间',
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='系统登录日志表';

-- 菜单表
CREATE TABLE `sys_menu`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `parent_id`   BIGINT COMMENT '父菜单ID',
    `name`        VARCHAR(100) COMMENT '菜单名称',
    `type`        INT COMMENT '菜单类型：1-目录，2-菜单，3-按钮',
    `path`        VARCHAR(255) COMMENT '路由路径',
    `component`   VARCHAR(255) COMMENT '组件路径',
    `perms`       VARCHAR(255) COMMENT '权限标识',
    `icon`        VARCHAR(100) COMMENT '菜单图标',
    `sort_value`  INT COMMENT '排序值',
    `status`      INT COMMENT '状态：0-禁用，1-启用',
    `active_menu` VARCHAR(255) COMMENT '激活菜单',
    `is_hide`     INT COMMENT '是否隐藏：0-显示，1-隐藏',
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='菜单表';

-- 操作日志表
CREATE TABLE `sys_oper_log`
(
    `id`             BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `title`          VARCHAR(100) COMMENT '操作标题',
    `business_type`  VARCHAR(20) COMMENT '业务类型',
    `method`         VARCHAR(255) COMMENT '方法名称',
    `request_method` VARCHAR(20) COMMENT '请求方式',
    `operator_type`  VARCHAR(20) COMMENT '操作人员类型',
    `oper_name`      VARCHAR(100) COMMENT '操作人员',
    `dept_name`      VARCHAR(100) COMMENT '部门名称',
    `oper_url`       VARCHAR(255) COMMENT '请求URL',
    `oper_ip`        VARCHAR(64) COMMENT '操作IP',
    `oper_param`     TEXT COMMENT '请求参数',
    `json_result`    TEXT COMMENT '返回结果',
    `status`         INT COMMENT '操作状态：0-失败，1-成功',
    `error_msg`      VARCHAR(500) COMMENT '错误信息',
    `oper_time`      DATETIME COMMENT '操作时间',
    `create_time`    DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`     TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='系统操作日志表';

-- 岗位表
CREATE TABLE `sys_post`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `post_code`   VARCHAR(50) COMMENT '岗位编码',
    `name`        VARCHAR(100) COMMENT '岗位名称',
    `description` VARCHAR(255) COMMENT '岗位描述',
    `status`      INT COMMENT '状态：0-禁用，1-启用',
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='岗位表';

-- 角色表
CREATE TABLE `sys_role`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `role_name`   VARCHAR(100) COMMENT '角色名称',
    `role_code`   VARCHAR(100) COMMENT '角色编码',
    `description` VARCHAR(255) COMMENT '角色描述',
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='角色表';

-- 角色菜单关联表
CREATE TABLE `sys_role_menu`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `role_id`     BIGINT COMMENT '角色ID',
    `menu_id`     BIGINT COMMENT '菜单ID',
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='角色菜单关联表';

-- 用户表
CREATE TABLE `sys_user`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `username`    VARCHAR(100) COMMENT '用户名',
    `password`    VARCHAR(200) COMMENT '密码',
    `name`        VARCHAR(100) COMMENT '姓名',
    `phone`       VARCHAR(30) COMMENT '手机号',
    `head_url`    VARCHAR(255) COMMENT '头像URL',
    `dept_id`     BIGINT COMMENT '部门ID',
    `post_id`     BIGINT COMMENT '岗位ID',
    `description` VARCHAR(255) COMMENT '描述',
    `status`      INT COMMENT '状态：0-禁用，1-启用',
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';

-- 用户角色关联表
CREATE TABLE `sys_user_role`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `role_id`     BIGINT COMMENT '角色ID',
    `user_id`     BIGINT COMMENT '用户ID',
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户角色关联表';