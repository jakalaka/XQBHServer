package XQBHServer.Server.Table.Mapper;

import XQBHServer.Server.Table.Model.CDSRW;
import XQBHServer.Server.Table.Model.CDSRWExample;
import XQBHServer.Server.Table.Model.CDSRWKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CDSRWMapper {
    int countByExample(CDSRWExample example);

    int deleteByExample(CDSRWExample example);

    int deleteByPrimaryKey(CDSRWKey key);

    int insert(CDSRW record);

    int insertSelective(CDSRW record);

    List<CDSRW> selectByExample(CDSRWExample example);

    CDSRW selectByPrimaryKey(CDSRWKey key);

    int updateByExampleSelective(@Param("record") CDSRW record, @Param("example") CDSRWExample example);

    int updateByExample(@Param("record") CDSRW record, @Param("example") CDSRWExample example);

    int updateByPrimaryKeySelective(CDSRW record);

    int updateByPrimaryKey(CDSRW record);
}