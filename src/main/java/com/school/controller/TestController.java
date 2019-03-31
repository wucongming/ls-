package com.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

class Param {
    String param;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "Param{" +
                "param='" + param + '\'' +
                '}';
    }
}

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public String get() {
        System.out.println("get");
        return "{ status: 0 }";
    }

    /**
     * Content-Type: application/json;charset=ISO-8859-1
     *
     * this.axios.get(this.linkPrefix + "/test/get1?param=123").then(res =>  {
     *         console.log(res);
     *       })
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/get1",method = RequestMethod.GET)
    public String get1(@RequestParam("param") String param) {
        System.out.println("get1" + param);
        return "{ status: 0 }";
    }

    @RequestMapping(value = "/get2", method = RequestMethod.GET)
    public String get2(@RequestAttribute("param") String param) {
        System.out.println("get2" + param);
        return "{ status: 0 }";
    }

    @RequestMapping(value = "/get3", method = RequestMethod.GET)
    public String get3(@RequestBody Param param) {
        System.out.println("get3" + param);
        return "{ status: 0 }";
    }


    /**
     * 跟contentType无关
     * this.axios.post(this.linkPrefix + "/test/post1?param=13")
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/post1",method = RequestMethod.POST)
    public String post1(@RequestParam("param") String param) {
        System.out.println("post1" + param);
        return "{ status: 0 }";
    }

    @RequestMapping(value = "/post2", method = RequestMethod.POST)
    public String post2(@RequestAttribute("param") String param) {
        System.out.println("post2" + param);
        return "{ status: 0 }";
    }

    /**
     *
     * Content-Type: application/json;charset=UTF-8
     *
     * this.axios.post(this.linkPrefix + "/test/post3", { param : "123" }).then(res =>  {
     *         console.log(res);
     *       })
     *
     *
     * 或者
     *
     * Content-Type: application/json;charset=UTF-8
     * 如何将参数设置为字符串则会报错
     * this.axios.post(this.linkPrefix + "/test/post3", { param : "123" },{headers: {'Content-Type': 'application/json;charset=ISO-8859-1'}}).then(res =>  {
     *         console.log(res);
     *       })
     * @param param
     * @return
     */
    @RequestMapping(value = "/post3", method = RequestMethod.POST)
    public String post3(@RequestBody Param param) {
        System.out.println("post3" + param);
        return "{ status: 0 }";
    }


    @RequestMapping(value = "/put1",method = RequestMethod.PUT)
    public String put1(@RequestParam("param") String param) {
        System.out.println("put1" + param);
        return "{ status: 0 }";
    }

    @RequestMapping(value = "/put2", method = RequestMethod.PUT)
    public String put2(@RequestAttribute("param") String param) {
        System.out.println("put2" + param);
        return "{ status: 0 }";
    }

    @RequestMapping(value = "/put3", method = RequestMethod.PUT)
    public String put3(@RequestBody Param param) {
        System.out.println("put3" + param);
        return "{ status: 0 }";
    }

    @RequestMapping(value = "/DELETE1",method = RequestMethod.DELETE)
    public String DELETE1(@RequestParam("param") String param) {
        System.out.println("DELETE1" + param);
        return "{ status: 0 }";
    }

    @RequestMapping(value = "/DELETE2", method = RequestMethod.DELETE)
    public String DELETE2(@RequestAttribute("param") String param) {
        System.out.println("DELETE2" + param);
        return "{ status: 0 }";
    }

    @RequestMapping(value = "/DELETE3", method = RequestMethod.DELETE)
    public String DELETE3(@RequestBody Param param) {
        System.out.println("DELETE3" + param);
        return "{ status: 0 }";
    }

}
