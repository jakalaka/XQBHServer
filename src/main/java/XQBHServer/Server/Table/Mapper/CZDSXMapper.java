package XQBHServer.Server.Table.Mapper;

import XQBHServer.Server.Table.Model.CZDSX;
import XQBHServer.Server.Table.Model.CZDSXExample;
import XQBHServer.Server.Table.Model.CZDSXKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CZDSXMapper {
    int countByExample(CZDSXExample example);

    int deleteByExample(CZDSXExample example);

    int deleteByPrimaryKey(CZDSXKey key);

    int insert(CZDSX record);

    int insertSelective(CZDSX record);

    List<CZDSX> selectByExample(CZDSXExample example);

    CZDSX selectByPrimaryKey(CZDSXKey key);

    int updateByExampleSelective(@Param("record") CZDSX record, @Param("example") CZDSXExample example);

    int updateByExample(@Param("record") CZDSX record, @Param("example") CZDSXExample example);

    int updateByPrimaryKeySelective(CZDSX record);

    int updateByPrimaryKey(CZDSX record);
}