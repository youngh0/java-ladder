package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserTest {
    @DisplayName("유저 이름이 1글자에서 5글자 사이가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"123456", ""})
    void userNameLengthFailTest(String name) {
        assertThatThrownBy(() -> new User(name))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("유저 이름에 'all'이 들어오면 예외가 발생한다.")
    @Test
    void invalidUserNameTest() {
        assertThatThrownBy(() -> new User("all"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("유저 이름이 1글자에서 5글자 사이면 정상적으로 수행된다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "12", "123", "1234"})
    void userNameLengthTest(String name) {
        assertThatCode(() -> new User(name))
                .doesNotThrowAnyException();
    }

    @DisplayName("유저 이름이 공백으로만 이루어진 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {" ", "  ", "    "})
    void userBlankTest(String name) {
        assertThatThrownBy(() -> new User(name))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
