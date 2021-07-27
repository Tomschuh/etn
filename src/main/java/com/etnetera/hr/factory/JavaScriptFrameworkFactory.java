package com.etnetera.hr.factory;

import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.service.JavaScriptFrameworkService.JavaScriptFrameworkIn;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

/**
 * Factory used for creating and updating JavaScriptFramework entity.
 *
 * @author Tom
 */
@Component
@Transactional(propagation = Propagation.MANDATORY)
public class JavaScriptFrameworkFactory {

    public JavaScriptFramework createJavaScriptFrameworkFrom(JavaScriptFrameworkIn in) {
        return mapJavaScriptFramework(in, new JavaScriptFramework());
    }

    public void updateJavaScriptFrameworkFrom(JavaScriptFrameworkIn in, JavaScriptFramework javaScriptFramework) {
        mapJavaScriptFramework(in, javaScriptFramework);
    }

    //~ Private method

    private JavaScriptFramework mapJavaScriptFramework(JavaScriptFrameworkIn in,
                                                       JavaScriptFramework javaScriptFramework) {
        javaScriptFramework.setName(in.name);
        javaScriptFramework.setDeprecationDate(in.deprecationDate);
        javaScriptFramework.setHypeLevel(in.hypeLevel);

        // version
        if (javaScriptFramework.getVersionSet() == null) {
            javaScriptFramework.setVersionSet(new HashSet<>());
        }
        if (in.versions != null) {
            javaScriptFramework.getVersionSet().addAll(in.versions);
            javaScriptFramework.getVersionSet().removeIf(version -> !in.versions.contains(version));
        }
        return javaScriptFramework;
    }
}
