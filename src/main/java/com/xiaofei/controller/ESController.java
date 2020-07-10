package com.xiaofei.controller;

import com.xiaofei.conf.annotation.SysLog;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaofei
 * @Classname ESController
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */
@RestController
@RequestMapping("/pub")
public class ESController {

    @Autowired
    @Qualifier(value = "restHighLevelClient")
    private RestHighLevelClient client;

    @GetMapping("/video/search")
    @SysLog(module = "es查询", methods = "com.xiaofei.controller.ESController.videoSearch",description = "通过多字段联合搜索的方式，非精确")
    public List<Object> videoSearch(String keywords, int pageNo, int pageSize) throws IOException {
        if (pageNo < 0) {
            pageNo = 0;
        }
        SearchRequest searchRequest = new SearchRequest("film_information");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 分页
        sourceBuilder.from(pageNo);
        sourceBuilder.size(pageSize);
        List<Object> result = new ArrayList<>();
        //多字段联合搜索,自行导入需要查询的索引即可
        MultiMatchQueryBuilder query = QueryBuilders.multiMatchQuery(keywords, "video_name");
        query.minimumShouldMatch("70%");
        query.field("video_name", 10);
        sourceBuilder.query(query);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        for (SearchHit documentFields : searchResponse.getHits().getHits()) {
           result.add( documentFields.getSourceAsMap());
        }
        return result;
    }

}
