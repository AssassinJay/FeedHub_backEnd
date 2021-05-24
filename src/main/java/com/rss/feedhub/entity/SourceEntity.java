package com.rss.feedhub.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Author: ZhangKaijie
 * @Date: 2021/4/4 12:47
 * @Description:
 */
@Entity
@Table(name = "source", schema = "FeedHub", catalog = "")
public class SourceEntity {
    private int id;
    private String souceName;
    private int typeId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "souce_name", nullable = false, length = 255)
    public String getSouceName() {
        return souceName;
    }

    public void setSouceName(String souceName) {
        this.souceName = souceName;
    }

    @Basic
    @Column(name = "type_id", nullable = false)
    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourceEntity that = (SourceEntity) o;
        return id == that.id &&
                typeId == that.typeId &&
                Objects.equals(souceName, that.souceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, souceName, typeId);
    }
}
