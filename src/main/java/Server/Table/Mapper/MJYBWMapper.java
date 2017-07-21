package Server.Table.Mapper;

import Server.Table.Model.MJYBW;
import Server.Table.Model.MJYBWExample;
import Server.Table.Model.MJYBWKey;
import Server.Table.Model.MJYBWWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MJYBWMapper {
    int countByExample(MJYBWExample example);

    int deleteByExample(MJYBWExample example);

    int deleteByPrimaryKey(MJYBWKey key);

    int insert(MJYBWWithBLOBs record);

    int insertSelective(MJYBWWithBLOBs record);

    List<MJYBWWithBLOBs> selectByExampleWithBLOBs(MJYBWExample example);

    List<MJYBW> selectByExample(MJYBWExample example);

    MJYBWWithBLOBs selectByPrimaryKey(MJYBWKey key);

    int updateByExampleSelective(@Param("record") MJYBWWithBLOBs record, @Param("example") MJYBWExample example);

    int updateByExampleWithBLOBs(@Param("record") MJYBWWithBLOBs record, @Param("example") MJYBWExample example);

    int updateByExample(@Param("record") MJYBW record, @Param("example") MJYBWExample example);

    int updateByPrimaryKeySelective(MJYBWWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(MJYBWWithBLOBs record);

    int updateByPrimaryKey(MJYBW record);
}