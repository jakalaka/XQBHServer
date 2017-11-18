package XQBHServer.Server.Table.Mapper;

import XQBHServer.Server.Table.Model.DSHZH;
import XQBHServer.Server.Table.Model.DSHZHExample;
import XQBHServer.Server.Table.Model.DSHZHKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DSHZHMapper {
    int countByExample(DSHZHExample example);

    int deleteByExample(DSHZHExample example);

    int deleteByPrimaryKey(DSHZHKey key);

    int insert(DSHZH record);

    int insertSelective(DSHZH record);

    List<DSHZH> selectByExample(DSHZHExample example);

    DSHZH selectByPrimaryKey(DSHZHKey key);

    int updateByExampleSelective(@Param("record") DSHZH record, @Param("example") DSHZHExample example);

    int updateByExample(@Param("record") DSHZH record, @Param("example") DSHZHExample example);

    int updateByPrimaryKeySelective(DSHZH record);

    int updateByPrimaryKey(DSHZH record);
}