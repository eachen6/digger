package com.digger.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digger.common.Const;
import com.digger.common.ServerResponse;
import com.digger.dao.UserMapper;
import com.digger.pojo.Game;
import com.digger.pojo.User;
import com.digger.service.UserService;
import com.digger.utils.DbUtil;
import com.digger.utils.MD5Util;
import com.digger.common.TokenCache;

/**
 * @author Administrator
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	/**
	 * 登陆功能
	 * 
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse<User> login(String username, String password) {
		// TODO Auto-generated method stub
		int resultCount = userMapper.checkUsername(username);
		if (resultCount == 0) {
			return ServerResponse.createByErrorMessage("用户名不存在");
		}
		String md5Password = MD5Util.MD5EncodeUtf8(password);
		System.out.println(md5Password);
		User user = userMapper.selectLogin(username, md5Password);
		if (user == null) {
			return ServerResponse.createByErrorMessage("密码错误");
		}
		user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
		return ServerResponse.createBySuccess("登录成功", user);
	}

	/**
	 * 判断封禁功能
	 * @author 徐子颖
	 */
	@Override
	public int checkState(String username){
		if(userMapper.checkState(username)==1){
			return 1;
		}
		else
			return 0;
	}
	
	/**
	 * 注册功能
	 * 
	 * @author 高志劲
	 */
	public ServerResponse<String> register(User user) {
		ServerResponse validResponse = checkValid(user.getUsername(), Const.USERNAME);
		if (!validResponse.isSuccess()) {
			return validResponse;
		}
		validResponse = checkValid(user.getEmail(), Const.EMAIL);
		if (!validResponse.isSuccess()) {
			return validResponse;
		}
		user.setRole(Const.Role.ROLE_CUSTOMER);
		String md5Password = MD5Util.MD5EncodeUtf8(user.getPassword());
		System.out.println(md5Password);
		user.setPassword(md5Password);
		int resultCount = 0;
		resultCount = userMapper.insert(user);
		// System.out.println(resultCount+"uuuuuuuuuuuuuuuuuuuuuuuuu");
		if (resultCount > 0) {
			return ServerResponse.createBySuccessMessage("注册成功");
		}
		return ServerResponse.createByErrorMessage("注册失败");
	}

	/**
	 * 校验功能
	 * 
	 * @author 高志劲
	 */
	public ServerResponse<String> checkValid(String str, String type) {
		if (org.apache.commons.lang3.StringUtils.isNotBlank(type)) {
			// 开始校验
			if (Const.USERNAME.equals(type)) {
				int resultCount = 0;
				resultCount = userMapper.checkUsername(str);
				if (resultCount > 0) {
					return ServerResponse.createByErrorMessage("用户名已存在");
				}
			}
			if (Const.EMAIL.equals(type)) {
				int resultCount = 0;
				resultCount = userMapper.checkEmail(str);
				if (resultCount > 0) {
					return ServerResponse.createByErrorMessage("email已存在");
				}
			}
		} else {
			return ServerResponse.createByErrorMessage("参数错误");
		}
		return ServerResponse.createBySuccessMessage("校验成功");
	}

	/**
	 * 查找密保问题
	 * 
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse selectQuestion(String username, String type) {
		// TODO Auto-generated method stub

		ServerResponse validResponse = this.checkValid(username, type);
		if (validResponse.isSuccess()) {
			// 用户不存在
			return ServerResponse.createByErrorMessage("用户不存在");
		}
		// type为username的情况
		if (Const.USERNAME.equals(type)) {
			String question = userMapper.selectQuestionByUsername(username);
			if (org.apache.commons.lang3.StringUtils.isNotBlank(question)) {
				return ServerResponse.createBySuccess(question);
			}
		}
		// type为email的情况
		else if (Const.EMAIL.equals(type)) {
			String question = userMapper.selectQuestionByEmail(username);
			if (org.apache.commons.lang3.StringUtils.isNotBlank(question)) {
				return ServerResponse.createBySuccess(question);
			}
		}
		return ServerResponse.createByErrorMessage("找回密码的问题是空的");
	}

	/**
	 * 校验密保答案
	 * 
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse<String> checkAnswer(String username, String question, String answer, String type) {
		// TODO Auto-generated method stub
		if (Const.USERNAME.equals(type)) {
			int resultCount = userMapper.checkAnswerByUsername(username, question, answer);
			if (resultCount > 0) {
				// 说明问题及问题答案是这个用户的,并且是正确的
				String forgetToken = UUID.randomUUID().toString();
				TokenCache.setKey(TokenCache.TOKEN_PREFIX + username, forgetToken);
				return ServerResponse.createBySuccess(forgetToken);
			}
			else {
				return ServerResponse.createByErrorMessage("密保答案错误");
			}
		} 
		else if (Const.EMAIL.equals(type)) {
			int resultCount = userMapper.checkAnswerByEmail(username, question, answer);
			if (resultCount > 0) {
				// 说明问题及问题答案是这个用户的,并且是正确的
				String forgetToken = UUID.randomUUID().toString();
				TokenCache.setKey(TokenCache.TOKEN_PREFIX + username, forgetToken);
				return ServerResponse.createBySuccess(forgetToken);
			}
			else {
				return ServerResponse.createByErrorMessage("密保答案错误");
			}
		}
		return ServerResponse.createByErrorMessage("问题的答案错误");
	}

	/**
	 * 根据密保修改密码
	 * 
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken, String type) {
		// TODO Auto-generated method stub
		if (org.apache.commons.lang3.StringUtils.isBlank(forgetToken)) {
			return ServerResponse.createByErrorMessage("参数错误,token需要传递");
		}
		ServerResponse validResponse = this.checkValid(username, Const.USERNAME);
		if (validResponse.isSuccess()) {
			// 用户不存在
			return ServerResponse.createByErrorMessage("用户不存在");
		}
		String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX + username);
		if (org.apache.commons.lang3.StringUtils.isBlank(token)) {
			return ServerResponse.createByErrorMessage("token无效或者过期");
		}

		if (org.apache.commons.lang3.StringUtils.equals(forgetToken, token)) {
			String md5Password = MD5Util.MD5EncodeUtf8(passwordNew);
			if (Const.USERNAME.equals(type)) {
				int rowCount = userMapper.updatePasswordByUsername(username, md5Password);
				if (rowCount > 0) {
					return ServerResponse.createBySuccessMessage("根据用户名修改密码成功");
				}
			} 
			else {
				int rowCount = userMapper.updatePasswordByEmail(username, md5Password);
				if (rowCount > 0) {
					return ServerResponse.createBySuccessMessage("根据邮箱修改密码成功");
				}
			}
		} else {
			return ServerResponse.createByErrorMessage("token错误,请重新获取重置密码的token");
		}
		return ServerResponse.createByErrorMessage("修改密码失败");
	}
	
	/**
	 * 登陆后修改密码
	 * 
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse<String> updatePassword(String passwordOld, String passwordNew, User user) {
		// TODO Auto-generated method stub
		//防止横向越权,要校验一下这个用户的旧密码,一定要指定是这个用户;
        int resultCount = userMapper.checkPassword(MD5Util.MD5EncodeUtf8(passwordOld),user.getId());
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("旧密码错误");
        }
        user.setPassword(MD5Util.MD5EncodeUtf8(passwordNew));
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if(updateCount > 0){
            return ServerResponse.createBySuccessMessage("密码更新成功");
        }
        return ServerResponse.createByErrorMessage("密码更新失败");
	}
	
	
	/**
	 * 更新个人信息
	 * 
	 * @author 高志劲
	 */
	public ServerResponse<User> updateInformation(User user, User currentUser){
        if(!currentUser.getUsername().equals(user.getUsername())){
		int resultCount = 0;
        resultCount = userMapper.checkUsername(user.getUsername());
        if(resultCount > 0){
            return ServerResponse.createByErrorMessage("用户名已存在,请更换用户名再尝试更新");
        }
        }
        if(!currentUser.getEmail().equals(user.getEmail())){
    		int resultCount = 0;
            resultCount = userMapper.checkEmail(user.getEmail());
            if(resultCount > 0){
                return ServerResponse.createByErrorMessage("email已存在,请更换email再尝试更新");
            }
            }
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setUsername(user.getUsername());
        updateUser.setEmail(user.getEmail());
        updateUser.setQuestion(user.getQuestion());
        updateUser.setAnswer(user.getAnswer());
        int updateCount = userMapper.updateByPrimaryKeySelective(updateUser);
        if(updateCount > 0){
            return ServerResponse.createBySuccess("更新个人信息成功",updateUser);
        }
        return ServerResponse.createByErrorMessage("更新个人信息失败");
    }
	
	/**
	 * @author eachen
	 * 获取用户信息
	 * @param userId
	 * @return
	 */
	public ServerResponse<User> getUserInfo(int userId) {
		User user = userMapper.selectByPrimaryKey(userId);
		if (user != null) {
			return ServerResponse.createBySuccess(user);
		}
		return ServerResponse.createByErrorMessage("用户ID不存在");
	}

	/**
	 * @author gzj
	 * 分页此时
	 * @param userId
	 * @return
	 */
	@Override
	public List<User> getAllUser() {
		List<User> list = new ArrayList<User>();
		list = userMapper.getAllUser();
		return list;
	}
	
	/**
	 * @author 徐子颖
	 * 获取用户列表
	 * @return
	 */
	@Override
	public List<User> getTotalUserList() {
		// TODO Auto-generated method stub
		List<User> list = new ArrayList<User>();
		list = userMapper.getTotalUserList();
		return list;
	}

	/**
	 * @author 徐子颖
	 * 修改用户封禁状态
	 * @param id
	 * @param state
	 * @return
	 */
	@Override
	public ServerResponse updateStateById(Integer id,Integer state) {
		// TODO Auto-generated method stub
		int updateCount = userMapper.updateStateById(id,state);
		if(updateCount>0){
			return ServerResponse.createBySuccess("修改用户状态成功");
		}
		return ServerResponse.createByErrorMessage("修改用户状态失败");
	}

	/**
	 * @author 徐子颖
	 * 删除用户
	 * @param id
	 * @return
	 */
	@Override
	public ServerResponse deleteUserById(Integer id) {
		// TODO Auto-generated method stub
		int deleteCount = userMapper.deleteByPrimaryKey(id);
		if(deleteCount>0){
			return ServerResponse.createBySuccess("删除用户成功");
		}
		return ServerResponse.createByErrorMessage("删除用户失败");
	}

	/**
	 * @author 徐子颖
	 * 管理员根据用户名搜索用户
	 * @param username
	 * @return
	 */
	@Override
	public ServerResponse selectUserByUsername(String username) {
		// TODO Auto-generated method stub
		User user = new User();
		user = userMapper.selectUserByUsername(username);
		return ServerResponse.createBySuccess("查找成功",user);

		
	}

	/**
	 * @author 徐子颖
	 * 根据id排序
	 * @return
	 */
	@Override
	public List<User> sortById() {
		// TODO Auto-generated method stub
		List<User> list = new ArrayList<User>();
		list = userMapper.sortById();
		return list;
	}

	/**
	 * @author 徐子颖
	 * 根据状态排序
	 * @return
	 */
	@Override
	public List<User> sortByState() {
		// TODO Auto-generated method stub
		List<User> list = new ArrayList<User>();
		list = userMapper.sortByState();
		return list;
	}

	/**
	 * @author 徐子颖
	 * 修改密码
	 * @param passwordOld
	 * @param passwordNew
	 * @param passwordRepeat
	 * @return
	 */
	@Override
	public ServerResponse updatePassword(String username, String passwordOld, String passwordNew,String passwordRepeat) {
		// TODO Auto-generated method stub
		if(passwordNew.equals(passwordRepeat)){
		String forgetToken = UUID.randomUUID().toString();
		TokenCache.setKey(TokenCache.TOKEN_PREFIX + username, forgetToken);
		String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX + username);
		if (org.apache.commons.lang3.StringUtils.isBlank(token)) {
			return ServerResponse.createByErrorMessage("token无效或者过期");
		}
		if (org.apache.commons.lang3.StringUtils.equals(forgetToken, token)) {
			String md5Password = MD5Util.MD5EncodeUtf8(passwordNew);
				int rowCount = userMapper.updatePasswordByUsername(username, md5Password);
				if (rowCount > 0) {
					return ServerResponse.createBySuccessMessage("根据用户名修改密码成功");
				}
		} else {
			return ServerResponse.createByErrorMessage("token错误,请重新获取重置密码的token");
		}
		return ServerResponse.createByErrorMessage("修改密码失败");
		}
		else{
			return ServerResponse.createByErrorMessage("密码不一致");
		}
	}

	
	@Override
	public String selectNameByID(Integer id) {
		return userMapper.selectNameByID(id);
	}

	/**
	 * @author 高志劲
	 * 根据username找到用户id
	 * @return
	 */
	@Override
	public String findIdByName(String username) {
		String targetid = null;
		targetid = userMapper.findIdByName(username);
		return targetid;
	}

}
