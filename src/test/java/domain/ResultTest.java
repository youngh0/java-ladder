package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ResultTest {
    /**
     * pobi : 꽝
     * honux : 3000
     * crong : 꽝
     * jk : 5000
     */
    @Test
    void a() {
        Map<String, Integer> ladderGameResult = new LinkedHashMap<>();
        ladderGameResult.put("pobi", 0);
        ladderGameResult.put("honux", 3);
        ladderGameResult.put("crong", 2);
        ladderGameResult.put("jk", 1);
        Result result = new Result(new ArrayList<>(List.of("꽝","5000","꽝","3000")),ladderGameResult);
        Assertions.assertThat(result.getUserResult("pobi")).isEqualTo("꽝");
        Assertions.assertThat(result.getUserResult("honux")).isEqualTo("3000");
        Assertions.assertThat(result.getUserResult("crong")).isEqualTo("꽝");
        Assertions.assertThat(result.getUserResult("jk")).isEqualTo("5000");
    }

}