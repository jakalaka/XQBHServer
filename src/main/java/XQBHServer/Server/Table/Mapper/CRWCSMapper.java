package XQBHServer.Server.Table.Mapper;

import XQBHServer.Server.Table.Model.CRWCS;
import XQBHServer.Server.Table.Model.CRWCSExample;
import XQBHServer.Server.Table.Model.CRWCSKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CRWCSMapper {
    int countByExample(CRWCSExample example);

    int deleteByExample(CRWCSExample example);

    int deleteByPrimaryKey(CRWCSKey key);

    int insert(CRWCS record);

    int insertSelective(CRWCS record);

    List<CRWCS> selectByExample(CRWCSExample example);

    CRWCS selectByPrimaryKey(CRWCSKey key);

    int updateByExampleSelective(@Param("record") CRWCS record, @Param("example") CRWCSExample example);

    int updateByExample(@Param("record") CRWCS record, @Param("example") CRWCSExample example);

    int updateByPrimaryKeySelective(CRWCS record);

    int updateByPrimaryKey(CRWCS record);
}