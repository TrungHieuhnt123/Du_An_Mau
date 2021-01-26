/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;

/**
 *
 * @author Laptop88
 */
public abstract class EduSysDao<Lop,con> {
    abstract public void insert(Lop ten);
    abstract public void update(Lop ten);
    abstract public void delete(con caNhan);
    abstract public List<Lop> selectAll();
    abstract public boolean selectByID(con caNhan);
    abstract protected List<Lop> selectBySql(String sql,Object...args);
}
