package com.example.http.dto;

// 블로그 게시글
    // - 제목
    // - 내용

/*{
    "title": "제목",
     "content": "content"
  }
*/

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ArticleWithComment {
    private String title;
    private String content;
    private String writer;
    private List<String> comments = new ArrayList<>();
}
