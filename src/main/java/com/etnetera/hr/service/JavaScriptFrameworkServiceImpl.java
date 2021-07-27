package com.etnetera.hr.service;

import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.factory.JavaScriptFrameworkFactory;
import com.etnetera.hr.mapper.JavaScriptFrameworkMappers.GetJavaScriptFrameworkListOutMapper;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Implementation of service interface.
 *
 * @author Tom
 */
@Service
@Transactional
public class JavaScriptFrameworkServiceImpl implements JavaScriptFrameworkService {

    private final JavaScriptFrameworkRepository javaScriptFrameworkRepository;
    private final JavaScriptFrameworkFactory javaScriptFrameworkFactory;

    @Autowired
    public JavaScriptFrameworkServiceImpl(JavaScriptFrameworkRepository javaScriptFrameworkRepository,
                                          JavaScriptFrameworkFactory javaScriptFrameworkFactory) {
        this.javaScriptFrameworkRepository = javaScriptFrameworkRepository;
        this.javaScriptFrameworkFactory = javaScriptFrameworkFactory;
    }

    @Override
    public void createJavaScriptFramework(Long id, JavaScriptFrameworkIn in) {
        Assert.notNull(in, "JavaScripFrameworkIn cannot be null!");

        if (id == null) {
            javaScriptFrameworkRepository.save(javaScriptFrameworkFactory.createJavaScriptFrameworkFrom(in));
        } else {
            JavaScriptFramework javaScriptFramework = javaScriptFrameworkRepository.mustFindById(id);
            javaScriptFrameworkFactory.updateJavaScriptFrameworkFrom(in, javaScriptFramework);
        }
    }

    @Override
    public void deleteJavaScriptFramework(Long id) {
        Assert.notNull(id, "Id of JavaScriptFramework cannot be null!");

        javaScriptFrameworkRepository.delete(javaScriptFrameworkRepository.mustFindById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<GetJavaScriptFrameworkItemOut> searchForFramework(String expression) {
        List<JavaScriptFramework> frameworks = javaScriptFrameworkRepository.findByName(expression);

        return GetJavaScriptFrameworkListOutMapper.fromJavaScriptFrameworkList(frameworks);
    }
}
