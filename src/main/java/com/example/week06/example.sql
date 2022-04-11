CREATE TABLE `test`.`user`
(
    `id`    INT NOT NULL,
    `name`  VARCHAR(45) NULL,
    `level` INT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `test`.`goods`
(
    `id`    INT NOT NULL,
    `name`  VARCHAR(45) NULL,
    `price` DECIMAL(10, 2) NULL,
    `url`   VARCHAR(45) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `test`.`order`
(
    `id`          INT         NOT NULL,
    `orderNo`     VARCHAR(32) NOT NULL,
    `user_id`     INT NULL,
    `goods_id`    INT NULL,
    `count`       INT NULL,
    `create_time` DATETIME NULL,
    `update_time` DATETIME NULL,
    PRIMARY KEY (`id`),
    INDEX         `index_order` (`orderNo` ASC) VISIBLE
);
