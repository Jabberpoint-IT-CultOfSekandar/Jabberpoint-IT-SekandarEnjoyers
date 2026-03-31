import org.junit.Test;

import static org.junit.Assert.*;

public class AccessorDemoTest {

    @Test
    public void testGetDemoAccessorReturnsDemoPresentation() {
        Accessor accessor = Accessor.getDemoAccessor();

        assertNotNull(accessor);
        assertTrue(accessor instanceof DemoPresentation);
        assertEquals(".xml", Accessor.DEFAULT_EXTENSION);
    }

    @Test
    public void testDemoLoadFilePopulatesPresentation() throws Exception {
        Presentation presentation = new Presentation();

        Accessor.getDemoAccessor().loadFile(presentation, "");

        assertEquals("Demo Presentation", presentation.getTitle());
        assertTrue(presentation.getSize() >= 3);
        assertEquals("JabberPoint", presentation.getSlide(0).getTitle());
    }

    @Test(expected = IllegalStateException.class)
    public void testDemoSaveFileThrows() throws Exception {
        Presentation presentation = new Presentation();
        Accessor.getDemoAccessor().saveFile(presentation, "unused");
    }
}
