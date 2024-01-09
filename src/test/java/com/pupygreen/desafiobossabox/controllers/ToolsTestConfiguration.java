package com.pupygreen.desafiobossabox.controllers;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ToolsTestConfiguration {

  @Bean
  @Qualifier("mysql")
  public DataSource mysqlDataSource() {
    // Configure a DataSource bean for MySQL here

    HikariDataSource dataSource = new HikariDataSource();
    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/tools");
    dataSource.setUsername("root");
    dataSource.setPassword("root");
    return dataSource;
  }
}
