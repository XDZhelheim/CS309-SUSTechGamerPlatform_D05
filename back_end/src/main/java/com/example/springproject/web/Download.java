package com.example.springproject.web;

import com.example.springproject.config.UsersGenerator;
import com.example.springproject.domain.SDKLog;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController("/Games")
public class Download {
    @RequestMapping("/game/{id}")
    public void Game(HttpServletResponse response, @PathVariable String id) throws IOException {
        response.setHeader("Content-Disposition", "attachment;filename=" + id + ".exe");
        response.setContentType("application/octet-stream;charset=UTF-8");
        File f = new File("E:\\ooad_pro\\game\\" + id + ".exe");
        int all = (int) f.length();
        FileInputStream in = new FileInputStream(f);
        byte[] bytes = new byte[all];
        in.read(bytes);
        response.getOutputStream().write(bytes);
    }
    @RequestMapping("/log")
    public void log(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Disposition", "attachment;filename=log.txt");
        response.setContentType("application/octet-stream;charset=UTF-8");
        List<SDKLog> list = UsersGenerator.Services.sdkLogService.listLog();
        StringBuilder s = new StringBuilder();
        for (SDKLog log : list) {
            s.append("user_id = ").append(log.getUsers().getId()).append(", game_name = ").append(log.getGame().getName()).append(", log = ").append(log.getLog()).append(System.lineSeparator());
        }
        response.getOutputStream().write(s.toString().getBytes());
    }
}
