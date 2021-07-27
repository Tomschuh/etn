package com.etnetera.hr.mapper;

import com.etnetera.hr.controller.JavaScriptFrameworkController;
import com.etnetera.hr.controller.JavaScriptFrameworkController.GetJavaScriptFrameworkItemResTo;
import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.service.JavaScriptFrameworkService.GetJavaScriptFrameworkItemOut;
import com.etnetera.hr.service.JavaScriptFrameworkService.JavaScriptFrameworkIn;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper used for mapping object between service and controller.
 *
 * @author Etnetera
 */
public class JavaScriptFrameworkMappers {

    public static class JavascriptFrameworkInMapper {

        public static JavaScriptFrameworkIn fromJavaScriptFrameworkReqTo(JavaScriptFrameworkController.JavaScriptFrameworkReqTo reqTo) {
            JavaScriptFrameworkIn in = new JavaScriptFrameworkIn();
            in.name = reqTo.name;
            in.deprecationDate = reqTo.deprecationDate;
            in.hypeLevel = reqTo.hypeLevel;
            in.versions = reqTo.versions;

            return in;
        }
    }

    public static class GetJavaScriptFrameworkListResToMapper {

        public static List<GetJavaScriptFrameworkItemResTo> fromGetJavaScriptFrameworkListOut(List<GetJavaScriptFrameworkItemOut> out) {
            List<GetJavaScriptFrameworkItemResTo> resTo = new ArrayList<>();
            out.forEach(itemOut -> {
                GetJavaScriptFrameworkItemResTo itemResTo = new GetJavaScriptFrameworkItemResTo();
                itemResTo.id = itemOut.id;
                itemResTo.name = itemOut.name;
                itemResTo.deprecationDate = itemOut.deprecationDate;
                itemResTo.hypeLevel = itemOut.hypeLevel;
                itemResTo.versions = itemOut.versions;

                resTo.add(itemResTo);
            });

            return resTo;
        }
    }

    public static class GetJavaScriptFrameworkListOutMapper {

        public static List<GetJavaScriptFrameworkItemOut> fromJavaScriptFrameworkList(List<JavaScriptFramework> frameworks) {
            List<GetJavaScriptFrameworkItemOut> out = new ArrayList<>();
            frameworks.forEach(framework -> {
                GetJavaScriptFrameworkItemOut itemOut = new GetJavaScriptFrameworkItemOut();
                itemOut.id = framework.getId();
                itemOut.name = framework.getName();
                itemOut.versions = new ArrayList<>(framework.getVersionSet());
                itemOut.deprecationDate = framework.getDeprecationDate();
                itemOut.hypeLevel = framework.getHypeLevel();

                out.add(itemOut);
            });

            return out;
        }
    }
}
