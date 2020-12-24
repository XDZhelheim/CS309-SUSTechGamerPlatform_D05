package com.example.springproject.web;

import com.example.springproject.config.Service;
import com.example.springproject.domain.SDKLog;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

@RestController("/Games")
public class Download {


    @RequestMapping("/upload/{id}")
    public void upload(MultipartFile file, @PathVariable String id) throws IOException {
        String path = "E:\\ooad_pro\\game\\" + id + ".exe";
        file.transferTo(new File(path));
    }
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
        List<SDKLog> list = Service.Services.sdkLogService.listLog();
        StringBuilder s = new StringBuilder();
        for (SDKLog log : list) {
            s.append("user_id = ").append(log.getUsers().getId()).append(", game_name = ").append(log.getGame().getName()).append(", log = ").append(log.getLog()).append(System.lineSeparator());
        }
        response.getOutputStream().write(s.toString().getBytes());
    }

//    @RequestMapping("/photo")
//    public void photo(HttpServletResponse response) throws IOException {
//        response.setHeader("Content-Disposition", "attachment;filename=photo.jpg");
//        response.setContentType("image/jpeg;charset=UTF-8");
//        List<SDKLog> list = Service.Services.sdkLogService.listLog();
//        StringBuilder s = new StringBuilder();
//        for (SDKLog log : list) {
//            s.append("user_id = ").append(log.getUsers().getId()).append(", game_name = ").append(log.getGame().getName()).append(", log = ").append(log.getLog()).append(System.lineSeparator());
//        }
//        response.getOutputStream().write(s.toString().getBytes());
//    }

    public static PrintStream out = new PrintStream(new BufferedOutputStream(
                new FileOutputStream(FileDescriptor.out), 128), true);

    @RequestMapping("/photo/{id}")
    public void photo(HttpServletResponse response, @PathVariable String id) throws IOException {
        out.println(id);

//        response.setContentType("image/jpeg;charset=UTF-8");
        File f = new File("E:\\ooad_pro\\avatars\\" + id.substring(0, id.indexOf('-')) );
        if (!f.exists()) {
            return;
        }
        int all = (int) f.length();
        FileInputStream in = new FileInputStream(f);
        byte[] bytes = new byte[all];
        in.read(bytes);
        response.getOutputStream().write(bytes);
    }

}
