package com.hitech;

import com.hitech.controller.DeptController;
import com.hitech.pojo.Dept;
import com.hitech.service.DeptService;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootTest
class SpringbootAopQuickstartApplicationTests {

    @Autowired
    private DeptService deptService;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private SAXReader saxReader;

    @Test
    public void testAopDelete() throws Exception {
        deptService.delete(10);
    }

    @Test
    public void testAopList() {
        List<Dept> list = deptService.list();
        System.out.println(list);
    }

    @Test
    public void testAopGetById() {
        Dept dept = deptService.getById(1);
        System.out.println(dept);
    }

    @Test
    public void testGetBean() {
        // 1.根据bean的名称获取
        DeptController bean1 = (DeptController) applicationContext.getBean("deptController");
        System.out.println(bean1);

        // 2.根据bean的类型获取
        DeptController bean2 = applicationContext.getBean(DeptController.class);
        System.out.println(bean2);

        // 3.根据bean的名称和类型获取
        DeptController bean3 = applicationContext.getBean("deptController", DeptController.class);
        System.out.println(bean3);
    }

    @Test
    public void testScope() {
        for (int i = 0; i < 10; i++) {
            DeptController deptController = applicationContext.getBean(DeptController.class);
            System.out.println(deptController);
        }
    }

    @Test
    public void testThirdBean() throws DocumentException {
        Document document = saxReader.read(this.getClass().getClassLoader().getResource("1.xml"));
        Element rootElement = document.getRootElement();
        String name = rootElement.element("name").getText();
        String age = rootElement.element("age").getText();

        System.out.println(name + " : " + age);
    }


}
