package com.etnetera.hr.controller;

import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.mapper.JavaScriptFrameworkMappers.GetJavaScriptFrameworkListResToMapper;
import com.etnetera.hr.mapper.JavaScriptFrameworkMappers.JavascriptFrameworkInMapper;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import com.etnetera.hr.service.JavaScriptFrameworkService;
import com.etnetera.hr.service.JavaScriptFrameworkService.GetJavaScriptFrameworkItemOut;
import com.etnetera.hr.service.JavaScriptFrameworkService.JavaScriptFrameworkIn;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Simple REST controller for accessing application logic.
 *
 * @author Etnetera
 */
@RestController
public class JavaScriptFrameworkController extends EtnRestController {

    private final JavaScriptFrameworkRepository repository;

    private final JavaScriptFrameworkService javaScriptFrameworkService;

    @Autowired
    public JavaScriptFrameworkController(JavaScriptFrameworkRepository repository, JavaScriptFrameworkService javaScriptFrameworkService) {
        this.repository = repository;
        this.javaScriptFrameworkService = javaScriptFrameworkService;
    }

    @GetMapping("/frameworks")
    public Iterable<JavaScriptFramework> frameworks() {
        return repository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/frameworks")
    public ResponseEntity<?> createJavaScriptFramework(@RequestBody @Valid JavaScriptFrameworkReqTo reqTo) {
        JavaScriptFrameworkIn in = JavascriptFrameworkInMapper.fromJavaScriptFrameworkReqTo(reqTo);
        // Call service layer
        javaScriptFrameworkService.createJavaScriptFramework(null, in);

        return created();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/frameworks/{javaScriptFrameworkId}")
    public ResponseEntity<?> updateJavaScriptFramework(@RequestBody @Valid JavaScriptFrameworkReqTo reqTo,
                                                       @PathVariable Long javaScriptFrameworkId) {
        JavaScriptFrameworkIn in = JavascriptFrameworkInMapper.fromJavaScriptFrameworkReqTo(reqTo);
        // Call service layer
        javaScriptFrameworkService.createJavaScriptFramework(javaScriptFrameworkId, in);

        return ok();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/frameworks/{javaScriptFrameworkId}")
    public ResponseEntity<?> deleteJavaScriptFramework(@PathVariable Long javaScriptFrameworkId) {
        // Call service layer
        javaScriptFrameworkService.deleteJavaScriptFramework(javaScriptFrameworkId);

        return ok();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/frameworks/search")
    public ResponseEntity<?> searchJavaScriptFramework(@RequestParam("expression") String expression) {
        // Call service layer
        List<GetJavaScriptFrameworkItemOut> out = javaScriptFrameworkService.searchForFramework(expression);
        List<GetJavaScriptFrameworkItemResTo> resTo = GetJavaScriptFrameworkListResToMapper.fromGetJavaScriptFrameworkListOut(out);

        return ok(resTo);
    }

    //~ Inner classes

    public static class JavaScriptFrameworkReqTo {
        @NotEmpty
        @Size(min = 3, max = 30)
        public String name;
        public List<String> versions;
        public BigDecimal hypeLevel;
        @JsonDeserialize(using = LocalDateDeserializer.class)
        public LocalDate deprecationDate;
    }

    public static class GetJavaScriptFrameworkItemResTo {
        public Long id;
        public String name;
        public List<String> versions;
        public BigDecimal hypeLevel;
        @JsonSerialize(using = LocalDateSerializer.class)
        public LocalDate deprecationDate;
    }
}
