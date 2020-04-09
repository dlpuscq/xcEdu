package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

/**
 * @description:
 * @author: drama
 * @date: 2019-08-12 19:50
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsPageRepositoryTest {

    @Autowired
    CmsPageRepository cmsPageRepository;

    @Test
    public void testFindAll() {
        final List<CmsPage> cmsPages = cmsPageRepository.findAll();
        System.out.println(cmsPages);
    }

    // 分页查询
    @Test
    public void testFindPage() {
        // 分页参数
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        final Page<CmsPage> all = cmsPageRepository.findAll(pageable);
        System.out.println(all);
    }

    //修改
    @Test
    public void updatePage() {
        Optional<CmsPage> cmsPage = cmsPageRepository.findById("5abefd525b05aa293098fca6");
        if (cmsPage.isPresent()) {
            CmsPage page = cmsPage.get();
            page.setPageAliase("课程详情页面");
            cmsPageRepository.save(page);
        }
    }

    // 自定义条件查询测试
    @Test
    public void testFIndAllByExample() {
        // 分页参数
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        // 条件值对象
        CmsPage cmsPage = new CmsPage();
//        cmsPage.setSiteId("5a751fab6abb5044e0d19ea1");
        cmsPage.setPageAliase("轮播");
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        exampleMatcher = exampleMatcher.withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        // 定义example
        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);
        Page<CmsPage> all = cmsPageRepository.findAll(example, pageable);
        System.out.println(all);
    }
}