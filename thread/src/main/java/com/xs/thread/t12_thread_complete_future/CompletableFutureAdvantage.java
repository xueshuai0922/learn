package com.xs.thread.t12_thread_complete_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xueshuai
 * @date 2022/3/23 22:18
 * @description 为什么用CompletableFuture?
 */

public class CompletableFutureAdvantage {
    private ExecutorService executor = Executors.newFixedThreadPool(1);

    public User loadUser() {
        User user = new User();

        // 1. 查询文章详情 0.5s
        // 下面的查询需要用到文章对应的发布用户，所以这里需要使用CompletableFuture.supplyAsync
        CompletableFuture<Void> articleCompletableFuture = CompletableFuture.supplyAsync(() -> {
            user.setAge(1);
            return null;
        }, executor);
        // 2. 查询文章博主个人信息 0.5s
        // 这里查询需要依赖文章关联的用户id，所以需要使用articleCompletableFuture.thenAcceptAsync()
        CompletableFuture<Void> userCompletableFuture = articleCompletableFuture.thenAcceptAsync(articleEntity -> {
        }, executor);
        // 3. 查询博主相关文章分类 1s
        // 这里查询需要依赖文章关联的用户id，所以需要使用articleCompletableFuture.thenAcceptAsync()
        CompletableFuture<Void> userOtherArticleCompletableFuture = articleCompletableFuture.thenAcceptAsync(articleEntity -> {
        }, executor);
        // 4. 查询文章评论 1s
        // 不需要依赖其他请求返回值，可以使用新的异步对象 CompletableFuture.runAsync()
        CompletableFuture<Void> commentsCompletableFuture =  CompletableFuture.runAsync(() -> {
        }, executor);
        // 5. 相关推荐文章 1s
        // 不需要依赖其他请求返回值，可以使用新的异步对象 CompletableFuture.runAsync()
        CompletableFuture<Void> relatedArticlesCompletableFuture =  CompletableFuture.runAsync(() -> {
        }, executor);
        // 多任务执行组合 CompletableFuture.allOf()
        CompletableFuture.allOf(articleCompletableFuture, userCompletableFuture, userOtherArticleCompletableFuture,
                commentsCompletableFuture, relatedArticlesCompletableFuture).join();
        return user;
    }
    class  User{
        private int age;
        private String name;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}


