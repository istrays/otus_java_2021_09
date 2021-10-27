import ru.strays.annotations.After;
import ru.strays.annotations.Before;
import ru.strays.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestList extends TestListParent {
    public List<String> list;

    @Before
    public void setUp() {
        list = new ArrayList<>();
    }

    @After
    public void tearDown() {
        list.addAll(Arrays.asList("some element", "one more element"));
    }

    @Test
    public void addElementTest() {
        list.add("testString1");
        list.add("testString2");
        parentList.add("parentString");

        assertThat(list.get(0)).isEqualTo("testString1");
        assertThat(list.get(1)).isEqualTo("testString2");
        assertThat(parentList.get(0)).isEqualTo("parentString");
    }

    @Test
    public void removeElementTest() {
        list.add("testString1");
        list.add("testString2");

        list.remove(0);

        assertThat(list.get(0)).isEqualTo("testString2");
    }
}
