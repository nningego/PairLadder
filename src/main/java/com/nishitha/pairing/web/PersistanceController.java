package com.nishitha.pairing.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping(value = "/")
public class PersistanceController {

    protected JdbcTemplate jdbcTemplate;

    @RequestMapping(value="/init", method = {RequestMethod.GET})
    public String init(){
        System.out.println("dropping and creating pair ladder");
        this.jdbcTemplate.update("drop table pair_ladder if exists");
        this.jdbcTemplate.update("create table pair_ladder (id bigint, devName1 varchar(50), devName2 varchar(50)) ");
        return "init";
    }

    @RequestMapping(value="/save", method = {RequestMethod.GET})
    public String save() {
        System.out.print("inserting nishi niki");
        this.jdbcTemplate.update("insert into pair_ladder values (1,'nishi','niki')");
        return "save";
    }         
    
    @RequestMapping(value="/display", method = {RequestMethod.GET})
    public void display(){
        System.out.println("displaying database values");
        System.out.println(this.jdbcTemplate.queryForList("select * from pair_ladder"));
    }

    @Autowired
    public  void setDatasource(javax.sql.DataSource datasource){
        this.jdbcTemplate = new JdbcTemplate(datasource);

    }

}
