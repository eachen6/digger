package com.digger.dao;

import java.util.List;

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

	int checkPassword(@Param("md5EncodeUtf8")String md5EncodeUtf8, @Param("id")Integer id);

	List<User> getAllUser();

	List<User> getTotalUserList();

	int updateStateById(@Param("id")Integer id, @Param("state")Integer state);

	User selectUserByUsername(String username);

	List<User> sortById();

	List<User> sortByState();

	String selectNameByID(Integer id);

	int checkState(String username);

    
    
}