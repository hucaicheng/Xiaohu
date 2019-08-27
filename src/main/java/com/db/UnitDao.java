package com.db;

import com.model.Unit;

import java.util.List;

public interface UnitDao {
    boolean InsertUnit(String unit_name);
    List<Unit> GetAllUnit();
    boolean UpdateUnit(String unit_name);
    boolean DeleteUnit(String unit_name);
}
