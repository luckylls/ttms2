package com.ttms.service;

import com.ttms.dao.EmployeeMapper;
import com.ttms.pojo.Employee;
import com.ttms.serviceInterface.employeeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Allen
 * @Description:    员工管理
 * @Date: Created in 10:06 2018/6/5
 * @Modify By:
 */
@Service
public class employeeService implements employeeManager {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public void addEmp(Employee employee) {
        employeeMapper.insert(employee);
    }

    @Override
    public void dismissEmp(Integer empId) {
        employeeMapper.deleteByPrimaryKey(empId);
    }
}
