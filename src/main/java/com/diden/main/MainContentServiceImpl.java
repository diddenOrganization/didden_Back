package com.diden.main;

import com.diden.main.mapper.MainContentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class MainContentServiceImpl implements MainContentService{
    final static Logger logger = LoggerFactory.getLogger(MainContentServiceImpl.class);

    @Autowired
    MainContentMapper mainContentMapper;

    static List<MainContentVo> cache = new ArrayList<>();

    @Override
    public List<MainContentVo> findMainContentImageAll() {
        try {
            if(cache.isEmpty() || LocalDateTime.now().isAfter(cache.get(0).getDataCreateTime().plusHours(1))) {
                cache = mainContentMapper.findMainContentImageAll();
                for (MainContentVo data : cache) {
                    String encodedString = Base64.getEncoder().encodeToString(data.getContentImageByte());
                    StringBuilder sb = new StringBuilder()
                            .append("data:image/")
                            .append(data.getContentExtension())
                            .append(";base64,")
                            .append(encodedString);
                    data.setContentUrl(sb.toString());
                }
            }
            logger.info("{}", LocalDateTime.now());
            logger.info("{}", cache.get(0).getDataCreateTime().plusHours(1));
            logger.info("{}", LocalDateTime.now().isAfter(cache.get(0).getDataCreateTime().plusHours(1)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cache;
    }

    @Override
    public MainContentVo findMainContentImageOne(MainContentVo mainContentVo) {
        return null;
    }

    @Override
    public Long findMainContentImageCount() {
        return mainContentMapper.findMainContentImageCount();
    }

    @Override
    public void saveMainContentImages(MainContentVo mainContentVo) {
        mainContentMapper.saveMainContentImages(mainContentVo);
    }
}
