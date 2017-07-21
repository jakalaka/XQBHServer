package Server.Table.Mapper;

import Server.Table.Model.CZDSX;
import Server.Table.Model.CZDSXExample;
import Server.Table.Model.CZDSXKey;
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