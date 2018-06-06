package com.ttms.service;

import com.ttms.dao.ScheduleMapper;
import com.ttms.pojo.Schedule;
import com.ttms.pojo.ScheduleExample;
import com.ttms.serviceInterface.scheduleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MINUTES;

/**
 * @Author: Allen
 * @Description: 演出计划管理
 * @Date: Created in 10:03 2018/6/6
 * @Modify By:
 */
@Service
public class scheduleService implements scheduleManager {

    @Autowired
    private ScheduleMapper sm;

    /**
     * 添加一个演出计划
     * 步骤
     * 1 冲突检测（即开始时间上对应演出厅观众是否在观看）
     * 2 批量生成票
     *
     * @param schedule
     */
    @Override
    public void addSch(Schedule schedule) {
        /*ScheduleExample example = new ScheduleExample();
        ScheduleExample.Criteria criteria = example.createCriteria();
        // 找出所有的演出计划
        List<Schedule> schedules = sm.selectByExample(null);
        Instant ori = schedule.getSchedTime().toInstant();

        int minindex = -1;
        for (int i = 0; i < schedules.size(); ++i) {
            int compare = schedules.get(i).getSchedTime().toInstant().compareTo(ori);
            if (compare >= 1)
                minindex = i;
        }
        // 如果存在
        if (minindex != -1) {
            // 增加123分钟
            // 然后就说明有布置未来演出计划的
            // 然后就需要保证和未来的演出计划不能有时间上的重合部分
            boolean isminexit = queryTime(schedules, minindex);
        }

        instant.plus(123, ChronoUnit.MINUTES);

        // 不存在就直接创建就好了
        sm.insert(schedule);*/

    }

  /*  *//**
     *//*
    private boolean queryTime(List<Schedule> schedules, int index) {
        int minindex = index-1;
        
        return false;
    }*/

    @Override
    public void modifySch(Schedule schedule) {

    }
}
