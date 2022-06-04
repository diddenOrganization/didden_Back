package com.diden.demo.main;

import com.diden.demo.main.mapper.MainContentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@Slf4j
public class MainContentServiceImpl implements MainContentService{
    private final MainContentMapper mainContentMapper;

    public MainContentServiceImpl(MainContentMapper mainContentMapper) {
        this.mainContentMapper = mainContentMapper;
    }

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
            log.info("{}", LocalDateTime.now());
            log.info("{}", cache.get(0).getDataCreateTime().plusHours(1));
            log.info("{}", LocalDateTime.now().isAfter(cache.get(0).getDataCreateTime().plusHours(1)));
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
