//package com.example.crushandi.serviceImpl.lovecalc;
//
//import com.example.crushandi.entity.BlogPost;
//import com.example.crushandi.entity.Comment;
//import com.example.crushandi.exception.BlogPostException;
//import com.example.crushandi.repository.AppUserRepository;
//import com.example.crushandi.repository.BlogPostRepository;
//import com.example.crushandi.repository.CommentRepository;
//import com.example.crushandi.repository.ReplyRepository;
//import com.example.crushandi.service.CommentService;
//import com.example.crushandi.utils.BadWordsCheck;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
//@Service
//public class CommentServiceImpl implements CommentService {
//    private final BlogPostRepository blogPostRepository;
//    private final AppUserRepository appUserRepository;
//    private final CommentRepository commentRepository;
//    private final ReplyRepository repository;
//    private final BadWordsCheck badWordsCheck;
//
//
//    public CommentServiceImpl(BlogPostRepository blogPostRepository, AppUserRepository appUserRepository, CommentRepository commentRepository, ReplyRepository repository, BadWordsCheck badWordsCheck) {
//        this.blogPostRepository = blogPostRepository;
//        this.appUserRepository = appUserRepository;
//        this.commentRepository = commentRepository;
//        this.repository = repository;
//        this.badWordsCheck = badWordsCheck;
//    }
//
//    @Override
//    public BlogPost addComment(String postId, String name, String content) {
//
//        BlogPost blogPost = blogPostRepository.findById(postId).orElseThrow(() -> new BlogPostException("Post Not Found"));
//        Comment comment = new Comment();
//        comment.setContent(content);
//        comment.setName(name);
//        comment.setAuthorized(true);
//        comment.setCreatedDate(LocalDateTime.now());
//        comment.setPostName(blogPost.getTitle());
//        comment.setPostId(postId);
//
//        if (badWordsCheck.isBadWordPresent(content)) {
//            comment.setAuthorized(false);
//        }
//        commentRepository.save(comment);
//
//        blogPost.getComments().add(comment);
//        return blogPostRepository.save(blogPost);
//    }
//
//
//    @Override
//    public Comment getComment(String commentId) {
//        return commentRepository.findById(commentId).orElseThrow(() -> new BlogPostException("Comment Not Found"));
//    }
//
//    @Override
//    public void deleteComment(String commentId) {
//        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new BlogPostException("Comment Not Found"));
//        BlogPost blogPost = blogPostRepository.findById(comment.getPostId()).orElseThrow(() -> new BlogPostException("Post Not Found"));
//        List<Comment> comments = blogPost.getComments();
//        List<Comment> commentList = comments.stream().filter(comment1 -> !Objects.equals(comment1.getId(), commentId)).collect(Collectors.toList());
//
//        blogPost.setComments(commentList);
//        blogPostRepository.save(blogPost);
//        commentRepository.deleteById(commentId);
//    }
//
//    @Override
//    public void deleteMultipleComments(List<String> ids) {
//        Iterable<Comment> comments = commentRepository.findAllById(ids);
//
//        comments.forEach(comment -> {
//            BlogPost blogPost = blogPostRepository.findById(comment.getPostId()).orElseThrow(() -> new BlogPostException("Post Not Found"));
//            List<Comment> postComments = blogPost.getComments();
//            List<Comment> commentList = postComments.stream().filter(comment1 -> !Objects.equals(comment1.getId(), comment.getId())).collect(Collectors.toList());
//
//            blogPost.setComments(commentList);
//            blogPostRepository.save(blogPost);
//            commentRepository.deleteById(comment.getId());
//        });
//    }
//
//    @Override
//    public void unAuthorizedComment(String commentId) {
//        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new BlogPostException("Comment Not Found"));
//        comment.setAuthorized(!comment.isAuthorized());
//        commentRepository.save(comment);
//    }
//
//    @Override
//    public List<Comment> getAllComments() {
//        return commentRepository.findAll();
//    }
//}
