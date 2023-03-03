package com.diden.demo.domain.tour.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TB_AREA_TOUR_INFO")
@Getter @Setter
public class TourAreaInfo{

    @Id @GeneratedValue
    @Column(name = "contentid")
    private String contentid;

    private String title;

    private String contenttypeid;

    private String addr1;
    private String addr2;
    private String cat1;
    private String cat2;
    private String cat3;
    private String firstimage;
    private String firstimage2;
    private String mapx;
    private String mapy;
    private String readcount;
    private String sigungucode;
    private String tel;
    private String zipcode;
    private String createdtime;
    private String modifiedtime;
    private String areacode;
    private String mlevel;
    private String overview;
    private String areaname;



}
