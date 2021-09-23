package com.bitcamp.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxController {
   @RequestMapping("/ajax")
   public String ajaxStart() {
      return "ajax/ajaxView";
   }
   @RequestMapping(value="/ajaxStr",method=RequestMethod.GET,produces = "application/text;charset=UTF-8")
   @ResponseBody
   public String ajaxString(int num, String name) {
      System.out.println("num="+num+", name="+name);
      return "<h1>번호="+num+", 이름="+name+"</h1>";
      
   }
}