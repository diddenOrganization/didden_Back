package com.diden.demo.user;

import com.diden.demo.social.NaverSocialAdepterImpl;
import com.diden.demo.social.SocialAdepter;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class NaverSocialAdepterImplTest {

    private SocialAdepter socialAdepter;

    @Test
    void process() throws IOException {
        String naverAccessToken = "AAAAOHoCJbQ5XV_ttOMxYIc16OjMcwCoYoDumOv--YvaxntTpaYpBm4-NlVrr0ND12t2Q6Lqc_NqlsLLEX4XhGZo5S4";
        socialAdepter = new NaverSocialAdepterImpl();
        socialAdepter.process(naverAccessToken);
    }
}