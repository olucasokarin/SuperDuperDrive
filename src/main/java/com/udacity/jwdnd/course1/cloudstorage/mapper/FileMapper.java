package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE userId = #{userId}")
    List<File> getFiles(Integer id);


    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    File getFile(Integer id);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata)" +
            "VALUES (#{filename}, #{contentType}, #{fileSize}, #{userId}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);


    @Delete("DELETE FROM FILES WHERE fileid = #{fileId}")
    void deleteFile(Integer fileId);
}
