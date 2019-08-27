package com.db;

import com.model.Phone;

import java.rmi.server.UID;

public interface PhoneDao {
    boolean InsertPhone(String id,String u_phone);
    Phone FindIdByPhone(String phone);
    Phone FindPhoneById(String id);
    boolean DeleteById(String id);
    boolean DeleteByPhone(String phone);
    boolean updatePhone(String id,String phone);

}
