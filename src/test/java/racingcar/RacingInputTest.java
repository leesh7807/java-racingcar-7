package racingcar;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class RacingInputTest {
    RacingInput racingInput = new RacingInput();

    @Test
    void 이름문자열_공백() {
        setSystemInput("\n");

        Assertions.assertThatThrownBy(() -> {
            racingInput.receiveCarNames();
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 차이름_5글자초과() {
        setSystemInput("foo,baemin,bar\n");

        Assertions.assertThatThrownBy(() -> {
            racingInput.receiveCarNames();
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 차이름_공백() {
        setSystemInput("foo,,bar\n");

        Assertions.assertThatThrownBy(() -> {
            racingInput.receiveCarNames();
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 라운드_공백() {
        setSystemInput("\n");

        Assertions.assertThatThrownBy(() -> {
            racingInput.receiveTotalRounds();
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 라운드_숫자X() {
        setSystemInput("one\n");

        Assertions.assertThatThrownBy(() -> {
            racingInput.receiveTotalRounds();
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 라운드_범위초과() {
        setSystemInput("-1\n");
        
        Assertions.assertThatThrownBy(() -> {
            racingInput.receiveTotalRounds();
        }).isInstanceOf(IllegalArgumentException.class);
    }

    private void setSystemInput(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }
}