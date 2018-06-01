package com.charsy.spring.boot.docker.demo1;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Data;

@Entity
@Data
@Table(name="wallet")
public class Wallet implements Serializable {
	
	private static final long serialVersionUID = -2810150677574812090L;
	
	@Id @GeneratedValue(strategy = GenerationType.TABLE, generator="wallet_gen")
    @TableGenerator(
            name="wallet_gen",
            table="generator_table",
            pkColumnName = "TABLE_ID",
            valueColumnName = "NEXT_ID",
            pkColumnValue="wallet",
            allocationSize=30
        )
	public Long id;
	
	private String name;

}
