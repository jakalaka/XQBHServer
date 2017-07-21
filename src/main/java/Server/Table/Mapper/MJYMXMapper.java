package Server.Table.Mapper;

import Server.Table.Model.MJYMX;
import Server.Table.Model.MJYMXExample;
import Server.Table.Model.MJYMXKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MJYMXMapper {
    int countByExample(MJYMXExample example);

    int deleteByExample(MJYMXExample example);

    int deleteByPrimaryKey(MJYMXKey key);

    int insert(MJYMX record);

    int insertSelective(MJYMX record);

    List<MJYMX> selectByExample(MJYMXExample example);

    MJYMX selectByPrimaryKey(MJYMXKey key);

    int updateByExampleSelective(@Param("record") MJYMX record, @Param("example") MJYMXExample example);

    int updateByExample(@Param("record") MJYMX record, @Param("example") MJYMXExample example);

    int updateByPrimaryKeySelective(MJYMX record);

    int updateByPrimaryKey(MJYMX record);
}