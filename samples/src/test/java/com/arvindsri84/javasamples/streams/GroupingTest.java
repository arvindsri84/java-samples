package com.arvindsri84.javasamples.streams;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.arvindsri84.javasamples.streams.GroupingTest.BlogPostType.*;

public class GroupingTest {

    record BlogPost(String title, String author, BlogPostType blogPostType, int likes) {
    }

    enum BlogPostType {GUIDE, REVIEW, NEWS}

    private List<BlogPost> posts;

    @Before
    public void setup() {
        this.posts = new ArrayList<>();

        posts.add(new BlogPost("Passwordless Authentication with SMS using AWS Cognito", "Arvind", GUIDE, 28));
        posts.add(new BlogPost("TOP 5 GOVT. JOBS FOR FRESHERS", "Manisha", GUIDE, 28));
        posts.add(new BlogPost("5 WAYS TO BE AT THE TOP OF JOB APPLICATIONS", "Manisha", GUIDE, 13));
        posts.add(new BlogPost("Data Analyst Intern opening alert", "Manisha", REVIEW, 200));
        posts.add(new BlogPost("Weekly Job Trend Tracker - 20th June 2023- 27th June 2023", "Vaeeshnavi", NEWS, 200));
        posts.add(new BlogPost("TOP 5 GOVT. JOBS FOR FRESHERS (02.09.2023 - 15.09.2023)", "Arvind", NEWS, 101));
        posts.add(new BlogPost("Private Job Options for 12th Pass and Graduate Candidates in India", "Vaeeshnavi", GUIDE, 55));
        posts.add(new BlogPost("Exploring Best Government Jobs in India", "Manisha", NEWS, 101));
        posts.add(new BlogPost("Living the Dream: The Lifestyle of IAS Officers in India - Exploring Salaries", "Vaeeshnavi", REVIEW, 22));
        posts.add(new BlogPost("Weekly Job Trend Analysis: 26th July - 2nd August 2023", "Vaeeshnavi", GUIDE, 51));
    }


    @Test
    public void groupByAuthor() {
        var result = this.posts.stream().collect(Collectors.groupingBy(BlogPost::author));
        System.out.println(result);
    }

    @Test
    public void groupByAuthorAndPostType() {
        var result =
                this.posts.stream().collect(Collectors.groupingBy(BlogPost::author, Collectors.groupingBy(BlogPost::blogPostType)));
        System.out.println(result);
    }

    @Test
    public void authorsWithPostsCount() {
        var result = this.posts.stream().collect(Collectors.groupingBy(BlogPost::author, Collectors.counting()));
        System.out.println(result);
    }

    @Test
    public void authorsWithTotalLikes() {
        var result =
                this.posts.stream().collect(Collectors.groupingBy(BlogPost::author, Collectors.summingInt(BlogPost::likes)));
        System.out.println(result);
    }


    @Test
    public void blogpostTypesWithStatistics() {
        var result =
                this.posts.stream().collect(Collectors.groupingBy(BlogPost::blogPostType, Collectors.summarizingInt(BlogPost::likes)));
        System.out.println(result);
    }


    @Test
    public void blogpostTypesWithMinMaxMean() {

        var result =
                this.posts.stream().collect(Collectors.groupingBy(BlogPost::blogPostType, Collector.of(
                        () -> new BlogPostStatistics(0, 0, 0, 0.0),
                        (x, y) -> {
                            x.setMax(x.getMax() < y.likes() ? y.likes() : x.getMax());
                            x.setMin(x.getMin() < y.likes() && x.getMin() != 0 ? x.getMin() : y.likes);
                            x.setMean((x.getCount() * x.getMean() + y.likes) / (x.getCount() + 1));
                            x.setCount(x.getCount() + 1);
                        },
                        (x, y) -> {
                            return new BlogPostStatistics(
                                    x.getCount() + y.getCount(),
                                    Math.min(x.getMin(), y.getMin()),
                                    Math.max(x.getMax(), y.getMax()),
                                    (x.getCount() * x.getMean() + y.getCount() * y.getMean()) / (x.getCount() + y.getCount())

                            );
                        },
                        Collector.Characteristics.UNORDERED
                )));
        System.out.println(result);
    }

    static class BlogPostStatistics {
        private int count;
        private int min;
        private int max;
        private double mean;

        public BlogPostStatistics() {

        }

        public BlogPostStatistics(int count, int min, int max, double mean) {
            this.count = count;
            this.min = min;
            this.max = max;
            this.mean = mean;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public double getMean() {
            return mean;
        }

        public void setMean(double mean) {
            this.mean = mean;
        }

        @Override
        public String toString() {
            return "BlogPostStatistics{" +
                    "count=" + count +
                    ", min=" + min +
                    ", max=" + max +
                    ", mean=" + mean +
                    '}';
        }
    }

    @After
    public void tearDown() {
        this.posts = null;
    }


}
