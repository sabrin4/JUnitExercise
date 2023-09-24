import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class SimpleTest {


    @Test
    static int sizeGreaterThanOrEqual(int i) {
        if (i <= 10) {
            return 10;
        }
        return i;
    }
}