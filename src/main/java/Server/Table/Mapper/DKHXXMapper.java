package Server.Table.Mapper;

import Server.Table.Model.DKHXX;
import Server.Table.Model.DKHXXExample;
import Server.Table.Model.DKHXXKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DKHXXMapper {
    int countByExample(DKHXXExample example);

    int deleteByExample(DKHXXExample example);

    int deleteByPrimaryKey(DKHXXKey key);

    int insert(DKHXX record);

    int insertSelective(DKHXX record);

    List<DKHXX> selectByExample(DKHXXExample example);

    DKHXX selectByPrimaryKey(DKHXXKey key);

    int updateByExampleSelective(@Param("record") DKHXX record, @Param("example") DKHXXExample example);

    int updateByExample(@Param("record") DKHXX record, @Param("example") DKHXXExample example);

    int updateByPrimaryKeySelective(DKHXX record);

    int updateByPrimaryKey(DKHXX record);
}