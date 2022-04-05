package com.xs.mongodb;

import com.mongodb.client.result.UpdateResult;
import com.xs.mongodb.entity.ReportImage;
import com.xs.mongodb.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Arrays;
import java.util.List;

/**
 * @author xueshuai
 * @date 2022/4/4 22:13
 * @description
 */
public class MongoDbTest  extends MongodbApplicationTests{


    private MongoTemplate mongoTemplate;


    @Autowired
    public MongoDbTest(MongoTemplate  mongoTemplate){
        this.mongoTemplate=mongoTemplate;
    }

    /**
     * 增加collection
     */
    @Test
    public void createCollection(){
        mongoTemplate.createCollection("xueshuai");
    }

    /**
     * 删除collection
     */
    @Test
    public void dropCollection(){
        mongoTemplate.dropCollection("xueshuai");
    }

    /**
     * 删除collection下的所有文档
     */
    @Test
    public void remove(){
        mongoTemplate.remove(new Query(),"root");
    }

    /**
     * 插入文档 （行）
     */
    @Test
    public  void  insert(){
        User user1 = new User(12, "xueshuai", 20000.1d, "male");
        User user2 = new User(2, "xiaozhang", 11111.1d, "female");
        User user3 = new User(3, "biechan", 20.1d, "female");
        List<User> users = Arrays.asList(user1, user2, user3);
        //插入的时候，主键冲突会报错
        mongoTemplate.insert(users, User.class);
    }

    @Test
    public void Query(){
        List<User> users = mongoTemplate.find(new Query(), User.class);
        users.forEach(System.out::println);
    }

    @Test
    public void CriteriaQuery(){
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("name").is("xueshuai");
        query.addCriteria(criteria);
        List<User> users = mongoTemplate.find(query, User.class);
        users.forEach(System.out::println);
    }

    /**
     * upsert 有则更新，没有则插入
     */
    @Test
    public void upsert(){
        ReportImage reportImage = new ReportImage();
        reportImage.setReportNo(0L);
        reportImage.setSrcHosCode("xiaoxue");
        reportImage.setImgBase64("44444");

        Query query = Query.query(
                Criteria.where("reportNo").is(reportImage.getReportNo())
                        .and("srcHosCode").is(reportImage.getSrcHosCode()));
        Update update = Update.update("imgBase64",reportImage.getImgBase64());

        UpdateResult upsert = mongoTemplate.upsert(query, update, ReportImage.class);
        System.out.println(upsert.toString());
    }



}
