package com.example.demo.controller;

import com.example.demo.repository.IPqrsRepository;
import com.example.demo.repository.IRespuestaPqrsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RespuestaPqrsController {

    @Autowired
    private IPqrsRepository iPqrsRepository;

    @Autowired
    private IRespuestaPqrsRepository iRespuestaPqrsRepository;


}
