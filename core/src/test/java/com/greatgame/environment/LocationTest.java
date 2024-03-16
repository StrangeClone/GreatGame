package com.greatgame.environment;

import junit.framework.TestCase;

public class LocationTest extends TestCase {

    public void testGetBehaviourInfos() {
        Location l = new Location(0, 0, null, null);
        BehaviourInfo info1 = new BehaviourInfo("Info1",10) {
            @Override
            public boolean apply(Behaviour behaviour) {
                return true;
            }
        };
        BehaviourInfo info2 = new BehaviourInfo("Info2",20) {
            @Override
            public boolean apply(Behaviour behaviour) {
                return true;
            }
        };
        l.addInfo(info1);
        assertEquals(l.getBehaviourInfo().size(), 1);
        assertEquals(l.getBehaviourInfo().get("Info1").getHP(), 10);
        l.addInfo(info2);
        assertEquals(l.getBehaviourInfo().size(), 2);
        assertEquals(l.getBehaviourInfo().get("Info2").getHP(), 20);
    }
}