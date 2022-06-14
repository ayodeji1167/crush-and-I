//package com.example.crushandi.serviceImpl.lovecalc;
//
//import com.example.crushandi.entity.BlogPost;
//import com.example.crushandi.entity.Comment;
//import com.example.crushandi.entity.Reply;
//import com.example.crushandi.exception.BlogPostException;
//import com.example.crushandi.repository.BlogPostRepository;
//import com.example.crushandi.repository.CommentRepository;
//import com.example.crushandi.repository.ReplyRepository;
//import com.example.crushandi.service.ReplyService;
//import com.example.crushandi.utils.BadWordsCheck;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
//@Service
//public class ReplyServiceImpl implements ReplyService {
//    private final ReplyRepository replyRepository;
//    private final CommentRepository commentRepository;
//    private final BlogPostRepository blogPostRepository;
//    private final BadWordsCheck badWordsCheck;
//
//    public ReplyServiceImpl(ReplyRepository replyRepository, CommentRepository commentRepository, BlogPostRepository blogPostRepository, BadWordsCheck badWordsCheck) {
//        this.replyRepository = replyRepository;
//        this.commentRepository = commentRepository;
//        this.blogPostRepository = blogPostRepository;
//        this.badWordsCheck = badWordsCheck;
//    }
//
//    @Override
//    public BlogPost addReply(String postId, String commentId, String name, String content) {
//
//        BlogPost blogPost = blogPostRepository.findById(postId).orElseThrow(() -> new BlogPostException("Post Not Found"));
//
//        List<Comment> comment = blogPost.getComments();
//
//        Comment commentToEdit = null;
//        for (Comment comment1 : comment) {
//            if (comment1.getId().equals(commentId)) {
//                commentToEdit = comment1;
//            }
//        }
//        if (commentToEdit == null) {
//            throw new BlogPostException("Comment With Id " + commentId + " Not inside post");
//        }
//        Reply reply = new Reply(content, name, commentId, true);
//        if (badWordsCheck.isBadWordPresent(content)) {
//            reply.setAuthorize(false);
//        }
//        replyRepository.save(reply);
//        commentToEdit.getReply().add(reply);
//        commentRepository.save(commentToEdit);
//        return blogPostRepository.save(blogPost);
//    }
//
//    @Override
//    public void deleteReply(String replyId) {
//        Reply reply = replyRepository.findById(replyId).orElseThrow(() -> new BlogPostException("Reply Not Found"));
//        Comment comment = commentRepository.findById(reply.getCommentId()).orElseThrow(() -> new BlogPostException("Comment Not Found"));
//        List<Reply> replies = comment.getReply();
//        List<Reply> replyList = replies.stream().filter(reply1 -> !Objects.equals(reply1.getId(), replyId)).collect(Collectors.toList());
//
//        comment.setReply(replyList);
//
//        BlogPost blogPost = blogPostRepository.findById(comment.getPostId()).orElseThrow(() -> new BlogPostException("Post Not Found"));
//        List<Comment> comments = blogPost.getComments();
//
//
//        commentRepository.save(comment);
//
//        replyRepository.deleteById(replyId);
//
//
//    }
//
//    @Override
//    public void unAuthorizeReply(String replyId) {
//        Reply reply = replyRepository.findById(replyId).orElseThrow(() -> new BlogPostException("Reply Not Found"));
//        reply.setAuthorize(!reply.isAuthorize());
//        replyRepository.save(reply);
//    }
//}
//
