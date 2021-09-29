package com.bitcamp.myapp;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
   
   @RequestMapping(value = "/", method = RequestMethod.GET)
   public String home() {
      
      return "home";
   }
   
   // 여러개의 파일을 업로드하는 경우(name이 같은 경우)
   @RequestMapping(value = "/dataUpload", method = RequestMethod.POST)
   public ModelAndView fileUploadTest(DataVO vo, HttpServletRequest req) {
      // vo에 작성자, 제목, 파일이름이 스프링에 의해 셋팅되어 있는 상태에서

      // ★ 얘가 실제 업로드될 폴더의 경로를 구해줄 것이다. 
      String path = req.getSession().getServletContext().getRealPath("/upload");
      System.out.println(path);
      
      // 파일 업로드를 위해서 HttpServletRequest 객체로 MultipartHttpServletRequest 인스턴스를 구해야 한다.
      // 이친구는 인터페이스이므로 new로 못하고 형변환을 통해서 인스턴스를 생성하는 것이다. 
      MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
      
      // mr에서 getFiles("form에서 업로드되는 name") 메소드를 이용해 MultipartFile 객체를 가져옴
      List<MultipartFile> files = mr.getFiles("filename");
      
      // 업로드된 파일명을 저장할 리스트
      List<String> fileList = new ArrayList<String>();
      if(files != null) {
         // 업로드된 파일이 있으면 업로드 실행
         for(int i=0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            // 덮어쓰기 방지를 위해 rename해야 하는데
            // 먼저 원래 파일 이름을 구한다.
            String fname = file.getOriginalFilename();
            if(fname != null && !fname.equals("")) {
               // 다음으로 서버의 해당 경로에 같은 파일명이 존재하는지를 확인한다.
               File fileObj = new File(path, fname);
               File newFileObj = new File(path, fname);
               if(fileObj.exists()) {
                  for(int num=1; ; num++) {
                  // 존재하는 경우 파일명 변경
                     int point = fname.lastIndexOf(".");
                     String orgFileName = fname.substring(0, point); // 파일명
                     String orgFileExt = fname.substring(point+1); // 확장자
                     // 파일명(1), 파일명(2), ... 등이 있는지 확인
                     String newFileName = orgFileName + "(" + num + ")." + orgFileExt;
                     newFileObj = new File(path, newFileName);
                     if(!newFileObj.exists()) {
                        break; // 없으면 중단하고 다음 i로
                     }
                  }                  
               }
               // 업로드
               try {
                  file.transferTo(newFileObj);
               } catch(Exception e){
                  e.printStackTrace();
               }
               fileList.add(newFileObj.getName());
            }
         }
      }
      // 파일리스트 vo에 저장
      vo.setFileList(fileList);
      
      ModelAndView mav = new ModelAndView();
      mav.addObject("vo", vo);
      mav.setViewName("result");
      return mav;
   }
}