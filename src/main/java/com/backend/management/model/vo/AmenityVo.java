package com.backend.management.model.vo;

import java.util.List;

/**
 * @author Administrator
 * @description TODO
 * @date 2023/5/18 0018 12:10
 */

public class AmenityVo {
    private Integer amenityId;

    private String amenityName;

    List<ReservationVo> childrenList;

    public Integer getAmenityId() {
        return amenityId;
    }

    public void setAmenityId(Integer amenityId) {
        this.amenityId = amenityId;
    }

    public String getAmenityName() {
        return amenityName;
    }

    public void setAmenityName(String amenityName) {
        this.amenityName = amenityName;
    }

    public List<ReservationVo> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<ReservationVo> childrenList) {
        this.childrenList = childrenList;
    }
}
