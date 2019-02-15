package com.seeds.yk;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.alibaba.druid.sql.ast.expr.SQLRealExpr;



public class TableService {
	
	
	public SqlResult insert(String table,Map<String,Object> map) {
		StringBuilder sb = new StringBuilder(" insert into ").append(table).append("(");
		StringBuilder tmpSb = new StringBuilder(" values(");
		List<Object> objList = new LinkedList<>();
		for(Map.Entry<String, Object> ent : map.entrySet()) {
			sb.append("`").append(ent.getKey().trim()).append("`,");
			tmpSb.append("?,");
			objList.add(ent.getValue());
		}
		String sql = sb.deleteCharAt(sb.length() -1).append(") ").append(tmpSb.deleteCharAt(tmpSb.length()-1).append(")")).toString();
		return new SqlResult(sql,objList.toArray(new Object[objList.size()]));
	}
	
	
	public SqlResult insert(String table,Map<String,Object> map,String key,Object obj) {
		StringBuilder sb = new StringBuilder(" update ").append(table).append(" set ");
		List<Object> objList =new LinkedList<>();
		for(Map.Entry<String, Object> ent : map.entrySet()) {
			sb.append(ent.getKey().trim()).append("=?,");
			objList.add(ent.getValue());
		}
		String sql = sb.deleteCharAt(sb.length() - 1).append(" where ").append(key).append(" = ").append(" ? ").toString();
		objList.add(obj);
		return new SqlResult(sql,objList.toArray(new Object[objList.size()]));
	}
	
	
	public static class SqlResult {
		String sql;
		Object[] objArr;
		public SqlResult() {};
		public SqlResult(String sql,Object[] arr) {
			this.sql = sql;
			this.objArr = arr;
		}
	}
}
