package com.greatgame.environment;

import junit.framework.TestCase;

public class LocationTest extends TestCase {

    public void testGetBehaviourInfos() {
        Location l = new Location(0, 0, null, null);
        BehaviourInfo info1 = new BehaviourInfo("Info1",10) {};
        BehaviourInfo info2 = new BehaviourInfo("Info2",20) {};
        l.addInfo(info1);
        assertEquals(l.getBehaviourInfos().size(), 1);
        assertEquals(l.getBehaviourInfos().get("Info1").getHP(), 10);
        l.addInfo(info2);
        assertEquals(l.getBehaviourInfos().size(), 2);
        assertEquals(l.getBehaviourInfos().get("Info2").getHP(), 20);
    }
}