import org.junit.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class XMLAccessorTest {

    @Test
    public void testSaveAndLoadRoundTrip() throws Exception {
        Presentation source = new Presentation();
        source.setTitle("My Show");

        Slide slide = new Slide();
        slide.setTitle("First");
        slide.append(new TextItem(1, "Hello"));
        source.append(slide);

        File tempXml = File.createTempFile("jabberpoint-roundtrip", ".xml", new File("."));
        tempXml.deleteOnExit();

        XMLAccessor accessor = new XMLAccessor();
        accessor.saveFile(source, tempXml.getAbsolutePath());

        String xml = new String(Files.readAllBytes(tempXml.toPath()), StandardCharsets.UTF_8);
        assertTrue(xml.contains("<showtitle>My Show</showtitle>"));
        assertTrue(xml.contains("<title>First</title>"));

        Presentation loaded = new Presentation();
        accessor.loadFile(loaded, tempXml.getAbsolutePath());

        assertEquals("My Show", loaded.getTitle());
        assertEquals(1, loaded.getSize());
        assertEquals("First", loaded.getSlide(0).getTitle());
        assertTrue(loaded.getSlide(0).getSlideItem(0) instanceof TextItem);
    }

    @Test
    public void testLoadMissingFileDoesNotThrow() throws Exception {
        XMLAccessor accessor = new XMLAccessor();
        Presentation presentation = new Presentation();

        accessor.loadFile(presentation, "definitely-not-a-file.xml");

        assertEquals(0, presentation.getSize());
    }
}
