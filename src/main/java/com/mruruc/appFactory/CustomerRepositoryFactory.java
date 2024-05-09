package com.mruruc.appFactory;

import com.mruruc.repository.CustomerRepository;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public final class CustomerRepositoryFactory {
    private static SqlSession sqlSession;
    private CustomerRepositoryFactory() {}

    public static CustomerRepository getCustomerRepository() {
        SqlSessionFactory sqlSessionFactory;
        try (InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml")) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        } catch (IOException ioException) {
            System.out.println("Error loading mybatis-config.xml file: " + ioException.getMessage());
            return null;
        }
         sqlSession = sqlSessionFactory.openSession(true);
        CustomerRepository mapper = sqlSession.getMapper(CustomerRepository.class);
        return mapper;
    }

    public void commitSession() {
        if (sqlSession != null) {
            sqlSession.commit();
        }
    }

    public void rollbackSession() {
        if (sqlSession != null) {
            sqlSession.rollback();
        }
    }

    public void flushSession() {
        if (sqlSession != null) {
            sqlSession.flushStatements();
        }
    }

    public void clearSession() {
        if (sqlSession != null) {
            sqlSession.clearCache();
        }
    }

    public void closeSession() {
        if (sqlSession != null) {
            sqlSession.close();
        }
    }
}

