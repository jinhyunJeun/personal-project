package com.jhjeun.pwiki.board.controller;

import java.util.List;

import com.jhjeun.pwiki.board.model.BoardModel;
import com.jhjeun.pwiki.board.service.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/board/*")
public class BoardController {
    
    @Autowired
    private BoardService service;

    @RequestMapping(value="/list_view", method=RequestMethod.GET)
    public String list_view() {
        return "board/list";
    }

    // @RequestMapping(value="/detail_view", method=RequestMethod.GET)
    // public String detail_view() {
    //     return "board/detail";
    // }

    // @RequestMapping(value="/form", method=RequestMethod.GET)
    // public String form() {
    //     return "board/form";
    // }
    
    @RequestMapping(value="/api/list_view", method=RequestMethod.GET)
    @ResponseBody
    public List<BoardModel> list(){
        List<BoardModel> list = service.listAll();
        
        return list;
    }

    @RequestMapping(value="/api/create", method=RequestMethod.POST)
    @ResponseBody
    public void form(@RequestBody BoardModel model) {
        model.setDeleteYn("N");
        service.create(model);
    }

    @RequestMapping(value="/api/update_view", method=RequestMethod.POST)
    @ResponseBody
    public BoardModel selectOne(@RequestBody BoardModel model) {
        int id = model.getId();
        return service.selectOne(id);
    }

    @RequestMapping(value="/api/delete", method=RequestMethod.GET)
    @ResponseBody
    public void delete(@RequestBody BoardModel model) {
        int id = model.getId();
        service.deleteOne(id);
    }

    @RequestMapping(value="/api/updateDelete", method=RequestMethod.POST)
    @ResponseBody
    public void updateDelete(@RequestBody BoardModel model) {
        int id = model.getId();
        service.updateDelete(id);
    }
}