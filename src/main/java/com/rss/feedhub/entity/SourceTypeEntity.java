package com.rss.feedhub.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Author: ZhangKaijie
 * @Date: 2021/4/4 12:47
 * @Description:
 */
@Entity
@Table(name = "source_type", schema = "FeedHub", catalog = "")
public class SourceTypeEntity {
    private int id;
    private String typeName;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type_name", nullable = false, length = 255)
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourceTypeEntity that = (SourceTypeEntity) o;
        return id == that.id &&
                Objects.equals(typeName, that.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeName);
    }
}
