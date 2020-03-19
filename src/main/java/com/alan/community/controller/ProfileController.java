package com.alan.community.controller;

import com.alan.community.dto.PaginationDTO;
import com.alan.community.mapper.UserMapper;
import com.alan.community.model.Question;
import com.alan.community.model.User;
import com.alan.community.service.QuestionService;
import com.github.pagehelper.PageHelper;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author alan
 * @date 2020/3/19 9:21
 */
@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile")
    public String profile(@RequestParam("action")String action, Model model,HttpServletRequest request,PaginationDTO pageDTO){
        User user = (User) request.getSession().getAttribute("CurrentUser");
        if (user==null){
            return "redirect:/";
        }

        if ("questions".equals(action)) {
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
            PaginationDTO paginationDTO = questionService.queryAllQuestion(pageDTO,user.getId());
            model.addAttribute("paginationDTO",paginationDTO);
            model.addAttribute("action",action);
        }else if ("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
            model.addAttribute("action",action);
        }

      return "profile";
    }

}