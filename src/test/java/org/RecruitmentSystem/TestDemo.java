package org.RecruitmentSystem;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.*;

/**
 * Created by daseel on 2/5/17.
 */
@RunWith(Arquillian.class)
public class TestDemo {

    @Inject
    private TestEJB ejb;

    @org.junit.Test
    public void getUsername() throws Exception {
        System.out.println(ejb.bajs());
        assertNotNull(ejb.bajs());
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addPackage(TestEJB.class.getPackage())
                .addClass(TestEJB.class).addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
}
