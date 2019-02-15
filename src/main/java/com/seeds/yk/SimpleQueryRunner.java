package com.seeds.yk;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

/**
 *
 */
public class SimpleQueryRunner  extends QueryRunner{
	
	public SimpleQueryRunner(DataSource ds ) {
		super(ds);
	}
	public long insert(String sql,Object ...objs) {
		try {
			return insert(this.getDataSource().getConnection(),sql,objs);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public long insert(Connection cnn ,String sql,Object ...objs) {
		try {
			return this.insert(cnn, sql, new ScalarHandler<Long>(),objs);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Map<String,Object> queryMap(String sql,Object ...objs){
		try {
			return queryMap(this.getDataSource().getConnection(),sql,objs);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public Map<String,Object> queryMap(Connection cnn ,String sql,Object ...objs){
		try {
			return this.query(cnn,sql,new MapHandler(),objs);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Map<String,Object>> queryListMap(String sql,Object ...objs){
		try {
			return queryListMap(this.getDataSource().getConnection(), sql, objs);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public List<Map<String,Object>> queryListMap(Connection cnn,String sql,Object ...objs){
		try {
			return this.query(cnn, sql, new MapListHandler(),objs);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	
	@Test
	public void test() {
	}
    	
}
