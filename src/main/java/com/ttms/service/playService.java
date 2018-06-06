package com.ttms.service;

import com.ttms.dao.DataDictMapper;
import com.ttms.dao.PlayMapper;
import com.ttms.pojo.DataDict;
import com.ttms.pojo.DataDictExample;
import com.ttms.pojo.Play;
import com.ttms.pojo.PlayExample;
import com.ttms.serviceInterface.playManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Allen
 * @Description:   剧目管理
 * @Date: Created in 8:29 2018/6/5
 * @Modify By:
 */
@Service
public class playService implements playManager {

    @Autowired
    private PlayMapper playMapper;
    @Autowired
    private DataDictMapper ddm;

    /**
     * 添加剧目
     * @param play
     */
    @Override
    public void addplay(String play_type,String play_language,Play play) {
        Integer play_typeId = selectPlayLanguageOrTypeId(play_type);
        Integer play_languageId = selectPlayLanguageOrTypeId(play_language);
        play.setPlayTypeId(play_typeId);
        play.setPlayLangId(play_languageId);
        playMapper.insert(play);
    }

    /**
     * 根据剧目语言value或者剧目类型value得到ID
     * @param play_language
     * @return
     */
    private Integer selectPlayLanguageOrTypeId(String play_language) {
        DataDictExample dde = new DataDictExample();
        DataDictExample.Criteria criteria = dde.createCriteria();
        criteria.andDictValueEqualTo(play_language);
        List<DataDict> dataDicts = ddm.selectByExample(dde);
        Integer dictId = dataDicts.get(0).getDictId();
        return dictId;
    }

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Play> selectAll() {
        return playMapper.selectByExample(null);
    }

    /**
     * 根据电影语言和电影类别查询
     * 需要的格式是：两个需要根据,隔开
     * 四种
     * 1 什么都没选
     * 2 选了剧目类型
     * 3 选了剧目语言
     * 4 两个都选了
     * @param condition
     */
    @Override
    public List<Play> selectByCondition(String condition) {
        // 1 什么都没选
        if (condition == null){
            return selectAll();
        }
        String[] conditions = condition.split(",");
        // 2 选了剧目类型
        if ("#".equals(conditions[1])){
            Integer play_type = getPlay_typeID(conditions[0]);
            return selectByType(play_type);
        }
        // 3 选了剧目语言
        if ("#".equals(conditions[0])){
            Integer play_language = getPlay_typeID(conditions[1]);
            return selectByLanguage(play_language);
        }
        // 4 两个都选了
        Integer play_type = getPlay_typeID(conditions[0]);
        Integer play_language = getPlay_typeID(conditions[1]);
        PlayExample pe = new PlayExample();
        PlayExample.Criteria pec = pe.createCriteria();
        pec.andPlayTypeIdEqualTo(play_type);
        pec.andPlayLangIdEqualTo(play_language);
        return playMapper.selectByExample(pe);
    }

    /**
     * 获取ID
     * @param con
     * @return
     */
    private Integer getPlay_typeID(String con){
        DataDictExample dde = new DataDictExample();
        DataDictExample.Criteria ddc = dde.createCriteria();
        ddc.andDictValueEqualTo(con);
        List<DataDict> dataDicts = ddm.selectByExample(dde);
        return dataDicts.get(0).getDictId();
    }

    /**
     * 根据剧目语言查询
     * @param play_language
     * @return
     */
    private List<Play> selectByLanguage(Integer play_language) {
        PlayExample pe = new PlayExample();
        PlayExample.Criteria pc = pe.createCriteria();
        pc.andPlayLangIdEqualTo(play_language);
        List<Play> playList = playMapper.selectByExample(pe);
        return playList;
    }

    /**
     * 根据剧目类型查询
     * @param play_type
     * @return
     */
    private List<Play> selectByType(Integer play_type) {
        PlayExample pe = new PlayExample();
        PlayExample.Criteria pc = pe.createCriteria();
        pc.andPlayTypeIdEqualTo(play_type);
        List<Play> playList = playMapper.selectByExample(pe);
        return playList;
    }

    /**
     * 电影下架
     * @param playId
     */
    @Override
    public void dropOff(Integer playId) {
        // 修改状态
        Play play = playMapper.selectByPrimaryKey(playId);
        play.setPlayStatus((short) -1);
        // 进行更新
        PlayExample example = new PlayExample();
        PlayExample.Criteria criteria = example.createCriteria();
        criteria.andPlayIdEqualTo(playId);
        playMapper.updateByExample(play,example);
    }

    /**
     *
     * 统计票房
     * @param playId
     * @return
     */
    @Override
    public Integer boxOfficeStatistics(Integer playId) {
        Play play = playMapper.selectByPrimaryKey(playId);
        return play.getPlayBookingOffice();
    }

    /**
     * 获取所有的电影类型和所有电影语言和电影状态选择
     * @return
     */
    @Override
    public List<List<String>> getPlayTypesAndLanguages() {
        // 查询电影类别
        DataDictExample example = new DataDictExample();
        DataDictExample.Criteria criteria = example.createCriteria();
        criteria.andDictParentIdEqualTo(2);
        List<DataDict> types = ddm.selectByExample(example);

        // 查询电影语言
        DataDictExample example2 = new DataDictExample();
        DataDictExample.Criteria criteria2 = example2.createCriteria();
        criteria2.andDictParentIdEqualTo(3);
        List<DataDict> languages = ddm.selectByExample(example2);

        List<List<String>> choiceInfo = new ArrayList<>();
        List<String> typeList = new ArrayList<>();
        List<String> languageList = new ArrayList<>();
        // 添加到集合中
        // 添加类型
        for (DataDict dict : types){
            typeList.add(dict.getDictValue());
        }
        // 添加类型
        for (DataDict dict: languages){
            languageList.add(dict.getDictValue());
        }
        // 添加状态
        List<String> playStatus = new ArrayList<>();
        playStatus.add(-1+"");
        playStatus.add(0+"");
        playStatus.add(1+"");

        choiceInfo.add(typeList);
        choiceInfo.add(languageList);
        choiceInfo.add(playStatus);
        return choiceInfo;
    }
}
