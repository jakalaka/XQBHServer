package XQBHServer.Server.Table.Mapper;

import XQBHServer.Server.Table.Model.MDZSJ;
import XQBHServer.Server.Table.Model.MDZSJExample;
import XQBHServer.Server.Table.Model.MDZSJKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MDZSJMapper {
    int countByExample(MDZSJExample example);

    int deleteByExample(MDZSJExample example);

    int deleteByPrimaryKey(MDZSJKey key);

    int insert(MDZSJ record);

    int insertSelective(MDZSJ record);

    List<MDZSJ> selectByExample(MDZSJExample example);

    MDZSJ selectByPrimaryKey(MDZSJKey key);

    int updateByExampleSelective(@Param("record") MDZSJ record, @Param("example") MDZSJExample example);

    int updateByExample(@Param("record") MDZSJ record, @Param("example") MDZSJExample example);

    int updateByPrimaryKeySelective(MDZSJ record);

    int updateByPrimaryKey(MDZSJ record);
}