package XQBHServer.Server.Table.Mapper;

import XQBHServer.Server.Table.Model.MJYBW;
import XQBHServer.Server.Table.Model.MJYBWExample;
import XQBHServer.Server.Table.Model.MJYBWKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MJYBWMapper {
    int countByExample(MJYBWExample example);

    int deleteByExample(MJYBWExample example);

    int deleteByPrimaryKey(MJYBWKey key);

    int insert(MJYBW record);

    int insertSelective(MJYBW record);

    List<MJYBW> selectByExampleWithBLOBs(MJYBWExample example);

    List<MJYBW> selectByExample(MJYBWExample example);

    MJYBW selectByPrimaryKey(MJYBWKey key);

    int updateByExampleSelective(@Param("record") MJYBW record, @Param("example") MJYBWExample example);

    int updateByExampleWithBLOBs(@Param("record") MJYBW record, @Param("example") MJYBWExample example);

    int updateByExample(@Param("record") MJYBW record, @Param("example") MJYBWExample example);

    int updateByPrimaryKeySelective(MJYBW record);

    int updateByPrimaryKeyWithBLOBs(MJYBW record);

    int updateByPrimaryKey(MJYBW record);
}