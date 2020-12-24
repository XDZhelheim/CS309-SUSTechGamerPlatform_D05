package com.example.springproject.config;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.springproject.domain.Game;
import com.example.springproject.domain.GameUser;
import com.example.springproject.domain.Users;
import org.apache.commons.lang.StringUtils;
import org.hibernate.id.GUIDGenerator;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.sql.Date;
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
                String put_mess = jsonObject.getString("put_mess");
//下面是，收到 管理游戏 消息时处理
                String title = jsonObject.getString("title");
                String date = jsonObject.getString("date");
                String price = jsonObject.getString("price");
                String type = jsonObject.getString("type");
                String publisher = jsonObject.getString("publisher");
                String language = jsonObject.getString("language");
                String abstract_String = jsonObject.getString("abstract");
                String discount_get = jsonObject.getString("discount");
                String AddDe_game = jsonObject.getString("AddDe_game");
                String AddDe_user = jsonObject.getString("AddDe_user");
                String Buy = jsonObject.getString("buy");
                String get_game_develop = jsonObject.getString("get_game_develop");
                String get_user_develop = jsonObject.getString("get_user_develop");
                String get_game_allGame = jsonObject.getString("get_game_allGame");
                String buy_game_gamepage = jsonObject.getString("buy_game_gamepage");
                String user_name_gamepage = jsonObject.getString("name");
                String game_name_gamepage = jsonObject.getString("game_name");
                String commit_rate = jsonObject.getString("commit_rate");
                String rate_user = jsonObject.getString("score");
                String recharge = jsonObject.getString("recharge");
                String money = jsonObject.getString("money");
                String downloadSDK_user = jsonObject.getString("downloadSDK_user");
                String commitComment = jsonObject.getString("commitComment");
                String user_comment = jsonObject.getString("comment");
                String get_comment = jsonObject.getString("get_comment");
                String get_rate =  jsonObject.getString("get_rate");

                if (check_login != null) {
                    Users users = new Users();
                    users.setName(id);
                    users.setPassword(password);

                    if (Service.Services.usersService.checkLogin(users)) {
                        Users user = Service.Services.usersService.findByUsername(id);
                        double mon = user.getAccount();
                        char user_type = user.getRole();
                        String ID = Long.toString(user.getId());
                        String str_ans = "[";
                        str_ans += "{\"login\":\"True\",";
                        str_ans += "\"money\":\"";
                        str_ans += mon;
                        str_ans += "\",\"user_type\":\"";
                        str_ans += user_type;
                        str_ans += "\",\"ID\":\"";
                        str_ans += ID;
                        str_ans += "\",\"avatarURL\":\"";
                        str_ans += "http://localhost:8083/photo/"+ID+"-"+Math.random();
//                        avatarURL = "localhost:8083/photo"+ID
                        str_ans += "\"}]";
                        System.out.println(str_ans);
                        sendMessage(str_ans);
                    } else {
                        sendMessage("False");
                    }
                } else if (check_regis != null) {
                    Users users = new Users();
                    users.setName(id);
                    users.setPassword(password);
                    users.setEmail(email);
                    users.setRole('U');
                    users.setAccount(0);
                    Service.Services.usersService.save(users);
                    sendMessage("success register");
                } else if (recharge != null){
                    Users users = Service.Services.usersService.findByUsername(id);
                    users.setAccount(Double.parseDouble(money));
                    sendMessage("success recharge");
                } else if (AddDe_game != null ){
                    if (AddDe_game.equals("Add")){
                        Game game = new Game();
                        game.setGameType(type);
                        game.setIntro(abstract_String);
                        game.setDiscount(Double.parseDouble(discount_get));
                        game.setLanguage(language);
                        game.setName(title);
                        game.setPrice(Double.parseDouble(price));
                        game.setPublisher(publisher);
                        Service.Services.gamesService.save(game);
                        sendMessage("success add game");
                    }else if (AddDe_game.equals("Change")){
                        Game Before_game = Service.Services.gamesService.getGame(title);
                        Game game = new Game();
                        game.setId(Before_game.getId());
                        game.setGameType(type);
                        game.setIntro(abstract_String);
                        game.setLanguage(language);
                        game.setName(title);
                        game.setDiscount(Double.parseDouble(discount_get));
                        game.setPrice(Double.parseDouble(price));
                        game.setPublisher(publisher);
                        Service.Services.gamesService.save(game);
                    }
                    else {
                        Game game = Service.Services.gamesService.getGame(title);
                        Service.Services.gamesService.delete(game);
                        sendMessage("delete success");
                    }
                }
                else if (AddDe_user != null ) {
                    if (AddDe_user.equals("Delete")) {
                        Game game = Service.Services.gamesService.getGame(title);
                        Service.Services.gamesService.delete(game);
                        sendMessage("delete success");
                    } else {
                        Users users = null;
                        String name = jsonObject.getString("username");
                        Users Before_user = Service.Services.usersService.findByUsername(name);
                        users = new Users();
                        if (AddDe_user.equals("Add")) {
                            users.setName(name);
                            users.setCreateDate(new Date(System.currentTimeMillis()));
                        }else if (AddDe_user.equals("Change")){
                            users.setCreateDate(Before_user.getCreateDate());
                            users.setId(Before_user.getId());
                        }
                        String pwd = jsonObject.getString("password");
                        users.setPassword(pwd);
                        String u_type = jsonObject.getString("usertype");
                        switch (u_type) {
                            case "用户":
                                users.setRole('U');
                                break;
                            case "开发者":
                                users.setRole('D');
                                break;
                            case "管理员":
                                users.setRole('A');
                                break;
                        }
                        String mail = jsonObject.getString("mail");
                        users.setEmail(mail);
                        String acco = jsonObject.getString("money");
                        users.setAccount(Double.parseDouble(acco));
                        Service.Services.usersService.save(users);
                        sendMessage("success add user");
                    }
                }else if (get_user_develop != null){
                    List<Users> all_user = Service.Services.usersService.findAllUsers();
                    String str_ans = "[";
                    for(int i=0;i<all_user.size();i++){
                        str_ans += "{";
                        str_ans += "\"username\":\"";
                        str_ans += all_user.get(i).getName();
                        str_ans += "\",\"password\":\"";
                        str_ans += all_user.get(i).getPassword();
                        str_ans += "\",\"usertype\":";
                        char u_type = all_user.get(i).getRole();
                        switch (u_type) {
                            case 'U':
                                str_ans += "\"用户\"";
                                break;
                            case 'D':
                                str_ans += "\"开发者\"";
                                break;
                            case 'A':
                                str_ans += "\"管理员\"";
                                break;
                        }

                        str_ans += ",\"createDate\":\"";
                        str_ans += all_user.get(i).getCreateDate();
                        str_ans += "\",\"mail\":\"";
                        str_ans += all_user.get(i).getEmail();

                        str_ans += "\",\"money\":\"";
                        str_ans += all_user.get(i).getAccount();

                        str_ans += "\",\"AddDe_game\":\"Delete\"}";
                        if (i<all_user.size()-1){
                            str_ans += ",";
                        }
                    }
                    str_ans += "]";
                    System.out.println(str_ans);
                    sendMessage(str_ans);
                }else if (get_game_allGame != null){
                    List<Game> all_game = Service.Services.gamesService.getAllGame();
                    String str_ans = "[";
                    String tmp_username = jsonObject.getString("name");
                    Users tmp_user = Service.Services.usersService.findByUsername(tmp_username);
                    for(int i=0;i<all_game.size();i++){
                        GameUser gu = Service.Services.gameUserService.getRecord(tmp_user,all_game.get(i));
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

                        str_ans += "\",\"rate\":\"";
                        str_ans += "4.8";
//                        str_ans += Service.Services.gameUserService.averageScore(all_game.get(i));
                        str_ans += "\",\"abstract\":\"";
                        str_ans += all_game.get(i).getIntro();
//                        "[{\"title\":\"123\",\"date\":\"2020-12-23\",\"price\":130.0,\"type\":\"FPS\",\"publisher\":\"313e\",\"language\":\"中文 (繁体)\",\"rate\":\"4.8\",\"abstract\":\"wedf\",\"userhave\":\"falseurl:null},{\"title\":\"The Witcher: Wild Hunt\",\"date\":\"2020-12-24\",\"price\":47.0,\"type\":\"RPG\",\"publisher\":\"Rubbish publisher\",\"language\":\"English\",\"rate\":\"4.8\",\"abstract\":\"该作承接《巫师2：国王刺客》的剧情，那些想要利用杰洛特的人已经不在了。杰洛特寻求改变自己的生活，着手于新的个人使命，而世界的秩序也在悄然改变。2015年10月，获第33届金摇杆奖最佳剧情、最佳视觉设计、最佳游戏时刻，更获得了年度最佳游戏大奖。并获得IGN 2015年度最佳游戏。2016年其DLC“血与酒”获得了The Game Awards2016年年度“最佳游戏角色扮演游戏”奖。\",\"userhave\":\"false\",\"url: \"/witcher3\"}]"
//                        "[{\"title\":\"123\",\"date\":\"2020-12-23\",\"price\":130.0,\"type\":\"FPS\",\"publisher\":\"313e\",\"language\":\"中文 (繁体)\",\"rate\":\"4.8\",\"abstract\":\"wedf\",\"userhave\":\"falseurl:null},{\"title\":\"The Witcher: Wild Hunt\",\"date\":\"2020-12-24\",\"price\":47.0,\"type\":\"RPG\",\"publisher\":\"Rubbish publisher\",\"language\":\"English\",\"rate\":\"4.8\",\"abstract\":\"该作承接《巫师2：国王刺客》的剧情，那些想要利用杰洛特的人已经不在了。杰洛特寻求改变自己的生活，着手于新的个人使命，而世界的秩序也在悄然改变。2015年10月，获第33届金摇杆奖最佳剧情、最佳视觉设计、最佳游戏时刻，更获得了年度最佳游戏大奖。并获得IGN 2015年度最佳游戏。2016年其DLC“血与酒”获得了The Game Awards2016年年度“最佳游戏角色扮演游戏”奖。\",\"userhave\":\"falseurl: \"/witcher3\"}]"
//                        List<GameUser> all = Service.Services.gameUserService.getOwnerUsers(tmp_user);
//                        Boolean has = false;
//                        for (GameUser tmp: all){
//                            Game g = tmp.getGame();
//                            if (g.getId() == all_game.get(i).getId()){
//                                has = true;
//                            }
//                        }
                        str_ans += "\",\"userhave\":\"";
                        str_ans += gu != null;
                        if (all_game.get(i).getName().equals("The Witcher: Wild Hunt")){
                            str_ans += "\",\"url\": \"/witcher3\"";
                        }else {
                            str_ans += "\",\"url\":null";
                        }
                        str_ans += "}";
                        if (i<all_game.size()-1){
                            str_ans += ",";
                        }
                    }
                    str_ans += "]";
                    System.out.println(str_ans);
                    sendMessage(str_ans);

                }
                else if (get_game_develop != null){
                    List<Game> all_game = Service.Services.gamesService.getAllGame();
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

                        str_ans += "\",\"discount\":\"";
                        str_ans += all_game.get(i).getDiscount();

                        str_ans += "\",\"language\":\"";
                        str_ans += all_game.get(i).getLanguage();
                        str_ans += "\",\"abstract\":\"";
                        str_ans += all_game.get(i).getIntro();
                        str_ans += "\",\"AddDe_game\":\"Delete\"}";
                        if (i<all_game.size()-1){
                            str_ans += ",";
                        }
                    }

                    str_ans += "]";
                    System.out.println(str_ans);
                    sendMessage(str_ans);
                } else if (buy_game_gamepage!=null){
                    Users user = Service.Services.usersService.findByUsername(buy_game_gamepage);
                    Game game = Service.Services.gamesService.getGame(game_name_gamepage);
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
                        Service.Services.gameUserService.save(g_u);
                        Service.Services.usersService.save(user);
                        sendMessage("购买成功");
                    }
                }else if (commit_rate != null){
                    Users user = Service.Services.usersService.findByUsername(user_name_gamepage);
                    Game game = Service.Services.gamesService.getGame(game_name_gamepage);
                    GameUser g_u = Service.Services.gameUserService.getRecord(user,game);
                    g_u.setScore(Double.parseDouble(rate_user));
                    Service.Services.gameUserService.save(g_u);
                } else if (commitComment != null){
                    Users user = Service.Services.usersService.findByUsername(user_name_gamepage);
                    Game game = Service.Services.gamesService.getGame(game_name_gamepage);
                    GameUser g_u = Service.Services.gameUserService.getRecord(user,game);
                    g_u.setDetails(user_comment);
                    Service.Services.gameUserService.save(g_u);
                } else if (get_comment != null){
                    Game game = Service.Services.gamesService.getGame(game_name_gamepage);
                    List<GameUser> list = Service.Services.gameUserService.getComment(game);


                    String str_ans = "[";
                    for(int i=0;i<list.size();i++){
                        str_ans += "{";
                        str_ans += "\"username\":\"";
                        str_ans += list.get(i).getUsers().getName();
                        str_ans += "\",\"comment\":\"";
                        str_ans += list.get(i).getDetails();
                        str_ans += "\",\"upVoteNum\":";
                        str_ans += list.get(i).goods();
                        str_ans += ",\"downVoteNum\":\"";
                        str_ans += list.get(i).bads();

                        str_ans += "}";
                        if (i<list.size()-1){
                            str_ans += ",";
                        }
                    }
                   str_ans += "]";
                    System.out.println(str_ans);
                    sendMessage(str_ans);


                } else if (get_rate != null){
                    Game game = Service.Services.gamesService.getGame(game_name_gamepage);
                    sendMessage(Double.toString(Service.Services.gameUserService.averageScore(game)));
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
