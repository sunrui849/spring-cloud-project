CREATE TABLE `config_properties` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key1` varchar(50) COLLATE utf8_bin NOT NULL,
  `value1` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `application` varchar(50) COLLATE utf8_bin NOT NULL,
  `profile` varchar(50) COLLATE utf8_bin NOT NULL,
  `label` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin


INSERT INTO config_jdbc.config_properties (key1,value1,application,profile,label) VALUES
	 ('port','8786','business-service-provider','prod','master'),
	 ('port','8888','api-gateway','prod','master'),
	 ('port','7788','user-service-provider','prod','master'),
	 ('db_host','11.50.67.103','business-service-provider','prod','master'),
	 ('db_port','3306','business-service-provider','prod','master'),
	 ('db_database','demo','business-service-provider','prod','master'),
	 ('db_user_name','root','business-service-provider','prod','master'),
	 ('db_password','admin!@#123','business-service-provider','prod','master');