package XQBHServer.Server.Table.Mapper;

import XQBHServer.Server.Table.Model.DZDXX;
import XQBHServer.Server.Table.Model.DZDXXExample;
import XQBHServer.Server.Table.Model.DZDXXKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DZDXXMapper {
    int countByExample(DZDXXExample example);

    int deleteByExample(DZDXXExample example);

    int deleteByPrimaryKey(DZDXXKey key);

    int insert(DZDXX record);

    int insertSelective(DZDXX record);

    List<DZDXX> selectByExample(DZDXXExample example);

    DZDXX selectByPrimaryKey(DZDXXKey key);

    int updateByExampleSelective(@Param("record") DZDXX record, @Param("example") DZDXXExample example);

    int updateByExample(@Param("record") DZDXX record, @Param("example") DZDXXExample example);

    int updateByPrimaryKeySelective(DZDXX record);

    int updateByPrimaryKey(DZDXX record);
}