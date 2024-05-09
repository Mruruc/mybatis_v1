package com.mruruc.repository;

import com.mruruc.model.Customer;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    @Insert("INSERT INTO customers(full_name, city, email, phone) VALUES(#{fullName}, #{city}, #{email}, #{phone})")
    @Options(useGeneratedKeys = true, keyProperty = "customerId")
    int saveCustomer(Customer customer);
    @Update("UPDATE customers " +
            "SET full_name = #{fullName}, city = #{city}, email = #{email}, phone = #{phone} " +
            "WHERE customer_id = #{customerId} RETURNING *")
    Customer updateCustomer(Customer customer);

    @Delete("DELETE FROM customers WHERE customer_id = #{customerId} RETURNING customer_id")
    Integer deleteCustomer(Integer customerId);
    @Select( "SELECT * FROM customers WHERE customer_id = #{customerId}")
    @Results({
            @Result(property = "customerId", column = "customer_id"),
            @Result(property = "fullName", column = "full_name"),
            @Result(property = "city", column = "city"),
            @Result(property = "email", column = "email"),
            @Result(property = "phone", column = "phone")
    })
    Optional<Customer> findCustomer(Integer customerId);
    @Select("SELECT customer_id, full_name, city, email, phone FROM customers")
    @Results({
            @Result(property = "customerId", column = "customer_id"),
            @Result(property = "fullName", column = "full_name"),
            @Result(property = "city", column = "city"),
            @Result(property = "email", column = "email"),
            @Result(property = "phone", column = "phone")
    })
    List<Customer> findAllCustomers();
}
