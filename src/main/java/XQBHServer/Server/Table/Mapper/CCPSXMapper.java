package XQBHServer.Server.Table.Mapper;

import XQBHServer.Server.Table.Model.CCPSX;
import XQBHServer.Server.Table.Model.CCPSXExample;
import XQBHServer.Server.Table.Model.CCPSXKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CCPSXMapper {
    int countByExample(CCPSXExample example);

    int deleteByExample(CCPSXExample example);

    int deleteByPrimaryKey(CCPSXKey key);

    int insert(CCPSX record);

    int insertSelective(CCPSX record);

    List<CCPSX> selectByExample(CCPSXExample example);

    CCPSX selectByPrimaryKey(CCPSXKey key);

    int updateByExampleSelective(@Param("record") CCPSX record, @Param("example") CCPSXExample example);

    int updateByExample(@Param("record") CCPSX record, @Param("example") CCPSXExample example);

    int updateByPrimaryKeySelective(CCPSX record);

    int updateByPrimaryKey(CCPSX record);
}