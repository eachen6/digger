package com.digger.dao;

import org.apache.ibatis.annotations.Param;

import com.digger.pojo.User;
import com.digger.common.ServerResponse;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	int checkUsername(String username);

	User selectLogin(@Param("username")String username,@Param("password")String password);

	int checkEmail(String email);

	String selectQuestionByUsername(String username);
	
	String selectQuestionByEmail(String email);

	int checkAnswerByUsername(@Param("username")String username,@Param("question")String question,@Param("answer")String answer);

	int updatePasswordByUsername(@Param("username")String username, @Param("md5Password")String md5Password);

	int updatePasswordByEmail(@Param("username")String username,@Param("md5Password")String md5Password);

	int checkAnswerByEmail(@Param("username")String username,@Param("question")String question,@Param("answer")String answer);

	

    
    
}