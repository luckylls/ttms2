package com.ttms.serviceInterface;

import com.ttms.pojo.Ticket;

import java.util.List;

/**
 * @Author: Allen
 * @Description:    票管理
 * @Date: Created in 16:46 2018/6/4
 * @Modify By:
 */
public interface ticketManager {
    /**
     * 批量生成票
     * @param ticket
     */
    void batchAddTic(List<Ticket> ticket);

    /**
     * 更改票的状态
     * 如：
     * 退票
     * @param ticketId
     */
    void modifyTicketStatus(Long ticketId);
}
