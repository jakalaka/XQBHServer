package Server.Table.Mapper;

import Server.Table.Model.CSHSX;
import Server.Table.Model.CSHSXExample;
import Server.Table.Model.CSHSXKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CSHSXMapper {
    int countByExample(CSHSXExample example);

    int deleteByExample(CSHSXExample example);

    int deleteByPrimaryKey(CSHSXKey key);

    int insert(CSHSX record);

    int insertSelective(CSHSX record);

    List<CSHSX> selectByExample(CSHSXExample example);

    CSHSX selectByPrimaryKey(CSHSXKey key);

    int updateByExampleSelective(@Param("record") CSHSX record, @Param("example") CSHSXExample example);

    int updateByExample(@Param("record") CSHSX record, @Param("example") CSHSXExample example);

    int updateByPrimaryKeySelective(CSHSX record);

    int updateByPrimaryKey(CSHSX record);
}