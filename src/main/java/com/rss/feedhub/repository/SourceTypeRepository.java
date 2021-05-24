package com.rss.feedhub.repository;

import com.rss.feedhub.entity.SourceTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author: ZhangKaijie
 * @Date: 2021/5/13 13:45
 * @Description:
 */
public interface SourceTypeRepository extends JpaRepository<SourceTypeEntity,Integer> {
    @Query(value = "select * from source_type",nativeQuery = true)
    List<SourceTypeEntity> getSourceType();
}
