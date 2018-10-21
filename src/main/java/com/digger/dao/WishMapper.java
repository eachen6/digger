package com.digger.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.digger.pojo.Wish;
import com.digger.vo.WishVO;

public interface WishMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Wish record);

    int insertSelective(Wish record);

    Wish selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Wish record);

    int updateByPrimaryKey(Wish record);

    Integer selectByGameid(Integer gameid);

	int delete(int wishid);

	Integer select(@Param("gameid")Integer gameid, @Param("userid")Integer userid);

	List<WishVO> toGetMyselfWishGame(Integer userid);

	Integer delete(@Param("gameid")int gameid, @Param("userid")Integer userid);

	Integer deleteWish(String ordernum);

	Integer deleteWish1(@Param("ordernum")String ordernum, @Param("targetid")String targetid);
}