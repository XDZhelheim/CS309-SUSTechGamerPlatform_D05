package com.example.springproject.config;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.springproject.domain.Game;
import com.example.springproject.domain.GameUser;
import com.example.springproject.domain.Users;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
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
                String email = jsonObject.getString("email");
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
                String get_game_develop = jsonObject.getString("get_game_develop");
                String buy_game_gamepage = jsonObject.getString("buy_game_gamepage");
                String user_name_gamepage = jsonObject.getString("name");
                String game_name_gamepage = jsonObject.getString("game_name");
                String commit_rate = jsonObject.getString("commit_rate");
                String rate_user = jsonObject.getString("score");
                String recharge = jsonObject.getString("recharge");
                String money = jsonObject.getString("money");
                String a = jsonObject.getString("");


                if (check_login != null) {
                    Users users = new Users();
                    users.setName(id);
                    users.setPassword(password);
                    if (UsersGenerator.Services.usersService.checkLogin(users)) {
                        Users user = UsersGenerator.Services.usersService.findByUsername(id);
                        double mon = user.getAccount();
                        char user_type = user.getRole();
                        String str_ans = "[";
                        str_ans += "{\"login\":\"True\",";
                        str_ans += "\"money\":\"";
                        str_ans += mon;
                        str_ans += "\",\"user_type\":\"";
                        str_ans += user_type;
                        str_ans += "\"}]";
                        sendMessage(str_ans);
                    } else {
                        sendMessage("False");
                    }
                } else if (check_regis != null) {
                    Users users = new Users();
                    users.setName(id);
                    users.setPassword(password);
                    users.setEmail(email);
                    UsersGenerator.Services.usersService.save(users);
                    sendMessage("success register");
                } else if (recharge != null){
                    Users users = UsersGenerator.Services.usersService.findByUsername(id);
                    users.setAccount(Double.parseDouble(money));
                    sendMessage("success recharge");
                } else if (AddDe != null ){
                    if (AddDe.equals("Add")){
                        Game game = new Game();
                        game.setGameType(type);
                        game.setIntro(abstract_String);
                        game.setLanguage(language);
                        game.setName(title);
                        game.setPrice(Double.parseDouble(price));
                        game.setPublisher(publisher);
                        UsersGenerator.Services.gamesService.save(game);
                        sendMessage("success add game");
                    }else if (AddDe.equals("Change")){
                        Game Before_game = UsersGenerator.Services.gamesService.getGame(title);
                        Game game = new Game();
                        game.setGameType(type);
                        game.setIntro(abstract_String);
                        game.setLanguage(language);
                        game.setName(title);
                        game.setPrice(Double.parseDouble(price));
                        game.setPublisher(publisher);
                        UsersGenerator.Services.gamesService.game_del(Before_game);
                        UsersGenerator.Services.game_save(game);
                    }
                    else {
                        Game game = UsersGenerator.Services.get_game(title);
                        UsersGenerator.Services.game_del(game);
                        sendMessage("delete success");
                    }

                } else if (get_game_develop != null){
                    List<Game> all_game = UsersGenerator.Services.game_get();
                    String str_ans = "[";

                    for(int i=0;i<all_game.size();i++){
                        str_ans += "{";
                        str_ans += "\"title\":\"";
                        str_ans += all_game.get(i).getName();
                        str_ans += "\",\"date\":\"";
                        str_ans += all_game.get(i).getCreateTime();
                        str_ans += "\",\"price\":";
                        str_ans += all_game.get(i).getPrice();
                        str_ans += ",\"type\":\"";
                        str_ans += all_game.get(i).getGameType();
                        str_ans += "\",\"publisher\":\"";
                        str_ans += all_game.get(i).getPublisher();
                        str_ans += "\",\"language\":\"";
                        str_ans += all_game.get(i).getLanguage();
                        str_ans += "\",\"abstract\":\"";
                        str_ans += all_game.get(i).getIntro();
                        str_ans += "\",\"AddDe\":\"Delete\"}";
                        if (i<all_game.size()-1){
                            str_ans += ",";
                        }
                    }
//                    String str = "[{\"title\":\"243\",\"date\":\"2020-12-18\",\"price\":240.0,\"type\":\"FPS\",\"publisher\":\"24\",\"language\":\"English\",\"abstract\":\"24\",\"AddDe\":\"Delete\"},{\"title\":\"234\",\"date\":\"2020-12-18\",\"price\":230.0,\"type\":\"MOBA\",\"publisher\":\"fdv\",\"language\":\"English\",\"abstract\":\"234\",\"AddDe\":\"Delete\"},{\"title\":\"原神\",\"date\":\"2020-12-18\",\"price\":666.66,\"type\":\"RPG\",\"publisher\":\"MiHoYo\",\"language\":\"中文 (简体)\",\"abstract\":\"sb游戏\",\"AddDe\":\"Delete\"},{\"title\":\"原神11\",\"date\":\"2020-12-18\",\"price\":666.66,\"type\":\"RPG\",\"publisher\":\"MiHoYo\",\"language\":\"中文 (简体)\",\"abstract\":\"sb游戏\",\"AddDe\":\"Delete\"},{\"title\":\"23\",\"date\":\"2020-12-18\",\"price\":230.0,\"type\":\"FPS\",\"publisher\":\"23\",\"language\":\"中文 (简体)\",\"abstract\":\"23\",\"AddDe\":\"Delete\"}]"
                    
                    // str = {"title":"原神","date":"2020-09-15","price":666.66,"type":"RPG","publisher":"MiHoYo","language":"中文 (简体)","abstract":"sb游戏","AddDe":"Delete"}
                    str_ans += "]";
                    sendMessage(str_ans);
                } else if (buy_game_gamepage!=null){
                    Users user = UsersGenerator.Services.get_user(buy_game_gamepage);
                    Game game = UsersGenerator.Services.get_game(game_name_gamepage);
                    double before_acc = user.getAccount();
                    double need_money = game.getPrice();
                    if (need_money>before_acc){
                        sendMessage("余额不足，购买失败");
                    }else {
                        user.setAccount(before_acc-need_money);
                        GameUser g_u = new GameUser();
                        g_u.setGame(game);
                        g_u.setUsers(user);
                        g_u.setCreditAs('u'); //表示，这个是普通用户
                        UsersGenerator.Services.save_game(g_u);
                        UsersGenerator.Services.save(user);
                        sendMessage("购买成功");
                    }
                }else if (commit_rate != null){
                    Users user = UsersGenerator.Services.get_user(buy_game_gamepage);
                    Game game = UsersGenerator.Services.get_game(game_name_gamepage);
                    GameUser g_u = UsersGenerator.Services.get_gu(user,game);
                    g_u.setScore(Double.parseDouble(rate_user));
                    UsersGenerator.Services.save_game(g_u);
                } else if (get_comment != null){

                }
//                else if (){
//
//                }



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
