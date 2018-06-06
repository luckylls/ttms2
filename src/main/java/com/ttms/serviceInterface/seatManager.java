package com.ttms.serviceInterface;

/**
 * @Author: Allen
 * @Description:   座位管理
 * @Date: Created in 16:36 2018/6/4
 * @Modify By:
 */
public interface seatManager {
    /**
     * 批量生成座位
     * 步骤：
     * 1 循环生成座位
     * @param seatRow
     * @param seatColumn
     */
    void batchAdd(Integer seatRow,Integer seatColumn);

    /**
     * 更改座位状态
     * @param seatId
     */
    void modifySeatStatus(Integer seatId);
}
