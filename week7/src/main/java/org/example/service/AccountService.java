package org.example.service;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.dao.AccountDao;
import org.example.domain.Account;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AccountService {
    private  InputStream inputStream;
    private SqlSession sqlSession;
    private AccountDao accountDao;

    public AccountService() throws IOException {
        inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
        accountDao = sqlSession.getMapper(AccountDao.class);
    }
    private void destroy(){
        try {
            sqlSession.commit();
            sqlSession.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void rollBack(){
        try {
            sqlSession.rollback();
            sqlSession.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Account> findAll() {
        return accountDao.findAll();
    }
    public void insert(Account record){
        int count = accountDao.insert(record);
        destroy();
    }
    public Account selectByPrimaryKey(String id){
        return accountDao.selectByPrimaryKey(id);
    }
    public void updateByPrimaryKey(Account record){
        int count = accountDao.updateByPrimaryKey(record);
        destroy();
    }
    public void deleteByPrimaryKey(String id){
        int count = accountDao.deleteByPrimaryKey(id);
        destroy();
    }
    public void transfer(String remitterId,String remittedId,Integer money){
        Account remitter = accountDao.selectByPrimaryKey(remitterId);
        Account remitted = accountDao.selectByPrimaryKey(remittedId);
        if (remitter.getMoney()>=money){
            remitter.setMoney(remitter.getMoney()-money);
            remitted.setMoney(remitted.getMoney()+money);
            accountDao.updateByPrimaryKey(remitted);
            accountDao.updateByPrimaryKey(remitter);
            destroy();
        }
        rollBack();
    }
}
