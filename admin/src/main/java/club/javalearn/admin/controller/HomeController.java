package club.javalearn.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {


    @RequestMapping(value = {"/","/home","/index"})
    public ModelAndView page(){
        return new ModelAndView("home");
    }


    @GetMapping("/hello1")
    public String hello(){
        return "hello1";
    }

    @GetMapping("/hello2")
    public String hello2(){
        return "hello2";
    }

    @GetMapping("/hello3")
    public String hello3(){
        return "hello3";
    }
}
