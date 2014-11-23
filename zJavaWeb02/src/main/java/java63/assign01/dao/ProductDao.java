package java63.assign01.dao;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java63.assign01.domain.Product;

import javax.servlet.annotation.WebServlet;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@WebServlet
public class ProductDao {
  SqlSessionFactory sqlSessionFactory;
  
  public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  
  public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}


	public ProductDao() throws Exception {
  	
  String resource = "java63/assign01/dao/mybatis-config.xml";
  InputStream inputStream = Resources.getResourceAsStream(resource);
 
  this.sqlSessionFactory = 
      new SqlSessionFactoryBuilder().build(inputStream);}

  
  public Product selectOne(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    
    try {
      return sqlSession.selectOne(
        "java63.assign01.dao.ProductDao.selectOne", 
        no /* new Integer(no) */);
    } finally {
      sqlSession.close();
    }
  }
  
  public void update(Product product) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      sqlSession.update(
        "java63.assign01.dao.ProductDao.update", product);
      sqlSession.commit();
    } finally {
      sqlSession.close();
    }
  }
  
  public void delete(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      sqlSession.delete(
        "java63.assign01.dao.ProductDao.delete", no);
      sqlSession.commit();
    } finally {
      sqlSession.close();
    }
  }
  
  public List<Product> selectList(int pageNo, int pageSize) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("startIndex", ((pageNo - 1) * pageSize));
    paramMap.put("pageSize", pageSize);
    
    try {
      return sqlSession.selectList(
        // 네임스페이스 + SQL문 아이디
        "java63.assign01.dao.ProductDao.selectList", 
        paramMap /* SQL문을 실행할 때 필요한 값 전달 */);
    } finally {
      sqlSession.close();
    }
  }
  
  public void insert(Product product) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      sqlSession.insert(
        "java63.assign01.dao.ProductDao.insert", product);
      sqlSession.commit();
    } finally {
      sqlSession.close();
    }
  }
}


















