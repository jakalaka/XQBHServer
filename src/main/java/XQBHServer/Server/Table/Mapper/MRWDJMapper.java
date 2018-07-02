package XQBHServer.Server.Table.Mapper;

import XQBHServer.Server.Table.Model.MRWDJ;
import XQBHServer.Server.Table.Model.MRWDJExample;
import XQBHServer.Server.Table.Model.MRWDJKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MRWDJMapper {
    int countByExample(MRWDJExample example);

    int deleteByExample(MRWDJExample example);

    int deleteByPrimaryKey(MRWDJKey key);

    int insert(MRWDJ record);

    int insertSelective(MRWDJ record);

    List<MRWDJ> selectByExample(MRWDJExample example);

    MRWDJ selectByPrimaryKey(MRWDJKey key);

    int updateByExampleSelective(@Param("record") MRWDJ record, @Param("example") MRWDJExample example);

    int updateByExample(@Param("record") MRWDJ record, @Param("example") MRWDJExample example);

    int updateByPrimaryKeySelective(MRWDJ record);

    int updateByPrimaryKey(MRWDJ record);
}