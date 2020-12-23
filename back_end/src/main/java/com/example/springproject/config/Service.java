package com.example.springproject.config;

import com.example.springproject.domain.Game;
import com.example.springproject.domain.SDKLog;
import com.example.springproject.domain.Users;
import com.example.springproject.service.GameService;
import com.example.springproject.service.GameUserService;
import com.example.springproject.service.SDKLogService;
import com.example.springproject.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


@RestController("/OOAD")
public class Service {
    public static Service Services;

    public Service() {
        Services = this;
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(8888);
                while (true) {
                    Socket s = serverSocket.accept();
                    Scanner in = new Scanner(s.getInputStream());
                    PrintStream out = new PrintStream(s.getOutputStream());
                    Users u = usersService.findByUsername(in.nextLine());
                    Game g = gamesService.getGame(in.nextLine());
                    if (u != null && g != null) {
                        SDKLog log = new SDKLog();
                        log.setUsers(u);
                        log.setGame(g);
                        log.setLog(in.nextLine());
                        sdkLogService.saveLog(log);
                        out.println("true");
                    } else {
                        out.println("false");
                    }
                    out.close();
                    in.close();
                    s.close();
                }
            } catch (IOException ignored) {
            }
        }).start();
    }


    @Autowired
    public UsersService usersService;
    @Autowired
    public GameService gamesService;
    @Autowired
    public GameUserService gameUserService;
    @Autowired
    public SDKLogService sdkLogService;

    @RequestMapping("/users")
    public void hello(){

    }
}
