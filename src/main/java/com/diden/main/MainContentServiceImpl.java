package com.diden.main;

import com.diden.main.mapper.MainContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class MainContentServiceImpl implements MainContentService{
    @Autowired
    MainContentMapper mainContentMapper;

    @Override
    public List<MainContentVo> findMainContentImageAll() {
        var findReturnData = mainContentMapper.findMainContentImageAll();
        try {
            for(MainContentVo data : findReturnData){
                String encodedString = Base64.getEncoder().encodeToString(data.getContentImageByte());
                StringBuilder sb = new StringBuilder()
                        .append("data:image/")
                        .append(data.getContentExtension())
                        .append(";base64,")
                        .append(encodedString);
                data.setContentUrl(sb.toString());
                data.setContentImageByte(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return findReturnData;
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
