package com.diden.demo.config.adepter;

import com.diden.demo.utils.JwtProperties;
import com.diden.demo.utils.JwtSocialTokenRetrofitCallInterface;
import com.diden.demo.utils.RetrofitCallUtils;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import retrofit2.Call;

import java.io.IOException;

import static com.diden.demo.utils.SocialJwtProperties.NAVER_BASE_URI;

@Slf4j
class LoginLogoutNaverTest {

  private final String NAVER_CLIENT_ID = "7wEVAYsChBxD4sQAPzzJ";
  private final String NAVER_CLIENT_SECRET = "LXVTpar6AQ";

  /**
   * 네이버 엑세스 토큰
   *
   * <p>https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=7wEVAYsChBxD4sQAPzzJ&redirect_uri=http://localhost:8080/info/naver/redirect&state=130
   */
  private final String NAVER_ACCESS_TOKEN =
      "AAAAOumpWX76h1R2gXT0kmj5QDDVTEmzjPgNIQLAKXWyCDHZhs-HhIyADsKgAnorOuTlsWkjHQ9_JED6s8KZ8wLCW5A";
  private LoginLogoutAdepter loginLogoutAdepter;

  @Test
  void supports() {}

  @Test
  void loginProcess() throws IOException {
    // Client ID : 7wEVAYsChBxD4sQAPzzJ
    // Client Secret : LXVTpar6AQ
    // String apiUri = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
    JwtSocialTokenRetrofitCallInterface apiService =
        RetrofitCallUtils.getApiService(NAVER_BASE_URI);
    Call<JsonObject> naverUser =
        apiService.getJwtNaverAccessToken(JwtProperties.TOKEN_PREFIX + NAVER_ACCESS_TOKEN);

    System.out.println(naverUser.execute().body().get("response"));
  }

  @Test
  void loginProcess_naver() throws IOException {
    // Client ID : 7wEVAYsChBxD4sQAPzzJ
    // Client Secret : LXVTpar6AQ
    // String apiUri = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
    //String naverAccessToken = "AAAAOHoCJbQ5XV_ttOMxYIc16OjMcwCoYoDumOv--YvaxntTpaYpBm4-NlVrr0ND12t2Q6Lqc_NqlsLLEX4XhGZo5S4";
    String naverAccessToken = "AAAAOHoCJbQ5XV_ttOMxYIc16OjMcwCoYoDumOv--YvaxntTpaYpBm4-NlVrr0ND12t2Q6Lqc_NqlsLLEX4XhGZo5S4";
    loginLogoutAdepter = new LoginLogoutNaver();
    loginLogoutAdepter.loginProcess(naverAccessToken);
  }

  @Test
  void logoutProcess() {}

  @Test
  void findUserEmail() {}
}
