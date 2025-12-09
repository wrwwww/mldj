-- ================================
-- 乐尚代驾初始化数据库
-- Author: ChatGPT
-- ================================

CREATE DATABASE IF NOT EXISTS leshang DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_general_ci;
USE leshang;

CREATE TABLE `coupon_info`
(
    `id`               bigint NOT NULL COMMENT '主键ID',
    `coupon_type`      int            DEFAULT NULL COMMENT '优惠卷类型 1 现金券 2 折扣',
    `name`             varchar(255)   DEFAULT NULL COMMENT '优惠卷名字',
    `amount`           decimal(10, 2) DEFAULT NULL COMMENT '金额',
    `discount`         decimal(10, 2) DEFAULT NULL COMMENT '折扣：取值[1 到 10]',
    `condition_amount` decimal(10, 2) DEFAULT NULL COMMENT '使用门槛 0->没门槛',
    `publish_count`    int            DEFAULT NULL COMMENT '发行数量',
    `per_limit`        int            DEFAULT NULL COMMENT '每人限领张数',
    `use_count`        int            DEFAULT NULL COMMENT '已使用数量',
    `receive_count`    int            DEFAULT NULL COMMENT '领取数量',
    `expire_time`      datetime       DEFAULT NULL COMMENT '过期时间',
    `description`      varchar(255)   DEFAULT NULL COMMENT '优惠券描述',
    `status`           int            DEFAULT NULL COMMENT '状态[0-未发布，1-已发布，-1-已过期]',

    -- BaseEntity 通用字段（如与您项目不一致，请告诉我）
    `create_time`      datetime       DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`      datetime       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`       tinyint(1)     DEFAULT 0 COMMENT '逻辑删除标志 0=未删除 1=已删除',

    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='CouponInfo 优惠券信息';
-- 公共字段约定：id 主键、自增；is_deleted 逻辑删除；create_time/ update_time 自动填充
-- 若需调整精度/长度，请按业务修改 varchar/decimal 长度


CREATE TABLE `customer_coupon`
(
    `id`           BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `coupon_id`    BIGINT,
    `customer_id`  BIGINT,
    `status`       INT,
    `receive_time` DATETIME,
    `used_time`    DATETIME,
    `order_id`     BIGINT,
    `expire_time`  DATETIME,
    `create_time`  DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time`  DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`   TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `customer_car`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `customer_id` BIGINT,
    `license`     VARCHAR(50),
    `brand`       VARCHAR(100),
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`  TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `customer_info`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `wx_open_id`  VARCHAR(100),
    `nickname`    VARCHAR(100),
    `gender`      VARCHAR(10),
    `avatar_url`  VARCHAR(255),
    `phone`       VARCHAR(30),
    `status`      INT,
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`  TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `customer_login_log`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `customer_id` BIGINT,
    `ipaddr`      VARCHAR(64),
    `status`      TINYINT,
    `msg`         VARCHAR(255),
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`  TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `order_job`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `order_id`    BIGINT,
    `job_id`      BIGINT,
    `parameter`   VARCHAR(500),
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`  TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `xxl_job_log`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `job_id`      BIGINT,
    `status`      INT,
    `error`       VARCHAR(1000),
    `times`       INT,
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`  TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `driver_account`
(
    `id`                  BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `driver_id`           BIGINT,
    `total_amount`        DECIMAL(18, 2),
    `lock_amount`         DECIMAL(18, 2),
    `available_amount`    DECIMAL(18, 2),
    `total_income_amount` DECIMAL(18, 2),
    `total_pay_amount`    DECIMAL(18, 2),
    `create_time`         DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time`         DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`          TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `driver_account_detail`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `driver_id`   BIGINT,
    `content`     VARCHAR(255),
    `trade_type`  VARCHAR(50),
    `amount`      DECIMAL(18, 2),
    `trade_no`    VARCHAR(100),
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`  TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `driver_face_recognition`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `driver_id`   BIGINT,
    `face_date`   DATETIME,
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`  TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `driver_info`
(
    `id`                        BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `wx_open_id`                VARCHAR(100),
    `nickname`                  VARCHAR(100),
    `avatar_url`                VARCHAR(255),
    `phone`                     VARCHAR(30),
    `name`                      VARCHAR(100),
    `gender`                    VARCHAR(10),
    `birthday`                  DATETIME,
    `idcard_no`                 VARCHAR(30),
    `idcard_address`            VARCHAR(255),
    `idcard_expire`             DATETIME,
    `idcard_front_url`          VARCHAR(255),
    `idcard_back_url`           VARCHAR(255),
    `idcard_hand_url`           VARCHAR(255),
    `driver_license_class`      VARCHAR(20),
    `driver_license_no`         VARCHAR(50),
    `driver_license_expire`     DATETIME,
    `driver_license_issue_date` DATETIME,
    `driver_license_front_url`  VARCHAR(255),
    `driver_license_back_url`   VARCHAR(255),
    `driver_license_hand_url`   VARCHAR(255),
    `contact_name`              VARCHAR(100),
    `contact_phone`             VARCHAR(30),
    `contact_relationship`      VARCHAR(50),
    `face_model_id`             VARCHAR(100),
    `job_no`                    VARCHAR(50),
    `order_count`               INT,
    `score`                     DECIMAL(18, 2),
    `auth_status`               INT,
    `status`                    INT,
    `create_time`               DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time`               DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`                TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `driver_login_log`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `driver_id`   BIGINT,
    `ipaddr`      VARCHAR(64),
    `status`      TINYINT,
    `msg`         VARCHAR(255),
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`  TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `driver_set`
(
    `id`              BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `driver_id`       BIGINT,
    `service_status`  INT,
    `order_distance`  DECIMAL(18, 2),
    `accept_distance` DECIMAL(18, 2),
    `is_auto_accept`  INT,
    `create_time`     DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time`     DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`      TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `order_bill`
(
    `id`                         BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `order_id`                   BIGINT,
    `fee_rule_id`                BIGINT,
    `total_amount`               DECIMAL(18, 2),
    `pay_amount`                 DECIMAL(18, 2),
    `distance_fee`               DECIMAL(18, 2),
    `wait_fee`                   DECIMAL(18, 2),
    `toll_fee`                   DECIMAL(18, 2),
    `parking_fee`                DECIMAL(18, 2),
    `other_fee`                  DECIMAL(18, 2),
    `long_distance_fee`          DECIMAL(18, 2),
    `favour_fee`                 DECIMAL(18, 2),
    `reward_fee`                 DECIMAL(18, 2),
    `reward_rule_id`             BIGINT,
    `coupon_amount`              DECIMAL(18, 2),
    `base_distance`              DECIMAL(18, 2),
    `base_distance_fee`          DECIMAL(18, 2),
    `exceed_distance`            DECIMAL(18, 2),
    `exceed_distance_price`      DECIMAL(18, 2),
    `base_wait_minute`           INT,
    `exceed_wait_minute`         INT,
    `exceed_wait_minute_price`   DECIMAL(18, 2),
    `base_long_distance`         DECIMAL(18, 2),
    `exceed_long_distance`       DECIMAL(18, 2),
    `exceed_long_distance_price` DECIMAL(18, 2),
    `create_time`                DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time`                DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`                 TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `order_comment`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `order_id`    BIGINT,
    `driver_id`   BIGINT,
    `customer_id` BIGINT,
    `rate`        INT,
    `remark`      VARCHAR(500),
    `status`      INT,
    `instance_id` VARCHAR(100),
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`  TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `order_info`
(
    `id`                    BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `customer_id`           BIGINT,
    `order_no`              VARCHAR(100),
    `start_location`        VARCHAR(255),
    `start_point_longitude` DECIMAL(18, 6),
    `start_point_latitude`  DECIMAL(18, 6),
    `end_location`          VARCHAR(255),
    `end_point_longitude`   DECIMAL(18, 6),
    `end_point_latitude`    DECIMAL(18, 6),
    `expect_distance`       DECIMAL(18, 2),
    `real_distance`         DECIMAL(18, 2),
    `expect_amount`         DECIMAL(18, 2),
    `real_amount`           DECIMAL(18, 2),
    `favour_fee`            DECIMAL(18, 2),
    `driver_id`             BIGINT,
    `accept_time`           DATETIME,
    `arrive_time`           DATETIME,
    `start_service_time`    DATETIME,
    `end_service_time`      DATETIME,
    `pay_time`              DATETIME,
    `cancel_rule_id`        BIGINT,
    `car_license`           VARCHAR(50),
    `car_type`              VARCHAR(100),
    `car_front_url`         VARCHAR(255),
    `car_back_url`          VARCHAR(255),
    `transaction_id`        VARCHAR(100),
    `status`                INT,
    `remark`                VARCHAR(500),
    `create_time`           DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time`           DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`            TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `order_monitor`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `order_id`    BIGINT,
    `file_num`    INT,
    `audit_num`   INT,
    `is_alarm`    INT,
    `status`      INT,
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`  TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `order_profitsharing`
(
    `id`              BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `order_id`        BIGINT,
    `rule_id`         BIGINT,
    `order_amount`    DECIMAL(18, 2),
    `payment_rate`    DECIMAL(18, 4),
    `payment_fee`     DECIMAL(18, 2),
    `driver_tax_rate` DECIMAL(18, 4),
    `driver_tax_fee`  DECIMAL(18, 2),
    `platform_income` DECIMAL(18, 2),
    `driver_income`   DECIMAL(18, 2),
    `status`          INT,
    `create_time`     DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time`     DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`      TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `order_status_log`
(
    `id`           BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `order_id`     BIGINT,
    `order_status` INT,
    `operate_time` DATETIME,
    `create_time`  DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time`  DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`   TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `order_track`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `order_id`    BIGINT,
    `driver_id`   BIGINT,
    `customer_id` BIGINT,
    `longitude`   VARCHAR(50),
    `latitude`    VARCHAR(50),
    `speed`       VARCHAR(50),
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`  TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `payment_info`
(
    `id`               BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `customer_open_id` VARCHAR(100),
    `driver_open_id`   VARCHAR(100),
    `order_no`         VARCHAR(100),
    `pay_way`          INT,
    `transaction_id`   VARCHAR(100),
    `amount`           DECIMAL(18, 2),
    `content`          VARCHAR(255),
    `payment_status`   INT,
    `callback_time`    DATETIME,
    `callback_content` TEXT,
    `create_time`      DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time`      DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`       TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `profitsharing_info`
(
    `id`              BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `driver_id`       BIGINT,
    `order_no`        VARCHAR(100),
    `transaction_id`  VARCHAR(100),
    `out_trade_no`    VARCHAR(100),
    `amount`          VARCHAR(50),
    `state`           VARCHAR(50),
    `respone_content` TEXT,
    `create_time`     DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time`     DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`      TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `cancel_rule`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `name`        VARCHAR(100),
    `rule`        TEXT,
    `status`      INT,
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`  TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `fee_rule`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `name`        VARCHAR(100),
    `rule`        TEXT,
    `status`      INT,
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`  TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `profitsharing_rule`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `name`        VARCHAR(100),
    `rule`        TEXT,
    `status`      INT,
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`  TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `reward_rule`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `name`        VARCHAR(100),
    `rule`        TEXT,
    `status`      INT,
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`  TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `sys_dept`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `name`        VARCHAR(100),
    `parent_id`   BIGINT,
    `tree_path`   VARCHAR(255),
    `sort_value`  INT,
    `leader`      VARCHAR(100),
    `phone`       VARCHAR(30),
    `status`      INT,
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`  TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `sys_login_log`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `username`    VARCHAR(100),
    `ipaddr`      VARCHAR(64),
    `status`      INT,
    `msg`         VARCHAR(255),
    `access_time` DATETIME,
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`  TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `sys_menu`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `parent_id`   BIGINT,
    `name`        VARCHAR(100),
    `type`        INT,
    `path`        VARCHAR(255),
    `component`   VARCHAR(255),
    `perms`       VARCHAR(255),
    `icon`        VARCHAR(100),
    `sort_value`  INT,
    `status`      INT,
    `active_menu` VARCHAR(255),
    `is_hide`     INT,
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`  TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `sys_oper_log`
(
    `id`             BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `title`          VARCHAR(100),
    `business_type`  VARCHAR(20),
    `method`         VARCHAR(255),
    `request_method` VARCHAR(20),
    `operator_type`  VARCHAR(20),
    `oper_name`      VARCHAR(100),
    `dept_name`      VARCHAR(100),
    `oper_url`       VARCHAR(255),
    `oper_ip`        VARCHAR(64),
    `oper_param`     TEXT,
    `json_result`    TEXT,
    `status`         INT,
    `error_msg`      VARCHAR(500),
    `oper_time`      DATETIME,
    `create_time`    DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time`    DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`     TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `sys_post`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `post_code`   VARCHAR(50),
    `name`        VARCHAR(100),
    `description` VARCHAR(255),
    `status`      INT,
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`  TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `sys_role`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `role_name`   VARCHAR(100),
    `role_code`   VARCHAR(100),
    `description` VARCHAR(255),
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`  TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `sys_role_menu`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `role_id`     BIGINT,
    `menu_id`     BIGINT,
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`  TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `sys_user`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `username`    VARCHAR(100),
    `password`    VARCHAR(200),
    `name`        VARCHAR(100),
    `phone`       VARCHAR(30),
    `head_url`    VARCHAR(255),
    `dept_id`     BIGINT,
    `post_id`     BIGINT,
    `description` VARCHAR(255),
    `status`      INT,
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`  TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `sys_user_role`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `role_id`     BIGINT,
    `user_id`     BIGINT,
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`  TINYINT(1) DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;