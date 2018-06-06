package com.ttms.controller;

import com.ttms.pojo.Play;
import com.ttms.service.playService;
import com.ttms.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: Allen
 * @Description:   剧目相关
 * @Date: Created in 8:26 2018/6/5
 * @Modify By:
 */
@Controller
@RequestMapping("/play")
public class playController {

    @Autowired
    private playService ps;

    @RequestMapping("/add")
    @ResponseBody
    public TaotaoResult addc(Play play){
        /*ps.addplay(play);*/
        return TaotaoResult.ok();
    }

    @RequestMapping("/ltInfo")
    @ResponseBody
    public TaotaoResult getlts(){
        List<List<String>> playTypesAndLanguages = ps.getPlayTypesAndLanguages();
        return TaotaoResult.ok(playTypesAndLanguages);
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public TaotaoResult getlist(){
        List<Play> playList = ps.selectAll();
        return TaotaoResult.ok(playList);
    }
}
