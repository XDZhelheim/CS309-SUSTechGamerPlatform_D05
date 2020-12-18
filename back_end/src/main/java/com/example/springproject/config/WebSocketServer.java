package com.example.springproject.config;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.springproject.domain.Game;
import com.example.springproject.domain.Users;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;




/**
 1.   game.setId(1);//这个怎么设？？？？？？？？？？？
这个怎么设置为唯一值
 2.

 */
@ServerEndpoint("/{userId}")
@Component

public class WebSocketServer {

    static Log log = LogFactory.get(WebSocketServer.class);
    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static int onlineCount = 0;
    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static final ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 接收userId
     */
    private String userId = "";

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
//    public void onOpen(Session session,String message) {
        this.session = session;
        this.userId = userId;
        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            webSocketMap.put(userId, this);
            //加入set中
        } else {
            webSocketMap.put(userId, this);
            //加入set中
            addOnlineCount();
            //在线数加1
        }

        log.info("用户连接:" + userId + ",当前在线人数为:" + getOnlineCount());

        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            log.error("用户:" + userId + ",网络异常!!!!!!");
        }
    }
//    public void onOpen(String message, Session session) {
//        this.session = session;
//
////        if(StringUtils.isNotBlank(message)){
////            try {
////                //解析发送的报文
////                JSONObject jsonObject = JSON.parseObject(message);
////                String id = jsonObject.getString("name");
////                String password = jsonObject.getString("password");
////                String confirmPassword = jsonObject.getString("confirmPassword");
////
////                //追加发送人(防止串改)
////                this.userId=id;
////                if(webSocketMap.containsKey(userId)){
////                    webSocketMap.remove(userId);
////                    webSocketMap.put(userId,this);
////                    //加入set中
////                }else{
////                    webSocketMap.put(userId,this);
////                    //加入set中
////                    addOnlineCount();
////                    //在线数加1
////                }
////                jsonObject.put("fromUserId",this.userId);
////
//////                UserInfo userInfo = new UserInfo(id,password);
//////                if (userService.checkLogin(userInfo)) {
//////                    sendMessage("True");
//////                }else {
//////                    sendMessage("False");
//////                }
////                if (confirmPassword==null){
//////                    sendMessage("this is not confirm");
////                    if (id.equals("1") && password.equals("1")){
////                        sendMessage("T");
////                    }else {
////                        sendMessage("F");
////                    }
//////                    Users user = new Users();
//////                    user.setName("11811305");
//////                    user.setPassword("123456");
//////                    if (usersService.checkLogin(user)){
//////                        sendMessage("True");
//////                    }else {
//////                        sendMessage("False");
//////                    }
////                }else {
////                    sendMessage("this is confirm");
////                }
//////                }
////            }catch (Exception e){
////                e.printStackTrace();
////            }
////        }
//
//        this.userId=message;
//        if(webSocketMap.containsKey(userId)){
//            webSocketMap.remove(userId);
//            webSocketMap.put(userId,this);
//            //加入set中
//        }else{
//            webSocketMap.put(userId,this);
//            //加入set中
//            addOnlineCount();
//            //在线数加1
//        }
//
//        log.info("用户连接:"+userId+",当前在线人数为:" + getOnlineCount());
//
//        try {
//            sendMessage("连接成功");
//        } catch (IOException e) {
//            log.error("用户:"+userId+",网络异常!!!!!!");
//        }
//    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            //从set中删除
            subOnlineCount();
        }
        log.info("用户退出:" + userId + ",当前在线人数为:" + getOnlineCount());
    }

    /**
     * @param message 客户端发送过来的消息
     *                收到客户端消息后调用的方法
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("用户消息:" + userId + ",报文:" + message);
        //可以群发消息
        //消息保存到数据库、redis
        if (StringUtils.isNotBlank(message)) {
            try {
                //解析发送的报文
                JSONObject jsonObject = JSON.parseObject(message);
                //追加发送人(防止串改)
                jsonObject.put("fromUserId", this.userId);
                String id = jsonObject.getString("name");
                String password = jsonObject.getString("password");
                String confirmPassword = jsonObject.getString("confirmPassword");
                String check_login = jsonObject.getString("login");
                String check_regis = jsonObject.getString("register");
                String get_comment = jsonObject.getString("get_comment");
                String put_comment = jsonObject.getString("put_comment");
                String put_mess = jsonObject.getString("put_mess");
//下面是，收到 管理游戏 消息时处理
                String title = jsonObject.getString("title");
                String date = jsonObject.getString("date");
                String price = jsonObject.getString("price");
                String type = jsonObject.getString("type");
                String publisher = jsonObject.getString("publisher");
                String language = jsonObject.getString("language");
                String abstract_String = jsonObject.getString("abstract");
                String AddDe = jsonObject.getString("AddDe");
                String Buy = jsonObject.getString("buy");



                if (check_login != null) {
                    Users users = new Users();
                    users.setName(id);
                    users.setPassword(password);
                    if (UsersGenerator.userService.checkLogin(users)) {
                        sendMessage("True");
                    } else {
                        sendMessage("False");
                    }
                } else if (check_regis != null) {
                    Users users = new Users();
                    users.setName(id);
                    users.setPassword(password);
                    UsersGenerator.userService.save(users);
                    sendMessage("this is confirm");
                } else if (get_comment != null) {
                    sendMessage("check");
                } else if (put_comment != null) {
                    sendMessage(put_mess);
                } else if (AddDe != null ){
                    Game game = new Game();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
                    Date d = sdf.parse(date);
                    java.sql.Date sql_date = new java.sql.Date(d.getTime());
                    game.setCreateTime(sql_date);

                    game.setId(1);//这个怎么设？？？？？？？？？？？
                    game.setGameType(type);
                    game.setIntro(abstract_String);
//                    game.setLanguage(language);
                    game.setLanguage('c');

                    game.setName(title);
                    game.setPrice(Double.parseDouble(price));
                    game.setPublisher(publisher);
                    if (AddDe.equals("Add")){
                        UsersGenerator.gameService.game_save(game);
                        sendMessage("guanli");
                    }else {
                        UsersGenerator.gameService.game_del(game);
                        sendMessage("del");
                    }

                } else if (Buy!=null){

                    sendMessage("success buy");

                }
//                String toUserId=jsonObject.getString("password");
//                //传送给对应toUserId用户的websocket
//                if(StringUtils.isNotBlank(toUserId)&&webSocketMap.containsKey(toUserId)){
//                    webSocketMap.get(toUserId).sendMessage(jsonObject.toJSONString());
//                }else{
//                    log.error("请求的userId:"+toUserId+"不在该服务器上");
//                    //否则不在这个服务器上，发送到mysql或者redis
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


//    public void check(String message, Session session){
//        log.info("用户："+userId+",消息"+message);
//        log.info("开启了check操作");
//        if(StringUtils.isNotBlank(message)){
//            try {
//                //解析发送的报文
//                JSONObject jsonObject = JSON.parseObject(message);
//                //追加发送人(防止串改)
//                jsonObject.put("fromUserId",this.userId);
//                String id = jsonObject.getString("name");
//                String password=jsonObject.getString("password");
//                //传送给对应toUserId用户的websocket
//                sendMessage("True");
//
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }

    /**
     * @param session a
     * @param error   a
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:" + this.userId + ",原因:" + error.getMessage());
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 发送自定义消息
     */
    public static void sendInfo(String message, @PathParam("userId") String userId) throws IOException {
        log.info("发送消息到:" + userId + "，报文:" + message);
        if (StringUtils.isNotBlank(userId) && webSocketMap.containsKey(userId)) {
            webSocketMap.get(userId).sendMessage(message);
        } else {
            log.error("用户" + userId + ",不在线！");
        }
    }


    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
