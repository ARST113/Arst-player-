package com.brouken.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;

/**
 * ARX TV UX contract: upstream 2.4.9+ experiments with controls inside
 * the timeline. ARX deliberately retains a centered play/pause button.
 * Run this on every preview build before applying upstream layout changes.
 */
public class TvCenterPlaybackControlsTest {
    private static final String ANDROID_NS = "http://schemas.android.com/apk/res/android";

    private static Element findById(Node root, String id) {
        if (root instanceof Element) {
            Element element = (Element) root;
            if (("@+id/" + id).equals(element.getAttributeNS(ANDROID_NS, "id"))) {
                return element;
            }
        }
        NodeList nodes = root.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Element found = findById(nodes.item(i), id);
            if (found != null) return found;
        }
        return null;
    }

    private static boolean isDescendant(Node ancestor, Node child) {
        for (Node current = child.getParentNode(); current != null; current = current.getParentNode()) {
            if (ancestor == current) return true;
        }
        return false;
    }

    @Test
    public void tvPlayPauseRemainsCenteredAndOutsideTheTimeline() throws Exception {
        File layout = new File("src/main/res/layout/exo_player_control_view.xml");
        if (!layout.isFile()) layout = new File("app/src/main/res/layout/exo_player_control_view.xml");
        assertTrue("Playback XML layout must exist", layout.isFile());

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        Document doc = factory.newDocumentBuilder().parse(layout);
        Element center = findById(doc.getDocumentElement(), "exo_center_controls");
        Element play = findById(doc.getDocumentElement(), "exo_play_pause");
        Element timeline = findById(doc.getDocumentElement(), "plate_time_row");
        Element bottom = findById(doc.getDocumentElement(), "exo_bottom_bar");

        assertNotNull(center);
        assertNotNull(play);
        assertNotNull(timeline);
        assertNotNull(bottom);
        assertEquals("center", center.getAttributeNS(ANDROID_NS, "layout_gravity"));
        assertTrue("Play/pause must remain inside the center overlay", isDescendant(center, play));
        assertTrue("Play/pause must not be moved into the timeline", !isDescendant(timeline, play));
        assertTrue("Play/pause must not be moved to the TV bottom bar", !isDescendant(bottom, play));
    }
}
