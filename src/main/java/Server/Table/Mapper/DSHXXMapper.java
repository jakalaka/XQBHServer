package Server.Table.Mapper;

import Server.Table.Model.DSHXX;
import Server.Table.Model.DSHXXExample;
import Server.Table.Model.DSHXXKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DSHXXMapper {
    int countByExample(DSHXXExample example);

    int deleteByExample(DSHXXExample example);

    int deleteByPrimaryKey(DSHXXKey key);

    int insert(DSHXX record);

    int insertSelective(DSHXX record);

    List<DSHXX> selectByExample(DSHXXExample example);

    DSHXX selectByPrimaryKey(DSHXXKey key);

    int updateByExampleSelective(@Param("record") DSHXX record, @Param("example") DSHXXExample example);

    int updateByExample(@Param("record") DSHXX record, @Param("example") DSHXXExample example);

    int updateByPrimaryKeySelective(DSHXX record);

    int updateByPrimaryKey(DSHXX record);
}