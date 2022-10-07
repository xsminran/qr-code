package com.xs.service;

import com.xs.entity.Test;
import com.xs.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TestService {
    @Autowired
    private TestMapper testMapper;

    @GetMapping("/test")
    public void selectAll(){
        final List<Test> tests = testMapper.selectList(null);
        for (Test test : tests) {
            System.out.println(test.getId());
            System.out.println(test.getName());
        }
    }

}
