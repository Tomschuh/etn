package com.etnetera.hr.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Service interface used for communication between repository layer and controller.
 *
 * @author Tom
 */
public interface JavaScriptFrameworkService {

    void createJavaScriptFramework(Long id, JavaScriptFrameworkIn in);

    void deleteJavaScriptFramework(Long id);

    List<GetJavaScriptFrameworkItemOut> searchForFramework(String expression);

    //~ Inner classes

    class JavaScriptFrameworkIn {
        public String name;
        public List<String> versions;
        public BigDecimal hypeLevel;
        public LocalDate deprecationDate;
    }

    class GetJavaScriptFrameworkItemOut {
        public Long id;
        public String name;
        public List<String> versions;
        public BigDecimal hypeLevel;
        public LocalDate deprecationDate;
    }
}
