package net.sf.marineapi.nmea.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.List;

import net.sf.marineapi.nmea.sentence.GSVSentence;
import net.sf.marineapi.nmea.util.SatelliteInfo;

import org.junit.Before;
import org.junit.Test;

/**
 * Test the GSV sentence parser.
 * 
 * @author Kimmo Tuukkanen
 */
public class GSVTest {

    /** Example sentence */
    public static final String EXAMPLE = "$GPGSV,3,2,12,15,56,182,51,17,38,163,47,18,63,058,50,21,53,329,47*73";

    private GSVSentence gsv;

    @Before
    public void setUp() {
        try {
            gsv = new GSVParser(EXAMPLE);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     * Test method for
     * {@link net.sf.marineapi.nmea.parser.GSVParser#getSentenceCount()}.
     */
    @Test
    public void testGetSentenceCount() {
        assertEquals(3, gsv.getSentenceCount());
    }

    /**
     * Test method for
     * {@link net.sf.marineapi.nmea.parser.GSVParser#getSentenceIndex()}.
     */
    @Test
    public void testGetSentenceNumber() {
        assertEquals(2, gsv.getSentenceIndex());
    }

    /**
     * Test method for {@link net.sf.marineapi.nmea.parser.GSVParser#isFirst()}.
     */
    @Test
    public void testIsFirstInSequence() {
        assertFalse(gsv.isFirst());
    }

    /**
     * Test method for {@link net.sf.marineapi.nmea.parser.GSVParser#isLast()} .
     */
    @Test
    public void testIsLastInSequence() {
        assertFalse(gsv.isLast());
    }

    /**
     * Test method for
     * {@link net.sf.marineapi.nmea.parser.GSVParser#getSatelliteCount()}.
     */
    @Test
    public void testGetSatelliteCount() {
        assertEquals(12, gsv.getSatelliteCount());
    }

    /**
     * Test method for
     * {@link net.sf.marineapi.nmea.parser.GSVParser#getSatelliteInfo()}.
     */
    @Test
    public void testGetSatelliteInfo() {
        List<SatelliteInfo> sat = gsv.getSatelliteInfo();
        assertEquals(4, sat.size());
        testSatelliteInfo(sat.get(0), "15", 56, 182, 51);
        testSatelliteInfo(sat.get(1), "17", 38, 163, 47);
        testSatelliteInfo(sat.get(2), "18", 63, 58, 50);
        testSatelliteInfo(sat.get(3), "21", 53, 329, 47);
    }

    /**
     * Tests the given SatelliteInfo against specified values.
     */
    private void testSatelliteInfo(SatelliteInfo si, String id, int elevation,
            int azimuth, int noise) {
        assertEquals(id, si.getId());
        assertEquals(elevation, si.getElevation(), 0.001);
        assertEquals(azimuth, si.getAzimuth(), 0.001);
        assertEquals(noise, si.getNoise(), 0.001);
    }
}