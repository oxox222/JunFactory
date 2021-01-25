package com.example.sale.serviceImpl;

import com.example.sale.dao.sale.TUserMapper;
import com.example.sale.model.TUser;
import com.example.sale.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: LoginServiceImpl
 * @Description: 登录
 * @Author: PANLVZ
 * @Date: 2020-03-13
 */
@Service
public class LoginServiceImpl implements LoginService {

    private static Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Resource
    private TUserMapper tUserMapper;

    @Override
    public TUser getLogin(TUser tUser) {
        TUser localUser = new TUser();
        localUser = tUserMapper.selectPassword(tUser.getUser());
        if (localUser == null) {
            return null;
        }
        if (localUser.getPassword().equals(tUser.getPassword())) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            tUserMapper.updateLastLoginTime(sdf.format(new Date()), tUser.getUser());
        }
        return localUser;
    }
}
