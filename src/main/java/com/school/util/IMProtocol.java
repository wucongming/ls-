package com.school.util;


import com.alibaba.fastjson.JSONObject;
import com.school.vo.UserVO;
import com.school.websocket.vo.IMMessage;

import static com.school.util.IMType.*;

public class IMProtocol {



    public static String produceAddFriend(int uid, int fid) {
        UserVO from = new UserVO();
        from.setUid(uid);

        UserVO to = new UserVO();
        to.setUid(fid);
//        IMMessage message = new IMMessage(ADD_FRIEND.ordinal(),from, to, "加为好友");
        IMMessage message = new IMMessage(0,uid,"",fid,"",
                ADD_FRIEND.ordinal(),"加为好友");
        return JSONObject.toJSON(message).toString();
    }

}
