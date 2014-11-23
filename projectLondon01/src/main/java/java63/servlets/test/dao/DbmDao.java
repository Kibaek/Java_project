package java63.servlets.test.dao;

import java.util.HashMap;
import java.util.List;
import java63.servlets.test.domain.Dbm;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DbmDao {
  @Autowired
  SqlSessionFactory sqlSessionFactory;
  
  public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  public DbmDao() {}

  public Dbm selectOne(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    
    try {
      return sqlSession.selectOne(
        "java63.servlets.test.dao.DbmDao.selectOne", 
        no /* new Integer(no) */);
    } finally {
      sqlSession.close();
    }
  }
  
  public void update(Dbm product) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      sqlSession.update(
        "java63.servlets.test.dao.DbmDao.update", product);
      sqlSession.commit();
    } finally {
      sqlSession.close();
    }
  }
  
  public void delete(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      sqlSession.delete(
        "java63.servlets.test.dao.DbmDao.delete", no);
      sqlSession.commit();
    } finally {
      sqlSession.close();
    }
  }
  
  public List<Dbm> selectList(int pageNo, int pageSize) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("startIndex", ((pageNo - 1) * pageSize));
    paramMap.put("pageSize", pageSize);
    
    try {
      return sqlSession.selectList(
        // 네임스페이스 + SQL문 아이디
        "java63.servlets.test.dao.DbmDao.selectList", 
        paramMap /* SQL문을 실행할 때 필요한 값 전달 */);
    } finally {
      sqlSession.close();
    }
  }
  
  public void insert(Dbm product) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      sqlSession.insert(
        "java63.servlets.test.dao.DbmDao.insert", product);
      sqlSession.commit();
    } finally {
      sqlSession.close();
    }
  }
}


















