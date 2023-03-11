package com.diden.demo.domain.tour.repository;

import com.diden.demo.domain.tour.domain.TourAreaInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TourRepository {

    @PersistenceContext
    private EntityManager em;

    public TourAreaInfo find(String contentid) {
        return em.find(TourAreaInfo.class, contentid);
    }

    public List<TourAreaInfo> findByTitle(String title){
        return em.createQuery("select m from Tb_area_tour_info m where m.title = :title", TourAreaInfo.class)
                .setParameter("title",title)
                .getResultList();
    }

}
