import ru.strays.annotations.After;
import ru.strays.annotations.Before;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestListParent {
    public List<String> parentList;

    @Before
    public void parentSetUp() {
        parentList = new ArrayList<>();
    }

    @After
    public void parentTearDown() {
        parentList.addAll(Arrays.asList("some element", "one more element"));
    }
}
